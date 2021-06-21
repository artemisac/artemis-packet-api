package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketPlayServerWorldBorder extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerWorldBorder(UUID player, ProtocolVersion version) {
        super("PacketPlayOutWorldBorder", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
