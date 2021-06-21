package cc.ghast.packet.protocol.nms;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.utils.PacketPair;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.PacketInfo;
import cc.ghast.packet.wrapper.packet.handshake.PacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.login.*;
import cc.ghast.packet.wrapper.packet.play.client.*;
import cc.ghast.packet.wrapper.packet.play.server.*;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientPing;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientStart;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerInfoServer;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerPing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import cc.ghast.packet.wrapper.packet.login.*;
import cc.ghast.packet.wrapper.packet.play.client.*;
import cc.ghast.packet.wrapper.packet.play.server.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

/**
 * Protocol for 1.8-1.8.9
 * @author Ghast
 * @since 29/12/2020
 * ArtemisPacket © 2020
 */

@AllArgsConstructor
@Getter
public enum EnumProtocol_47 implements EnumProtocol {
    HANDSHAKE(
            new PacketPair(
                new PacketInfo[]{
                        new PacketInfo<>(0x00, PacketHandshakeClientSetProtocol.class, "PacketHandshakingInSetProtocol"),
                },
                new PacketInfo[]{}
            )
    ),

    PLAY(
            new PacketPair(
                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketPlayClientKeepAlive.class, "PacketPlayInKeepAlive"),
                        new PacketInfo<>(0x01, PacketPlayClientChat.class, "PacketPlayInChat"),
                        new PacketInfo<>(0x02, PacketPlayClientUseEntity.class, "PacketPlayInUseEntity"),
                        new PacketInfo<>(0x03, PacketPlayClientFlying.class, "PacketPlayInFlying"),
                        new PacketInfo<>(0x04, PacketPlayClientFlying.PacketPlayClientPosition.class,"PacketPlayInPosition"),
                        new PacketInfo<>(0x05, PacketPlayClientFlying.PacketPlayClientLook.class,"PacketPlayInLook"),
                        new PacketInfo<>(0x06, PacketPlayClientFlying.PacketPlayClientPositionLook.class,"PacketPlayInPositionLook"),
                        new PacketInfo<>(0x07, PacketPlayClientBlockDig.class,"PacketPlayInBlockDig"),
                        new PacketInfo<>(0x08, PacketPlayClientBlockPlace.class,"PacketPlayInBlockPlace"),
                        new PacketInfo<>(0x09, PacketPlayClientHeldItemSlot.class,"PacketPlayInHeldItemSlot"),
                        new PacketInfo<>(0x0A, PacketPlayClientArmAnimation.class,"PacketPlayInArmAnimation"),
                        new PacketInfo<>(0x0B, PacketPlayClientEntityAction.class,"PacketPlayInEntityAction"),
                        new PacketInfo<>(0x0C, PacketPlayClientSteerVehicle.class,"PacketPlayInSteerVehicle"),
                        new PacketInfo<>(0x0D, PacketPlayClientWindowClose.class,"PacketPlayInCloseWindow"),
                        new PacketInfo<>(0x0E, PacketPlayClientWindowClick.class,"PacketPlayInWindowClick"),
                        new PacketInfo<>(0x0F, PacketPlayClientTransaction.class,"PacketPlayInTransaction"),
                        new PacketInfo<>(0x10, PacketPlayClientSetCreativeSlot.class,"PacketPlayInSetCreativeSlot"),
                        new PacketInfo<>(0x11, PacketPlayClientEnchantItem.class,"PacketPlayInEnchantItem"),
                        new PacketInfo<>(0x12, PacketPlayClientUpdateSign.class,"PacketPlayInUpdateSign"),
                        new PacketInfo<>(0x13, PacketPlayClientAbilities.class,"PacketPlayInAbilities"),
                        new PacketInfo<>(0x14, PacketPlayClientTabComplete.class,"PacketPlayInTabComplete"),
                        new PacketInfo<>(0x15, PacketPlayClientSettings.class,"PacketPlayInSettings"),
                        new PacketInfo<>(0x16, PacketPlayClientClientCommand.class,"PacketPlayInClientCommand"),
                        new PacketInfo<>(0x17, PacketPlayClientCustomPayload.class,"PacketPlayInCustomPayload"),
                        new PacketInfo<>(0x18, PacketPlayClientSpectate.class, "PacketPlayInSpectate"),
                        new PacketInfo<>(0x19, PacketPlayClientResourcePackStatus.class, "PacketPlayInResourcePackStatus")
                },
                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketPlayServerKeepAlive.class, "PacketPlayOutKeepAlive"),
                        /*
                         * /!\ If the dimension to this packet is not specified, the client will crash.
                         */
                        new PacketInfo<>(0x01, PacketPlayServerLogin.class, "PacketPlayOutLogin"),
                        /*
                         * /!\ Malformed JSON data will disconnect the pipeline.
                         */
                        new PacketInfo<>(0x02, PacketPlayServerChat.class, "PacketPlayOutChat"),
                        new PacketInfo<>(0x03, PacketPlayServerUpdateTime.class, "PacketPlayOutUpdateTime"),
                        new PacketInfo<>(0x04, PacketPlayServerEntityEquipment.class, "PacketPlayOutEntityEquipment"),
                        new PacketInfo<>(0x05, PacketPlayServerSpawnPosition.class, "PacketPlayOutSpawnPosition"),
                        new PacketInfo<>(0x06, PacketPlayServerUpdateHealth.class, "PacketPlayOutUpdateHealth"),
                        new PacketInfo<>(0x07, PacketPlayServerRespawn.class, "PacketPlayOutRespawn"),
                        new PacketInfo<>(0x08, PacketPlayServerPosition.class, "PacketPlayOutPosition"),
                        new PacketInfo<>(0x09, PacketPlayServerHeldItemSlot.class, "PacketPlayOutHeldItemSlot"),
                        new PacketInfo<>(0x0A, PacketPlayServerBed.class, "PacketPlayOutBed"),
                        new PacketInfo<>(0x0B, PacketPlayServerAnimation.class, "PacketPlayOutAnimation"),
                        /*
                         * This packet allows for the spawn of players. On this specific version, it is exclusively
                         * to spawn players. Same should apply across all versions?
                         */
                        new PacketInfo<>(0x0C, PacketPlayServerSpawnNamedEntity.class, "PacketPlayOutNamedEntitySpawn"),
                        new PacketInfo<>(0x0D, PacketPlayServerCollect.class, "PacketPlayOutCollect"),

                        /*
                         * Spawns an entity on
                         */
                        new PacketInfo<>(0x0E, PacketPlayServerSpawnObject.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo<>(0x0F, PacketPlayServerSpawnEntityLiving.class, "PacketPlayOutSpawnEntityLiving"),
                        new PacketInfo<>(0x10, PacketPlayServerSpawnEntityPainting.class, "PacketPlayOutSpawnEntityPainting"),
                        new PacketInfo<>(0x11, PacketPlayServerSpawnEntityExperienceOrb.class, "PacketPlayOutSpawnEntityExperienceOrb"),
                        new PacketInfo<>(0x12, PacketPlayServerEntityVelocity.class, "PacketPlayOutEntityVelocity"),
                        new PacketInfo<>(0x13, PacketPlayServerEntityDestroy.class, "PacketPlayOutEntityDestroy"),
                        new PacketInfo<>(0x14, PacketPlayServerEntity.class, "PacketPlayOutEntity"),
                        new PacketInfo<>(0x15, PacketPlayServerEntity.PacketPlayServerRelEntityMove.class, "PacketPlayOutRelEntityMove"),
                        new PacketInfo<>(0x16, PacketPlayServerEntity.PacketPlayServerEntityLook.class, "PacketPlayOutEntityLook"),
                        new PacketInfo<>(0x17, PacketPlayServerEntity.PacketPlayServerRelEntityMoveLook.class, "PacketPlayOutRelEntityMoveLook"),
                        new PacketInfo<>(0x18, PacketPlayServerEntityTeleport.class, "PacketPlayOutEntityTeleport"),
                        new PacketInfo<>(0x19, PacketPlayServerEntityHeadRotation.class, "PacketPlayOutEntityHeadRotation"),
                        new PacketInfo<>(0x1A, PacketPlayServerEntityStatus.class, "PacketPlayOutEntityStatus"),
                        new PacketInfo<>(0x1B, PacketPlayServerEntityAttach.class, "PacketPlayOutAttachEntity"),
                        new PacketInfo<>(0x1C, PacketPlayServerEntityMetadata.class, "PacketPlayOutEntityMetadata"),
                        new PacketInfo<>(0x1D, PacketPlayServerEntityEffect.class, "PacketPlayOutEntityEffect"),
                        new PacketInfo<>(0x1E, PacketPlayServerEntityEffectRemove.class, "PacketPlayOutRemoveEntityEffect"),
                        new PacketInfo<>(0x1F, PacketPlayServerExperience.class, "PacketPlayOutExperience"),
                        new PacketInfo<>(0x20, PacketPlayServerUpdateAttributes.class, "PacketPlayOutUpdateAttributes"),
                        new PacketInfo<>(0x21, PacketPlayServerChunkLoad.class, "PacketPlayOutMapChunk"),
                        new PacketInfo<>(0x22, PacketPlayServerBlockChangeMulti.class, "PacketPlayOutMultiBlockChange"),
                        new PacketInfo<>(0x23, PacketPlayServerBlockChange.class, "PacketPlayOutBlockChange"),
                        new PacketInfo<>(0x24, PacketPlayServerBlockAction.class, "PacketPlayOutBlockAction"),
                        new PacketInfo<>(0x25, PacketPlayServerBlockBreakAnimation.class, "PacketPlayOutBlockBreakAnimation"),
                        new PacketInfo<>(0x26, PacketPlayServerChunkLoadBulk.class, "PacketPlayOutMapChunkBulk"),
                        new PacketInfo<>(0x27, PacketPlayServerExplosion.class, "PacketPlayOutExplosion"),
                        new PacketInfo<>(0x28, PacketPlayServerWorldEvent.class, "PacketPlayOutWorldEvent"),
                        new PacketInfo<>(0x29, PacketPlayServerSoundEffectNamed.class, "PacketPlayOutNamedSoundEffect"),
                        new PacketInfo<>(0x2A, PacketPlayServerWorldParticles.class, "PacketPlayOutWorldParticles"),
                        new PacketInfo<>(0x2B, PacketPlayServerGameStateChange.class, "PacketPlayOutGameStateChange"),
                        new PacketInfo<>(0x2C, PacketPlayServerSpawnEntityWeather.class, "PacketPlayOutSpawnEntityWeather"),
                        new PacketInfo<>(0x2D, PacketPlayServerWindowOpen.class, "PacketPlayOutOpenWindow"),
                        new PacketInfo<>(0x2E, PacketPlayServerWindowClose.class, "PacketPlayOutCloseWindow"),
                        new PacketInfo<>(0x2F, PacketPlayServerSetSlot.class, "PacketPlayOutSetSlot"),
                        new PacketInfo<>(0x30, PacketPlayServerWindowItems.class, "PacketPlayOutWindowItems"),
                        new PacketInfo<>(0x31, PacketPlayServerWindowData.class, "PacketPlayOutWindowData"),
                        new PacketInfo<>(0x32, PacketPlayServerTransaction.class, "PacketPlayOutTransaction"),
                        new PacketInfo<>(0x33, PacketPlayServerUpdateSign.class, "PacketPlayOutUpdateSign"),
                        new PacketInfo<>(0x34, PacketPlayServerMap.class, "PacketPlayOutMap"),
                        new PacketInfo<>(0x35, PacketPlayServerTileEntityData.class, "PacketPlayOutTileEntityData"),
                        new PacketInfo<>(0x36, PacketPlayServerOpenSignEditor.class, "PacketPlayOutOpenSignEditor"),
                        new PacketInfo<>(0x37, PacketPlayServerStatistic.class, "PacketPlayOutStatistic"),
                        new PacketInfo<>(0x38, PacketPlayServerPlayerInfo.class, "PacketPlayOutPlayerInfo"),
                        new PacketInfo<>(0x39, PacketPlayServerAbilities.class, "PacketPlayOutAbilities"),
                        new PacketInfo<>(0x3A, PacketPlayServerTabComplete.class, "PacketPlayOutTabComplete"),
                        new PacketInfo<>(0x3B, PacketPlayServerScoreboardObjective.class, "PacketPlayOutScoreboardObjective"),
                        new PacketInfo<>(0x3C, PacketPlayServerScoreboardScore.class, "PacketPlayOutScoreboardScore"),
                        new PacketInfo<>(0x3D, PacketPlayServerScoreboardDisplayObjective.class, "PacketPlayOutScoreboardDisplayObjective"),
                        new PacketInfo<>(0x3E, PacketPlayServerScoreboardTeam.class, "PacketPlayOutScoreboardTeam"),
                        new PacketInfo<>(0x3F, PacketPlayServerCustomPayload.class, "PacketPlayOutCustomPayload"),
                        new PacketInfo<>(0x40, PacketPlayServerKickDisconnect.class, "PacketPlayOutKickDisconnect"), //
                        new PacketInfo<>(0x41, PacketPlayServerServerDifficulty.class, "PacketPlayOutServerDifficulty"),
                        new PacketInfo<>(0x42, PacketPlayServerCombatEvent.class, "PacketPlayOutCombatEvent"),
                        new PacketInfo<>(0x43, PacketPlayServerCamera.class, "PacketPlayOutCamera"),
                        new PacketInfo<>(0x44, PacketPlayServerWorldBorder.class, "PacketPlayOutWorldBorder"),
                        new PacketInfo<>(0x45, PacketPlayServerTitle.class, "PacketPlayOutTitle"),
                        new PacketInfo<>(0x46, PacketPlayServerSetCompression.class, "PacketPlayOutSetCompression"),
                        new PacketInfo<>(0x47, PacketPlayServerPlayerListHeaderFooter.class, "PacketPlayOutPlayListHeaderFooter"),
                        new PacketInfo<>(0x48, PacketPlayServerResourcePackSend.class, "PacketPlayOutResourcePackSend"),
                        new PacketInfo<>(0x49, PacketPlayServerUpdateEntityNBT.class, "PacketPlayOutUpdateEntityNBT")
                }
            )
    ),

    STATUS(new PacketPair(
                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketStatusClientStart.class, "PacketStatusInStart"),
                        new PacketInfo<>(0x01, PacketStatusClientPing.class, "PacketStatusInPing")
                },

                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketStatusServerInfoServer.class, "PacketStatusOutServerInfo"),
                        new PacketInfo<>(0x01, PacketStatusServerPing.class, "PacketStatusOutPong")
                }
            )
    ),
    LOGIN(
            new PacketPair(
                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketLoginClientStart.class, "PacketLoginInStart"),
                        new PacketInfo<>(0x01, PacketLoginClientEncryptionBegin.class, "PacketLoginInEncryptionBegin")

                    },

                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketLoginServerDisconnect.class, "PacketLoginOutDisconnect"),
                        new PacketInfo<>(0x01, PacketLoginServerEncryptionBegin.class, "PacketLoginOutEncryptionBegin"),
                        new PacketInfo<>(0x02, PacketLoginServerSuccess.class, "PacketLoginOutSuccess"),
                        new PacketInfo<>(0x03, PacketLoginServerSetCompression.class, "PacketLoginOutSetCompression")
                }
            )
    );


    private final PacketPair packetPair;


    @Override
    public Packet<?> getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        final Map<Integer, PacketInfo> packets = direction.equals(ProtocolDirection.IN) ? packetPair.getClient() : packetPair.getServer();

        final PacketInfo packetInfo = packets.get(id);

        if (packetInfo == null) {
            throw new IllegalStateException(playerId + " -> Packet of id " + id + " had no associated information (dir: "
                    + direction + " ver: " + version + " type: " + ordinal() + ")");
        }

        final Constructor<? extends Packet<?>> packetConstructor = packetInfo.getConstructor();

        if (packetConstructor == null) {
            throw new IllegalStateException(playerId + " -> Packet of id " + id
                    + " had no associated constructor (dir: " + direction + " ver: " + version + " type: " + ordinal() + ")");
        }

        return packetConstructor.newInstance(playerId, version);
    }

    @Override
    public int getPacketId(ProtocolDirection direction, Packet<?> packet) {
        final Map<Integer, PacketInfo> packets = direction.equals(ProtocolDirection.IN) ? packetPair.getClient() : packetPair.getServer();
        return packets.values()
                .stream()
                .filter(packetInfo -> packetInfo.getClazz().equals(packet.getClass()))
                .map(PacketInfo::getId)
                .findFirst()
                .orElse(-1);
    }

    @Override
    public Class<? extends Packet<?>> getPacketClass(ProtocolDirection direction, String name) {
        final Map<Integer, PacketInfo> packets = direction.equals(ProtocolDirection.IN) ? packetPair.getClient() : packetPair.getServer();
        return packets.values()
                .stream()
                .filter(packetInfo -> packetInfo.getNmsName().equals(name))
                .map(PacketInfo::getClazz)
                .findFirst()
                .orElse(null);
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
