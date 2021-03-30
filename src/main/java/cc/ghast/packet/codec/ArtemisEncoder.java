package cc.ghast.packet.codec;

import cc.ghast.packet.PacketManager;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.buffer.types.Converters;
import cc.ghast.packet.exceptions.InvalidPacketException;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.EnumProtocolCurrent;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.SneakyThrows;

import java.awt.*;

/**
 * @author Ghast
 * @since 30/08/2020
 * Artemis © 2020
 */
public class ArtemisEncoder extends MessageToByteEncoder<Packet> {

    private final Profile profile;

    public ArtemisEncoder(Profile profile) {
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

        final boolean viaVersion = PacketManager.INSTANCE.getHookManager().getViaVersionHook() != null;


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

        if (viaVersion) {
            byteBuf.resetReaderIndex();
            final ByteBuf parent = PacketManager.INSTANCE
                    .getHookManager()
                    .getViaVersionHook()
                    .transformPacketSend(profile.getUuid(), byteBuf, packetId);

            if (parent == null) {
                byteBuf.clear().resetReaderIndex();
                return;
            }

        } else {
        }

        //System.out.println("Sending packet of id " + new ProtocolByteBuf(MutableByteBuf.translate(byteBuf), profile.getVersion()).readVarInt() + " of class " + obj.getClass().getName());
        byteBuf.resetReaderIndex();

    }

    @Override
    public boolean acceptOutboundMessage(Object msg) {
        return Packet.class.isAssignableFrom(msg.getClass());
    }
}
