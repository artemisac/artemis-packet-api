package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientHeldItemSlot extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientHeldItemSlot(UUID player, ProtocolVersion version) {
        super("PacketPlayInHeldItemSlot", player, version);
    }

    private short slot;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.slot = byteBuf.readShort();
    }
}
