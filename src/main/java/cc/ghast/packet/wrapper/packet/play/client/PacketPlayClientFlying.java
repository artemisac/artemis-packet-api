package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientFlying extends Packet {


    public PacketPlayClientFlying(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    private double x;
    private double y;
    private double z;

    private float yaw;
    private float pitch;
    private boolean onGround;
    private boolean hasPos;
    private boolean hasLook;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.onGround = byteBuf.readBoolean();
    }

    @Getter
    public static class PacketPlayClientPosition extends PacketPlayClientFlying {

        public PacketPlayClientPosition(UUID player, ProtocolVersion version) {
            super(player, version);
        }

        @Override
        public void handle(ProtocolByteBuf byteBuf) {
            super.x = byteBuf.readDouble();
            super.y = byteBuf.readDouble();
            super.z = byteBuf.readDouble();
            super.hasPos = true;
            super.handle(byteBuf);
        }

    }

    @Getter
    public static class PacketPlayClientLook extends PacketPlayClientFlying {


        public PacketPlayClientLook(UUID player, ProtocolVersion version) {
            super(player, version);
        }

        @Override
        public void handle(ProtocolByteBuf byteBuf) {
            super.yaw = byteBuf.readFloat();
            super.pitch = byteBuf.readFloat();
            super.hasLook = true;
            super.handle(byteBuf);
        }

    }

    @Getter
    public static class PacketPlayClientPositionLook extends PacketPlayClientFlying {

        public PacketPlayClientPositionLook(UUID player, ProtocolVersion version) {
            super(player, version);
        }

        @Override
        public void handle(ProtocolByteBuf byteBuf) {
            super.x = byteBuf.readDouble();
            super.y = byteBuf.readDouble();
            super.z = byteBuf.readDouble();

            super.yaw = byteBuf.readFloat();
            super.pitch = byteBuf.readFloat();
            super.hasPos = true;
            super.hasLook = true;
            super.handle(byteBuf);
        }

    }
}
