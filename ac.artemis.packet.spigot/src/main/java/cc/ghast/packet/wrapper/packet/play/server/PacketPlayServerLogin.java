package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.bukkit.Dimension;
import cc.ghast.packet.wrapper.bukkit.GameMode;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;
import org.bukkit.Difficulty;
import org.bukkit.WorldType;

import java.util.UUID;

@Getter
public class PacketPlayServerLogin extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerLogin(UUID player, ProtocolVersion version) {
        super("PacketPlayOutLogin", player, version);
    }

    private int entityId;
    private GameMode gamemode;
    private boolean hardcoreMode;
    private Dimension dimension;
    private Difficulty difficulty;
    private int maxPlayers;
    private WorldType worldLevelType;
    private boolean reducedDebugInfo;


    @Override
    public void read(ProtocolByteBuf byteBuf) {
        // Entity ID
        this.entityId = byteBuf.readInt();

        // Gamemode
        int i = byteBuf.readUnsignedByte();
        this.hardcoreMode = (i & 8) == 8;
        i = i & -9;
        this.gamemode = GameMode.getById(i);

        // Dimension
        this.dimension = Dimension.values()[byteBuf.readByte() + 1];

        // Difficulty
        this.difficulty = Difficulty.values()[byteBuf.readUnsignedByte()];

        // Max Players
        this.maxPlayers = byteBuf.readUnsignedByte();

        // Level Type
        this.worldLevelType = WorldType.getByName(byteBuf.readStringBuf(16));
        if (this.worldLevelType == null) {
            this.worldLevelType = WorldType.NORMAL;

        }

        if (version.isOrAbove(ProtocolVersion.V1_8)) {
            // Reduced Debug Info
            this.reducedDebugInfo = byteBuf.readBoolean();
        }
    }
}
