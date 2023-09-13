package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;

import java.util.UUID;

public interface PacketPlayClientSpectate extends PacketClient {

    UUID getEntityId();
}
