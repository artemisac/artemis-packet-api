package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerAnimation extends PacketServer {
    int getEntityId();
    int getAnimationId();
}
