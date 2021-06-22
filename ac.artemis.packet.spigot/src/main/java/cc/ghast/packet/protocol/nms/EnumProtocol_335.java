package cc.ghast.packet.protocol.nms;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.utils.PacketPair;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.PacketInfo;
import cc.ghast.packet.wrapper.packet.handshake.GPacketHandshakeClientSetProtocol;
import cc.ghast.packet.wrapper.packet.handshake.GPacketHandshakeLegacyServerListPing;
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
public enum EnumProtocol_335 implements EnumProtocol {
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
                        new PacketInfo<>(0x00, PacketPlayClientConfirmTeleport.class, "PacketPlayInTeleportAccept"),
                        new PacketInfo<>(0x01, PacketPlayClientCraftingPrepareGrid.class, "PacketPlayInCraftingPrepareGrid"),
                        new PacketInfo<>(0x02, PacketPlayClientTabComplete.class, "PacketPlayInTabComplete"),
                        new PacketInfo<>(0x03, PacketPlayClientChat.class, "PacketPlayInChat"),
                        new PacketInfo<>(0x04, PacketPlayClientClientCommand.class,"PacketPlayInClientCommand"),
                        new PacketInfo<>(0x05, PacketPlayClientSettings.class,"PacketPlayInSettings"),
                        new PacketInfo<>(0x06, PacketPlayClientTransaction.class,"PacketPlayInTransaction"),
                        new PacketInfo<>(0x07, PacketPlayClientEnchantItem.class,"PacketPlayInEnchantItem"),
                        new PacketInfo<>(0x08, PacketPlayClientWindowClick.class,"PacketPlayInWindowClick"),
                        new PacketInfo<>(0x09, PacketPlayClientWindowClose.class,"PacketPlayInCloseWindow"),
                        new PacketInfo<>(0x0A, PacketPlayClientCustomPayload.class,"PacketPlayInCustomPayload"),
                        new PacketInfo<>(0x0B, PacketPlayClientUseEntity.class, "PacketPlayInUseEntity"),
                        new PacketInfo<>(0x0C, PacketPlayClientKeepAlive.class, "PacketPlayInKeepAlive"),
                        new PacketInfo<>(0x0D, PacketPlayClientFlying.class, "PacketPlayInFlying"),
                        new PacketInfo<>(0x0E, PacketPlayClientFlying.PacketPlayClientPosition.class,"PacketPlayInPosition"),
                        new PacketInfo<>(0x0F, PacketPlayClientFlying.PacketPlayClientPositionLook.class,"PacketPlayInPositionLook"),
                        new PacketInfo<>(0x10, PacketPlayClientFlying.PacketPlayClientLook.class,"PacketPlayInLook"),
                        new PacketInfo<>(0x11, PacketPlayClientVehicleMove.class,"PacketPlayInVehicleMove"),
                        new PacketInfo<>(0x12, PacketPlayClientBoatMove.class, "PacketPlayInSteerBoat"),
                        new PacketInfo<>(0x13, PacketPlayClientAbilities.class,"PacketPlayInAbilities"),
                        new PacketInfo<>(0x14, PacketPlayClientBlockDig.class,"PacketPlayInBlockDig"),
                        new PacketInfo<>(0x15, PacketPlayClientEntityAction.class,"PacketPlayInEntityAction"),
                        new PacketInfo<>(0x16, PacketPlayClientSteerVehicle.class,"PacketPlayInSteerVehicle"),
                        new PacketInfo<>(0x17, PacketPlayClientCraftingBookData.class, "PacketPlayInCraftBookData"),
                        new PacketInfo<>(0x18, PacketPlayClientResourcePackStatus.class, "PacketPlayInResourcePackStatus"),
                        new PacketInfo<>(0x19, PacketPlayClientAdvancementTab.class, "PacketPlayInAdvancementTab"),
                        new PacketInfo<>(0x1A, PacketPlayClientHeldItemSlot.class,"PacketPlayInHeldItemSlot"),
                        new PacketInfo<>(0x1B, PacketPlayClientSetCreativeSlot.class,"PacketPlayInSetCreativeSlot"),
                        new PacketInfo<>(0x1C, PacketPlayClientUpdateSign.class,"PacketPlayInUpdateSign"),
                        new PacketInfo<>(0x1D, PacketPlayClientArmAnimation.class,"PacketPlayInArmAnimation"),
                        new PacketInfo<>(0x1E, PacketPlayClientSpectate.class, "PacketPlayInSpectate"),
                        new PacketInfo<>(0x1F, PacketPlayClientBlockPlace.class,"PacketPlayInBlockPlace"),
                        new PacketInfo<>(0x20, PacketPlayClientItemUse.class, "PacketPlayInUseItem")

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
                        new PacketInfo<>(0x25, GPacketPlayServerEntity.class, "PacketPlayOutEntity"),
                        new PacketInfo<>(0x26, GPacketPlayServerEntity.GPacketPlayServerRelEntityMove.class, "PacketPlayOutRelEntityMove"),
                        new PacketInfo<>(0x27, GPacketPlayServerEntity.GPacketPlayServerRelEntityMoveLook.class, "PacketPlayOutRelEntityMoveLook"),
                        new PacketInfo<>(0x28, GPacketPlayServerEntity.GPacketPlayServerEntityLook.class, "PacketPlayOutEntityLook"),
                        new PacketInfo<>(0x29, PacketPlayClientVehicleMove.class, "PacketPlayInVehicleMove"),
                        new PacketInfo<>(0x2A, PacketPlayServerOpenSignEditor.class, "PacketPlayOutOpenSignEditor"),
                        new PacketInfo<>(0x2B, GPacketPlayServerAbilities.class, "PacketPlayOutAbilities"),
                        new PacketInfo<>(0x2C, PacketPlayServerCombatEvent.class, "PacketPlayOutCombatEvent"),
                        // AKA Packet List Item
                        new PacketInfo<>(0x2D, PacketPlayServerPlayerInfo.class, "PacketPlayOutPlayerInfo"),
                        new PacketInfo<>(0x2E, GPacketPlayServerPosition.class, "PacketPlayOutPosition"),
                        new PacketInfo<>(0x2F, GPacketPlayServerBed.class, "PacketPlayOutBed"),
                        new PacketInfo<>(0x30, PacketPlayServerCraftingRecipeUnlock.class, "PacketPlayOutUnlockRecipe"),
                        new PacketInfo<>(0x31, GPacketPlayServerEntityDestroy.class, "PacketPlayOutEntityDestroy"),
                        new PacketInfo<>(0x32, GPacketPlayServerEntityEffectRemove.class, "PacketPlayOutRemoveEntityEffect"),
                        new PacketInfo<>(0x33, GPacketPlayServerResourcePackSend.class, "PacketPlayOutResourcePackSend"),
                        new PacketInfo<>(0x34, GPacketPlayServerRespawn.class, "PacketPlayOutRespawn"),
                        new PacketInfo<>(0x35, GPacketPlayServerEntityHeadRotation.class, "PacketPlayOutEntityHeadRotation"),
                        // Todo figure out the NMS name for this
                        new PacketInfo<>(0x36, PacketPlayServerAdvancementProgress.class, "PacketPlayOutAdvancementProgress"),
                        new PacketInfo<>(0x37, PacketPlayServerWorldBorder.class, "PacketPlayOutWorldBorder"),
                        new PacketInfo<>(0x38, PacketPlayServerCamera.class, "PacketPlayOutCamera"),
                        new PacketInfo<>(0x39, GPacketPlayServerHeldItemSlot.class, "PacketPlayOutHeldItemSlot"),
                        new PacketInfo<>(0x3A, PacketPlayServerScoreboardDisplayObjective.class, "PacketPlayOutScoreboardDisplayObjective"),
                        new PacketInfo<>(0x3B, GPacketPlayServerEntityMetadata.class, "PacketPlayOutEntityMetadata"),
                        new PacketInfo<>(0x3C, GPacketPlayServerEntityAttach.class, "PacketPlayOutAttachEntity"),
                        new PacketInfo<>(0x3D, GPacketPlayServerEntityVelocity.class, "PacketPlayOutEntityVelocity"),
                        new PacketInfo<>(0x3E, GPacketPlayServerEntityEquipment.class, "PacketPlayOutEntityEquipment"),
                        new PacketInfo<>(0x3F, PacketPlayServerExperience.class, "PacketPlayOutExperience"),
                        new PacketInfo<>(0x40, PacketPlayServerUpdateHealth.class, "PacketPlayOutUpdateHealth"),
                        new PacketInfo<>(0x41, PacketPlayServerScoreboardObjective.class, "PacketPlayOutScoreboardObjective"),
                        new PacketInfo<>(0x42, PacketPlayServerSetPassengers.class, "PacketPlayOutMount"),
                        new PacketInfo<>(0x43, PacketPlayServerScoreboardTeam.class, "PacketPlayOutScoreboardTeam"),
                        new PacketInfo<>(0x44, PacketPlayServerScoreboardScore.class, "PacketPlayOutScoreboardScore"),
                        new PacketInfo<>(0x45, PacketPlayServerSpawnPosition.class, "PacketPlayOutSpawnPosition"),
                        new PacketInfo<>(0x46, PacketPlayServerUpdateTime.class, "PacketPlayOutUpdateTime"),
                        new PacketInfo<>(0x47, PacketPlayServerTitle.class, "PacketPlayOutTitle"),
                        new PacketInfo<>(0x48, PacketPlayServerSoundEffectCustom.class, "PacketPlayOutCustomSoundEffect"),
                        new PacketInfo<>(0x49, PacketPlayServerPlayerListHeaderFooter.class, "PacketPlayOutPlayListHeaderFooter"),
                        new PacketInfo<>(0x4A, PacketPlayServerCollect.class, "PacketPlayOutCollect"),
                        new PacketInfo<>(0x4B, GPacketPlayServerEntityTeleport.class, "PacketPlayOutEntityTeleport"),
                        // Todo find NMS name
                        new PacketInfo<>(0x4C, PacketPlayServerAdvancements.class, "PacketPlayOutAdvancements"),
                        new PacketInfo<>(0x4D, GPacketPlayServerUpdateAttributes.class,  "PacketPlayOutUpdateAttributes"),
                        new PacketInfo<>(0x4E, GPacketPlayServerEntityEffect.class,"PacketPlayOutEntityEffect"),
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
