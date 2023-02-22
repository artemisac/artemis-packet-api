package cc.ghast.packet.wrapper.packet.play.client;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.spigot.utils.ServerUtil;
import ac.artemis.packet.wrapper.client.PacketPlayClientItemUse;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.EnumDirection;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.bukkit.Vector3D;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Ghast
 * @since 31/10/2020
 * ArtemisPacket © 2020
 */
@Getter
@PacketLink(PacketPlayClientItemUse.class)
public class GPacketPlayClientItemUse extends GPacket implements PacketPlayClientItemUse, ReadableBuffer {
    public GPacketPlayClientItemUse(UUID player, ProtocolVersion version) {
        super("PacketPlayInBlockPlace", player, version, e -> e.isOrAbove(ProtocolVersion.V1_9));
    }

    private Optional<EnumDirection> direction;
    private Optional<ItemStack> item;
    private Optional<BlockPosition> position;
    private Optional<Vector3D> vector;
    private PlayerEnums.Hand hand;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        System.out.println("got this packet");
        int direction = 255;

        // Lolz this is empty
        if (version.isBelow(ProtocolVersion.V1_9)) {
            this.hand = PlayerEnums.Hand.MAIN_HAND;
            this.position = Optional.empty();
            this.item = Optional.empty();
            this.direction = Optional.empty();
        } else {
            if(version.isOrBelow(ProtocolVersion.V1_16)) {
                BlockPosition pos = byteBuf.readBlockPositionFromLong();
                position = Optional.of(pos);
                direction = byteBuf.readVarInt();
                this.hand = PlayerEnums.Hand.values()[byteBuf.readVarInt()];
                this.item = Optional.empty();

                try {
                    float x = (float) byteBuf.readUnsignedByte() / 16.0F;
                    float y = (float) byteBuf.readUnsignedByte() / 16.0F;
                    float z = (float) byteBuf.readUnsignedByte() / 16.0F;
                    this.vector = Optional.of(new Vector3D(x, y, z));
                } catch (Exception ignore) {
                    this.vector = Optional.empty();
                }
            } else {
                this.hand = PlayerEnums.Hand.values()[byteBuf.readVarInt()];
                BlockPosition pos = byteBuf.readBlockPositionFromLong();
                position = Optional.of(pos);
                direction = byteBuf.readVarInt();
                this.item = Optional.empty();

                try {
                    float x = (float) byteBuf.readUnsignedByte() / 16.0F;
                    float y = (float) byteBuf.readUnsignedByte() / 16.0F;
                    float z = (float) byteBuf.readUnsignedByte() / 16.0F;
                    this.vector = Optional.of(new Vector3D(x, y, z));
                } catch (Exception ignore) {
                    this.vector = Optional.empty();
                }
            }
        }

        if (direction == 255) {
            this.direction = Optional.empty();
        } else {
            this.direction = Optional.of(EnumDirection.values()[direction]);
        }

        System.out.println(vector);
        System.out.println(position);
    }

}

