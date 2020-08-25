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
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private boolean onGround;


    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.x = byteBuf.readInt() / 32.D;
        this.y = byteBuf.readInt() / 32.D;
        this.z = byteBuf.readInt() / 32.D;
        this.yaw = (byteBuf.readByte() * 360.0F / 256.0F);
        this.pitch = (byteBuf.readByte() * 360.0F / 256.0F);
        this.onGround = byteBuf.readBoolean();
    }
}
