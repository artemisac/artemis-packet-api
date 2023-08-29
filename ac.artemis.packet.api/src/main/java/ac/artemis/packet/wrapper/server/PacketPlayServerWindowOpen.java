package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerWindowOpen extends PacketServer {
    int getWindowId();

    String getWindowType();
    String getWindowTitle();

    int getSlots();
    int getEntityId();
}
