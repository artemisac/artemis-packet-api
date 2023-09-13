package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerLogin extends PacketServer {

    int getEntityId();
    int getMaxPlayers();
    boolean isHardcoreMode();
    boolean isReducedDebugInfo();
}
