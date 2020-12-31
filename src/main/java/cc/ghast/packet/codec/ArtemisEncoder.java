package cc.ghast.packet.codec;

import cc.ghast.packet.PacketManager;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.exceptions.InvalidPacketException;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.SneakyThrows;

/**
 * @author Ghast
 * @since 30/08/2020
 * Artemis © 2020
 */
public class ArtemisEncoder extends MessageToByteEncoder<cc.ghast.packet.wrapper.packet.Packet<?>> {

    private final Profile profile;

    public ArtemisEncoder(Profile profile) {
        this.profile = profile;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet<?> obj, ByteBuf byteBuf) {
        try {
            encode(obj, byteBuf);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void encode(Packet<?> packet, ByteBuf buf){
        //System.out.println(packet);
        int packetId = profile.getProtocol().getPacketId(ProtocolDirection.OUT, packet);

        if (packetId < 0){
            throw new InvalidPacketException(packet.getClass());
        }

        final ProtocolByteBuf buffer = new ProtocolByteBuf(MutableByteBuf.translate(buf.copy()),
                profile.getVersion());

        // Modify with hooks
        //PacketManager.INSTANCE.getHookManager().modifyAll(profile, ProtocolDirection.OUT, b);

        buffer.writeVarInt(packetId);

        if (packet instanceof WriteableBuffer) {
            WriteableBuffer writeableBuffer = (WriteableBuffer) packet;
            writeableBuffer.write(buffer);
        }
    }

    @Override
    public boolean acceptOutboundMessage(Object msg) {
        return Packet.class.isAssignableFrom(msg.getClass());
    }
}
