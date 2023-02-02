package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;

import java.util.Optional;

public interface PacketPlayClientResourcePackStatus extends PacketClient {

    Optional<String> getUrl();
}
