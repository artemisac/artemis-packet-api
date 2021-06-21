package cc.ghast.packet.wrapper.packet.status;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.ServerPacket;

import java.util.UUID;

public class PacketStatusServerInfoServer extends Packet<ServerPacket> implements ReadableBuffer {

    public PacketStatusServerInfoServer(UUID player, ProtocolVersion version) {
        super("PacketStatusOutInfoServer", player, version);
    }


    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
