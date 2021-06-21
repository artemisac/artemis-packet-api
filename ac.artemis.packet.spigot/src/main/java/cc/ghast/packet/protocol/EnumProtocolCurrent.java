package cc.ghast.packet.protocol;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.reflections.ReflectUtil;
import cc.ghast.packet.utils.PacketUtil;
import cc.ghast.packet.utils.Pair;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.PacketInformation;
import cc.ghast.packet.wrapper.packet.handshake.PacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.login.*;
import cc.ghast.packet.wrapper.packet.play.client.*;
import cc.ghast.packet.wrapper.packet.play.server.*;
import cc.ghast.packet.wrapper.packet.play.client.*;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientPing;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientStart;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerInfoServer;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerPing;
import lombok.Getter;
import lombok.SneakyThrows;
import cc.ghast.packet.wrapper.packet.login.*;
import cc.ghast.packet.wrapper.packet.play.server.*;

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
                new Pair<>(PacketHandshakeClientSetProtocol.class, new PacketInformation("PacketHandshakingInSetProtocol"))
            },

            // Server
            new Pair[]{}

    }),
    PLAY(0, new Pair[][]{
            // Client
            new Pair[]{
                new Pair<>(PacketPlayClientKeepAlive.class, new PacketInformation("PacketPlayInKeepAlive")),
                new Pair<>(PacketPlayClientChat.class, new PacketInformation("PacketPlayInChat")),
                new Pair<>(PacketPlayClientUseEntity.class, new PacketInformation("PacketPlayInUseEntity")),
                new Pair<>(PacketPlayClientFlying.class, new PacketInformation("PacketPlayInFlying")),
                new Pair<>(PacketPlayClientFlying.PacketPlayClientPosition.class,new PacketInformation("PacketPlayInPosition")),
                new Pair<>(PacketPlayClientFlying.PacketPlayClientLook.class,new PacketInformation("PacketPlayInLook")),
                new Pair<>(PacketPlayClientFlying.PacketPlayClientPositionLook.class,new PacketInformation("PacketPlayInPositionLook")),
                new Pair<>(PacketPlayClientBlockDig.class, new PacketInformation("PacketPlayInBlockDig")),
                new Pair<>(PacketPlayClientBlockPlace.class, new PacketInformation(PacketUtil.BLOCK_PLACE)),
                new Pair<>(PacketPlayClientHeldItemSlot.class, new PacketInformation("PacketPlayInHeldItemSlot")),
                new Pair<>(PacketPlayClientArmAnimation.class, new PacketInformation("PacketPlayInArmAnimation")),
                new Pair<>(PacketPlayClientEntityAction.class, new PacketInformation("PacketPlayInEntityAction")),
                new Pair<>(PacketPlayClientSteerVehicle.class, new PacketInformation("PacketPlayInSteerVehicle")),
                new Pair<>(PacketPlayClientWindowClose.class, new PacketInformation("PacketPlayInCloseWindow")),
                new Pair<>(PacketPlayClientWindowClick.class, new PacketInformation("PacketPlayInWindowClick")),
                new Pair<>(PacketPlayClientTransaction.class, new PacketInformation("PacketPlayInTransaction")),
                new Pair<>(PacketPlayClientSetCreativeSlot.class, new PacketInformation("PacketPlayInSetCreativeSlot")),
                new Pair<>(PacketPlayClientEnchantItem.class, new PacketInformation("PacketPlayInEnchantItem")),
                new Pair<>(PacketPlayClientUpdateSign.class, new PacketInformation("PacketPlayInUpdateSign")),
                new Pair<>(PacketPlayClientAbilities.class, new PacketInformation("PacketPlayInAbilities")),
                new Pair<>(PacketPlayClientTabComplete.class, new PacketInformation("PacketPlayInTabComplete")),
                new Pair<>(PacketPlayClientSettings.class, new PacketInformation("PacketPlayInSettings")),
                new Pair<>(PacketPlayClientClientCommand.class, new PacketInformation("PacketPlayInClientCommand")),
                new Pair<>(PacketPlayClientCustomPayload.class, new PacketInformation("PacketPlayInCustomPayload")),
                new Pair<>(PacketPlayClientSpectate.class, new PacketInformation("PacketPlayInSpectate")),
                new Pair<>(PacketPlayClientResourcePackStatus.class, new PacketInformation("PacketPlayInResourcePackStatus")),
            },

            // Server
            new Pair[]{
                new Pair<>(PacketPlayServerKeepAlive.class, new PacketInformation("PacketPlayOutKeepAlive")), // 0
                new Pair<>(PacketPlayServerLogin.class, new PacketInformation("PacketPlayOutLogin")), // 1
                new Pair<>(PacketPlayServerChat.class, new PacketInformation("PacketPlayOutChat")), // 2
                new Pair<>(PacketPlayServerUpdateTime.class, new PacketInformation("PacketPlayOutUpdateTime")), // 3
                new Pair<>(PacketPlayServerEntityEquipment.class, new PacketInformation("PacketPlayOutEntityEquipment")), // 4
                new Pair<>(PacketPlayServerSpawnPosition.class, new PacketInformation("PacketPlayOutSpawnPosition")), // 5
                new Pair<>(PacketPlayServerUpdateHealth.class, new PacketInformation("PacketPlayOutUpdateHealth")), // 6
                new Pair<>(PacketPlayServerRespawn.class, new PacketInformation("PacketPlayOutRespawn")), // 7
                new Pair<>(PacketPlayServerPosition.class, new PacketInformation("PacketPlayOutPosition")), // 8
                new Pair<>(PacketPlayServerHeldItemSlot.class, new PacketInformation("PacketPlayOutHeldItemSlot")), // 9
                new Pair<>(PacketPlayServerBed.class, new PacketInformation("PacketPlayOutBed")), // 10
                new Pair<>(PacketPlayServerAnimation.class, new PacketInformation("PacketPlayOutAnimation")),
                new Pair<>(PacketPlayServerSpawnNamedEntity.class, new PacketInformation("PacketPlayOutNamedEntitySpawn")),
                new Pair<>(PacketPlayServerCollect.class, new PacketInformation("PacketPlayOutCollect")),
                new Pair<>(PacketPlayServerSpawnEntityWeather.class, new PacketInformation("PacketPlayOutSpawnEntity")),
                new Pair<>(PacketPlayServerSpawnEntityLiving.class, new PacketInformation("PacketPlayOutSpawnEntityLiving")),
                new Pair<>(PacketPlayServerSpawnEntityPainting.class, new PacketInformation("PacketPlayOutSpawnEntityPainting")),
                new Pair<>(PacketPlayServerSpawnEntityExperienceOrb.class, new PacketInformation("PacketPlayOutSpawnEntityExperienceOrb")),
                new Pair<>(PacketPlayServerEntityVelocity.class, new PacketInformation("PacketPlayOutEntityVelocity")),
                new Pair<>(PacketPlayServerEntityDestroy.class, new PacketInformation("PacketPlayOutEntityDestroy")),
                new Pair<>(PacketPlayServerEntity.class, new PacketInformation("PacketPlayOutEntity")),
                new Pair<>(PacketPlayServerEntity.PacketPlayServerRelEntityMove.class,new PacketInformation("PacketPlayOutRelEntityMove")),
                new Pair<>(PacketPlayServerEntity.PacketPlayServerEntityLook.class,new PacketInformation("PacketPlayOutEntityLook")),
                new Pair<>(PacketPlayServerEntity.PacketPlayServerRelEntityMoveLook.class,new PacketInformation("PacketPlayOutRelEntityMoveLook")),
                new Pair<>(PacketPlayServerEntityTeleport.class, new PacketInformation("PacketPlayOutEntityTeleport")),
                new Pair<>(PacketPlayServerEntityHeadRotation.class, new PacketInformation("PacketPlayOutEntityHeadRotation")),
                new Pair<>(PacketPlayServerEntityStatus.class, new PacketInformation("PacketPlayOutEntityStatus")),
                new Pair<>(PacketPlayServerEntityAttach.class, new PacketInformation("PacketPlayOutAttachEntity")),
                new Pair<>(PacketPlayServerEntityMetadata.class, new PacketInformation("PacketPlayOutEntityMetadata")),
                new Pair<>(PacketPlayServerEntityEffect.class, new PacketInformation("PacketPlayOutEntityEffect")),
                new Pair<>(PacketPlayServerEntityEffectRemove.class, new PacketInformation("PacketPlayOutRemoveEntityEffect")),
                new Pair<>(PacketPlayServerExperience.class, new PacketInformation("PacketPlayOutExperience")),
                new Pair<>(PacketPlayServerUpdateAttributes.class, new PacketInformation("PacketPlayOutUpdateAttributes")),
                new Pair<>(PacketPlayServerChunkLoad.class, new PacketInformation("PacketPlayOutMapChunk")),
                new Pair<>(PacketPlayServerBlockChangeMulti.class, new PacketInformation("PacketPlayOutMultiBlockChange")),
                new Pair<>(PacketPlayServerBlockChange.class, new PacketInformation("PacketPlayOutBlockChange")),
                new Pair<>(PacketPlayServerBlockBreakAnimation.class, new PacketInformation("PacketPlayOutBlockBreakAnimation")),
                new Pair<>(PacketPlayServerChunkLoadBulk.class, new PacketInformation("PacketPlayOutMapChunkBulk")),
                new Pair<>(PacketPlayServerExplosion.class, new PacketInformation("PacketPlayOutExplosion")),
                new Pair<>(PacketPlayServerWorldEvent.class, new PacketInformation("PacketPlayOutWorldEvent")),
                new Pair<>(PacketPlayServerSoundEffectNamed.class, new PacketInformation("PacketPlayOutNamedSoundEffect")),
                new Pair<>(PacketPlayServerWorldParticles.class, new PacketInformation("PacketPlayOutWorldParticles")),
                new Pair<>(PacketPlayServerGameStateChange.class, new PacketInformation("PacketPlayOutGameStateChange")),
                new Pair<>(PacketPlayServerSpawnEntityWeather.class, new PacketInformation("PacketPlayOutSpawnEntityWeather")),
                new Pair<>(PacketPlayServerWindowOpen.class, new PacketInformation("PacketPlayOutOpenWindow")),
                new Pair<>(PacketPlayServerWindowClose.class, new PacketInformation("PacketPlayOutCloseWindow")),
                new Pair<>(PacketPlayServerSetSlot.class, new PacketInformation("PacketPlayOutSetSlot")),
                new Pair<>(PacketPlayServerWindowItems.class, new PacketInformation("PacketPlayOutWindowItems")),
                new Pair<>(PacketPlayServerWindowData.class, new PacketInformation("PacketPlayOutWindowData")),
                new Pair<>(PacketPlayServerTransaction.class, new PacketInformation("PacketPlayOutTransaction")),
                new Pair<>(PacketPlayServerUpdateSign.class, new PacketInformation("PacketPlayOutUpdateSign")),
                new Pair<>(PacketPlayServerMap.class, new PacketInformation("PacketPlayOutMap")),
                new Pair<>(PacketPlayServerTileEntityData.class, new PacketInformation("PacketPlayOutTileEntityData")),
                new Pair<>(PacketPlayServerOpenSignEditor.class, new PacketInformation("PacketPlayOutOpenSignEditor")),
                new Pair<>(PacketPlayServerStatistic.class, new PacketInformation("PacketPlayOutStatistic")),
                new Pair<>(PacketPlayServerPlayerInfo.class, new PacketInformation("PacketPlayOutPlayerInfo")),
                new Pair<>(PacketPlayServerAbilities.class, new PacketInformation("PacketPlayOutAbilities")),
                new Pair<>(PacketPlayServerTabComplete.class, new PacketInformation("PacketPlayOutTabComplete")),
                new Pair<>(PacketPlayServerScoreboardObjective.class, new PacketInformation("PacketPlayOutScoreboardObjective")),
                new Pair<>(PacketPlayServerScoreboardScore.class, new PacketInformation("PacketPlayOutScoreboardScore")),
                new Pair<>(PacketPlayServerScoreboardDisplayObjective.class, new PacketInformation("PacketPlayOutScoreboardDisplayObjective")),
                new Pair<>(PacketPlayServerScoreboardTeam.class, new PacketInformation("PacketPlayOutScoreboardTeam")),
                new Pair<>(PacketPlayServerCustomPayload.class, new PacketInformation("PacketPlayOutCustomPayload")),
                new Pair<>(PacketPlayServerKickDisconnect.class, new PacketInformation("PacketPlayOutKickDisconnect")), //
                new Pair<>(PacketPlayServerServerDifficulty.class, new PacketInformation("PacketPlayOutServerDifficulty")),
                new Pair<>(PacketPlayServerCombatEvent.class, new PacketInformation("PacketPlayOutCombatEvent")),
                new Pair<>(PacketPlayServerCamera.class, new PacketInformation("PacketPlayOutCamera")),
                new Pair<>(PacketPlayServerWorldBorder.class, new PacketInformation("PacketPlayOutWorldBorder")), //
                new Pair<>(PacketPlayServerTitle.class, new PacketInformation("PacketPlayOutTitle")),
                new Pair<>(PacketPlayServerSetCompression.class, new PacketInformation("PacketPlayOutSetCompression")),
                new Pair<>(PacketPlayServerPlayerListHeaderFooter.class, new PacketInformation("PacketPlayOutPlayerListHeaderFooter")),
                new Pair<>(PacketPlayServerResourcePackSend.class, new PacketInformation("PacketPlayOutResourcePackSend")),
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
                new Pair<>(PacketLoginClientStart.class, new PacketInformation("PacketLoginInStart")),
                new Pair<>(PacketLoginClientEncryptionBegin.class, new PacketInformation("PacketLoginInEncryptionBegin")),
                new Pair<>(PacketLoginClientPluginRequest.class, new PacketInformation("PacketLoginInCustomPayload")),
            },

            // Server
            new Pair[]{
                new Pair<>(PacketLoginServerDisconnect.class, new PacketInformation("PacketLoginOutDisconnect")),
                new Pair<>(PacketLoginServerEncryptionBegin.class, new PacketInformation("PacketLoginOutEncryptionBegin")),
                new Pair<>(PacketLoginServerSuccess.class, new PacketInformation("PacketLoginOutSuccess")),
                new Pair<>(PacketLoginServerSetCompression.class, new PacketInformation("PacketLoginOutSetCompression")),
            }
    });

    private final int id;

    // Direction = Ordinal so 0 = IN and 1 = OUT
    // Second bit is just the id lol
    private final Pair<Class<? extends Packet<?>>, PacketInformation>[][] packets;
    private final Map<ProtocolDirection, Map<Integer, Class<? extends Packet<?>>>> packetMap;

    EnumProtocolCurrent(int id, Pair<Class<? extends Packet<?>>, PacketInformation>[][] packets) {
        this.id = id;
        this.packets = packets;
        this.packetMap = ReflectUtil.getPacketMap(this);
    }

    @Override
    @SneakyThrows
    public Packet<?> getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) {
        Class<? extends Packet<?>> clazz = packetMap.get(direction).get(id);
        if (clazz == null) {
            System.out.println("Packet of id " + id + " in direction does not exist! - " + packetMap.get(direction));
            return null;
        }
        return clazz.getConstructor(UUID.class, ProtocolVersion.class).newInstance(playerId, version);
    }

    @Override
    public int getPacketId(ProtocolDirection direction, Packet<?> packet) {
        Map.Entry<Integer, Class<? extends Packet<?>>> v = packetMap
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
    public Class<? extends Packet<?>> getPacketClass(ProtocolDirection direction, String name){
        for (Pair<Class<? extends Packet<?>>, PacketInformation> pair : packets[direction.ordinal()]) {
            if (pair.getV().getNmsName().equalsIgnoreCase(name) && pair.getV().isValid(ProtocolVersion.getGameVersion())) {
                return pair.getK();
            }
        }
        return null;
    }


    
}
