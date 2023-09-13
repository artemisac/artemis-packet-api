package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

import java.util.Optional;

public interface PacketPlayServerAbilities extends PacketServer {

    boolean isFlying();
    Optional<Boolean> getInvulnerable();
    Optional<Boolean> getAllowedFlight();
    Optional<Boolean> getCreativeMode();
    Optional<Float> getFlySpeed();
    Optional<Float> getWalkSpeed();
}
