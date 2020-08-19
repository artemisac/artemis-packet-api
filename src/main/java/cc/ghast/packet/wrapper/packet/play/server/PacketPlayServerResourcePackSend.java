package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;

import java.util.UUID;

public class PacketPlayServerResourcePackSend extends Packet<ServerPacket> {
    public PacketPlayServerResourcePackSend(UUID player, ProtocolVersion version) {
        super("PacketPlayOutResourcePackSend", player, version);
    }

    @Override
    public void handle(ProtocolByteBuf byteBuf) {

    }
}
