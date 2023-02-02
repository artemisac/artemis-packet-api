package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;

public interface PacketPlayClientSettings extends PacketClient {

    String getLocale();
    int getViewDistance();
    int getDisplayedSkinPartsMask();
    boolean isChatColors();
}
