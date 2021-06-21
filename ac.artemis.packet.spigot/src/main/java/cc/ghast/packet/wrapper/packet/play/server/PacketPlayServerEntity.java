package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerEntity extends Packet<ServerPacket> implements ReadableBuffer {

    protected int entityId;
    protected short x;
    protected short y;
    protected short z;
    protected byte yaw;
    protected byte pitch;
    protected boolean onGround;
    protected boolean hasLook, hasPos;

    public PacketPlayServerEntity(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntity", player, version);
    }

    public PacketPlayServerEntity(String realName, UUID player, ProtocolVersion version) {
        super(realName, player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
    }

    public static class PacketPlayServerRelEntityMove extends PacketPlayServerEntity {
        public PacketPlayServerRelEntityMove(UUID player, ProtocolVersion version) {
            super("PacketPlayOutRelEntityMove", player, version);
        }

        @Override
        public void read(ProtocolByteBuf byteBuf) {
            super.read(byteBuf);

            if (version.isBelow(ProtocolVersion.V1_16)) {
                this.x = byteBuf.readByte();
                this.y = byteBuf.readByte();
                this.z = byteBuf.readByte();
            } else {
                this.x = byteBuf.readShort();
                this.y = byteBuf.readShort();
                this.z = byteBuf.readShort();
            }

            this.onGround = byteBuf.readBoolean();
            this.hasPos = true;
        }
    }

    public static class PacketPlayServerEntityLook extends PacketPlayServerEntity {
        public PacketPlayServerEntityLook(UUID player, ProtocolVersion version) {
            super("PacketPlayOutEntityLook", player, version);
        }

        @Override
        public void read(ProtocolByteBuf byteBuf) {
            super.read(byteBuf);

            this.yaw = byteBuf.readByte();
            this.pitch = byteBuf.readByte();
            this.onGround = byteBuf.readBoolean();
            this.hasLook = true;
        }
    }

    public static class PacketPlayServerRelEntityMoveLook extends PacketPlayServerEntity {
        public PacketPlayServerRelEntityMoveLook(UUID player, ProtocolVersion version) {
            super("PacketPlayOutRelEntityMoveLook", player, version);
        }

        @Override
        public void read(ProtocolByteBuf byteBuf) {
            super.read(byteBuf);
            if (version.isBelow(ProtocolVersion.V1_16)) {
                this.x = byteBuf.readByte();
                this.y = byteBuf.readByte();
                this.z = byteBuf.readByte();
            } else {
                this.x = byteBuf.readShort();
                this.y = byteBuf.readShort();
                this.z = byteBuf.readShort();
            }

            this.yaw = byteBuf.readByte();
            this.pitch = byteBuf.readByte();
            this.onGround = byteBuf.readBoolean();
            this.hasLook = this.hasPos = true;
        }
    }
}
