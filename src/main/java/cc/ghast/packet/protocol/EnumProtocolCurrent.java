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

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ghast
 * @since 18/08/2020
 * Artemis © 2020
 */

@Getter
public enum EnumProtocolCurrent implements EnumProtocol {
    HANDSHAKE(-1, new Pair[][]{
            // Client
            new Pair[]{
                new Pair<>(PacketHandshakeClientSetProtocol.class, "PacketHandshakeInSetProtocol")
            },

            // Server
            new Pair[]{}

    }),
    PLAY(0, new Pair[][]{
            // Client
            new Pair[]{
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
                new Pair<>(PacketPlayClientClientCommand.class, "PacketPlayInClientCommand"),
                new Pair<>(PacketPlayClientCustomPayload.class, "PacketPlayInCustomPayload"),
                new Pair<>(PacketPlayClientSpectate.class, "PacketPlayInSpectate"),
                new Pair<>(PacketPlayClientResourcePackStatus.class, "PacketPlayInResourcePackStatus"),
            },

            // Server
            new Pair[]{
                new Pair<>(PacketPlayServerKeepAlive.class, "PacketPlayOutKeepAlive"), // 0
                new Pair<>(PacketPlayServerLogin.class, "PacketPlayOutLogin"), // 1
                new Pair<>(PacketPlayServerChat.class, "PacketPlayOutChat"), // 2
                new Pair<>(PacketPlayServerUpdateTime.class, "PacketPlayOutUpdateTime"), // 3
                new Pair<>(PacketPlayServerEntityEquipment.class, "PacketPlayOutEntityEquipment"), // 4
                new Pair<>(PacketPlayServerSpawnPosition.class, "PacketPlayOutSpawnPosition"), // 5
                new Pair<>(PacketPlayServerUpdateHealth.class, "PacketPlayOutUpdateHealth"), // 6
                new Pair<>(PacketPlayServerRespawn.class, "PacketPlayOutRespawn"), // 7
                new Pair<>(PacketPlayServerPosition.class, "PacketPlayOutPosition"), // 8
                new Pair<>(PacketPlayServerHeldItemSlot.class, "PacketPlayOutHeldItemSlot"), // 9
                new Pair<>(PacketPlayServerBed.class, "PacketPlayOutBed"), // 10
                new Pair<>(PacketPlayServerAnimation.class, "PacketPlayOutAnimation"),
                new Pair<>(PacketPlayServerNamedEntitySpawn.class, "PacketPlayOutNamedEntitySpawn"),
                new Pair<>(PacketPlayServerCollect.class, "PacketPlayOutCollect"),
                new Pair<>(PacketPlayServerSpawnEntity.class, "PacketPlayOutSpawnEntity"),
                new Pair<>(PacketPlayServerSpawnEntityLiving.class, "PacketPlayOutSpawnEntityLiving"),
                new Pair<>(PacketPlayServerSpawnEntityPainting.class, "PacketPlayOutSpawnEntityPainting"),
                new Pair<>(PacketPlayServerSpawnEntityExperienceOrb.class, "PacketPlayOutSpawnEntityExperienceOrb"),
                new Pair<>(PacketPlayServerEntityVelocity.class, "PacketPlayOutEntityVelocity"),
                new Pair<>(PacketPlayServerEntityDestroy.class, "PacketPlayOutEntityDestroy"),
                new Pair<>(PacketPlayServerEntity.class, "PacketPlayOutEntity"),
                new Pair<>(PacketPlayServerEntity.PacketPlayServerRelEntityMove.class,
                        ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_8)
                        ? "PacketPlayOutEntity$PacketPlayOutRelEntityMove" : "PacketPlayOutRelEntityMove"),
                new Pair<>(PacketPlayServerEntity.PacketPlayServerEntityLook.class,
                        ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_8)
                                ? "PacketPlayOutEntity$PacketPlayOutEntityLook" : "PacketPlayOutEntityLook"),
                new Pair<>(PacketPlayServerEntity.PacketPlayServerRelEntityMoveLook.class,
                        ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_8)
                                ? "PacketPlayOutEntity$PacketPlayOutRelEntityMove" : "PacketPlayOutRelEntityMoveLook"),
                new Pair<>(PacketPlayServerEntityTeleport.class, "PacketPlayOutEntityTeleport"),
                new Pair<>(PacketPlayServerEntityHeadRotation.class, "PacketPlayOutEntityHeadRotation"),
                new Pair<>(PacketPlayServerEntityStatus.class, "PacketPlayOutEntityStatus"),
                new Pair<>(PacketPlayServerAttachEntity.class, "PacketPlayOutAttachEntity"),
                new Pair<>(PacketPlayServerEntityMetadata.class, "PacketPlayOutEntityMetadata"),
                new Pair<>(PacketPlayServerEntityEffect.class, "PacketPlayOutEntityEffect"),
                new Pair<>(PacketPlayServerExperience.class, "PacketPlayOutExperience"),
                new Pair<>(PacketPlayServerUpdateAttributes.class, "PacketPlayOutUpdateAttributes"),
                new Pair<>(PacketPlayServerMapChunk.class, "PacketPlayOutMapChunk"),
                new Pair<>(PacketPlayServerMultiBlockChange.class, "PacketPlayOutMultiBlockChange"),
                new Pair<>(PacketPlayServerBlockChange.class, "PacketPlayOutBlockChange"),
                new Pair<>(PacketPlayServerBlockBreakAnimation.class, "PacketPlayOutBlockBreakAnimation"),
                new Pair<>(PacketPlayServerMapChunkBulk.class, "PacketPlayOutMapChunkBulk"),
                new Pair<>(PacketPlayServerExplosion.class, "PacketPlayOutExplosion"),
                new Pair<>(PacketPlayServerWorldEvent.class, "PacketPlayOutWorldEvent"),
                new Pair<>(PacketPlayServerNamedSoundEffect.class, "PacketPlayOutNamedSoundEffect"),
                new Pair<>(PacketPlayServerWorldParticles.class, "PacketPlayOutWorldParticles"),
                new Pair<>(PacketPlayServerGameStateChange.class, "PacketPlayOutGameStateChange"),
                new Pair<>(PacketPlayServerSpawnEntityWeather.class, "PacketPlayOutSpawnEntityWeather"),
                new Pair<>(PacketPlayServerOpenWindow.class, "PacketPlayOutOpenWindow"),
                new Pair<>(PacketPlayServerCloseWindow.class, "PacketPlayOutCloseWindow"),
                new Pair<>(PacketPlayServerSetSlot.class, "PacketPlayOutSetSlot"),
                new Pair<>(PacketPlayServerWindowItems.class, "PacketPlayOutWindowItems"),
                new Pair<>(PacketPlayServerWindowData.class, "PacketPlayOutWindowData"),
                new Pair<>(PacketPlayServerTransaction.class, "PacketPlayOutTransaction"),
                new Pair<>(PacketPlayServerUpdateSign.class, "PacketPlayOutUpdateSign"),
                new Pair<>(PacketPlayServerMap.class, "PacketPlayOutMap"),
                new Pair<>(PacketPlayServerTileEntityData.class, "PacketPlayOutTileEntityData"),
                new Pair<>(PacketPlayServerOpenSignEditor.class, "PacketPlayOutOpenSignEditor"),
                new Pair<>(PacketPlayServerStatistic.class, "PacketPlayOutStatistic"),
                new Pair<>(PacketPlayServerPlayerInfo.class, "PacketPlayOutPlayerInfo"),
                new Pair<>(PacketPlayServerAbilities.class, "PacketPlayOutAbilities"),
                new Pair<>(PacketPlayServerTabComplete.class, "PacketPlayOutTabComplete"),
                new Pair<>(PacketPlayServerScoreboardObjective.class, "PacketPlayOutScoreboardObjective"),
                new Pair<>(PacketPlayServerScoreboardScore.class, "PacketPlayOutScoreboardScore"),
                new Pair<>(PacketPlayServerScoreboardDisplayObjective.class, "PacketPlayOutScoreboardDisplayObjective"),
                new Pair<>(PacketPlayServerScoreboardTeam.class, "PacketPlayOutScoreboardTeam"),
                new Pair<>(PacketPlayServerCustomPayload.class, "PacketPlayOutCustomPayload"),
                new Pair<>(PacketPlayServerKickDisconnect.class, "PacketPlayOutKickDisconnect"),
                new Pair<>(PacketPlayServerServerDifficulty.class, "PacketPlayOutServerDifficulty"),
                new Pair<>(PacketPlayServerCombatEvent.class, "PacketPlayOutCombatEvent"),
                new Pair<>(PacketPlayServerCamera.class, "PacketPlayOutCamera"),
                new Pair<>(PacketPlayServerWorldBorder.class, "PacketPlayOutWorldBoarder"),
                new Pair<>(PacketPlayServerTitle.class, "PacketPlayOutTitle"),
                new Pair<>(PacketPlayServerSetCompression.class, "PacketPlayOutSetCompression"),
                new Pair<>(PacketPlayServerPlayerListHeaderFooter.class, "PacketPlayOutPlayerListHeaderFooter"),
                new Pair<>(PacketPlayServerResourcePackSend.class, "PacketPlayOutResourcePackSend"),
                new Pair<>(PacketPlayServerUpdateEntityNBT.class, "PacketPlayOutUpdateEntityNBT"),
            }

    }),
    STATUS(1, new Pair[][]{
            // Client
            new Pair[]{
                new Pair<>(PacketStatusClientStart.class, "PacketStatusInStart"),
                new Pair<>(PacketStatusClientPing.class, "PacketStatusInPing")
            },

            // Server
            new Pair[]{
                new Pair<>(PacketStatusServerInfoServer.class, "PacketStatusOutInfoServer"),
                new Pair<>(PacketStatusServerPing.class, "PacketStatusOutPing")
            }
    }),
    LOGIN(2, new Pair[][]{
            // Client
            new Pair[]{
                new Pair<>(PacketLoginClientStart.class, "PacketLoginInStart"),
                new Pair<>(PacketLoginClientEncryptionBegin.class, "PacketLoginInEncryptionBegin")
            },

            // Server
            new Pair[]{
                new Pair<>(PacketLoginServerDisconnect.class, "PacketLoginOutDisconnect"),
                new Pair<>(PacketLoginServerEncryptionBegin.class, "PacketLoginOutEncryptionBegin"),
                new Pair<>(PacketLoginServerSuccess.class, "PacketLoginOutSuccess"),
                new Pair<>(PacketLoginServerSetCompression.class, "PacketLoginOutSetCompression"),
            }
    });

    private final int id;

    // Direction = Ordinal so 0 = IN and 1 = OUT
    // Second bit is just the id lol
    private final Pair<Class<? extends Packet<?>>, String>[][] packets;
    private final Map<ProtocolDirection, Map<Integer, Class<? extends Packet<?>>>> packetMap;

    EnumProtocolCurrent(int id, Pair<Class<? extends Packet<?>>, String>[][] packets) {
        this.id = id;
        this.packets = packets;
        this.packetMap = ReflectUtil.getPacketMap(this);
    }

    @Override
    @SneakyThrows
    public Packet<?> getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) {
        Class<? extends Packet<?>> clazz = packetMap.get(direction).get(id);
        return clazz.getConstructor(UUID.class, ProtocolVersion.class).newInstance(playerId, version);
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }

    @Override
    public Class<? extends Packet<?>> getPacketClass(ProtocolDirection direction, String name){
        for (Pair<Class<? extends Packet<?>>, String> pair : packets[direction.ordinal()]) {
            if (pair.getV().equalsIgnoreCase(name)) {
                return pair.getK();
            }
        }
        return null;
    }
    
}
