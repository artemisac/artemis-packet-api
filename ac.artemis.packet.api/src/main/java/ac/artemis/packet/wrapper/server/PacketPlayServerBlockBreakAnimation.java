package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerBlockBreakAnimation extends PacketServer {

    int getEntityId();
    int getDestroyStage();
}
