package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;

public interface PacketPlayClientSteerVehicle extends PacketClient {

    float getMoveForward();
    float getMoveStrafing();

    boolean isJumping();
    boolean isSneaking();
}
