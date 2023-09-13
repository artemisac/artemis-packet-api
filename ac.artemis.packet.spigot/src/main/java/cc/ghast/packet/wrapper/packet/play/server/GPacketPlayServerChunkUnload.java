package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.server.PacketPlayServerChunkUnload;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerChunkUnload.class)
public class GPacketPlayServerChunkUnload extends GPacket implements PacketPlayServerChunkUnload, ReadableBuffer {
    public GPacketPlayServerChunkUnload(UUID player, ProtocolVersion version) {
        super("PacketPlayOutUnloadChunk", player, version);
    }

    private int chunkX, chunkZ;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        chunkX = byteBuf.readInt();
        chunkZ = byteBuf.readInt();
    }
}
