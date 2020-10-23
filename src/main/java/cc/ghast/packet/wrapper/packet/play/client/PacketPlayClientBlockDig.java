package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.EnumDirection;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.bukkit.BlockFace;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;
import org.bukkit.Location;

import java.util.UUID;

@Getter
public class PacketPlayClientBlockDig extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientBlockDig(UUID player, ProtocolVersion version) {
        super("PacketPlayInBlockDig", player, version);
    }

    private DigType type;
    private Location location;
    private EnumDirection face;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        // Type of the dig
        this.type = DigType.values()[byteBuf.readVarInt()];

        // Position of the block placed
        if (version.isBelow(ProtocolVersion.V1_8)) {
            final int x = byteBuf.readInt();
            final int y = byteBuf.readByte();
            final int z = byteBuf.readInt();
            this.location = new Location(getPlayer().getWorld(), x, y, z);
        } else {
            BlockPosition position = byteBuf.readBlockPositionFromLong();
            this.location = new Location(getPlayer().getWorld(), position.getX(), position.getY(), position.getZ());
        }

        // Face of the block
        this.face = EnumDirection.fromType1(byteBuf.readUnsignedByte());
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
