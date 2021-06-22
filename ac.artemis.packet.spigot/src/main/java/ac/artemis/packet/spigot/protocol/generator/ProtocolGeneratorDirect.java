package ac.artemis.packet.spigot.protocol.generator;

import ac.artemis.packet.PacketGenerator;
import ac.artemis.packet.protocol.ProtocolDirection;
import ac.artemis.packet.protocol.ProtocolState;
import ac.artemis.packet.protocol.format.EnumProtocolFormat;
import ac.artemis.packet.protocol.format.WrittenEnumProtocol;
import ac.artemis.packet.spigot.ArtemisSpigotPlugin;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.spigot.utils.Accessor;
import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.Packet;
import ac.artemis.packet.wrapper.PacketInfo;
import cc.ghast.packet.wrapper.packet.handshake.GPacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.login.GPacketLoginServerSuccess;
import cc.ghast.packet.wrapper.packet.play.client.*;

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
                GPacketLoginServerSuccess.class,
                GPacketPlayClientAbilities.class,
                GPacketPlayClientArmAnimation.class,
                GPacketPlayClientBlockDig.class,
                GPacketPlayClientBlockMetadataQuery.class,
                GPacketPlayClientBlockPlace.class,
                GPacketPlayClientBoatMove.class,
                GPacketPlayClientChat.class,
                GPacketPlayClientClientCommand.class,
                GPacketPlayClientConfirmTeleport.class,
                GPacketPlayClientCustomPayload.class,
                GPacketPlayClientEntityAction.class,
                GPacketPlayClientFlying.class,
                GPacketPlayClientItemUse.class,
                GPacketPlayClientKeepAlive.class,
                GPacketPlayClientLook.class,
                GPacketPlayClientPosition.class,
                GPacketPlayClientPositionLook.class,
                GPacketPlayClientResourcePackStatus.class,
                GPacketPlayClientSetCreativeSlot.class,
                GPacketPlayClientSettings.class,
                GPacketPlayClientSpectate.class,
                GPacketPlayClientSteerVehicle.class,
                GPacketPlayClientTabComplete.class,
                GPacketPlayClientTransaction.class,
                GPacketPlayClientUpdateSign.class,
                GPacketPlayClientUseEntity.class,
                GPacketPlayClientVehicleMove.class,
                GPacketPlayClientWindowClick.class,
                GPacketPlayClientWindowClose.class,
                GPacketPlayClientWindowHorse.class
        );


        for (Class<? extends GPacket> packet : packets) {
            final PacketLink packetLink = packet.getAnnotation(PacketLink.class);

            translationMap.put(packetLink.value(), packet);
        }
    }

    @Override
    public void dispose() {

    }

}
