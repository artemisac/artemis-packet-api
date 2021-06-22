package ac.artemis.packet.spigot.protocol.generator;

import ac.artemis.packet.PacketGenerator;
import ac.artemis.packet.protocol.ProtocolDirection;
import ac.artemis.packet.protocol.ProtocolState;
import ac.artemis.packet.protocol.format.EnumProtocolFormat;
import ac.artemis.packet.protocol.format.WrittenEnumProtocol;
import ac.artemis.packet.spigot.ArtemisSpigotPlugin;
import ac.artemis.packet.spigot.utils.Accessor;
import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.Packet;
import ac.artemis.packet.wrapper.PacketInfo;
import cc.ghast.packet.wrapper.packet.handshake.GPacketHandshakeClientSetProtocol;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProtocolGeneratorDirect extends Accessor implements PacketGenerator {
    private final Map<Class<? extends Packet>, Class<? extends GPacket>> translationMap = new HashMap<>();
    private final Map<Integer, Constructor<? extends GPacket>> constructorMap = new HashMap<>();
    private final WrittenEnumProtocol version;

    public ProtocolGeneratorDirect(ArtemisSpigotPlugin plugin, WrittenEnumProtocol version) {
        super(plugin);
        this.version = version;
    }

    @Override
    public Packet getPacketFromId(ProtocolDirection direction, ProtocolState protocol, int id) {
        final EnumProtocolFormat format = version.getFormatMap().get(protocol);

        if (format == null) {
            throw new IllegalStateException("Enum protocol map is null! Invalid state!");
        }

        final PacketInfo info;

        if (direction == ProtocolDirection.IN) {
            info = format.getInboundPackets().get(id);
        } else {
            info = format.getOutboundPackets().get(id);
        }

        if (info == null)
            return null;

    }

    @Override
    public void create() {
        List<Class<? extends GPacket>> packets = Arrays.asList(
                GPacketHandshakeClientSetProtocol.class,
                GPacketLoginClientEncryptionBegin.class,
                GPacketLoginClient
        )

    }

    @Override
    public void dispose() {

    }

}
