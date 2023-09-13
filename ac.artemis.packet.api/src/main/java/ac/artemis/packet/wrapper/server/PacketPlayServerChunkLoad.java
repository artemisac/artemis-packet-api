package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerChunkLoad extends PacketServer {

    int getX();
    int getZ();
    boolean isOverworld();
}
