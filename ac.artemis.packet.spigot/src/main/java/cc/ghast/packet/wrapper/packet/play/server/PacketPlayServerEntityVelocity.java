package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.MathHelper;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerEntityVelocity extends Packet<ServerPacket> implements ReadableBuffer, WriteableBuffer {
    public PacketPlayServerEntityVelocity(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntityVelocity", player, version);
    }

    public PacketPlayServerEntityVelocity(int entityId, short x, short y, short z) {
        super("PacketPlayOutEntityVelocity");
        this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PacketPlayServerEntityVelocity(int entityId, double x, double y, double z) {
        super("PacketPlayOutEntityVelocity");
        this.entityId = entityId;
        this.x = (short) MathHelper.floor(x / 8000.D);
        this.y = (short) MathHelper.floor(y / 8000.D);
        this.z = (short) MathHelper.floor(z / 8000.D);
    }

    private int entityId;
    private short x;
    private short y;
    private short z;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();

        this.x = byteBuf.readShort();
        this.y = byteBuf.readShort();
        this.z = byteBuf.readShort();
    }

    @Override
    public void write(ProtocolByteBuf byteBuf) {
        byteBuf.writeVarInt(entityId);
        byteBuf.writeShort(x);
        byteBuf.writeShort(y);
        byteBuf.writeShort(z);
    }

    public double getValueX() {
        return (x / 8000.D);
    }

    public double getValueY() {
        return (y / 8000.D);
    }

    public double getValueZ() {
        return (z / 8000.D);
    }
}
