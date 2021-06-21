package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.ServerPacket;

import java.util.UUID;

public class PacketPlayServerPlayerFace extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerPlayerFace(UUID player, ProtocolVersion version) {
        super("PacketPlayOutBoss", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
    }
}
