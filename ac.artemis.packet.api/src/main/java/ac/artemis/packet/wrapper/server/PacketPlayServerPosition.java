package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

import java.util.Optional;

public interface PacketPlayServerPosition extends PacketServer {

    double getX();
    double getY();
    double getZ();
    float getYaw();
    float getPitch();
    Optional<Integer> getConfirmId();
}
