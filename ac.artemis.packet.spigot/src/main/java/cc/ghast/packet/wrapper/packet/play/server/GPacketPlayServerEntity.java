package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.server.PacketPlayServerEntity;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerEntity.class)
public class GPacketPlayServerEntity extends GPacket implements ReadableBuffer {

    protected int entityId;
    protected short x;
    protected short y;
    protected short z;
    protected byte yaw;
    protected byte pitch;
    protected boolean onGround;
    protected boolean hasLook, hasPos;

    public GPacketPlayServerEntity(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntity", player, version);
    }

    public GPacketPlayServerEntity(String realName, UUID player, ProtocolVersion version) {
        super(realName, player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
    }

    public static class GPacketPlayServerRelEntityMove extends GPacketPlayServerEntity {
        public GPacketPlayServerRelEntityMove(UUID player, ProtocolVersion version) {
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

    public static class GPacketPlayServerEntityLook extends GPacketPlayServerEntity {
        public GPacketPlayServerEntityLook(UUID player, ProtocolVersion version) {
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

    public static class GPacketPlayServerRelEntityMoveLook extends GPacketPlayServerEntity {
        public GPacketPlayServerRelEntityMoveLook(UUID player, ProtocolVersion version) {
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
