package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class PacketPlayServerExplosion extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerExplosion(UUID player, ProtocolVersion version) {
        super("PacketPlayOutExplosion", player, version);
    }

    private double posX;
    private double posY;
    private double posZ;
    private float strength;
    private List<BlockPosition> affectedBlockPositions;
    private float motionX;
    private float motionY;
    private float motionZ;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.posX = byteBuf.readFloat();
        this.posY = byteBuf.readFloat();
        this.posZ = byteBuf.readFloat();
        this.strength = byteBuf.readFloat();

        final int size = byteBuf.readInt();

        this.affectedBlockPositions = Lists.newArrayListWithCapacity(size);

        final int x = (int) this.posX;
        final int y = (int) this.posY;
        final int z = (int) this.posZ;

        for (int i = 0; i < size; ++i) {
            int offX = byteBuf.readByte() + x;
            int offY = byteBuf.readByte() + y;
            int offZ = byteBuf.readByte() + z;
            this.affectedBlockPositions.add(new BlockPosition(offX, offY, offZ));
        }

        this.motionX = byteBuf.readFloat();
        this.motionY = byteBuf.readFloat();
        this.motionZ = byteBuf.readFloat();
    }
}
