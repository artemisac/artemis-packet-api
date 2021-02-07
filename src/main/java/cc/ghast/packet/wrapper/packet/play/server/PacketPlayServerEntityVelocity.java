package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerEntityVelocity extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerEntityVelocity(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntityVelocity", player, version);
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
