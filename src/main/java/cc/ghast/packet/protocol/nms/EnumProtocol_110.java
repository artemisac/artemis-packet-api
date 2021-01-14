package cc.ghast.packet.protocol.nms;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.utils.PacketPair;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.PacketInfo;
import cc.ghast.packet.wrapper.packet.handshake.PacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.handshake.PacketHandshakeLegacyServerListPing;
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
 * @author Ghast
 * @since 29/12/2020
 * ArtemisPacket © 2020
 */

@AllArgsConstructor
@Getter
public enum EnumProtocol_110 implements EnumProtocol {
    HANDSHAKE(
            new PacketPair(
                new PacketInfo[]{
                        new PacketInfo<>(0x00, PacketHandshakeClientSetProtocol.class, "PacketHandshakingInSetProtocol"),
                        // Todo double check NMS name
                        new PacketInfo<>(0xFE, PacketHandshakeLegacyServerListPing.class, "PacketHandshakingLegacyServerListPing")
                },
                new PacketInfo[]{}
            )
    ),

    PLAY(
            new PacketPair(
                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketPlayClientConfirmTeleport.class, "PacketPlayInTeleportAccept"),
                        new PacketInfo<>(0x01, PacketPlayClientTabComplete.class, "PacketPlayInTabComplete"),
                        new PacketInfo<>(0x02, PacketPlayClientChat.class, "PacketPlayInChat"),
                        new PacketInfo<>(0x03, PacketPlayClientClientCommand.class,"PacketPlayInClientCommand"),
                        new PacketInfo<>(0x04, PacketPlayClientSettings.class,"PacketPlayInSettings"),
                        new PacketInfo<>(0x05, PacketPlayClientTransaction.class,"PacketPlayInTransaction"),
                        new PacketInfo<>(0x06, PacketPlayClientEnchantItem.class,"PacketPlayInEnchantItem"),
                        new PacketInfo<>(0x07, PacketPlayClientWindowClick.class,"PacketPlayInWindowClick"),
                        new PacketInfo<>(0x08, PacketPlayClientWindowClose.class,"PacketPlayInCloseWindow"),
                        new PacketInfo<>(0x09, PacketPlayClientCustomPayload.class,"PacketPlayInCustomPayload"),
                        new PacketInfo<>(0x0A, PacketPlayClientUseEntity.class, "PacketPlayInUseEntity"),
                        new PacketInfo<>(0x0B, PacketPlayClientKeepAlive.class, "PacketPlayInKeepAlive"),
                        new PacketInfo<>(0x0C, PacketPlayClientFlying.PacketPlayClientPosition.class,"PacketPlayInPosition"),
                        new PacketInfo<>(0x0D, PacketPlayClientFlying.PacketPlayClientPositionLook.class,"PacketPlayInPositionLook"),
                        new PacketInfo<>(0x0E, PacketPlayClientFlying.PacketPlayClientLook.class,"PacketPlayInLook"),
                        new PacketInfo<>(0x0F, PacketPlayClientFlying.class, "PacketPlayInFlying"),
                        new PacketInfo<>(0x10, PacketPlayClientVehicleMove.class,"PacketPlayInVehicleMove"),
                        new PacketInfo<>(0x11, PacketPlayClientBoatMove.class, "PacketPlayInSteerBoat"),
                        new PacketInfo<>(0x12, PacketPlayClientAbilities.class,"PacketPlayInAbilities"),
                        new PacketInfo<>(0x13, PacketPlayClientBlockDig.class,"PacketPlayInBlockDig"),
                        new PacketInfo<>(0x14, PacketPlayClientEntityAction.class,"PacketPlayInEntityAction"),
                        new PacketInfo<>(0x15, PacketPlayClientSteerVehicle.class,"PacketPlayInSteerVehicle"),
                        new PacketInfo<>(0x16, PacketPlayClientResourcePackStatus.class, "PacketPlayInResourcePackStatus"),
                        new PacketInfo<>(0x17, PacketPlayClientHeldItemSlot.class,"PacketPlayInHeldItemSlot"),
                        new PacketInfo<>(0x18, PacketPlayClientSetCreativeSlot.class,"PacketPlayInSetCreativeSlot"),
                        new PacketInfo<>(0x19, PacketPlayClientUpdateSign.class,"PacketPlayInUpdateSign"),
                        new PacketInfo<>(0x1A, PacketPlayClientArmAnimation.class,"PacketPlayInArmAnimation"),
                        new PacketInfo<>(0x1B, PacketPlayClientSpectate.class, "PacketPlayInSpectate"),
                        new PacketInfo<>(0x1C, PacketPlayClientBlockPlace.class,"PacketPlayInBlockPlace"),
                        new PacketInfo<>(0x1D, PacketPlayClientItemUse.class, "PacketPlayInUseItem")

                },
                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketPlayServerSpawnObject.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo<>(0x01, PacketPlayServerSpawnEntityExperienceOrb.class, "PacketPlayOutSpawnEntityExperienceOrb"),
                        /*
                         * Corresponds to wiki.vg's 'global entity'
                         */
                        new PacketInfo<>(0x02, PacketPlayServerSpawnEntityWeather.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo<>(0x03, PacketPlayServerSpawnEntityLiving.class, "PacketPlayOutSpawnEntityLiving"),
                        new PacketInfo<>(0x04, PacketPlayServerSpawnEntityPainting.class, "PacketPlayOutSpawnEntityPainting"),
                        new PacketInfo<>(0x05, PacketPlayServerSpawnNamedEntity.class, "PacketPlayOutNamedEntitySpawn"),
                        new PacketInfo<>(0x06, PacketPlayServerAnimation.class, "PacketPlayOutAnimation"),
                        new PacketInfo<>(0x07, PacketPlayServerStatistic.class, "PacketPlayOutStatistic"),
                        new PacketInfo<>(0x08, PacketPlayServerBlockBreakAnimation.class, "PacketPlayOutBlockBreakAnimation"),
                        new PacketInfo<>(0x09, PacketPlayServerTileEntityData.class, "PacketPlayOutTileEntityData"),
                        new PacketInfo<>(0x0A, PacketPlayServerBlockAction.class, "PacketPlayOutBlockAction"),
                        new PacketInfo<>(0x0B, PacketPlayServerBlockChange.class, "PacketPlayOutBlockChange"),
                        new PacketInfo<>(0x0C, PacketPlayServerBossBar.class, "PacketPlayOutBoss"),
                        new PacketInfo<>(0x0D, PacketPlayServerServerDifficulty.class, "PacketPlayOutServerDifficulty"),
                        new PacketInfo<>(0x0E, PacketPlayServerTabComplete.class, "PacketPlayOutTabComplete"),
                        new PacketInfo<>(0x0F, PacketPlayServerChat.class, "PacketPlayOutChat"),
                        new PacketInfo<>(0x10, PacketPlayServerBlockChangeMulti.class, "PacketPlayOutMultiBlockChange"),
                        new PacketInfo<>(0x11, PacketPlayServerTransaction.class, "PacketPlayOutTransaction"),
                        new PacketInfo<>(0x12, PacketPlayClientWindowClose.class, "PacketPlayInCloseWindow"),
                        new PacketInfo<>(0x13, PacketPlayServerWindowOpen.class, "PacketPlayOutOpenWindow"),
                        new PacketInfo<>(0x14, PacketPlayServerWindowItems.class, "PacketPlayOutWindowItems"),
                        new PacketInfo<>(0x15, PacketPlayServerWindowData.class, "PacketPlayOutWindowData"),
                        new PacketInfo<>(0x16, PacketPlayServerSetSlot.class, "PacketPlayOutSetSlot"),
                        new PacketInfo<>(0x17, PacketPlayServerSetCooldown.class, "PacketPlayOutSetCooldown"),
                        new PacketInfo<>(0x18, PacketPlayServerCustomPayload.class, "PacketPlayOutCustomPayload"),
                        new PacketInfo<>(0x19, PacketPlayServerSoundEffectNamed.class, "PacketPlayOutNameSoundEffect"),
                        new PacketInfo<>(0x1A, PacketPlayServerKickDisconnect.class, "PacketPlayOutKickDisconnect"),
                        new PacketInfo<>(0x1B, PacketPlayServerEntityStatus.class, "PacketPlayOutEntityStatus"),
                        new PacketInfo<>(0x1C, PacketPlayServerExplosion.class, "PacketPlayOutExplosion"),
                        new PacketInfo<>(0x1D, PacketPlayServerChunkUnload.class, "PacketPlayOutUnloadChunk"),
                        new PacketInfo<>(0x1E, PacketPlayServerGameStateChange.class, "PacketPlayOutGameStateChange"),
                        new PacketInfo<>(0x1F, PacketPlayServerKeepAlive.class, "PacketPlayOutKeepAlive"),
                        // AKA Chunk Data
                        new PacketInfo<>(0x20, PacketPlayServerChunkLoad.class, "PacketPlayOutMap"),
                        // AKA Effect
                        new PacketInfo<>(0x21, PacketPlayServerWorldEvent.class, "PacketPlayOutWorldEvent"),
                        // AKA Particles
                        new PacketInfo<>(0x22, PacketPlayServerWorldParticles.class, "PacketPlayOutWorldParticles"),
                        // AKA Join Game
                        new PacketInfo<>(0x23, PacketPlayServerLogin.class, "PacketPlayOutLogin"),
                        // AKA Map
                        new PacketInfo<>(0x24, PacketPlayServerMap.class, "PacketPlayOutMap"),
                        new PacketInfo<>(0x25, PacketPlayServerEntity.PacketPlayServerRelEntityMove.class, "PacketPlayOutRelEntityMove"),
                        new PacketInfo<>(0x26, PacketPlayServerEntity.PacketPlayServerRelEntityMoveLook.class, "PacketPlayOutRelEntityMoveLook"),
                        new PacketInfo<>(0x27, PacketPlayServerEntity.PacketPlayServerEntityLook.class, "PacketPlayOutEntityLook"),
                        new PacketInfo<>(0x28, PacketPlayServerEntity.class, "PacketPlayOutEntity"),
                        new PacketInfo<>(0x29, PacketPlayClientVehicleMove.class, "PacketPlayInVehicleMove"),
                        new PacketInfo<>(0x2A, PacketPlayServerOpenSignEditor.class, "PacketPlayOutOpenSignEditor"),
                        new PacketInfo<>(0x2B, PacketPlayServerAbilities.class, "PacketPlayOutAbilities"),
                        new PacketInfo<>(0x2C, PacketPlayServerCombatEvent.class, "PacketPlayOutCombatEvent"),
                        // AKA Packet List Item
                        new PacketInfo<>(0x2D, PacketPlayServerPlayerInfo.class, "PacketPlayOutPlayerInfo"),
                        new PacketInfo<>(0x2E, PacketPlayServerPosition.class, "PacketPlayOutPosition"),
                        new PacketInfo<>(0x2F, PacketPlayServerBed.class, "PacketPlayOutBed"),
                        new PacketInfo<>(0x30, PacketPlayServerEntityDestroy.class, "PacketPlayOutEntityDestroy"),
                        new PacketInfo<>(0x31, PacketPlayServerEntityEffectRemove.class, "PacketPlayOutRemoveEntityEffect"),
                        new PacketInfo<>(0x32, PacketPlayServerResourcePackSend.class, "PacketPlayOutResourcePackSend"),
                        new PacketInfo<>(0x33, PacketPlayServerRespawn.class, "PacketPlayOutRespawn"),
                        new PacketInfo<>(0x34, PacketPlayServerEntityHeadRotation.class, "PacketPlayOutEntityHeadRotation"),
                        new PacketInfo<>(0x35, PacketPlayServerWorldBorder.class, "PacketPlayOutWorldBorder"),
                        new PacketInfo<>(0x36, PacketPlayServerCamera.class, "PacketPlayOutCamera"),
                        new PacketInfo<>(0x37, PacketPlayServerHeldItemSlot.class, "PacketPlayOutHeldItemSlot"),
                        new PacketInfo<>(0x38, PacketPlayServerScoreboardDisplayObjective.class, "PacketPlayOutScoreboardDisplayObjective"),
                        new PacketInfo<>(0x39, PacketPlayServerEntityMetadata.class, "PacketPlayOutEntityMetadata"),
                        new PacketInfo<>(0x3A, PacketPlayServerEntityAttach.class, "PacketPlayOutAttachEntity"),
                        new PacketInfo<>(0x3B, PacketPlayServerEntityVelocity.class, "PacketPlayOutEntityVelocity"),
                        new PacketInfo<>(0x3C, PacketPlayServerEntityEquipment.class, "PacketPlayOutEntityEquipment"),
                        new PacketInfo<>(0x3D, PacketPlayServerExperience.class, "PacketPlayOutExperience"),
                        new PacketInfo<>(0x3E, PacketPlayServerUpdateHealth.class, "PacketPlayOutUpdateHealth"),
                        new PacketInfo<>(0x3F, PacketPlayServerScoreboardObjective.class, "PacketPlayOutScoreboardObjective"),
                        new PacketInfo<>(0x40, PacketPlayServerSetPassengers.class, "PacketPlayOutMount"),
                        new PacketInfo<>(0x41, PacketPlayServerScoreboardTeam.class, "PacketPlayOutScoreboardTeam"),
                        new PacketInfo<>(0x42, PacketPlayServerScoreboardScore.class, "PacketPlayOutScoreboardScore"),
                        new PacketInfo<>(0x43, PacketPlayServerSpawnPosition.class, "PacketPlayOutSpawnPosition"),
                        new PacketInfo<>(0x44, PacketPlayServerUpdateTime.class, "PacketPlayOutUpdateTime"),
                        new PacketInfo<>(0x45, PacketPlayServerTitle.class, "PacketPlayOutTitle"),
                        new PacketInfo<>(0x46, PacketPlayServerSoundEffectCustom.class, "PacketPlayOutCustomSoundEffect"),
                        new PacketInfo<>(0x47, PacketPlayServerPlayerListHeaderFooter.class, "PacketPlayOutPlayListHeaderFooter"),
                        new PacketInfo<>(0x48, PacketPlayServerCollect.class, "PacketPlayOutCollect"),
                        new PacketInfo<>(0x49, PacketPlayServerEntityTeleport.class, "PacketPlayOutEntityTeleport"),
                        new PacketInfo<>(0x4A, PacketPlayServerEntityMetadata.class,  "PacketPlayOutEntityMetadata"),
                        new PacketInfo<>(0x4B, PacketPlayServerEntityEffect.class,"PacketPlayOutEntityEffect"),
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
    @SneakyThrows
    public Packet<?> getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) {
        final Map<Integer, PacketInfo> packets = direction.equals(ProtocolDirection.IN) ? packetPair.getClient() : packetPair.getServer();
        final Constructor<? extends Packet<?>> packetConstructor = packets.get(id).getConstructor();

        if (packetConstructor == null) {
            throw new IllegalStateException(playerId + " -> Packet of id " + id + " had no associated constructor (dir: " + direction + " ver:" + version + ")");
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
