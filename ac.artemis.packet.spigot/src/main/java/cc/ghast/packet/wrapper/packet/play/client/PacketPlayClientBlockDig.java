package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.EnumDirection;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

@Getter
public class PacketPlayClientBlockDig extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientBlockDig(UUID player, ProtocolVersion version) {
        super("PacketPlayInBlockDig", player, version);
    }

    private PlayerEnums.DigType type;
    private Location location;
    @Deprecated
    private int direction;
    private EnumDirection face;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        // Type of the dig
        this.type = PlayerEnums.DigType.values()[byteBuf.readVarInt()];

        final World world = getPlayer() == null ? null : getPlayer().getWorld();

        // Position of the block placed
        if (version.isBelow(ProtocolVersion.V1_8)) {
            final int x = byteBuf.readInt();
            final int y = byteBuf.readByte();
            final int z = byteBuf.readInt();
            this.location = new Location(world, x, y, z);
        } else {
            final BlockPosition position = byteBuf.readBlockPositionFromLong();
            this.location = new Location(world, position.getX(), position.getY(), position.getZ());
        }

        // Face of the block
        this.direction = byteBuf.readUnsignedByte();
        this.face = EnumDirection.fromType1(direction);
    }


}
