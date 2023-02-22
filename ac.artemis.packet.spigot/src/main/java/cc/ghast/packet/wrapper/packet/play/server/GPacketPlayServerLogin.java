package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.server.PacketPlayServerLogin;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.bukkit.Dimension;
import cc.ghast.packet.wrapper.bukkit.GameMode;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;
import org.bukkit.Difficulty;
import org.bukkit.WorldType;

import java.util.Optional;
import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerLogin.class)
public class GPacketPlayServerLogin extends GPacket implements PacketPlayServerLogin, ReadableBuffer {
    public GPacketPlayServerLogin(UUID player, ProtocolVersion version) {
        super("PacketPlayOutLogin", player, version);
    }

    private int entityId;
    private GameMode gamemode;
    private boolean hardcoreMode;
    private Optional<Dimension> dimension;
    private Optional<Difficulty> difficulty;
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
        if(version.isOrAbove(ProtocolVersion.V1_16)) {
            // TODO: Add NBT Codec reader to handle dimension on 1.16
            this.dimension = Optional.empty();
        } else {
            this.dimension = Optional.of(Dimension.values()[byteBuf.readByte() + 1]);
        }

        // Difficulty
        if(version.isOrAbove(ProtocolVersion.V1_16)) {
            this.difficulty = Optional.empty();
        } else {
            this.difficulty = Optional.of(Difficulty.values()[byteBuf.readUnsignedByte()]);
        }

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
