package ac.artemis.packet.spigot.protocol;

import ac.artemis.packet.wrapper.Packet;

public @interface PacketLink {
    Class<? extends Packet> value();

}
