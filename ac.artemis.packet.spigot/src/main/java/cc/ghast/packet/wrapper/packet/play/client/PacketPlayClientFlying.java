package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
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

        @Override
        public int hashCode() {
            return super.hashCode();
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

        @Override
        public int hashCode() {
            return super.hashCode();
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


        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PacketPlayClientFlying flying = (PacketPlayClientFlying) o;

        if (Double.compare(flying.x, x) != 0) return false;
        if (Double.compare(flying.y, y) != 0) return false;
        if (Double.compare(flying.z, z) != 0) return false;
        if (Float.compare(flying.yaw, yaw) != 0) return false;
        if (Float.compare(flying.pitch, pitch) != 0) return false;
        if (onGround != flying.onGround) return false;
        if (hasPos != flying.hasPos) return false;
        return hasLook == flying.hasLook;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (yaw != +0.0f ? Float.floatToIntBits(yaw) : 0);
        result = 31 * result + (pitch != +0.0f ? Float.floatToIntBits(pitch) : 0);
        result = 31 * result + (onGround ? 1 : 0);
        result = 31 * result + (hasPos ? 1 : 0);
        result = 31 * result + (hasLook ? 1 : 0);
        return result;
    }
}
