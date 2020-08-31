package cc.ghast.packet.protocol;

import cc.ghast.packet.exceptions.AlreadyConsumedPacketIdException;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.handshake.PacketHandshakeClientSetProtocol;
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
            this.addPacket(ProtocolDirection.IN, PacketHandshakeClientSetProtocol.class);
        }
    },

    PLAY(0) {;
        {
            // Server
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerKeepAlive.class); // 0
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerLogin.class); // 1
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerChat.class); // 2
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerUpdateTime.class); // 3
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntityEquipment.class); // 4
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSpawnPosition.class); // 5
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerUpdateHealth.class); // 6
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerRespawn.class); // 7
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerPosition.class); // 8
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerHeldItemSlot.class); // 9
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerBed.class); // 10
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerAnimation.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerNamedEntitySpawn.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerCollect.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSpawnEntity.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSpawnEntityLiving.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSpawnEntityPainting.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSpawnEntityExperienceOrb.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntityVelocity.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntityDestroy.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntity.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntity.PacketPlayServerRelEntityMove.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntity.PacketPlayServerEntityLook.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntity.PacketPlayServerRelEntityMoveLook.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntityTeleport.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntityHeadRotation.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntityStatus.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerAttachEntity.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntityMetadata.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerEntityEffect.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerExperience.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerUpdateAttributes.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerMapChunk.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerMultiBlockChange.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerBlockChange.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerBlockBreakAnimation.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerMapChunkBulk.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerExplosion.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerWorldEvent.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerNamedSoundEffect.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerWorldParticles.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerGameStateChange.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSpawnEntityWeather.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerOpenWindow.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerCloseWindow.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSetSlot.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerWindowItems.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerWindowData.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerTransaction.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerUpdateSign.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerMap.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerTileEntityData.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerOpenSignEditor.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerStatistic.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerPlayerInfo.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerAbilities.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerTabComplete.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerScoreboardObjective.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerScoreboardScore.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerScoreboardDisplayObjective.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerScoreboardTeam.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerCustomPayload.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerKickDisconnect.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerServerDifficulty.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerCombatEvent.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerCamera.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerWorldBorder.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerTitle.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerSetCompression.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerPlayerListHeaderFooter.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerResourcePackSend.class);
            this.addPacket(ProtocolDirection.OUT, PacketPlayServerUpdateEntityNBT.class);

            // Client
            this.addPacket(ProtocolDirection.IN, PacketPlayClientKeepAlive.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientChat.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientUseEntity.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientFlying.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientFlying.PacketPlayClientPosition.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientFlying.PacketPlayClientLook.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientFlying.PacketPlayClientPositionLook.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientBlockDig.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientBlockPlace.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientHeldItemSlot.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientArmAnimation.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientEntityAction.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientSteerVehicle.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientCloseWindow.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientWindowClick.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientTransaction.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientSetCreativeSlot.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientEnchantItem.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientUpdateSign.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientAbilities.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientTabComplete.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientSettings.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientClientCommand.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientCustomPayload.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientSpectate.class);
            this.addPacket(ProtocolDirection.IN, PacketPlayClientResourcePackStatus.class);

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
            this.addPacket(ProtocolDirection.OUT, PacketLoginServerDisconnect.class);
            this.addPacket(ProtocolDirection.OUT, PacketLoginServerEncryptionBegin.class);
            this.addPacket(ProtocolDirection.OUT, PacketLoginServerSuccess.class);
            this.addPacket(ProtocolDirection.OUT, PacketLoginServerSetCompression.class);
            this.addPacket(ProtocolDirection.IN, PacketLoginClientStart.class);
            this.addPacket(ProtocolDirection.IN, PacketLoginClientEncryptionBegin.class);
        }
    };

    // SERVERBOUND = IN


    private int i;

    private final Map<ProtocolDirection, BiMap<Integer, Class<? extends Packet<?>>>> packetMap;

    EnumProtocolLegacy(int i) {
        this.i = i;
        this.packetMap = new EnumMap<>(ProtocolDirection.class);
    }

    public EnumProtocolLegacy addPacket(ProtocolDirection enumProtocolDirection, Class<? extends Packet<?>> clazz) {
        BiMap<Integer, Class<? extends Packet<?>>> object = this.packetMap.get(enumProtocolDirection);

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
    public Packet<?> getPacket(ProtocolDirection enumProtocolDirection, int i, UUID player, ProtocolVersion version) {
        Class<? extends Packet<?>> clazz = this.packetMap.get(enumProtocolDirection).get(i);
        return clazz == null ? null : clazz.getConstructor(UUID.class, ProtocolVersion.class).newInstance(player, version);
    }

    @Override
    public int getPacketId(ProtocolDirection direction, Packet<?> packet) {
        return this.packetMap.get(direction).inverse().get(packet.getClass());
    }

    @Override
    public Class<? extends Packet<?>> getPacketClass(ProtocolDirection direction, String name) {
        return this.packetMap.get(direction).get(i);
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }
}
