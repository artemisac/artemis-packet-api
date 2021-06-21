package cc.ghast.packet.wrapper.packet.status;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketStatusClientPing extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketStatusClientPing(UUID player, ProtocolVersion version) {
        super("PacketStatusInPing", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
