package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerEntity extends Packet<ServerPacket> {

    protected int entityId;
    protected byte x;
    protected byte y;
    protected byte z;
    protected byte yaw;
    protected byte pitch;
    protected boolean onGround;
    protected boolean hasLook, hasPos;

    public PacketPlayServerEntity(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntity", player, version);
    }

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
    }

    public static class PacketPlayServerRelEntityMove extends PacketPlayServerEntity {
        public PacketPlayServerRelEntityMove(UUID player, ProtocolVersion version) {
            super(player, version);
        }

        @Override
        public void handle(ProtocolByteBuf byteBuf) {
            super.handle(byteBuf);

            this.x = byteBuf.readByte();
            this.y = byteBuf.readByte();
            this.z = byteBuf.readByte();

            this.onGround = byteBuf.readBoolean();
            this.hasPos = true;
        }
    }

    public static class PacketPlayServerEntityLook extends PacketPlayServerEntity {
        public PacketPlayServerEntityLook(UUID player, ProtocolVersion version) {
            super(player, version);
        }

        @Override
        public void handle(ProtocolByteBuf byteBuf) {
            super.handle(byteBuf);

            this.yaw = byteBuf.readByte();
            this.pitch = byteBuf.readByte();
            this.onGround = byteBuf.readBoolean();
            this.hasLook = true;
        }
    }

    public static class PacketPlayServerRelEntityMoveLook extends PacketPlayServerEntity {
        public PacketPlayServerRelEntityMoveLook(UUID player, ProtocolVersion version) {
            super(player, version);
        }

        @Override
        public void handle(ProtocolByteBuf byteBuf) {
            super.handle(byteBuf);
            this.x = byteBuf.readByte();
            this.y = byteBuf.readByte();
            this.z = byteBuf.readByte();
            this.yaw = byteBuf.readByte();
            this.pitch = byteBuf.readByte();
            this.onGround = byteBuf.readBoolean();
            this.hasLook = this.hasPos = true;
        }
    }
}
