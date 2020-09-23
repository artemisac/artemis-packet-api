package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.EnumDirection;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.buffer.types.Converters;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.bukkit.Vector3D;
import cc.ghast.packet.wrapper.nbt.WrappedItem;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Getter
public class PacketPlayClientBlockPlace extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientBlockPlace(UUID player, ProtocolVersion version) {
        super(ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_11) ? "PacketPlayInBlockPlace" : "PacketPlayInUseItem", player, version);
    }

    private Optional<EnumDirection> direction;
    private ItemStack item;
    private BlockPosition position;
    private Vector3D vector;

    @Override
    public void read(ProtocolByteBuf byteBuf) {

        final boolean legacy = gameVersion.isOrBelow(ProtocolVersion.V1_8_9);

        if (legacy) {

            // 1.6.4 - 1.7.10 version range
            if (version.isBelow(ProtocolVersion.V1_8)) {
                // Position
                int x = byteBuf.readInt();
                int y = byteBuf.readByte();
                int z = byteBuf.readInt();
                this.position = new BlockPosition(x, y, z);
            }

            // 1.8 - 1.8.8 version range
            else if (version.isBelow(ProtocolVersion.V1_9)) {
                // Position
                this.position = Converters.LOCATION_LONG.read(byteBuf.getByteBuf());
            }

            // Direction
            final short direction = byteBuf.readUnsignedByte();

            if (direction == 255) {
                this.direction = Optional.empty();
            } else {
                this.direction = Optional.of(EnumDirection.values()[direction]);
            }

            // Item
            try {
                this.item = Converters.ITEM_STACK.read(byteBuf.getByteBuf());
            } catch (IOException e){
                e.printStackTrace();
            }

            //
            float x = (float) byteBuf.readUnsignedByte() / 16.0F;
            float y = (float) byteBuf.readUnsignedByte() / 16.0F;
            float z = (float) byteBuf.readUnsignedByte() / 16.0F;
            this.vector = new Vector3D(x, y, z);
        } else {

        }


    }

    public enum Hand {
        MAIN_HAND, OFF_HAND;
    }
}
