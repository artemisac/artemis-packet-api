package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerSpawnNamedEntity extends GPacket implements ReadableBuffer {
    public PacketPlayServerSpawnNamedEntity(UUID player, ProtocolVersion version) {
        super("PacketPlayOutNamedEntitySpawn", player, version);
    }

    public PacketPlayServerSpawnNamedEntity(String realName, UUID player, ProtocolVersion version) {
        super(realName, player, version);
    }

    private int entityId;
    private UUID objectUUID;
    private int type;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;
    private int data;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.objectUUID = byteBuf.readUUID();

        if (version.isBelow(ProtocolVersion.V1_9)) {
            this.x = byteBuf.readInt() / 32.D;
            this.y = byteBuf.readInt() / 32.D;
            this.z = byteBuf.readInt() / 32.D;
        } else {
            this.x = byteBuf.readDouble();
            this.y = byteBuf.readDouble();
            this.z = byteBuf.readDouble();
        }

        this.pitch = byteBuf.readByte();
        this.yaw = byteBuf.readByte();

        if (version.isBelow(ProtocolVersion.V1_15)) {
            this.type = byteBuf.readShort();
        }
    }

    public float getValueYaw() {
        return (yaw * 360.0F / 256.0F);
    }

    public float getValuePitch() {
        return (pitch * 360.0F / 256.0F);
    }
}
