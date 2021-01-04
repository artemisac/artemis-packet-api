package cc.ghast.packet.wrapper.packet;

import cc.ghast.packet.nms.ProtocolVersion;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * @author Ghast
 * @since 31/10/2020
 * ArtemisPacket © 2020
 */

@Getter
public class PacketInfo {
    private final int id;
    private final Class<? extends Packet<?>> clazz;
    private final String nmsName;
    private final Constructor<? extends Packet<?>> constructor;

    @SneakyThrows
    public PacketInfo(int id, Class<? extends Packet<?>> clazz, String nmsName) {
        this.id = id;
        this.clazz = clazz;
        this.nmsName = nmsName;
        this.constructor = clazz.getConstructor(UUID.class, ProtocolVersion.class);
    }
}
