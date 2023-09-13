package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerEntityEffectRemove extends PacketServer {
    int getEntityId();
    int getEffectId();
}
