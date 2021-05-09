package cc.ghast.packet.codec;

import cc.ghast.packet.PacketManager;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.exceptions.InvalidPacketException;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.EnumProtocolCurrent;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import lombok.SneakyThrows;
import net.minecraft.util.io.netty.buffer.ByteBuf;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Ghast
 * @since 30/08/2020
 * Artemis © 2020
 */
public class ArtemisEncoderLegacy extends MessageToByteEncoder<Packet> {

    private final Profile profile;

    public ArtemisEncoderLegacy(Profile profile) {
        this.profile = profile;
    }

    @Override
    @SneakyThrows
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet obj, ByteBuf byteBuf) {
        final int packetId = EnumProtocolCurrent
                .values()[profile.getProtocol().ordinal()]
                .getPacketId(ProtocolDirection.OUT, obj);

        if (packetId < 0){
            throw new InvalidPacketException(obj.getClass());
        }


        obj.setUuid(profile.getUuid());
        obj.setVersion(profile.getVersion());
        // Modify with hooks
        //PacketManager.INSTANCE.getHookManager().modifyAll(profile, ProtocolDirection.OUT, getZ);
        final MutableByteBuf alloc = MutableByteBuf.translate(byteBuf.retain());
        final ProtocolByteBuf transformed = new ProtocolByteBuf(alloc, profile.getVersion());

        try {
            transformed.writeVarInt(packetId);

            if (obj instanceof WriteableBuffer) {
                WriteableBuffer writeableBuffer = (WriteableBuffer) obj;
                writeableBuffer.write(transformed);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        byteBuf.resetReaderIndex();

    }

    @Override
    public boolean acceptOutboundMessage(Object msg) {
        return Packet.class.isAssignableFrom(msg.getClass());
    }
}
