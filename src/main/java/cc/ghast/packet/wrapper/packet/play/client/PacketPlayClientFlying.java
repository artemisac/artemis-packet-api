package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientFlying extends Packet<ClientPacket> implements ReadableBuffer {


    public PacketPlayClientFlying(UUID player, ProtocolVersion version) {
        super("PacketPlayInFlying", player, version);
    }

    public PacketPlayClientFlying(String sub, UUID player, ProtocolVersion version) {
        super((ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8) ? "" : "PacketPlayInFlying$")
                + "PacketPlayIn" + sub, player, version);
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
    public void read(ProtocolByteBuf byteBuf) {
        this.onGround = byteBuf.readUnsignedByte() != 0;
    }

    @Getter
    public static class PacketPlayClientPosition extends PacketPlayClientFlying {

        public PacketPlayClientPosition(UUID player, ProtocolVersion version) {
            super("Position", player, version);
        }

        @Override
        public void read(ProtocolByteBuf byteBuf) {
            super.x = byteBuf.readDouble();
            super.y = byteBuf.readDouble();
            super.z = byteBuf.readDouble();
            super.hasPos = true;
            super.read(byteBuf);
        }

    }

    @Getter
    public static class PacketPlayClientLook extends PacketPlayClientFlying {


        public PacketPlayClientLook(UUID player, ProtocolVersion version) {
            super("Look", player, version);
        }

        @Override
        public void read(ProtocolByteBuf byteBuf) {
            super.yaw = byteBuf.readFloat();
            super.pitch = byteBuf.readFloat();
            super.hasLook = true;
            super.read(byteBuf);
        }

    }

    @Getter
    public static class PacketPlayClientPositionLook extends PacketPlayClientFlying {

        public PacketPlayClientPositionLook(UUID player, ProtocolVersion version) {
            super("PositionLook", player, version);
        }

        @Override
        public void read(ProtocolByteBuf byteBuf) {
            super.x = byteBuf.readDouble();
            super.y = byteBuf.readDouble();
            super.z = byteBuf.readDouble();

            super.yaw = byteBuf.readFloat();
            super.pitch = byteBuf.readFloat();
            super.hasPos = true;
            super.hasLook = true;
            super.read(byteBuf);
        }

    }
}
