package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerTransaction extends PacketServer {
    byte getWindowId();
    short getActionNumber();
    boolean isAccepted();
}
