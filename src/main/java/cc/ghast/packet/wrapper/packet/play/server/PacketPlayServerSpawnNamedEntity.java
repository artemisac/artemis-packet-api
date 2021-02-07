package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@Getter
public class PacketPlayServerSpawnNamedEntity extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerSpawnNamedEntity(UUID player, ProtocolVersion version) {
        super("PacketPlayOutNamedEntitySpawn", player, version);
    }

    public PacketPlayServerSpawnNamedEntity(String realName, UUID player, ProtocolVersion version) {
        super(realName, player, version);
    }

    private int entityId;
    private UUID objectUUID;
    private int type;
    private int x;
    private int y;
    private int z;
    private float pitch;
    private float yaw;
    private int data;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.objectUUID = byteBuf.readUUID();
        this.x = byteBuf.readInt();
        this.y = byteBuf.readInt();
        this.z = byteBuf.readInt();
        this.pitch = byteBuf.readByte();
        this.yaw = byteBuf.readByte();
        this.type = byteBuf.readShort();
    }

    public double getValueX() {
        return x / 32.D;
    }

    public double getValueY() {
        return y / 32.D;
    }

    public double getValueZ() {
        return z / 32.D;
    }

    public float getValueYaw() {
        return (yaw * 360.0F / 256.0F);
    }

    public float getValuePitch() {
        return (pitch * 360.0F / 256.0F);
    }
}
