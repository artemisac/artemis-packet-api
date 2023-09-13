package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

import java.util.List;
import java.util.Optional;

public interface PacketPlayServerBlockChangeMulti extends PacketServer {

    Integer getChunkX();
    Integer getChunkZ();
    Optional<Integer> getChunkY();
}
