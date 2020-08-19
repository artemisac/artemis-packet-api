package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;

import java.util.UUID;

public class PacketPlayServerEntityMetadata extends Packet<ServerPacket> {
    public PacketPlayServerEntityMetadata(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntityMetadata", player, version);
    }

    @Override
    public void handle(ProtocolByteBuf byteBuf) {

    }

}
