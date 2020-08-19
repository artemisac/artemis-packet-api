package cc.ghast.packet.wrapper.packet.status;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;

import java.util.UUID;

public class PacketStatusServerPing extends Packet<ServerPacket> {

    public PacketStatusServerPing(UUID player, ProtocolVersion version) {
        super("PacketStatusOutPing", player, version);
    }


    @Override
    public void handle(ProtocolByteBuf byteBuf) {

    }
}
