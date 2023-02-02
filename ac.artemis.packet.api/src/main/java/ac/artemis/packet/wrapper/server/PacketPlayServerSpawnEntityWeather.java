package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerSpawnEntityWeather extends PacketServer {

    int getEntityId();
    double getX();
    double getY();
    double getZ();
    byte getType();
}
