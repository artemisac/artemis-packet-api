package cc.ghast.packet.protocol;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.reflections.ReflectUtil;
import cc.ghast.packet.utils.PacketUtil;
import cc.ghast.packet.utils.Pair;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.PacketInformation;
import cc.ghast.packet.wrapper.packet.handshake.GPacketHandshakeClientSetProtocol;
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
public enum EnumProtocolCurrent implements EnumProtocol {
    HANDSHAKE(-1, new Pair[][]{
            // Client
            new Pair[]{
                new Pair<>(GPacketHandshakeClientSetProtocol.class, new PacketInformation("PacketHandshakingInSetProtocol"))
            },

            // Server
            new Pair[]{}

    }),
    PLAY(0, new Pair[][]{
            // Client
            new Pair[]{
                new Pair<>(GPacketPlayClientKeepAlive.class, new PacketInformation("PacketPlayInKeepAlive")),
                new Pair<>(GPacketPlayClientChat.class, new PacketInformation("PacketPlayInChat")),
                new Pair<>(GPacketPlayClientUseEntity.class, new PacketInformation("PacketPlayInUseEntity")),
                new Pair<>(GPacketPlayClientFlying.class, new PacketInformation("PacketPlayInFlying")),
                new Pair<>(GPacketPlayClientFlying.PacketPlayClientPosition.class,new PacketInformation("PacketPlayInPosition")),
                new Pair<>(GPacketPlayClientFlying.PacketPlayClientLook.class,new PacketInformation("PacketPlayInLook")),
                new Pair<>(GPacketPlayClientFlying.PacketPlayClientPositionLook.class,new PacketInformation("PacketPlayInPositionLook")),
                new Pair<>(GPacketPlayClientBlockDig.class, new PacketInformation("PacketPlayInBlockDig")),
                new Pair<>(GPacketPlayClientBlockPlace.class, new PacketInformation(PacketUtil.BLOCK_PLACE)),
                new Pair<>(GPacketPlayClientHeldItemSlot.class, new PacketInformation("PacketPlayInHeldItemSlot")),
                new Pair<>(GPacketPlayClientArmAnimation.class, new PacketInformation("PacketPlayInArmAnimation")),
                new Pair<>(GPacketPlayClientEntityAction.class, new PacketInformation("PacketPlayInEntityAction")),
                new Pair<>(GPacketPlayClientSteerVehicle.class, new PacketInformation("PacketPlayInSteerVehicle")),
                new Pair<>(GPacketPlayClientWindowClose.class, new PacketInformation("PacketPlayInCloseWindow")),
                new Pair<>(GPacketPlayClientWindowClick.class, new PacketInformation("PacketPlayInWindowClick")),
                new Pair<>(GPacketPlayClientTransaction.class, new PacketInformation("PacketPlayInTransaction")),
                new Pair<>(GPacketPlayClientSetCreativeSlot.class, new PacketInformation("PacketPlayInSetCreativeSlot")),
                new Pair<>(GPacketPlayClientEnchantItem.class, new PacketInformation("PacketPlayInEnchantItem")),
                new Pair<>(GPacketPlayClientUpdateSign.class, new PacketInformation("PacketPlayInUpdateSign")),
                new Pair<>(GPacketPlayClientAbilities.class, new PacketInformation("PacketPlayInAbilities")),
                new Pair<>(GPacketPlayClientTabComplete.class, new PacketInformation("PacketPlayInTabComplete")),
                new Pair<>(GPacketPlayClientSettings.class, new PacketInformation("PacketPlayInSettings")),
                new Pair<>(GPacketPlayClientClientCommand.class, new PacketInformation("PacketPlayInClientCommand")),
                new Pair<>(GPacketPlayClientCustomPayload.class, new PacketInformation("PacketPlayInCustomPayload")),
                new Pair<>(GPacketPlayClientSpectate.class, new PacketInformation("PacketPlayInSpectate")),
                new Pair<>(GPacketPlayClientResourcePackStatus.class, new PacketInformation("PacketPlayInResourcePackStatus")),
            },

            // Server
            new Pair[]{
                new Pair<>(GPacketPlayServerKeepAlive.class, new PacketInformation("PacketPlayOutKeepAlive")), // 0
                new Pair<>(GPacketPlayServerLogin.class, new PacketInformation("PacketPlayOutLogin")), // 1
                new Pair<>(GPacketPlayServerChat.class, new PacketInformation("PacketPlayOutChat")), // 2
                new Pair<>(PacketPlayServerUpdateTime.class, new PacketInformation("PacketPlayOutUpdateTime")), // 3
                new Pair<>(GPacketPlayServerEntityEquipment.class, new PacketInformation("PacketPlayOutEntityEquipment")), // 4
                new Pair<>(PacketPlayServerSpawnPosition.class, new PacketInformation("PacketPlayOutSpawnPosition")), // 5
                new Pair<>(PacketPlayServerUpdateHealth.class, new PacketInformation("PacketPlayOutUpdateHealth")), // 6
                new Pair<>(GPacketPlayServerRespawn.class, new PacketInformation("PacketPlayOutRespawn")), // 7
                new Pair<>(GPacketPlayServerPosition.class, new PacketInformation("PacketPlayOutPosition")), // 8
                new Pair<>(GPacketPlayServerHeldItemSlot.class, new PacketInformation("PacketPlayOutHeldItemSlot")), // 9
                new Pair<>(GPacketPlayServerBed.class, new PacketInformation("PacketPlayOutBed")), // 10
                new Pair<>(GPacketPlayServerAnimation.class, new PacketInformation("PacketPlayOutAnimation")),
                new Pair<>(GPacketPlayServerSpawnNamedEntity.class, new PacketInformation("PacketPlayOutNamedEntitySpawn")),
                new Pair<>(PacketPlayServerCollect.class, new PacketInformation("PacketPlayOutCollect")),
                new Pair<>(GPacketPlayServerSpawnEntityWeather.class, new PacketInformation("PacketPlayOutSpawnEntity")),
                new Pair<>(PacketPlayServerSpawnEntityLiving.class, new PacketInformation("PacketPlayOutSpawnEntityLiving")),
                new Pair<>(PacketPlayServerSpawnEntityPainting.class, new PacketInformation("PacketPlayOutSpawnEntityPainting")),
                new Pair<>(PacketPlayServerSpawnEntityExperienceOrb.class, new PacketInformation("PacketPlayOutSpawnEntityExperienceOrb")),
                new Pair<>(GPacketPlayServerEntityVelocity.class, new PacketInformation("PacketPlayOutEntityVelocity")),
                new Pair<>(GPacketPlayServerEntityDestroy.class, new PacketInformation("PacketPlayOutEntityDestroy")),
                new Pair<>(GPacketPlayServerEntity.class, new PacketInformation("PacketPlayOutEntity")),
                new Pair<>(GPacketPlayServerEntity.GPacketPlayServerRelEntityMove.class,new PacketInformation("PacketPlayOutRelEntityMove")),
                new Pair<>(GPacketPlayServerEntity.GPacketPlayServerEntityLook.class,new PacketInformation("PacketPlayOutEntityLook")),
                new Pair<>(GPacketPlayServerEntity.GPacketPlayServerRelEntityMoveLook.class,new PacketInformation("PacketPlayOutRelEntityMoveLook")),
                new Pair<>(GPacketPlayServerEntityTeleport.class, new PacketInformation("PacketPlayOutEntityTeleport")),
                new Pair<>(GPacketPlayServerEntityHeadRotation.class, new PacketInformation("PacketPlayOutEntityHeadRotation")),
                new Pair<>(GPacketPlayServerEntityStatus.class, new PacketInformation("PacketPlayOutEntityStatus")),
                new Pair<>(GPacketPlayServerEntityAttach.class, new PacketInformation("PacketPlayOutAttachEntity")),
                new Pair<>(GPacketPlayServerEntityMetadata.class, new PacketInformation("PacketPlayOutEntityMetadata")),
                new Pair<>(GPacketPlayServerEntityEffect.class, new PacketInformation("PacketPlayOutEntityEffect")),
                new Pair<>(GPacketPlayServerEntityEffectRemove.class, new PacketInformation("PacketPlayOutRemoveEntityEffect")),
                new Pair<>(PacketPlayServerExperience.class, new PacketInformation("PacketPlayOutExperience")),
                new Pair<>(GPacketPlayServerUpdateAttributes.class, new PacketInformation("PacketPlayOutUpdateAttributes")),
                new Pair<>(GPacketPlayServerChunkLoad.class, new PacketInformation("PacketPlayOutMapChunk")),
                new Pair<>(GPacketPlayServerBlockChangeMulti.class, new PacketInformation("PacketPlayOutMultiBlockChange")),
                new Pair<>(GPacketPlayServerBlockChange.class, new PacketInformation("PacketPlayOutBlockChange")),
                new Pair<>(GPacketPlayServerBlockBreakAnimation.class, new PacketInformation("PacketPlayOutBlockBreakAnimation")),
                new Pair<>(GPacketPlayServerChunkLoadBulk.class, new PacketInformation("PacketPlayOutMapChunkBulk")),
                new Pair<>(GPacketPlayServerExplosion.class, new PacketInformation("PacketPlayOutExplosion")),
                new Pair<>(PacketPlayServerWorldEvent.class, new PacketInformation("PacketPlayOutWorldEvent")),
                new Pair<>(PacketPlayServerSoundEffectNamed.class, new PacketInformation("PacketPlayOutNamedSoundEffect")),
                new Pair<>(PacketPlayServerWorldParticles.class, new PacketInformation("PacketPlayOutWorldParticles")),
                new Pair<>(PacketPlayServerGameStateChange.class, new PacketInformation("PacketPlayOutGameStateChange")),
                new Pair<>(GPacketPlayServerSpawnEntityWeather.class, new PacketInformation("PacketPlayOutSpawnEntityWeather")),
                new Pair<>(GPacketPlayServerWindowOpen.class, new PacketInformation("PacketPlayOutOpenWindow")),
                new Pair<>(GPacketPlayServerWindowClose.class, new PacketInformation("PacketPlayOutCloseWindow")),
                new Pair<>(PacketPlayServerSetSlot.class, new PacketInformation("PacketPlayOutSetSlot")),
                new Pair<>(PacketPlayServerWindowItems.class, new PacketInformation("PacketPlayOutWindowItems")),
                new Pair<>(PacketPlayServerWindowData.class, new PacketInformation("PacketPlayOutWindowData")),
                new Pair<>(GPacketPlayServerTransaction.class, new PacketInformation("PacketPlayOutTransaction")),
                new Pair<>(PacketPlayServerUpdateSign.class, new PacketInformation("PacketPlayOutUpdateSign")),
                new Pair<>(PacketPlayServerMap.class, new PacketInformation("PacketPlayOutMap")),
                new Pair<>(PacketPlayServerTileEntityData.class, new PacketInformation("PacketPlayOutTileEntityData")),
                new Pair<>(PacketPlayServerOpenSignEditor.class, new PacketInformation("PacketPlayOutOpenSignEditor")),
                new Pair<>(PacketPlayServerStatistic.class, new PacketInformation("PacketPlayOutStatistic")),
                new Pair<>(PacketPlayServerPlayerInfo.class, new PacketInformation("PacketPlayOutPlayerInfo")),
                new Pair<>(GPacketPlayServerAbilities.class, new PacketInformation("PacketPlayOutAbilities")),
                new Pair<>(GPacketPlayServerTabComplete.class, new PacketInformation("PacketPlayOutTabComplete")),
                new Pair<>(PacketPlayServerScoreboardObjective.class, new PacketInformation("PacketPlayOutScoreboardObjective")),
                new Pair<>(PacketPlayServerScoreboardScore.class, new PacketInformation("PacketPlayOutScoreboardScore")),
                new Pair<>(PacketPlayServerScoreboardDisplayObjective.class, new PacketInformation("PacketPlayOutScoreboardDisplayObjective")),
                new Pair<>(PacketPlayServerScoreboardTeam.class, new PacketInformation("PacketPlayOutScoreboardTeam")),
                new Pair<>(GPacketPlayServerCustomPayload.class, new PacketInformation("PacketPlayOutCustomPayload")),
                new Pair<>(GPacketPlayServerKickDisconnect.class, new PacketInformation("PacketPlayOutKickDisconnect")), //
                new Pair<>(PacketPlayServerServerDifficulty.class, new PacketInformation("PacketPlayOutServerDifficulty")),
                new Pair<>(PacketPlayServerCombatEvent.class, new PacketInformation("PacketPlayOutCombatEvent")),
                new Pair<>(PacketPlayServerCamera.class, new PacketInformation("PacketPlayOutCamera")),
                new Pair<>(PacketPlayServerWorldBorder.class, new PacketInformation("PacketPlayOutWorldBorder")), //
                new Pair<>(PacketPlayServerTitle.class, new PacketInformation("PacketPlayOutTitle")),
                new Pair<>(PacketPlayServerSetCompression.class, new PacketInformation("PacketPlayOutSetCompression")),
                new Pair<>(PacketPlayServerPlayerListHeaderFooter.class, new PacketInformation("PacketPlayOutPlayerListHeaderFooter")),
                new Pair<>(GPacketPlayServerResourcePackSend.class, new PacketInformation("PacketPlayOutResourcePackSend")),
                new Pair<>(PacketPlayServerUpdateEntityNBT.class, new PacketInformation("PacketPlayOutUpdateEntityNBT")),
            }

    }),
    STATUS(1, new Pair[][]{
            // Client
            new Pair[]{
                new Pair<>(PacketStatusClientStart.class, new PacketInformation("PacketStatusInStart")),
                new Pair<>(PacketStatusClientPing.class, new PacketInformation("PacketStatusInPing"))
            },

            // Server
            new Pair[]{
                new Pair<>(PacketStatusServerInfoServer.class, new PacketInformation("PacketStatusOutServerInfo")),
                new Pair<>(PacketStatusServerPing.class, new PacketInformation("PacketStatusOutPong"))
            }
    }),
    LOGIN(2, new Pair[][]{
            // Client
            new Pair[]{
                new Pair<>(GPacketLoginClientStart.class, new PacketInformation("PacketLoginInStart")),
                new Pair<>(GPacketLoginClientEncryptionBegin.class, new PacketInformation("PacketLoginInEncryptionBegin")),
                new Pair<>(GPacketLoginClientPluginRequest.class, new PacketInformation("PacketLoginInCustomPayload")),
            },

            // Server
            new Pair[]{
                new Pair<>(GPacketLoginServerDisconnect.class, new PacketInformation("PacketLoginOutDisconnect")),
                new Pair<>(GPacketLoginServerEncryptionBegin.class, new PacketInformation("PacketLoginOutEncryptionBegin")),
                new Pair<>(GPacketLoginServerSuccess.class, new PacketInformation("PacketLoginOutSuccess")),
                new Pair<>(GPacketLoginServerSetCompression.class, new PacketInformation("PacketLoginOutSetCompression")),
            }
    });

