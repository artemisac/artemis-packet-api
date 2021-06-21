package ac.artemis.packet.wrapper;

import ac.artemis.packet.wrapper.Packet;

public class PacketClass {
    private Class<? extends Packet> clazz;

    public PacketClass(Class<? extends Packet> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Packet> getClazz() {
        return clazz;
    }
}
