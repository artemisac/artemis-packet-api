package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;

public interface PacketPlayClientTransaction extends PacketClient {

    byte getWindowId();
    boolean isAccepted();
    short getActionNumber();
}