    private final int id;

    // Direction = Ordinal so 0 = IN and 1 = OUT
    // Second bit is just the id lol
    private final Pair<Class<? extends GPacket>, PacketInformation>[][] packets;
    private final Map<ProtocolDirection, Map<Integer, Class<? extends GPacket>>> packetMap;

    EnumProtocolCurrent(int id, Pair<Class<? extends GPacket>, PacketInformation>[][] packets) {
        this.id = id;
        this.packets = packets;
        this.packetMap = ReflectUtil.getPacketMap(this);
    }

    @Override
    @SneakyThrows
    public GPacket getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) {
        Class<? extends GPacket> clazz = packetMap.get(direction).get(id);
        if (clazz == null) {
            System.out.println("Packet of id " + id + " in direction does not exist! - " + packetMap.get(direction));
            return null;
        }
        return clazz.getConstructor(UUID.class, ProtocolVersion.class).newInstance(playerId, version);
    }

    @Override
    public int getPacketId(ProtocolDirection direction, GPacket packet) {
        Map.Entry<Integer, Class<? extends GPacket>> v = packetMap
                .get(direction)
                .entrySet()
                .parallelStream()
                .filter(e -> e != null && e.getValue() != null && e.getValue().equals(packet.getClass()))
                .findFirst()
                .orElse(null);
        if (v == null) return -1;

        return v.getKey();
    }

    @Override
    public int getOrdinal() {
        return ordinal();
    }

    @Override
    public EnumProtocol[] getValues() {
        return values();
    }


    @Override
    public Class<? extends GPacket> getPacketClass(ProtocolDirection direction, String name){
        for (Pair<Class<? extends GPacket>, PacketInformation> pair : packets[direction.ordinal()]) {
            if (pair.getV().getNmsName().equalsIgnoreCase(name) && pair.getV().isValid(ProtocolVersion.getGameVersion())) {
                return pair.getK();
            }
        }
        return null;
    }


    
}
