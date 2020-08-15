package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.bukkit.BlockFace;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;
import org.bukkit.Location;

import java.util.UUID;

@Getter
public class PacketPlayClientBlockDig extends Packet<ClientPacket> {
    public PacketPlayClientBlockDig(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    private DigType type;
    private Location location;
    private BlockFace face;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        // Type of the dig
        this.type = DigType.values()[byteBuf.readByte()];

        // Position of the block placed
        if (version.isBelow(ProtocolVersion.V1_8)) {
            int x = byteBuf.readInt();
            int y = byteBuf.readByte();
            int z = byteBuf.readInt();
            this.location = new Location(getPlayer().getWorld(), x, y, z);
        } else {
            BlockPosition position = byteBuf.readBlockPositionFromLong();
            this.location = new Location(getPlayer().getWorld(), position.getX(), position.getY(), position.getZ());
        }

        // Face of the block
        this.face = BlockFace.values()[byteBuf.readByte()];
    }

    public enum DigType {
        // Start digging a block
        START_DESTROY_BLOCK,
        // Cancel the process of digging a block
        ABORT_DESTROY_BLOCK,
        // Finish digging the block
        STOP_DESTROY_BLOCK,
        // Drop item as a stack
        DROP_ALL_ITEMS,
        // Drop item as a singular
        DROP_ITEM,
        // Shoot arrow / finish eating
        RELEASE_USE_ITEM,
        // Swap from main-hand to off-hand
        SWAP_HELD_ITEMS;
    }


}
