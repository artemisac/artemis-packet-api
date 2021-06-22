package ac.artemis.packet.spigot.protocol.generator;

import ac.artemis.packet.PacketGenerator;
import ac.artemis.packet.protocol.ProtocolDirection;
import ac.artemis.packet.protocol.ProtocolState;
import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.protocol.format.EnumProtocolFormat;
import ac.artemis.packet.protocol.format.WrittenEnumProtocol;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.spigot.utils.access.Accessor;
import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.Packet;
import ac.artemis.packet.wrapper.PacketInfo;
import cc.ghast.packet.wrapper.packet.handshake.GPacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.login.GPacketLoginServerSuccess;
import cc.ghast.packet.wrapper.packet.play.client.*;
import cc.ghast.packet.wrapper.packet.play.server.*;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ProtocolGeneratorDirect extends Accessor implements PacketGenerator {
    private final Map<Class<? extends Packet>, Class<? extends GPacket>> translationMap = new HashMap<>();
    private final Map<Class<? extends Packet>, Integer> idMap = new HashMap<>();
    private final Map<Integer, Constructor<? extends GPacket>> constructorMap = new HashMap<>();
    private final WrittenEnumProtocol version;

    public ProtocolGeneratorDirect(Plugin plugin, WrittenEnumProtocol version) {
        super(plugin);
        this.version = version;
    }

    @Override
    public ProtocolVersion getVersion() {
        return version.getVersion();
    }

    @Override
    public Packet getPacketFromId(ProtocolDirection direction, ProtocolState protocol, int id, UUID uuid, ProtocolVersion protocolVersion) {
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

        Constructor<? extends GPacket> constructor = constructorMap.get(info.getClazzId());

        if (constructor == null) {
            final Class<? extends GPacket> clazz = translationMap.get(info.getClazz());

            try {
                constructor = clazz.getConstructor(UUID.class, ProtocolVersion.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            constructorMap.put(info.getClazzId(), constructor);
        }

        try {
            return constructor.newInstance(uuid, version);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public Integer getPacketId(Packet packet) {
        return idMap.get(packet.getClass());
    }

    @Override
    public void create() {
        List<Class<? extends GPacket>> packets = Arrays.asList(
                // Handshake
                GPacketHandshakeClientSetProtocol.class,

                // Login
                GPacketLoginServerSuccess.class,

                // Play - Client
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
                GPacketPlayClientWindowHorse.class,

                // Play - Server
                GPacketPlayServerAbilities.class,
                GPacketPlayServerAnimation.class,
                GPacketPlayServerBed.class,
                GPacketPlayServerBlockAction.class,
                GPacketPlayServerBlockBreakAnimation.class,
                GPacketPlayServerBlockChange.class,
                GPacketPlayServerBlockChangeMulti.class,
                GPacketPlayServerChat.class,
                GPacketPlayServerChunkLoad.class,
                GPacketPlayServerChunkLoadBulk.class,
                GPacketPlayServerChunkUnload.class,
                GPacketPlayServerCustomPayload.class,
                GPacketPlayServerEntity.class,
                GPacketPlayServerEntity.GPacketPlayServerEntityLook.class,
                GPacketPlayServerEntity.GPacketPlayServerRelEntityMove.class,
                GPacketPlayServerEntity.GPacketPlayServerRelEntityMoveLook.class,
                GPacketPlayServerEntityAttach.class,
                GPacketPlayServerEntityDestroy.class,
                GPacketPlayServerEntityEffect.class,
                GPacketPlayServerEntityEffectRemove.class,
                GPacketPlayServerEntityEquipment.class,
                GPacketPlayServerEntityHeadRotation.class,
                GPacketPlayServerEntityMetadata.class,
                GPacketPlayServerEntityStatus.class,
                GPacketPlayServerEntityTeleport.class,
                GPacketPlayServerEntityVelocity.class,
                GPacketPlayServerExplosion.class,
                GPacketPlayServerHeldItemSlot.class,
                GPacketPlayServerKeepAlive.class,
                GPacketPlayServerKickDisconnect.class,
                GPacketPlayServerLogin.class,
                GPacketPlayServerPosition.class,
                GPacketPlayServerResourcePackSend.class,
                GPacketPlayServerRespawn.class,
                GPacketPlayServerSpawnEntityWeather.class,
                GPacketPlayServerSpawnNamedEntity.class,
                GPacketPlayServerSpawnObject.class,
                GPacketPlayServerTabComplete.class,
                GPacketPlayServerTransaction.class,
                GPacketPlayServerUpdateAttributes.class,
                GPacketPlayServerVehicleMove.class,
                GPacketPlayServerWindowClose.class,
                GPacketPlayServerWindowOpen.class
        );


        for (Class<? extends GPacket> packet : packets) {
            final PacketLink packetLink = packet.getAnnotation(PacketLink.class);

            translationMap.put(packetLink.value(), packet);
        }

        for (EnumProtocolFormat value : version.getFormatMap().values()) {
            for (PacketInfo packetInfo : value.getInboundPackets().values()) {
                idMap.put(packetInfo.getClazz(), packetInfo.getId());
            }

            for (PacketInfo packetInfo : value.getOutboundPackets().values()) {
                idMap.put(packetInfo.getClazz(), packetInfo.getId());
            }
        }
    }

    @Override
    public void dispose() {
        translationMap.clear();
        constructorMap.clear();
    }

}
