package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.EnumDirection;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.buffer.types.Converters;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.bukkit.Vector3D;
import cc.ghast.packet.wrapper.nbt.WrappedItem;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.io.IOException;
import java.util.UUID;

@Getter
public class PacketPlayClientBlockPlace extends Packet<ClientPacket> {
    public PacketPlayClientBlockPlace(UUID player, ProtocolVersion version) {
        super("PacketPlayInBlockPlace", player, version);
    }

    private EnumDirection direction;
    private WrappedItem itemNMS;
    private BlockPosition position;
    private boolean mainHand;
    private boolean missed;
    private Vector3D vector;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        if (version.isBelow(ProtocolVersion.V1_8)) {
            // 1.6.4 - 1.7.10 version range

            // Position
            int x = byteBuf.readInt();
            int y = byteBuf.readByte();
            int z = byteBuf.readInt();
            this.position = new BlockPosition(x, y, z);

            //

        }

        else if (version.isBelow(ProtocolVersion.V1_9)) {
            // 1.8 - 1.8.8 version range

            // Position
            this.position = Converters.LOCATION_LONG.read(byteBuf.getByteBuf());

            // Direction
            this.direction = EnumDirection.values()[byteBuf.readUnsignedByte()];

            // Item
            try {
                this.itemNMS = Converters.ITEM.read(byteBuf.getByteBuf());
            } catch (IOException e){
                e.printStackTrace();
            }

            //
            float x = (float) byteBuf.readUnsignedByte() / 16.0F;
            float y = (float) byteBuf.readUnsignedByte() / 16.0F;
            float z = (float) byteBuf.readUnsignedByte() / 16.0F;
            this.vector = new Vector3D(x, y, z);
        }
    }
}
