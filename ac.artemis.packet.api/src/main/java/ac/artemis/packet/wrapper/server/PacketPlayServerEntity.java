package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerEntity extends PacketServer {
    int getEntityId();

    short getX();
    short getY();
    short getZ();

    byte getYaw();
    byte getPitch();

    boolean isOnGround();
    boolean isHasLook();
    boolean isHasPos();
}
