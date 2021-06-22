package cc.ghast.packet.protocol.nms;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.utils.PacketPair;
import cc.ghast.packet.protocol.EnumProtocol;
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
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.UUID;

/**
 * Protocol for 1.9-1.9.1.
 * @author Ghast
 * @since 29/12/2020
 * ArtemisPacket © 2020
 */

@AllArgsConstructor
@Getter
public enum EnumProtocol_107 implements EnumProtocol {
    HANDSHAKE(
            new PacketPair(
                new PacketInfo[]{
                        new PacketInfo<>(0x00, GPacketHandshakeClientSetProtocol.class, "PacketHandshakingInSetProtocol"),
                        // Todo double check NMS name
                        new PacketInfo<>(0xFE, GPacketHandshakeLegacyServerListPing.class, "PacketHandshakingLegacyServerListPing")
                },
                new PacketInfo[]{}
            )
    ),

    PLAY(
            new PacketPair(
                new PacketInfo[] {
                        new PacketInfo<>(0x00, GPacketPlayClientConfirmTeleport.class, "PacketPlayInTeleportAccept"),
                        new PacketInfo<>(0x01, GPacketPlayClientTabComplete.class, "PacketPlayInTabComplete"),
                        new PacketInfo<>(0x02, GPacketPlayClientChat.class, "PacketPlayInChat"),
                        new PacketInfo<>(0x03, GPacketPlayClientClientCommand.class,"PacketPlayInClientCommand"),
                        new PacketInfo<>(0x04, GPacketPlayClientSettings.class,"PacketPlayInSettings"),
                        new PacketInfo<>(0x05, GPacketPlayClientTransaction.class,"PacketPlayInTransaction"),
                        new PacketInfo<>(0x06, GPacketPlayClientEnchantItem.class,"PacketPlayInEnchantItem"),
                        new PacketInfo<>(0x07, GPacketPlayClientWindowClick.class,"PacketPlayInWindowClick"),
                        new PacketInfo<>(0x08, GPacketPlayClientWindowClose.class,"PacketPlayInCloseWindow"),
                        new PacketInfo<>(0x09, GPacketPlayClientCustomPayload.class,"PacketPlayInCustomPayload"),
                        new PacketInfo<>(0x0A, GPacketPlayClientUseEntity.class, "PacketPlayInUseEntity"),
                        new PacketInfo<>(0x0B, GPacketPlayClientKeepAlive.class, "PacketPlayInKeepAlive"),
                        new PacketInfo<>(0x0C, GPacketPlayClientFlying.PacketPlayClientPosition.class,"PacketPlayInPosition"),
                        new PacketInfo<>(0x0D, GPacketPlayClientFlying.PacketPlayClientPositionLook.class,"PacketPlayInPositionLook"),
                        new PacketInfo<>(0x0E, GPacketPlayClientFlying.PacketPlayClientLook.class,"PacketPlayInLook"),
                        new PacketInfo<>(0x0F, GPacketPlayClientFlying.class, "PacketPlayInFlying"),
                        new PacketInfo<>(0x10, GPacketPlayClientVehicleMove.class,"PacketPlayInVehicleMove"),
                        new PacketInfo<>(0x11, GPacketPlayClientBoatMove.class, "PacketPlayInSteerBoat"),
                        new PacketInfo<>(0x12, GPacketPlayClientAbilities.class,"PacketPlayInAbilities"),
                        new PacketInfo<>(0x13, GPacketPlayClientBlockDig.class,"PacketPlayInBlockDig"),
                        new PacketInfo<>(0x14, GPacketPlayClientEntityAction.class,"PacketPlayInEntityAction"),
                        new PacketInfo<>(0x15, GPacketPlayClientSteerVehicle.class,"PacketPlayInSteerVehicle"),
                        new PacketInfo<>(0x16, GPacketPlayClientResourcePackStatus.class, "PacketPlayInResourcePackStatus"),
                        new PacketInfo<>(0x17, GPacketPlayClientHeldItemSlot.class,"PacketPlayInHeldItemSlot"),
                        new PacketInfo<>(0x18, GPacketPlayClientSetCreativeSlot.class,"PacketPlayInSetCreativeSlot"),
                        new PacketInfo<>(0x19, GPacketPlayClientUpdateSign.class,"PacketPlayInUpdateSign"),
                        new PacketInfo<>(0x1A, GPacketPlayClientArmAnimation.class,"PacketPlayInArmAnimation"),
                        new PacketInfo<>(0x1B, GPacketPlayClientSpectate.class, "PacketPlayInSpectate"),
                        new PacketInfo<>(0x1C, GPacketPlayClientBlockPlace.class,"PacketPlayInBlockPlace"),
                        new PacketInfo<>(0x1D, GPacketPlayClientItemUse.class, "PacketPlayInUseItem")

                },
                new PacketInfo[] {
                        new PacketInfo<>(0x00, GPacketPlayServerSpawnObject.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo<>(0x01, PacketPlayServerSpawnEntityExperienceOrb.class, "PacketPlayOutSpawnEntityExperienceOrb"),
                        /*
                         * Corresponds to wiki.vg's 'global entity'
                         */
                        new PacketInfo<>(0x02, GPacketPlayServerSpawnEntityWeather.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo<>(0x03, PacketPlayServerSpawnEntityLiving.class, "PacketPlayOutSpawnEntityLiving"),
                        new PacketInfo<>(0x04, PacketPlayServerSpawnEntityPainting.class, "PacketPlayOutSpawnEntityPainting"),
                        new PacketInfo<>(0x05, GPacketPlayServerSpawnNamedEntity.class, "PacketPlayOutNamedEntitySpawn"),
                        new PacketInfo<>(0x06, GPacketPlayServerAnimation.class, "PacketPlayOutAnimation"),
                        new PacketInfo<>(0x07, PacketPlayServerStatistic.class, "PacketPlayOutStatistic"),
                        new PacketInfo<>(0x08, GPacketPlayServerBlockBreakAnimation.class, "PacketPlayOutBlockBreakAnimation"),
                        new PacketInfo<>(0x09, PacketPlayServerTileEntityData.class, "PacketPlayOutTileEntityData"),
                        new PacketInfo<>(0x0A, GPacketPlayServerBlockAction.class, "PacketPlayOutBlockAction"),
                        new PacketInfo<>(0x0B, GPacketPlayServerBlockChange.class, "PacketPlayOutBlockChange"),
                        new PacketInfo<>(0x0C, PacketPlayServerBossBar.class, "PacketPlayOutBoss"),
                        new PacketInfo<>(0x0D, PacketPlayServerServerDifficulty.class, "PacketPlayOutServerDifficulty"),
                        new PacketInfo<>(0x0E, GPacketPlayServerTabComplete.class, "PacketPlayOutTabComplete"),
                        new PacketInfo<>(0x0F, GPacketPlayServerChat.class, "PacketPlayOutChat"),
                        new PacketInfo<>(0x10, GPacketPlayServerBlockChangeMulti.class, "PacketPlayOutMultiBlockChange"),
                        new PacketInfo<>(0x11, GPacketPlayServerTransaction.class, "PacketPlayOutTransaction"),
                        new PacketInfo<>(0x12, PacketPlayClientWindowClose.class, "PacketPlayInCloseWindow"),
                        new PacketInfo<>(0x13, GPacketPlayServerWindowOpen.class, "PacketPlayOutOpenWindow"),
                        new PacketInfo<>(0x14, PacketPlayServerWindowItems.class, "PacketPlayOutWindowItems"),
                        new PacketInfo<>(0x15, PacketPlayServerWindowData.class, "PacketPlayOutWindowData"),
                        new PacketInfo<>(0x16, PacketPlayServerSetSlot.class, "PacketPlayOutSetSlot"),
                        new PacketInfo<>(0x17, PacketPlayServerSetCooldown.class, "PacketPlayOutSetCooldown"),
                        new PacketInfo<>(0x18, GPacketPlayServerCustomPayload.class, "PacketPlayOutCustomPayload"),
                        new PacketInfo<>(0x19, PacketPlayServerSoundEffectNamed.class, "PacketPlayOutNameSoundEffect"),
                        new PacketInfo<>(0x1A, GPacketPlayServerKickDisconnect.class, "PacketPlayOutKickDisconnect"),
                        new PacketInfo<>(0x1B, GPacketPlayServerEntityStatus.class, "PacketPlayOutEntityStatus"),
                        new PacketInfo<>(0x1C, GPacketPlayServerExplosion.class, "PacketPlayOutExplosion"),
                        new PacketInfo<>(0x1D, GPacketPlayServerChunkUnload.class, "PacketPlayOutUnloadChunk"),
                        new PacketInfo<>(0x1E, PacketPlayServerGameStateChange.class, "PacketPlayOutGameStateChange"),
                        new PacketInfo<>(0x1F, GPacketPlayServerKeepAlive.class, "PacketPlayOutKeepAlive"),
                        // AKA Chunk Data
                        new PacketInfo<>(0x20, GPacketPlayServerChunkLoad.class, "PacketPlayOutMap"),
                        // AKA Effect
                        new PacketInfo<>(0x21, PacketPlayServerWorldEvent.class, "PacketPlayOutWorldEvent"),
                        // AKA Particles
                        new PacketInfo<>(0x22, PacketPlayServerWorldParticles.class, "PacketPlayOutWorldParticles"),
                        // AKA Join Game
                        new PacketInfo<>(0x23, GPacketPlayServerLogin.class, "PacketPlayOutLogin"),
                        // AKA Map
                        new PacketInfo<>(0x24, PacketPlayServerMap.class, "PacketPlayOutMap"),
                        new PacketInfo<>(0x25, GPacketPlayServerEntity.GPacketPlayServerRelEntityMove.class, "PacketPlayOutRelEntityMove"),
                        new PacketInfo<>(0x26, GPacketPlayServerEntity.GPacketPlayServerRelEntityMoveLook.class, "PacketPlayOutRelEntityMoveLook"),
                        new PacketInfo<>(0x27, GPacketPlayServerEntity.GPacketPlayServerEntityLook.class, "PacketPlayOutEntityLook"),
                        new PacketInfo<>(0x28, GPacketPlayServerEntity.class, "PacketPlayOutEntity"),
                        new PacketInfo<>(0x29, PacketPlayClientVehicleMove.class, "PacketPlayInVehicleMove"),
                        new PacketInfo<>(0x2A, PacketPlayServerOpenSignEditor.class, "PacketPlayOutOpenSignEditor"),
                        new PacketInfo<>(0x2B, GPacketPlayServerAbilities.class, "PacketPlayOutAbilities"),
                        new PacketInfo<>(0x2C, PacketPlayServerCombatEvent.class, "PacketPlayOutCombatEvent"),
                        // AKA Packet List Item
                        new PacketInfo<>(0x2D, PacketPlayServerPlayerInfo.class, "PacketPlayOutPlayerInfo"),
                        new PacketInfo<>(0x2E, GPacketPlayServerPosition.class, "PacketPlayOutPosition"),
                        new PacketInfo<>(0x2F, GPacketPlayServerBed.class, "PacketPlayOutBed"),
                        new PacketInfo<>(0x30, GPacketPlayServerEntityDestroy.class, "PacketPlayOutEntityDestroy"),
                        new PacketInfo<>(0x31, GPacketPlayServerEntityEffectRemove.class, "PacketPlayOutRemoveEntityEffect"),
                        new PacketInfo<>(0x32, GPacketPlayServerResourcePackSend.class, "PacketPlayOutResourcePackSend"),
                        new PacketInfo<>(0x33, GPacketPlayServerRespawn.class, "PacketPlayOutRespawn"),
                        new PacketInfo<>(0x34, GPacketPlayServerEntityHeadRotation.class, "PacketPlayOutEntityHeadRotation"),
                        new PacketInfo<>(0x35, PacketPlayServerWorldBorder.class, "PacketPlayOutWorldBorder"),
                        new PacketInfo<>(0x36, PacketPlayServerCamera.class, "PacketPlayOutCamera"),
                        new PacketInfo<>(0x37, GPacketPlayServerHeldItemSlot.class, "PacketPlayOutHeldItemSlot"),
                        new PacketInfo<>(0x38, PacketPlayServerScoreboardDisplayObjective.class, "PacketPlayOutScoreboardDisplayObjective"),
                        new PacketInfo<>(0x39, GPacketPlayServerEntityMetadata.class, "PacketPlayOutEntityMetadata"),
                        new PacketInfo<>(0x3A, GPacketPlayServerEntityAttach.class, "PacketPlayOutAttachEntity"),
                        new PacketInfo<>(0x3B, GPacketPlayServerEntityVelocity.class, "PacketPlayOutEntityVelocity"),
                        new PacketInfo<>(0x3C, GPacketPlayServerEntityEquipment.class, "PacketPlayOutEntityEquipment"),
                        new PacketInfo<>(0x3D, PacketPlayServerExperience.class, "PacketPlayOutExperience"),
                        new PacketInfo<>(0x3E, PacketPlayServerUpdateHealth.class, "PacketPlayOutUpdateHealth"),
                        new PacketInfo<>(0x3F, PacketPlayServerScoreboardObjective.class, "PacketPlayOutScoreboardObjective"),
                        new PacketInfo<>(0x40, PacketPlayServerSetPassengers.class, "PacketPlayOutMount"),
                        new PacketInfo<>(0x41, PacketPlayServerScoreboardTeam.class, "PacketPlayOutScoreboardTeam"),
                        new PacketInfo<>(0x42, PacketPlayServerScoreboardScore.class, "PacketPlayOutScoreboardScore"),
                        new PacketInfo<>(0x43, PacketPlayServerSpawnPosition.class, "PacketPlayOutSpawnPosition"),
                        new PacketInfo<>(0x44, PacketPlayServerUpdateTime.class, "PacketPlayOutUpdateTime"),
                        new PacketInfo<>(0x45, PacketPlayServerTitle.class, "PacketPlayOutTitle"),
                        new PacketInfo<>(0x46, PacketPlayServerUpdateSign.class, "PacketPlayOutUpdateSign"),
                        new PacketInfo<>(0x47, PacketPlayServerSoundEffectCustom.class, "PacketPlayOutCustomSoundEffect"),
                        new PacketInfo<>(0x48, PacketPlayServerPlayerListHeaderFooter.class, "PacketPlayOutPlayListHeaderFooter"),
                        new PacketInfo<>(0x49, PacketPlayServerCollect.class, "PacketPlayOutCollect"),
                        new PacketInfo<>(0x4A, GPacketPlayServerEntityTeleport.class, "PacketPlayOutEntityTeleport"),
                        new PacketInfo<>(0x4B, GPacketPlayServerUpdateAttributes.class,  "PacketPlayOutUpdateAttributes"),
                        new PacketInfo<>(0x4C, GPacketPlayServerEntityEffect.class,"PacketPlayOutEntityEffect"),
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
    @SneakyThrows
    public GPacket getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) {
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