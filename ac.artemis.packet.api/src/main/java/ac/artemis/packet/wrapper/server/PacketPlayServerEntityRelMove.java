package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerEntityRelMove extends PacketPlayServerEntity {

    short getX();
    short getY();
    short getZ();
}
