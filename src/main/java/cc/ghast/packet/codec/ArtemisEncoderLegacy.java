package cc.ghast.packet.codec;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.exceptions.InvalidPacketException;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import net.minecraft.util.io.netty.buffer.ByteBuf;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.handler.codec.MessageToByteEncoder;


/**
 * @author Ghast
 * @since 30/08/2020
 * Artemis © 2020
 */
public class ArtemisEncoderLegacy extends MessageToByteEncoder<Packet<?>> {

    private final Profile profile;

    public ArtemisEncoderLegacy(Profile profile) {
        this.profile = profile;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, ByteBuf byteBuf) {

        int packetId = profile.getProtocol().getPacketId(ProtocolDirection.OUT, packet);

        if (packetId < 0){
            throw new InvalidPacketException(packet.getClass());
        }

        ProtocolByteBuf buffer = new ProtocolByteBuf(MutableByteBuf.translate(byteBuf), profile.getVersion());

        buffer.writeVarInt(packetId);

        if (packet instanceof WriteableBuffer) {
            WriteableBuffer writeableBuffer = (WriteableBuffer) packet;
            writeableBuffer.write(buffer);
        }
    }
}
