package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketPlayServerChunkUnload extends GPacket implements ReadableBuffer {
    public PacketPlayServerChunkUnload(UUID player, ProtocolVersion version) {
        super("PacketPlayOutUnloadChunk", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
