package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketPlayServerChunkLoadBulk extends GPacket implements ReadableBuffer {
    public PacketPlayServerChunkLoadBulk(UUID player, ProtocolVersion version) {
        super("PacketPlayOutMapChunkBulk", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
