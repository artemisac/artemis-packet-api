package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;

public interface PacketPlayClientItemHeldSlot extends PacketClient {

    short getSlot();
}
