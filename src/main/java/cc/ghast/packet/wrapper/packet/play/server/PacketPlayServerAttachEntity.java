package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketPlayServerAttachEntity extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerAttachEntity(UUID player, ProtocolVersion version) {
        super("PacketPlayOutAttachEntity", player, version);
    }

    private int entityId;
    private int vehicleEntityId;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        if (version.isBelow(ProtocolVersion.V1_9)) {
            //I am unsure what this is, but this only exists on 1.7 - 1.8 anyway...
            int idk = byteBuf.readInt();
            this.entityId = byteBuf.readInt();
            this.vehicleEntityId = byteBuf.readUnsignedByte();
        }
        else {
            this.vehicleEntityId = byteBuf.readInt();
            this.entityId = byteBuf.readInt();
        }
    }
}
