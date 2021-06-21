package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerSpawnEntityWeather extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerSpawnEntityWeather(UUID player, ProtocolVersion version) {
        super("PacketPlayOutSpawnEntityWeather", player, version);
    }

    private int entityId;
    private byte type;
    private double x;
    private double y;
    private double z;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.type = byteBuf.readByte();

        if (version.isAbove(ProtocolVersion.V1_9)) {
            this.x = byteBuf.readDouble();
            this.y = byteBuf.readDouble();
            this.z = byteBuf.readDouble();
        } else {
            this.x = byteBuf.readInt();
            this.y = byteBuf.readInt();
            this.z = byteBuf.readInt();
        }

    }
}
