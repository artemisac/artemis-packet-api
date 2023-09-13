package ac.artemis.packet.wrapper.server;

import ac.artemis.packet.wrapper.PacketServer;

public interface PacketPlayServerEntityEffect extends PacketServer {
    int getEntityId();
    int getDuration();
    byte getEffectId();
    byte getAmplifier();
    boolean isShowParticles();
}
