package cc.ghast.packet.protocol;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.reflections.ReflectUtil;
import cc.ghast.packet.utils.Pair;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.handshake.PacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.login.*;
import cc.ghast.packet.wrapper.packet.play.client.*;
import cc.ghast.packet.wrapper.packet.play.server.*;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientPing;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientStart;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerInfoServer;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerPing;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.UUID;

/**
 * @author Ghast
 * @since 18/08/2020
 * Artemis © 2020
 */

@Getter
public enum ArtemisEnumProtocol implements EnumProtocol {
    HANDSHAKE(-1, new Pair[][]{
            // Client
            {
                new Pair<>(PacketHandshakeClientSetProtocol.class, "PacketHandshakeInSetProtocol")
            },

            // Server
            {}

    }),
    PLAY(0, new Pair[][]{
            // Client
            {
                new Pair<>(PacketPlayClientKeepAlive.class, "PacketPlayInKeepAlive"),
                new Pair<>(PacketPlayClientChat.class, "PacketPlayInChat"),
                new Pair<>(PacketPlayClientUseEntity.class, "PacketPlayInUseEntity"),
                new Pair<>(PacketPlayClientFlying.class, "PacketPlayInFlying"),
                new Pair<>(PacketPlayClientFlying.PacketPlayClientPosition.class,
                        ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_8)
                                ? "PacketPlayInFlying$PacketPlayInPosition" : "PacketPlayInPosition"),
                new Pair<>(PacketPlayClientFlying.PacketPlayClientLook.class,
                        ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_8)
                        ? "PacketPlayInFlying$PacketPlayInLook" : "PacketPlayInLook"),
                new Pair<>(PacketPlayClientFlying.PacketPlayClientPositionLook.class,
                        ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_8)
                        ? "PacketPlayInFlying$PacketPlayInPositionLook" : "PacketPlayInPositionLook"),
                new Pair<>(PacketPlayClientBlockDig .class, "PacketPlayInBlockDig"),
                new Pair<>(PacketPlayClientBlockPlace.class, "PacketPlayInBlockPlace"),
                new Pair<>(PacketPlayClientHeldItemSlot.class, "PacketPlayInHeldItemSlot"),
                new Pair<>(PacketPlayClientArmAnimation.class, "PacketPlayInArmAnimation"),
                new Pair<>(PacketPlayClientEntityAction.class, "PacketPlayInEntityAction"),
                new Pair<>(PacketPlayClientSteerVehicle.class, "PacketPlayInSteerVehicle"),
                new Pair<>(PacketPlayClientCloseWindow.class, "PacketPlayInCloseWindow"),
                new Pair<>(PacketPlayClientWindowClick.class, "PacketPlayInWindowClick"),
                new Pair<>(PacketPlayClientTransaction.class, "PacketPlayInTransaction"),
                new Pair<>(PacketPlayClientSetCreativeSlot.class, "PacketPlayInSetCreativeSlot"),
                new Pair<>(PacketPlayClientEnchantItem.class, "PacketPlayInEnchantItem"),
                new Pair<>(PacketPlayClientUpdateSign.class, "PacketPlayInUpdateSign"),
                new Pair<>(PacketPlayClientAbilities.class, "PacketPlayInAbilities"),
                new Pair<>(PacketPlayClientTabComplete.class, "PacketPlayInTabComplete"),
                new Pair<>(PacketPlayClientSettings.class, "PacketPlayInSettings"),
                new Pair<>(PacketPlayClientClientCommand.class, ""),
                new Pair<>(PacketPlayClientCustomPayload.class, ""),
                new Pair<>(PacketPlayClientSpectate.class, ""),
                new Pair<>(PacketPlayClientResourcePackStatus.class, ""),
            },

            // Server
            new Pair[]{
                new Pair<>(PacketPlayServerKeepAlive.class, ""), // 0
                new Pair<>(PacketPlayServerLogin .class, ""), // 1
                new Pair<>(PacketPlayServerChat .class, ""), // 2
                new Pair<>(PacketPlayServerUpdateTime .class, ""), // 3
                new Pair<>(PacketPlayServerEntityEquipment .class, ""), // 4
                new Pair<>(PacketPlayServerSpawnPosition.class, ""), // 5
                new Pair<>(PacketPlayServerUpdateHealth.class, ""), // 6
                new Pair<>(PacketPlayServerRespawn.class, ""), // 7
                new Pair<>(PacketPlayServerPosition.class, ""), // 8
                new Pair<>(PacketPlayServerHeldItemSlot.class, ""), // 9
                new Pair<>(PacketPlayServerBed.class, ""), // 10
                new Pair<>(PacketPlayServerAnimation.class, ""),
                new Pair<>(PacketPlayServerNamedEntitySpawn.class, ""),
                new Pair<>(PacketPlayServerCollect.class, ""),
                new Pair<>(PacketPlayServerSpawnEntity.class, ""),
                new Pair<>(PacketPlayServerSpawnEntityLiving.class, ""),
                new Pair<>(PacketPlayServerSpawnEntityPainting.class, ""),
                new Pair<>(PacketPlayServerSpawnEntityExperienceOrb.class, ""),
                new Pair<>(PacketPlayServerEntityVelocity.class, ""),
                new Pair<>(PacketPlayServerEntityDestroy.class, ""),
                new Pair<>(PacketPlayServerEntity.class, ""),
                new Pair<>(PacketPlayServerEntity.PacketPlayServerRelEntityMove.class, ""),
                new Pair<>(PacketPlayServerEntity.PacketPlayServerEntityLook.class, ""),
                new Pair<>(PacketPlayServerEntity.PacketPlayServerRelEntityMoveLook.class, ""),
                new Pair<>(PacketPlayServerEntityTeleport.class, ""),
                new Pair<>(PacketPlayServerEntityHeadRotation.class, ""),
                new Pair<>(PacketPlayServerEntityStatus.class, ""),
                new Pair<>(PacketPlayServerAttachEntity.class, ""),
                new Pair<>(PacketPlayServerEntityMetadata.class, ""),
                new Pair<>(PacketPlayServerEntityEffect.class, ""),
                new Pair<>(PacketPlayServerExperience.class, ""),
                new Pair<>(PacketPlayServerUpdateAttributes.class, ""),
                new Pair<>(PacketPlayServerMapChunk.class, ""),
                new Pair<>(PacketPlayServerMultiBlockChange.class, ""),
                new Pair<>(PacketPlayServerBlockChange.class, ""),
                new Pair<>(PacketPlayServerBlockBreakAnimation.class, ""),
                new Pair<>(PacketPlayServerMapChunkBulk.class, ""),
                new Pair<>(PacketPlayServerExplosion.class, ""),
                new Pair<>(PacketPlayServerWorldEvent.class, ""),
                new Pair<>(PacketPlayServerNamedSoundEffect.class, ""),
                new Pair<>(PacketPlayServerWorldParticles.class, ""),
                new Pair<>(PacketPlayServerGameStateChange.class, ""),
                new Pair<>(PacketPlayServerSpawnEntityWeather.class, ""),
                new Pair<>(PacketPlayServerOpenWindow.class, ""),
                new Pair<>(PacketPlayServerCloseWindow.class, ""),
                new Pair<>(PacketPlayServerSetSlot.class, ""),
                new Pair<>(PacketPlayServerWindowItems.class, ""),
                new Pair<>(PacketPlayServerWindowData.class, ""),
                new Pair<>(PacketPlayServerTransaction.class, ""),
                new Pair<>(PacketPlayServerUpdateSign.class, ""),
                new Pair<>(PacketPlayServerMap.class, ""),
                new Pair<>(PacketPlayServerTileEntityData.class, ""),
                new Pair<>(PacketPlayServerOpenSignEditor.class, ""),
                new Pair<>(PacketPlayServerStatistic.class, ""),
                new Pair<>(PacketPlayServerPlayerInfo.class, ""),
                new Pair<>(PacketPlayServerAbilities.class, ""),
                new Pair<>(PacketPlayServerTabComplete.class, ""),
                new Pair<>(PacketPlayServerScoreboardObjective.class, ""),
                new Pair<>(PacketPlayServerScoreboardScore.class, ""),
                new Pair<>(PacketPlayServerScoreboardDisplayObjective.class, ""),
                new Pair<>(PacketPlayServerScoreboardTeam.class, ""),
                new Pair<>(PacketPlayServerCustomPayload.class, ""),
                new Pair<>(PacketPlayServerKickDisconnect.class, ""),
                new Pair<>(PacketPlayServerServerDifficulty.class, ""),
                new Pair<>(PacketPlayServerCombatEvent.class, ""),
                new Pair<>(PacketPlayServerCamera.class, ""),
                new Pair<>(PacketPlayServerWorldBorder.class, ""),
                new Pair<>(PacketPlayServerTitle.class, ""),
                new Pair<>(PacketPlayServerSetCompression.class, ""),
                new Pair<>(PacketPlayServerPlayerListHeaderFooter.class, ""),
                new Pair<>(PacketPlayServerResourcePackSend.class, ""),
                new Pair<>(PacketPlayServerUpdateEntityNBT.class, ""),
            }

    }),
    STATUS(1, new Pair[][]{
            // Client
            {new Pair<>(PacketStatusClientStart.class, ""), new Pair<>(PacketStatusClientPing.class, "")},

            // Server
            {new Pair<>(PacketStatusServerInfoServer.class, ""), new Pair<>(PacketStatusServerPing.class, "")}
    }),
    LOGIN(2, new Pair[][]{
            // Client
            {new Pair<>(PacketLoginClientStart.class, ""), new Pair<>(PacketLoginClientEncryptionBegin.class, "")},

            // Server
            {
                new Pair<>(PacketLoginServerDisconnect.class, ""),
                new Pair<>(PacketLoginServerEncryptionBegin.class, ""),
                new Pair<>(PacketLoginServerSuccess.class, ""),
                new Pair<>(PacketLoginServerSetCompression.class, ""),
            }
    });

    private final int id;

    // Direction = Ordinal so 0 = IN and 1 = OUT
    // Second bit is just the id lol
    private final Pair<Class<? extends Packet<?>>, String>[][] packets;
    private final Map<EnumProtocolDirection, Map<Integer, Class<? extends Packet<?>>>> packetMap = ReflectUtil.getPacketMap(this);

    ArtemisEnumProtocol(int id, Pair<Class<? extends Packet<?>>, String>[][] packets) {
        this.id = id;
        this.packets = packets;
    }

    @Override
    @SneakyThrows
    public Packet<?> getPacket(EnumProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) {
        Class<? extends Packet<?>> clazz = packetMap.get(direction).get(id);
        return clazz.getConstructor(UUID.class, ProtocolVersion.class).newInstance(playerId, version);
    }
    
    public Class<? extends Packet<?>> getPacketClass(EnumProtocolDirection direction, String name){
        for (Pair<Class<? extends Packet<?>>, String> pair : packets[direction.ordinal()]) {
            if (pair.getV().equalsIgnoreCase(name)) {
                return pair.getK();
            }
        }
        return null;
    }
    
}
