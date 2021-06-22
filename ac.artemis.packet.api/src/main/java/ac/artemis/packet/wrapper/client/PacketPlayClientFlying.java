package ac.artemis.packet.wrapper.client;

import ac.artemis.packet.wrapper.PacketClient;

public interface PacketPlayClientFlying extends PacketClient {

    default boolean isPos() {
        return PacketPlayClientPosition.class.isAssignableFrom(this.getClass());
    }

    default boolean isLook() {
        return PacketPlayClientLook.class.isAssignableFrom(this.getClass());
    }
}
