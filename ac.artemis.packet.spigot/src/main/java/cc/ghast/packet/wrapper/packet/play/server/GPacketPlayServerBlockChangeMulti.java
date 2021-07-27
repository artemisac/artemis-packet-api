package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.server.PacketPlayServerBlockChangeMulti;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerBlockChangeMulti.class)
public class GPacketPlayServerBlockChangeMulti extends GPacket implements PacketPlayServerBlockChangeMulti, ReadableBuffer {
    public GPacketPlayServerBlockChangeMulti(UUID player, ProtocolVersion version) {
        super("PacketPlayOutMultiBlockChange", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }

    @Data
    static class BlockChange {
        private int blockId;
        private BlockPosition position;
    }
}
