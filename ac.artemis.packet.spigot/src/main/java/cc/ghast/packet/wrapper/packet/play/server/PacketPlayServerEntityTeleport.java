package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerEntityTeleport extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerEntityTeleport(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntityTeleport", player, version);
    }

    private int entityId;
    private int x;
    private int y;
    private int z;
    private byte yaw;
    private byte pitch;
    private boolean onGround;


    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.x = byteBuf.readInt();
        this.y = byteBuf.readInt();
        this.z = byteBuf.readInt();
        this.yaw = byteBuf.readByte();
        this.pitch = byteBuf.readByte();
        this.onGround = byteBuf.readBoolean();
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
