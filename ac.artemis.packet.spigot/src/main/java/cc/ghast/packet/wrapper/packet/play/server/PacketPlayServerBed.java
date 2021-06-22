package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerBed extends GPacket implements ReadableBuffer {
    public PacketPlayServerBed(UUID player, ProtocolVersion version) {
        super("PacketPlayOutBed", player, version);
    }

    private int playerID;
    private BlockPosition bedPos;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.playerID = byteBuf.readVarInt();
        this.bedPos = byteBuf.readBlockPositionFromLong();
    }
}
