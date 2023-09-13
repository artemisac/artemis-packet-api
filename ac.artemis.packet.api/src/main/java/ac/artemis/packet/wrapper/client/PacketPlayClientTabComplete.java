package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;

import java.util.Optional;

public interface PacketPlayClientTabComplete extends PacketClient {

    String getValue();
    Optional<Boolean> getAssumeCommand();
    Optional<Integer> getTransactionId();
}
