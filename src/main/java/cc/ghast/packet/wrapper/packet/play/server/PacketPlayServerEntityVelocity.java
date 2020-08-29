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
    private double x;
    private double y;
    private double z;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();

        this.x = (byteBuf.readShort() / 8000.0D);
        this.y = (byteBuf.readShort() / 8000.0D);
        this.z = (byteBuf.readShort() / 8000.0D);
    }
}
