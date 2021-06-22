package cc.ghast.packet.protocol.nms;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.utils.PacketPair;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.PacketInfo;
import cc.ghast.packet.wrapper.packet.handshake.GPacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.login.*;
import cc.ghast.packet.wrapper.packet.play.client.*;
import cc.ghast.packet.wrapper.packet.play.server.*;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientPing;
import cc.ghast.packet.wrapper.packet.status.PacketStatusClientStart;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerInfoServer;
import cc.ghast.packet.wrapper.packet.status.PacketStatusServerPing;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
                        new PacketInfo<>(0x00, GPacketHandshakeClientSetProtocol.class, "PacketHandshakingInSetProtocol"),
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
                        new PacketInfo<>(0x00, GPacketPlayServerKeepAlive.class, "PacketPlayOutKeepAlive"),
                        /*
                         * /!\ If the dimension to this packet is not specified, the client will crash.
                         */
                        new PacketInfo<>(0x01, GPacketPlayServerLogin.class, "PacketPlayOutLogin"),
                        /*
                         * /!\ Malformed JSON data will disconnect the pipeline.
                         */
                        new PacketInfo<>(0x02, GPacketPlayServerChat.class, "PacketPlayOutChat"),
                        new PacketInfo<>(0x03, PacketPlayServerUpdateTime.class, "PacketPlayOutUpdateTime"),
                        new PacketInfo<>(0x04, GPacketPlayServerEntityEquipment.class, "PacketPlayOutEntityEquipment"),
                        new PacketInfo<>(0x05, PacketPlayServerSpawnPosition.class, "PacketPlayOutSpawnPosition"),
                        new PacketInfo<>(0x06, PacketPlayServerUpdateHealth.class, "PacketPlayOutUpdateHealth"),
                        new PacketInfo<>(0x07, GPacketPlayServerRespawn.class, "PacketPlayOutRespawn"),
                        new PacketInfo<>(0x08, GPacketPlayServerPosition.class, "PacketPlayOutPosition"),
                        new PacketInfo<>(0x09, GPacketPlayServerHeldItemSlot.class, "PacketPlayOutHeldItemSlot"),
                        new PacketInfo<>(0x0A, GPacketPlayServerBed.class, "PacketPlayOutBed"),
                        new PacketInfo<>(0x0B, GPacketPlayServerAnimation.class, "PacketPlayOutAnimation"),
                        /*
                         * This packet allows for the spawn of players. On this specific version, it is exclusively
                         * to spawn players. Same should apply across all versions?
                         */
                        new PacketInfo<>(0x0C, GPacketPlayServerSpawnNamedEntity.class, "PacketPlayOutNamedEntitySpawn"),
                        new PacketInfo<>(0x0D, PacketPlayServerCollect.class, "PacketPlayOutCollect"),

                        /*
                         * Spawns an entity on
                         */
                        new PacketInfo<>(0x0E, GPacketPlayServerSpawnObject.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo<>(0x0F, PacketPlayServerSpawnEntityLiving.class, "PacketPlayOutSpawnEntityLiving"),
                        new PacketInfo<>(0x10, PacketPlayServerSpawnEntityPainting.class, "PacketPlayOutSpawnEntityPainting"),
                        new PacketInfo<>(0x11, PacketPlayServerSpawnEntityExperienceOrb.class, "PacketPlayOutSpawnEntityExperienceOrb"),
                        new PacketInfo<>(0x12, GPacketPlayServerEntityVelocity.class, "PacketPlayOutEntityVelocity"),
                        new PacketInfo<>(0x13, GPacketPlayServerEntityDestroy.class, "PacketPlayOutEntityDestroy"),
                        new PacketInfo<>(0x14, GPacketPlayServerEntity.class, "PacketPlayOutEntity"),
                        new PacketInfo<>(0x15, GPacketPlayServerEntity.GPacketPlayServerRelEntityMove.class, "PacketPlayOutRelEntityMove"),
                        new PacketInfo<>(0x16, GPacketPlayServerEntity.GPacketPlayServerEntityLook.class, "PacketPlayOutEntityLook"),
                        new PacketInfo<>(0x17, GPacketPlayServerEntity.GPacketPlayServerRelEntityMoveLook.class, "PacketPlayOutRelEntityMoveLook"),
                        new PacketInfo<>(0x18, GPacketPlayServerEntityTeleport.class, "PacketPlayOutEntityTeleport"),
                        new PacketInfo<>(0x19, GPacketPlayServerEntityHeadRotation.class, "PacketPlayOutEntityHeadRotation"),
                        new PacketInfo<>(0x1A, GPacketPlayServerEntityStatus.class, "PacketPlayOutEntityStatus"),
                        new PacketInfo<>(0x1B, GPacketPlayServerEntityAttach.class, "PacketPlayOutAttachEntity"),
                        new PacketInfo<>(0x1C, GPacketPlayServerEntityMetadata.class, "PacketPlayOutEntityMetadata"),
                        new PacketInfo<>(0x1D, GPacketPlayServerEntityEffect.class, "PacketPlayOutEntityEffect"),
                        new PacketInfo<>(0x1E, GPacketPlayServerEntityEffectRemove.class, "PacketPlayOutRemoveEntityEffect"),
                        new PacketInfo<>(0x1F, PacketPlayServerExperience.class, "PacketPlayOutExperience"),
                        new PacketInfo<>(0x20, GPacketPlayServerUpdateAttributes.class, "PacketPlayOutUpdateAttributes"),
                        new PacketInfo<>(0x21, GPacketPlayServerChunkLoad.class, "PacketPlayOutMapChunk"),
                        new PacketInfo<>(0x22, GPacketPlayServerBlockChangeMulti.class, "PacketPlayOutMultiBlockChange"),
                        new PacketInfo<>(0x23, GPacketPlayServerBlockChange.class, "PacketPlayOutBlockChange"),
                        new PacketInfo<>(0x24, GPacketPlayServerBlockAction.class, "PacketPlayOutBlockAction"),
                        new PacketInfo<>(0x25, GPacketPlayServerBlockBreakAnimation.class, "PacketPlayOutBlockBreakAnimation"),
                        new PacketInfo<>(0x26, GPacketPlayServerChunkLoadBulk.class, "PacketPlayOutMapChunkBulk"),
                        new PacketInfo<>(0x27, GPacketPlayServerExplosion.class, "PacketPlayOutExplosion"),
                        new PacketInfo<>(0x28, PacketPlayServerWorldEvent.class, "PacketPlayOutWorldEvent"),
                        new PacketInfo<>(0x29, PacketPlayServerSoundEffectNamed.class, "PacketPlayOutNamedSoundEffect"),
                        new PacketInfo<>(0x2A, PacketPlayServerWorldParticles.class, "PacketPlayOutWorldParticles"),
                        new PacketInfo<>(0x2B, PacketPlayServerGameStateChange.class, "PacketPlayOutGameStateChange"),
                        new PacketInfo<>(0x2C, GPacketPlayServerSpawnEntityWeather.class, "PacketPlayOutSpawnEntityWeather"),
                        new PacketInfo<>(0x2D, GPacketPlayServerWindowOpen.class, "PacketPlayOutOpenWindow"),
                        new PacketInfo<>(0x2E, GPacketPlayServerWindowClose.class, "PacketPlayOutCloseWindow"),
                        new PacketInfo<>(0x2F, PacketPlayServerSetSlot.class, "PacketPlayOutSetSlot"),
                        new PacketInfo<>(0x30, PacketPlayServerWindowItems.class, "PacketPlayOutWindowItems"),
                        new PacketInfo<>(0x31, PacketPlayServerWindowData.class, "PacketPlayOutWindowData"),
                        new PacketInfo<>(0x32, GPacketPlayServerTransaction.class, "PacketPlayOutTransaction"),
                        new PacketInfo<>(0x33, PacketPlayServerUpdateSign.class, "PacketPlayOutUpdateSign"),
                        new PacketInfo<>(0x34, PacketPlayServerMap.class, "PacketPlayOutMap"),
                        new PacketInfo<>(0x35, PacketPlayServerTileEntityData.class, "PacketPlayOutTileEntityData"),
                        new PacketInfo<>(0x36, PacketPlayServerOpenSignEditor.class, "PacketPlayOutOpenSignEditor"),
                        new PacketInfo<>(0x37, PacketPlayServerStatistic.class, "PacketPlayOutStatistic"),
                        new PacketInfo<>(0x38, PacketPlayServerPlayerInfo.class, "PacketPlayOutPlayerInfo"),
                        new PacketInfo<>(0x39, GPacketPlayServerAbilities.class, "PacketPlayOutAbilities"),
                        new PacketInfo<>(0x3A, GPacketPlayServerTabComplete.class, "PacketPlayOutTabComplete"),
                        new PacketInfo<>(0x3B, PacketPlayServerScoreboardObjective.class, "PacketPlayOutScoreboardObjective"),
                        new PacketInfo<>(0x3C, PacketPlayServerScoreboardScore.class, "PacketPlayOutScoreboardScore"),
                        new PacketInfo<>(0x3D, PacketPlayServerScoreboardDisplayObjective.class, "PacketPlayOutScoreboardDisplayObjective"),
                        new PacketInfo<>(0x3E, PacketPlayServerScoreboardTeam.class, "PacketPlayOutScoreboardTeam"),
                        new PacketInfo<>(0x3F, GPacketPlayServerCustomPayload.class, "PacketPlayOutCustomPayload"),
                        new PacketInfo<>(0x40, GPacketPlayServerKickDisconnect.class, "PacketPlayOutKickDisconnect"), //
                        new PacketInfo<>(0x41, PacketPlayServerServerDifficulty.class, "PacketPlayOutServerDifficulty"),
                        new PacketInfo<>(0x42, PacketPlayServerCombatEvent.class, "PacketPlayOutCombatEvent"),
                        new PacketInfo<>(0x43, PacketPlayServerCamera.class, "PacketPlayOutCamera"),
                        new PacketInfo<>(0x44, PacketPlayServerWorldBorder.class, "PacketPlayOutWorldBorder"),
                        new PacketInfo<>(0x45, PacketPlayServerTitle.class, "PacketPlayOutTitle"),
                        new PacketInfo<>(0x46, PacketPlayServerSetCompression.class, "PacketPlayOutSetCompression"),
                        new PacketInfo<>(0x47, PacketPlayServerPlayerListHeaderFooter.class, "PacketPlayOutPlayListHeaderFooter"),
                        new PacketInfo<>(0x48, GPacketPlayServerResourcePackSend.class, "PacketPlayOutResourcePackSend"),
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
                        new PacketInfo<>(0x00, GPacketLoginClientStart.class, "PacketLoginInStart"),
                        new PacketInfo<>(0x01, GPacketLoginClientEncryptionBegin.class, "PacketLoginInEncryptionBegin")

                    },

                new PacketInfo[] {
                        new PacketInfo<>(0x00, GPacketLoginServerDisconnect.class, "PacketLoginOutDisconnect"),
                        new PacketInfo<>(0x01, GPacketLoginServerEncryptionBegin.class, "PacketLoginOutEncryptionBegin"),
                        new PacketInfo<>(0x02, GPacketLoginServerSuccess.class, "PacketLoginOutSuccess"),
                        new PacketInfo<>(0x03, GPacketLoginServerSetCompression.class, "PacketLoginOutSetCompression")
                }
            )
    );


    private final PacketPair packetPair;


    @Override
    public GPacket getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        final Map<Integer, PacketInfo> packets = direction.equals(ProtocolDirection.IN) ? packetPair.getClient() : packetPair.getServer();

        final PacketInfo packetInfo = packets.get(id);

        if (packetInfo == null) {
            throw new IllegalStateException(playerId + " -> Packet of id " + id + " had no associated information (dir: "
                    + direction + " ver: " + version + " type: " + ordinal() + ")");
        }

        final Constructor<? extends GPacket> packetConstructor = packetInfo.getConstructor();

        if (packetConstructor == null) {
            throw new IllegalStateException(playerId + " -> Packet of id " + id
                    + " had no associated constructor (dir: " + direction + " ver: " + version + " type: " + ordinal() + ")");
        }

        return packetConstructor.newInstance(playerId, version);
    }

    @Override
    public int getPacketId(ProtocolDirection direction, GPacket packet) {
        final Map<Integer, PacketInfo> packets = direction.equals(ProtocolDirection.IN) ? packetPair.getClient() : packetPair.getServer();
        return packets.values()
                .stream()
                .filter(packetInfo -> packetInfo.getClazz().equals(packet.getClass()))
                .map(PacketInfo::getId)
                .findFirst()
                .orElse(-1);
    }

    @Override
    public Class<? extends GPacket> getPacketClass(ProtocolDirection direction, String name) {
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
