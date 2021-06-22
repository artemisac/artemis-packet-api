package cc.ghast.packet.protocol;

import cc.ghast.packet.exceptions.AlreadyConsumedPacketIdException;
import cc.ghast.packet.nms.ProtocolVersion;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.handshake.GPacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.login.*;
import cc.ghast.packet.wrapper.packet.play.client.*;
import cc.ghast.packet.wrapper.packet.play.server.*;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientPing;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientStart;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerInfoServer;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerPing;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.SneakyThrows;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public enum EnumProtocolLegacy implements EnumProtocol {
    HANDSHAKE(-1) {;
        {
            this.addPacket(ProtocolDirection.IN, GPacketHandshakeClientSetProtocol.class);
        }
    },

    PLAY(0) {;
        {
            // Server
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerKeepAlive.class); // 0
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerLogin.class); // 1
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerChat.class); // 2
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerUpdateTime.class); // 3
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntityEquipment.class); // 4
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSpawnPosition.class); // 5
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerUpdateHealth.class); // 6
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerRespawn.class); // 7
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerPosition.class); // 8
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerHeldItemSlot.class); // 9
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerBed.class); // 10
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerAnimation.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerSpawnNamedEntity.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerCollect.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerSpawnObject.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSpawnEntityLiving.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSpawnEntityPainting.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSpawnEntityExperienceOrb.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntityVelocity.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntityDestroy.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntity.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntity.GPacketPlayServerRelEntityMove.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntity.GPacketPlayServerEntityLook.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntity.GPacketPlayServerRelEntityMoveLook.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntityTeleport.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntityHeadRotation.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntityStatus.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntityAttach.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntityMetadata.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerEntityEffect.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerExperience.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerUpdateAttributes.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerChunkLoad.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerBlockChangeMulti.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerBlockChange.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerBlockBreakAnimation.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerChunkLoadBulk.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerExplosion.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerWorldEvent.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSoundEffectNamed.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerWorldParticles.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerGameStateChange.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerSpawnEntityWeather.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerWindowOpen.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerWindowClose.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSetSlot.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerWindowItems.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerWindowData.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerTransaction.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerUpdateSign.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerMap.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerTileEntityData.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerOpenSignEditor.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerStatistic.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerPlayerInfo.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerAbilities.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerTabComplete.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerScoreboardObjective.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerScoreboardScore.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerScoreboardDisplayObjective.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerScoreboardTeam.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerCustomPayload.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerKickDisconnect.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerServerDifficulty.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerCombatEvent.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerCamera.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerWorldBorder.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerTitle.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSetCompression.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerPlayerListHeaderFooter.class);
            this.addPacket(ProtocolDirection.OUT, GPacketPlayServerResourcePackSend.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerUpdateEntityNBT.class);

            // Client
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientKeepAlive.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientChat.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientUseEntity.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientFlying.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientFlying.PacketPlayClientPosition.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientFlying.PacketPlayClientLook.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientFlying.PacketPlayClientPositionLook.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientBlockDig.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientBlockPlace.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientHeldItemSlot.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientArmAnimation.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientEntityAction.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientSteerVehicle.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientWindowClose.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientWindowClick.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientTransaction.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientSetCreativeSlot.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientEnchantItem.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientUpdateSign.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientAbilities.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientTabComplete.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientSettings.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientClientCommand.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientCustomPayload.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientSpectate.class);
            this.addPacket(ProtocolDirection.IN, GPacketPlayClientResourcePackStatus.class);

        }

    },

    STATUS(1) {;
        {
            this.addPacket(ProtocolDirection.IN, PacketStatusClientStart.class);
            this.addPacket(ProtocolDirection.OUT, PacketStatusServerInfoServer.class);
            this.addPacket(ProtocolDirection.IN, PacketStatusClientPing.class);
            this.addPacket(ProtocolDirection.OUT, PacketStatusServerPing.class);
        }
    },

    LOGIN(2) {;
        {
            this.addPacket(ProtocolDirection.OUT, GPacketLoginServerDisconnect.class);
            this.addPacket(ProtocolDirection.OUT, GPacketLoginServerEncryptionBegin.class);
            this.addPacket(ProtocolDirection.OUT, GPacketLoginServerSuccess.class);
            this.addPacket(ProtocolDirection.OUT, GPacketLoginServerSetCompression.class);
            this.addPacket(ProtocolDirection.IN, GPacketLoginClientStart.class);
            this.addPacket(ProtocolDirection.IN, GPacketLoginClientEncryptionBegin.class);
        }
    };

    // SERVERBOUND = IN


    private int i;

    private final Map<ProtocolDirection, BiMap<Integer, Class<? extends GPacket>>> packetMap;

    EnumProtocolLegacy(int i) {
        this.i = i;
        this.packetMap = new EnumMap<>(ProtocolDirection.class);
    }

    public EnumProtocolLegacy addPacket(ProtocolDirection enumProtocolDirection, Class<? extends GPacket> clazz) {
        BiMap<Integer, Class<? extends GPacket>> object = this.packetMap.get(enumProtocolDirection);

        if (object == null) {
            object = HashBiMap.create();
            this.packetMap.put(enumProtocolDirection, object);
        }

        if (object.containsValue(clazz)) {
            throw new AlreadyConsumedPacketIdException(enumProtocolDirection, clazz, object.inverse().get(clazz));
        } else {
            object.put(object.size(), clazz);
            return this;
        }
    }



    @SneakyThrows
    public GPacket getPacket(ProtocolDirection enumProtocolDirection, int i, UUID player, ProtocolVersion version) {
        Class<? extends GPacket> clazz = this.packetMap.get(enumProtocolDirection).get(i);
        return clazz == null ? null : clazz.getConstructor(UUID.class, ProtocolVersion.class).newInstance(player, version);
    }

    @Override
    public int getPacketId(ProtocolDirection direction, GPacket packet) {
        return this.packetMap.get(direction).inverse().get(packet.getClass());
    }

    @Override
    public Class<? extends GPacket> getPacketClass(ProtocolDirection direction, String name) {
        return this.packetMap.get(direction).get(i);
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }

    @Override
    public EnumProtocol[] getValues() {
        return values();
    }


}
