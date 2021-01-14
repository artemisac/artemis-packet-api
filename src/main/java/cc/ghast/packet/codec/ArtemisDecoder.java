package cc.ghast.packet.codec;

import cc.ghast.packet.PacketManager;
import cc.ghast.packet.exceptions.IncompatiblePipelineException;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.buffer.types.Converters;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.EnumProtocolCurrent;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.handshake.PacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.login.PacketLoginServerSuccess;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.DecoderException;
import org.bukkit.Bukkit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * @author Ghast
 * @since 24-Apr-20
 */
public class ArtemisDecoder extends ChannelDuplexHandler {

    private static final boolean debug = false;

    private final Profile profile;
    private final Inflater inflater;
    private final ProtocolDirection direction;
    private static final ExecutorService PACKET_SERVICE = Executors.newSingleThreadExecutor();
    private EnumProtocol protocol;

    public ArtemisDecoder(Profile profile, ProtocolDirection direction) {
        this.profile = profile;
        this.inflater = new Inflater();
        this.direction = direction;
        this.protocol = EnumProtocolCurrent.HANDSHAKE;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (direction.equals(ProtocolDirection.IN)) {
            this.handle(ctx, msg);
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (direction.equals(ProtocolDirection.OUT)){
            this.handle(ctx, msg);
        }
        super.write(ctx, msg, promise);
    }

    private void handle(ChannelHandlerContext ctx, Object msg) {
        if (debug) {
            System.out.println("Has decoder: " + (ctx.channel().pipeline().get("decoder") != null));
            System.out.println("Has decompresser: " + (ctx.channel().pipeline().get("decompress") != null));
            System.out.println("Structure: " + ctx.channel().pipeline().toMap());
            System.out.println(msg.toString());

        }

        // Make sure we're receiving a ByteBuf. If a protocol is placed before such, it may screw with the decompression
        if (msg instanceof ByteBuf) {
            // Decode the message and send it off
            final ByteBuf buffer = Unpooled.unmodifiableBuffer((ByteBuf) msg).copy().retain();
            try {
                final int readIndex = buffer.readerIndex();
                final boolean cancelled = buffer.isReadable() && decode(MutableByteBuf.translate(buffer));

                // Nullify if the packet was cancelled
                buffer.readerIndex(readIndex);
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            // If the message is not a ByteBuf, there's obviously an issue with the pipeline, so disinject and throw error
            new IncompatiblePipelineException(msg.getClass()).printStackTrace();
        }
    }

    protected boolean decode(MutableByteBuf in) throws Exception {

        // If there's no readable bytes, the packet is empty. Don't worry, such can happen
        if (((Channel) profile.getChannel()).pipeline().names().contains("decompress") ||
                ((Channel) profile.getChannel()).pipeline().names().contains("compress")) {
            try {
                in = decompress(in);
            } catch (DecoderException e){
                e.printStackTrace();
                Bukkit.getScheduler().runTask(PacketManager.INSTANCE.getPlugin(), () -> {
                    Bukkit.getPlayer(profile.getUuid()).kickPlayer(e.getMessage());
                });
            }
        }


        if (in.readableBytes() != 0) {
            // Get the var_int packet id of the packet. This is quite important as it's what determines it's type
            int id = Converters.VAR_INT.read(in, profile.getVersion());

            final boolean viaVersion = PacketManager.INSTANCE.getHookManager().getViaVersionHook() != null
                    && !profile.getProtocol().equals(Profile.Protocol.HANDSHAKE)
                    && direction.equals(ProtocolDirection.IN);


            if (viaVersion) {

                in.resetReaderIndex();

                ByteBuf parent = PacketManager.INSTANCE
                        .getHookManager()
                        .getViaVersionHook()
                        .transformPacket(profile.getUuid(), (ByteBuf) in.getParent(), id);

                if (parent == null)
                    return false;

                in = MutableByteBuf.translate(parent);
                in.resetReaderIndex();
                id = Converters.VAR_INT.read(in, ProtocolVersion.getGameVersion());
            }



            if (debug) {
                System.out.println("Reader index=" + in.readerIndex());
                System.out.println("Id of " + id);
            }

            // Initialize the protocol byte buf
            final ProtocolByteBuf protocolByteBuf = new ProtocolByteBuf(in, profile.getVersion());
            protocolByteBuf.setId(id);

            // Collect the packet from the enum map. This needs to be rewritten for better accuracy tho
            Packet<?> packet;

            if (viaVersion) {
                packet = EnumProtocol
                        .getProtocolByVersion(ProtocolVersion.getGameVersion())[profile.getProtocol().ordinal()]
                        .getPacket(direction, protocolByteBuf.getId(),
                        profile.getUuid(), ProtocolVersion.getGameVersion());
            } else {
                packet = EnumProtocol
                        .getProtocolByVersion(profile.getVersion())[profile.getProtocol().ordinal()]
                        .getPacket(direction, protocolByteBuf.getId(),
                        profile.getUuid(), profile.getVersion());
            }

            if (packet != null) {
                if (packet instanceof ReadableBuffer) {
                    ReadableBuffer buffer = (ReadableBuffer) packet;
                    try {
                        buffer.read(protocolByteBuf);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (debug) {
                    System.out.println("pakcet=" + packet.getRealName());
                }

                // Handle and collect the handshake
                if (packet instanceof PacketHandshakeClientSetProtocol){
                    handleHandshake((PacketHandshakeClientSetProtocol) packet);
                }

                else if (packet instanceof PacketLoginServerSuccess) {
                    handleLoginSuccess((PacketLoginServerSuccess) packet);
                }

                if (profile.getUuid() != null) {
                    PacketManager.INSTANCE.getManager().callPacket(profile, packet);
                }

                if (packet.isCancelled()) {
                    return true;
                }
            }

            // Reset the reader index to prevent following pipelines to have a sort of issue. Normally it doesn't, I'm
            // Still new to Netty. I'll need to investigate. More can be seen @ https://netty.io/4.0/api/io/netty/buffer/ByteBuf.html
            in.resetReaderIndex();

            if (debug) {
                System.out.println("Packet of ID=" + packet);
            }

        } else {
            if (debug) System.out.println("NO READABLE BYTES BRUH");
        }

        return false;
    }

    private MutableByteBuf decompress(MutableByteBuf byteBuf) throws DataFormatException, DecoderException {
        if (byteBuf.readableBytes() != 0) {
            ProtocolByteBuf packetdataserializer = new ProtocolByteBuf(byteBuf, profile.getVersion());
            int i = packetdataserializer.readVarInt();

            if (i == 0) {
                return packetdataserializer.readBytes(byteBuf.readableBytes());
            } else {
                if (i < 256) {
                    throw new DecoderException("Badly compressed packet - size of " + i
                            + " is below server threshold of " + 256);
                }

                if (i > 2097152) {
                    throw new DecoderException("Badly compressed packet - size of " + i + " is larger than protocol maximum of " + 2097152);
                }

                byte[] abyte = new byte[packetdataserializer.readableBytes()];

                packetdataserializer.readBytes(abyte);
                this.inflater.setInput(abyte);
                byte[] abyte1 = new byte[i];

                this.inflater.inflate(abyte1);
                this.inflater.reset();
                return MutableByteBuf.translate(Unpooled.wrappedBuffer(abyte1));
            }

        }
        return byteBuf;
    }

    private void handleHandshake(PacketHandshakeClientSetProtocol handshake){
        final ProtocolVersion version = ProtocolVersion.getVersion(handshake.getProtocolVersion());

        if (PacketManager.INSTANCE.getHookManager().getViaVersionHook() == null) {
            profile.setVersion(version);

        }
        System.out.println("Version=" + handshake.getVersion());
        PacketHandshakeClientSetProtocol.State state = handshake.getNextState();
        this.profile.setProtocol(state.equals(PacketHandshakeClientSetProtocol.State.STATUS)
                ? Profile.Protocol.STATUS : Profile.Protocol.LOGIN);
    }

    private void handleLoginSuccess(PacketLoginServerSuccess loginSuccess) {
        this.profile.setProtocol(Profile.Protocol.PLAY);

        if (PacketManager.INSTANCE.getHookManager().getViaVersionHook() != null) {
            this.profile.setVersion(null);
        }
    }


}
