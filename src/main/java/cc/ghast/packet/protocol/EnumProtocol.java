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

import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public enum EnumProtocol {
    HANDSHAKE(-1) {;
        {
            this.addPacket(EnumProtocolDirection.IN, PacketHandshakeClientSetProtocol.class);
        }
    },

    PLAY(0) {;
        {
            // Server
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerKeepAlive.class); // 0
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerLogin.class); // 1
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerChat.class); // 2
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerUpdateTime.class); // 3
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntityEquipment.class); // 4
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerSpawnPosition.class); // 5
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerUpdateHealth.class); // 6
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerRespawn.class); // 7
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerPosition.class); // 8
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerHeldItemSlot.class); // 9
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerBed.class); // 10
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerAnimation.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerNamedEntitySpawn.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerCollect.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerSpawnEntity.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerSpawnEntityLiving.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerSpawnEntityPainting.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerSpawnEntityExperienceOrb.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntityVelocity.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntityDestroy.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntity.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntity.PacketPlayServerRelEntityMove.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntity.PacketPlayServerEntityLook.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntity.PacketPlayServerRelEntityMoveLook.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntityTeleport.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntityHeadRotation.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntityStatus.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerAttachEntity.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntityMetadata.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerEntityEffect.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerExperience.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerUpdateAttributes.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerMapChunk.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerMultiBlockChange.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerBlockChange.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerBlockBreakAnimation.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerMapChunkBulk.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerExplosion.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerWorldEvent.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerNamedSoundEffect.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerWorldParticles.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerGameStateChange.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerSpawnEntityWeather.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerOpenWindow.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerCloseWindow.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerSetSlot.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerWindowItems.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerWindowData.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerTransaction.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerUpdateSign.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerMap.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerTileEntityData.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerOpenSignEditor.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerStatistic.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerPlayerInfo.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerAbilities.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerTabComplete.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerScoreboardObjective.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerScoreboardScore.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerScoreboardDisplayObjective.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerScoreboardTeam.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerCustomPayload.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerKickDisconnect.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerServerDifficulty.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerCombatEvent.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerCamera.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerWorldBorder.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerTitle.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerSetCompression.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerPlayerListHeaderFooter.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerResourcePackSend.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketPlayServerUpdateEntityNBT.class);

            // Client
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientKeepAlive.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientChat.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientUseEntity.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientFlying.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientFlying.PacketPlayClientPosition.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientFlying.PacketPlayClientLook.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientFlying.PacketPlayClientPositionLook.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientBlockDig.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientBlockPlace.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientHeldItemSlot.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientArmAnimation.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientEntityAction.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientSteerVehicle.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientCloseWindow.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientWindowClick.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientTransaction.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientSetCreativeSlot.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientEnchantItem.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientUpdateSign.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientAbilities.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientTabComplete.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientSettings.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientClientCommand.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientCustomPayload.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientSpectate.class);
            this.addPacket(EnumProtocolDirection.IN, PacketPlayClientResourcePackStatus.class);

        }

    },

    STATUS(1) {;
        {
            this.addPacket(EnumProtocolDirection.IN, PacketStatusClientStart.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketStatusServerInfoServer.class);
            this.addPacket(EnumProtocolDirection.IN, PacketStatusClientPing.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketStatusServerPing.class);
        }
    },

    LOGIN(2) {;
        {
            this.addPacket(EnumProtocolDirection.OUT, PacketLoginServerDisconnect.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketLoginServerEncryptionBegin.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketLoginServerSuccess.class);
            this.addPacket(EnumProtocolDirection.OUT, PacketLoginServerSetCompression.class);
            this.addPacket(EnumProtocolDirection.IN, PacketLoginClientStart.class);
            this.addPacket(EnumProtocolDirection.IN, PacketLoginClientEncryptionBegin.class);
        }
    };

    // SERVERBOUND = IN


    private int i;

    private final Map<EnumProtocolDirection, BiMap<Integer, Class<? extends Packet>>> packetMap;

    EnumProtocol(int i) {
        this.i = i;
        this.packetMap = new EnumMap<>(EnumProtocolDirection.class);
    }

    public EnumProtocol addPacket(EnumProtocolDirection enumProtocolDirection, Class<? extends Packet> clazz) {
        System.out.println("ADDED PACKET " + clazz);
        BiMap<Integer, Class<? extends Packet>> object = this.packetMap.get(enumProtocolDirection);

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

    public Packet getPacket(EnumProtocolDirection enumProtocolDirection, int i, UUID player, ProtocolVersion version)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<? extends Packet> clazz = this.packetMap.get(enumProtocolDirection).get(i);
        return clazz == null ? null : clazz.getConstructor(UUID.class, ProtocolVersion.class).newInstance(player, version);
    }
}
