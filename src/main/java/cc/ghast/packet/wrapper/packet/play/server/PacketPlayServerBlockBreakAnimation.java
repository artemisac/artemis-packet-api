package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;

import java.util.UUID;

public class PacketPlayServerBlockBreakAnimation extends Packet<ServerPacket> {
    public PacketPlayServerBlockBreakAnimation(UUID player, ProtocolVersion version) {
        super("PacketPlayOutBlockBreakAnimation", player, version);
    }

    @Override
    public void handle(ProtocolByteBuf byteBuf) {

    }
}
