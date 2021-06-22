package ac.artemis.packet.spigot.protocol;

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
import ac.artemis.packet.wrapper.client.PacketHandshakeClientSetProtocol;
import ac.artemis.packet.wrapper.client.PacketLoginClientEncryptionBegin;
import ac.artemis.packet.wrapper.client.PacketLoginClientStart;
import cc.ghast.packet.wrapper.packet.handshake.GPacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.login.GPacketLoginClientStart;
import cc.ghast.packet.wrapper.packet.login.GPacketLoginServerEncryptionBegin;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class ProtocolGenerator extends Accessor implements PacketGenerator {
    private final Map<Class<? extends Packet>, Class<? extends GPacket>> translationMap = new HashMap<>();
    private final Map<Integer, Constructor<? extends GPacket>> constructorMap = new HashMap<>();
    private final WrittenEnumProtocol version;

    public ProtocolGenerator(ArtemisSpigotPlugin plugin, WrittenEnumProtocol version) {
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
        

    }

    @Override
    public void dispose() {

    }

}
