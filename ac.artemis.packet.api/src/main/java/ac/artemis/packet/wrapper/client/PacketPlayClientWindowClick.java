package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;

public interface PacketPlayClientWindowClick extends PacketClient {

    byte getWindowId();
    byte getButton();
    short getActionNumber();
    short getSlot();
    int getShiftedMode();
}
