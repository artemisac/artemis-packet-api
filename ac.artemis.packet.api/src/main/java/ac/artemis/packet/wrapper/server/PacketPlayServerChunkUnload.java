package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerChunkUnload extends PacketServer {

    int getChunkX();

    int getChunkZ();
}
