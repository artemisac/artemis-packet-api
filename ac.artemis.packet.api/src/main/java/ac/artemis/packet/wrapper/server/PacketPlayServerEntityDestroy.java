package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerEntityDestroy extends PacketServer {
    int[] getEntities();
}
