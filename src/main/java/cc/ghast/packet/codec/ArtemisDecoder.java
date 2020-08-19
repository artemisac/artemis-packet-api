package cc.ghast.packet.codec;

import cc.ghast.packet.PacketManager;
import cc.ghast.packet.exceptions.IncompatiblePipelineException;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.buffer.types.Converters;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.EnumProtocolLegacy;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.handshake.PacketHandshakeClientSetProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;

import java.util.Arrays;
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
    private EnumProtocolLegacy protocol;

    public ArtemisDecoder(Profile profile) {
        this.profile = profile;
        this.inflater = new Inflater();
        this.protocol = EnumProtocolLegacy.HANDSHAKE;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (debug) {
            System.out.println("Has decoder: " + (ctx.channel().pipeline().get("decoder") != null));
            System.out.println("Has decompresser: " + (ctx.channel().pipeline().get("decompress") != null));
            System.out.println("Structure: " + Arrays.toString(ctx.channel().pipeline().names().toArray()));
            System.out.println(msg.toString());
        }

        // Make sure we're receiving a ByteBuf. If a protocol is placed before such, it may screw with the decompression
        if (msg instanceof ByteBuf) {
            // Decode the message and send it off
            final boolean cancelled = decode(MutableByteBuf.translate((ByteBuf) msg).copy());

            // Nullify if the packet was cancelled
            if (cancelled) {
                msg = null;
            }

        } else {
            // If the message is not a ByteBuf, there's obviously an issue with the pipeline, so disinject and throw error
            ctx.pipeline().remove(this);
            throw new IncompatiblePipelineException(msg.getClass());
        }
        super.channelRead(ctx, msg);
    }

    protected boolean decode(MutableByteBuf in) throws Exception {

        // If there's no readable bytes, the packet is empty. Don't worry, such can happen
        in = decompress(in);

        if (in.readableBytes() != 0) {

            // Get the var_int packet id of the packet. This is quite important as it's what determins it's type
            int id = Converters.VAR_INT.read(in);

            if (debug) {
                System.out.println("Reader index=" + in.readerIndex());
                System.out.println("Id of " + id);
            }

            // Collect the packet from the enum map. This needs to be rewritten for better accuracy tho
            Packet<?> packet = protocol.getPacket(ProtocolDirection.IN, id, profile.getUuid(), profile.getVersion());
            packet.handle(new ProtocolByteBuf(in));

            // Handle and collect the handshake
            if (packet instanceof PacketHandshakeClientSetProtocol){
                handleHandshake((PacketHandshakeClientSetProtocol) packet);
            }

            PacketManager.INSTANCE.getManager().callPacket(profile, packet);

            if (packet.isCancelled()) {
                return true;
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

    private MutableByteBuf decompress(MutableByteBuf byteBuf) throws DataFormatException {
        if (byteBuf.readableBytes() != 0) {
            ProtocolByteBuf packetdataserializer = new ProtocolByteBuf(byteBuf);
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
        profile.setVersion(ProtocolVersion.getVersion(handshake.getProtocolVersion()));
        protocol = EnumProtocolLegacy.PLAY;
    }
}
