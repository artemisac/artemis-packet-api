package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;

public interface PacketPlayClientEntityAction extends PacketClient {

    int getEntityId();
    int getParameter();
}
