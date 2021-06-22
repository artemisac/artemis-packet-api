package cc.ghast.packet.wrapper.packet;

import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.nms.ProtocolVersion;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.UUID;

/**
 * @author Ghast
 * @since 31/10/2020
 * ArtemisPacket © 2020
 */

@Getter
public class PacketInfo<T> {
    private final int id;
    private final Class<? extends GPacket<T>> clazz;
    private final String nmsName;
    private final Constructor<? extends GPacket<T>> constructor;

    @SneakyThrows
    public PacketInfo(int id, Class<? extends GPacket<T>> clazz, String nmsName) {
        this.id = id;
        this.clazz = clazz;
        this.nmsName = nmsName;
        this.constructor = clazz.getConstructor(UUID.class, ProtocolVersion.class);
    }
}
