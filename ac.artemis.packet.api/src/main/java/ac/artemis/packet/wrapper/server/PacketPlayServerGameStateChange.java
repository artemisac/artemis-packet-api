package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerGameStateChange extends PacketServer {
    int getReason();
    float getValue();
}
