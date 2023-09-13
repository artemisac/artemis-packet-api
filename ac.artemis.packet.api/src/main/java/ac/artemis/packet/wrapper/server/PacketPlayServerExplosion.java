package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerExplosion extends PacketServer {
    double getPosX();
    double getPosY();
    double getPosZ();

    float getStrength();

    float getMotionX();
    float getMotionY();
    float getMotionZ();
}
