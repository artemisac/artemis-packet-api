package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerEntityTeleport extends PacketServer {

    int getEntityId();
    int getX();
    int getY();
    int getZ();
    byte getYaw();
    byte getPitch();
    boolean isOnGround();
}
