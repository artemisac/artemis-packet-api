package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

import java.util.UUID;

public interface PacketPlayServerSpawnEntityNamed extends PacketServer {

    int getEntityId();
    UUID getObjectUUID();
    double getX();
    double getY();
    double getZ();
    float getYaw();
    float getPitch();
    int getData();
}
