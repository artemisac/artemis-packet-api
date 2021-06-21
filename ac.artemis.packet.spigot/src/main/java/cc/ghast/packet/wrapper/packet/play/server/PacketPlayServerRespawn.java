package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.bukkit.GameMode;
import cc.ghast.packet.wrapper.bukkit.WorldType;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;
import org.bukkit.Difficulty;

import java.util.UUID;

@Getter
public class PacketPlayServerRespawn extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerRespawn(UUID player, ProtocolVersion version) {
        super("PacketPlayOutRespawn", player, version);
    }

    private int id;
    private Difficulty difficulty;
    private GameMode gamemode;
    private WorldType dimension;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        if (version.isAbove(ProtocolVersion.V1_14)) {

        } else {
            this.id = byteBuf.readInt();
            this.difficulty = Difficulty.values()[byteBuf.readUnsignedByte()];
            this.gamemode = GameMode.getById(byteBuf.readUnsignedByte());
            this.dimension = WorldType.getByName(byteBuf.readStringBuf(16).toUpperCase());
            if (this.dimension == null) {
                this.dimension = WorldType.DEFAULT;
            }
        }

    }
}
