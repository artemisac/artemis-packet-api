package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerEntityAttach extends PacketServer {

    int getEntityId();
    int getVehicleId();
    boolean isLeash();
}
