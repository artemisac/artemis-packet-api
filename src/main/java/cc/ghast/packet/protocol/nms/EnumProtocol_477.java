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
public enum EnumProtocol_477 implements EnumProtocol {
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
                        // Todo figure out NMS name for this
                        new PacketInfo<>(0x01, PacketPlayClientBlockMetadataQuery.class, "PacketPlayInQueryBlockNBT"),
                        // Todo NMS name
                        new PacketInfo<>(0x02, PacketPlayClientDifficultySet.class, "PacketPlayInSetDifficulty"),
                        new PacketInfo<>(0x03, PacketPlayClientChat.class, "PacketPlayInChat"),
                        new PacketInfo<>(0x04, PacketPlayClientClientCommand.class,"PacketPlayInClientCommand"),
                        new PacketInfo<>(0x05, PacketPlayClientSettings.class,"PacketPlayInSettings"),
                        new PacketInfo<>(0x06, PacketPlayClientTabComplete.class, "PacketPlayInTabComplete"),
                        new PacketInfo<>(0x07, PacketPlayClientTransaction.class,"PacketPlayInTransaction"),
                        // Todo NMS name
                        new PacketInfo<>(0x08, PacketPlayClientWindowButton.class,"PacketPlayInWindowClickButton"),
                        new PacketInfo<>(0x09, PacketPlayClientWindowClick.class,"PacketPlayInWindowClick"),
                        new PacketInfo<>(0x0A, PacketPlayClientWindowClose.class,"PacketPlayInCloseWindow"),
                        // AKA Plugin Message
                        new PacketInfo<>(0x0B, PacketPlayClientCustomPayload.class,"PacketPlayInCustomPayload"),
                        // Todo find NMS name
                        new PacketInfo<>(0x0C, PacketPlayClientBookEdit.class, "PacketPlayInEditBook"),
                        // Todo find NMS name
                        new PacketInfo<>(0x0D, PacketPlayClientEntityMetadataQuery.class, "PacketPlayInQueryEntityNBT"),
                        new PacketInfo<>(0x0E, PacketPlayClientUseEntity.class, "PacketPlayInUseEntity"),
                        new PacketInfo<>(0x0F, PacketPlayClientKeepAlive.class, "PacketPlayInKeepAlive"),
                        // Todo find NMS name
                        new PacketInfo<>(0x10, PacketPlayClientDifficultyLock.class, "PacketPlayInLockDifficulty"),
                        new PacketInfo<>(0x11, PacketPlayClientFlying.PacketPlayClientPosition.class,"PacketPlayInPosition"),
                        new PacketInfo<>(0x12, PacketPlayClientFlying.PacketPlayClientPositionLook.class,"PacketPlayInPositionLook"),
                        new PacketInfo<>(0x13, PacketPlayClientFlying.PacketPlayClientLook.class,"PacketPlayInLook"),
                        new PacketInfo<>(0x14, PacketPlayClientFlying.class, "PacketPlayInFlying"),

                        new PacketInfo<>(0x15, PacketPlayClientVehicleMove.class,"PacketPlayInVehicleMove"),
                        new PacketInfo<>(0x16, PacketPlayClientBoatMove.class, "PacketPlayInSteerBoat"),
                        // Todo find NMS name
                        new PacketInfo<>(0x17, PacketPlayClientItemPick.class, "PacketPlayInPickItem"),
                        // Todo find NMS name
                        new PacketInfo<>(0x18, PacketPlayClientCraftingPrepareGrid.class, "PacketPlayInCraftingPrepareGrid"),
                        new PacketInfo<>(0x19, PacketPlayClientAbilities.class,"PacketPlayInAbilities"),
                        new PacketInfo<>(0x1A, PacketPlayClientBlockDig.class,"PacketPlayInBlockDig"),
                        new PacketInfo<>(0x1B, PacketPlayClientEntityAction.class,"PacketPlayInEntityAction"),
                        new PacketInfo<>(0x1C, PacketPlayClientSteerVehicle.class,"PacketPlayInSteerVehicle"),
                        new PacketInfo<>(0x1D, PacketPlayClientCraftingBookData.class, "PacketPlayInCraftBookData"),
                        // Todo find NMS name
                        new PacketInfo<>(0x1E, PacketPlayClientItemName.class, "PacketPlayInNameItem"),
                        new PacketInfo<>(0x1F, PacketPlayClientResourcePackStatus.class, "PacketPlayInResourcePackStatus"),
                        new PacketInfo<>(0x20, PacketPlayClientAdvancementTab.class, "PacketPlayInAdvancementTab"),
                        // Todo find NMS name
                        new PacketInfo<>(0x21, PacketPlayClientTradeSelect.class, "PacketPlayInSelectTrade"),
                        new PacketInfo<>(0x22, PacketPlayClientEffectBeaconSet.class, "PacketPlayInSetBeaconEffect"),
                        new PacketInfo<>(0x23, PacketPlayClientHeldItemSlot.class,"PacketPlayInHeldItemSlot"),
                        // Todo find NMS name
                        new PacketInfo<>(0x24, PacketPlayClientUpdateCommandBlock.class,"PacketPlayInUpdateCommandBlock"),
                        // Todo find NMS name
                        new PacketInfo<>(0x25, PacketPlayClientUpdateCommandMinecart.class, "PacketPlayInUpdateCommandBlockMinecart"),
                        new PacketInfo<>(0x26, PacketPlayClientSetCreativeSlot.class,"PacketPlayInSetCreativeSlot"),
                        // Todo find NMS name
                        new PacketInfo<>(0x27, PacketPlayClientUpdateJigsawBlock.class, "PacketPlayInUpdateJigsawBlock"),
                        // Todo Find NMS name
                        new PacketInfo<>(0x28, PacketPlayClientUpdateStructureBlock.class, "PacketPlayInUpdateStructureBlock"),
                        new PacketInfo<>(0x29, PacketPlayClientUpdateSign.class,"PacketPlayInUpdateSign"),
                        new PacketInfo<>(0x2A, PacketPlayClientArmAnimation.class,"PacketPlayInArmAnimation"),
                        new PacketInfo<>(0x2B, PacketPlayClientSpectate.class, "PacketPlayInSpectate"),
                        new PacketInfo<>(0x2C, PacketPlayClientBlockPlace.class,"PacketPlayInBlockPlace"),
                        new PacketInfo<>(0x2D, PacketPlayClientItemUse.class, "PacketPlayInUseItem")

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
                        new PacketInfo<>(0x0E, PacketPlayServerChat.class, "PacketPlayOutChat"),
                        new PacketInfo<>(0x0F, PacketPlayServerBlockChangeMulti.class, "PacketPlayOutMultiBlockChange"),
                        new PacketInfo<>(0x10, PacketPlayServerTabComplete.class, "PacketPlayOutTabComplete"),
                        // Todo figure out NMS name for this
                        new PacketInfo<>(0x11, PacketPlayServerCommandsDeclare.class, "PacketPlayOutDeclareCommands"),
                        new PacketInfo<>(0x12, PacketPlayServerTransaction.class, "PacketPlayOutTransaction"),
                        new PacketInfo<>(0x13, PacketPlayClientWindowClose.class, "PacketPlayInCloseWindow"),
                        new PacketInfo<>(0x14, PacketPlayServerWindowItems.class, "PacketPlayOutWindowItems"),
                        new PacketInfo<>(0x15, PacketPlayServerWindowData.class, "PacketPlayOutWindowData"),
                        new PacketInfo<>(0x16, PacketPlayServerSetSlot.class, "PacketPlayOutSetSlot"),
                        new PacketInfo<>(0x17, PacketPlayServerSetCooldown.class, "PacketPlayOutSetCooldown"),
                        new PacketInfo<>(0x18, PacketPlayServerCustomPayload.class, "PacketPlayOutCustomPayload"),
                        new PacketInfo<>(0x19, PacketPlayServerSoundEffectNamed.class, "PacketPlayOutNameSoundEffect"),
                        new PacketInfo<>(0x1A, PacketPlayServerKickDisconnect.class, "PacketPlayOutKickDisconnect"),
                        new PacketInfo<>(0x1B, PacketPlayServerEntityStatus.class, "PacketPlayOutEntityStatus"),
                        // Todo figure out NMS name for this, AKA NBT Query Response
                        new PacketInfo<>(0x1C, PacketPlayServerExplosion.class, "PacketPlayOutExplosion"),
                        new PacketInfo<>(0x1D, PacketPlayServerChunkUnload.class, "PacketPlayOutUnloadChunk"),
                        new PacketInfo<>(0x1E, PacketPlayServerGameStateChange.class, "PacketPlayOutGameStateChange"),
                        // Todo find NMS
                        new PacketInfo<>(0x1F, PacketPlayClientWindowHorse.class, "PacketPlayOutOpenHorseWindow"),
                        new PacketInfo<>(0x20, PacketPlayServerKeepAlive.class, "PacketPlayOutKeepAlive"),
                        // AKA Chunk Data
                        new PacketInfo<>(0x21, PacketPlayServerChunkLoad.class, "PacketPlayOutMap"),
                        // AKA Effect
                        new PacketInfo<>(0x22, PacketPlayServerWorldEvent.class, "PacketPlayOutWorldEvent"),
                        // AKA Particles
                        new PacketInfo<>(0x23, PacketPlayServerWorldParticles.class, "PacketPlayOutWorldParticles"),
                        new PacketInfo<>(0x24 , PacketPlayServerUpdateLight.class, "PacketPlayOutUpdateLight"),
                        // AKA Join Game
                        new PacketInfo<>(0x25, PacketPlayServerLogin.class, "PacketPlayOutLogin"),
                        // AKA Map
                        new PacketInfo<>(0x26, PacketPlayServerMap.class, "PacketPlayOutMap"),
                        // Todo find NMS
                        new PacketInfo<>(0x27, PacketPlayServerTradeList.class, "PacketPlayOutTradeList"),
                        new PacketInfo<>(0x28, PacketPlayServerEntity.PacketPlayServerRelEntityMove.class, "PacketPlayOutRelEntityMove"),
                        new PacketInfo<>(0x29, PacketPlayServerEntity.PacketPlayServerRelEntityMoveLook.class, "PacketPlayOutRelEntityMoveLook"),
                        new PacketInfo<>(0x2A, PacketPlayServerEntity.PacketPlayServerEntityLook.class, "PacketPlayOutEntityLook"),
                        new PacketInfo<>(0x2B, PacketPlayServerEntity.class, "PacketPlayOutEntity"),
                        new PacketInfo<>(0x2C, PacketPlayClientVehicleMove.class, "PacketPlayInVehicleMove"),
                        // Todo find NMS
                        new PacketInfo<>(0x2D, PacketPlayClientBookOpen.class, "PacketPlayInOpenBook"),
                        new PacketInfo<>(0x2E, PacketPlayServerWindowOpen.class, "PacketPlayOutOpenWindow"),

                        new PacketInfo<>(0x2F, PacketPlayServerOpenSignEditor.class, "PacketPlayOutOpenSignEditor"),
                        // Todo figure out the NMS name for this
                        new PacketInfo<>(0x30, PacketPlayServerCraftingRecipeResponse.class, "PacketPlayOutCraftingResponse"),
                        new PacketInfo<>(0x31, PacketPlayServerAbilities.class, "PacketPlayOutAbilities"),
                        new PacketInfo<>(0x32, PacketPlayServerCombatEvent.class, "PacketPlayOutCombatEvsent"),
                        // AKA Packet List Item
                        new PacketInfo<>(0x33, PacketPlayServerPlayerInfo.class, "PacketPlayOutPlayerInfo"),
                        // Todo figure out NMS name for this
                        new PacketInfo<>(0x34, PacketPlayServerPlayerFace.class, "PacketPlayOutFacePlayer"),
                        new PacketInfo<>(0x35, PacketPlayServerPosition.class, "PacketPlayOutPosition"),

                        new PacketInfo<>(0x36, PacketPlayServerCraftingRecipeUnlock.class, "PacketPlayOutUnlockRecipe"),
                        new PacketInfo<>(0x37, PacketPlayServerEntityDestroy.class, "PacketPlayOutEntityDestroy"),
                        new PacketInfo<>(0x38, PacketPlayServerEntityEffectRemove.class, "PacketPlayOutRemoveEntityEffect"),
                        new PacketInfo<>(0x39, PacketPlayServerResourcePackSend.class, "PacketPlayOutResourcePackSend"),
                        new PacketInfo<>(0x3A, PacketPlayServerRespawn.class, "PacketPlayOutRespawn"),
                        new PacketInfo<>(0x3B, PacketPlayServerEntityHeadRotation.class, "PacketPlayOutEntityHeadRotation"),
                        // Todo figure out the NMS name for this
                        new PacketInfo<>(0x3C, PacketPlayServerAdvancementProgress.class, "PacketPlayOutAdvancementProgress"),
                        new PacketInfo<>(0x3D, PacketPlayServerWorldBorder.class, "PacketPlayOutWorldBorder"),
                        new PacketInfo<>(0x3E, PacketPlayServerCamera.class, "PacketPlayOutCamera"),
                        new PacketInfo<>(0x3F, PacketPlayServerHeldItemSlot.class, "PacketPlayOutHeldItemSlot"),
                        // Todo figure out the NMS name for this
                        new PacketInfo<>(0x40, PacketPlayClientUpdateViewPosition.class, "PacketPlayOutUpdateViewPosition"),
                        // Todo figure out the NMS name for this
                        new PacketInfo<>(0x41, PacketPlayClientUpdateViewDistance.class, "PacketPlayOutUpdateViewDistance"),
                        new PacketInfo<>(0x42, PacketPlayServerScoreboardDisplayObjective.class, "PacketPlayOutScoreboardDisplayObjective"),
                        new PacketInfo<>(0x43, PacketPlayServerEntityMetadata.class, "PacketPlayOutEntityMetadata"),
                        new PacketInfo<>(0x44, PacketPlayServerEntityAttach.class, "PacketPlayOutAttachEntity"),
                        new PacketInfo<>(0x45, PacketPlayServerEntityVelocity.class, "PacketPlayOutEntityVelocity"),
                        new PacketInfo<>(0x46, PacketPlayServerEntityEquipment.class, "PacketPlayOutEntityEquipment"),
                        new PacketInfo<>(0x47, PacketPlayServerExperience.class, "PacketPlayOutExperience"),
                        new PacketInfo<>(0x48, PacketPlayServerUpdateHealth.class, "PacketPlayOutUpdateHealth"),
                        new PacketInfo<>(0x49, PacketPlayServerScoreboardObjective.class, "PacketPlayOutScoreboardObjective"),
                        new PacketInfo<>(0x4A, PacketPlayServerSetPassengers.class, "PacketPlayOutMount"),
                        new PacketInfo<>(0x4B, PacketPlayServerScoreboardTeam.class, "PacketPlayOutScoreboardTeam"),
                        new PacketInfo<>(0x4C, PacketPlayServerScoreboardScore.class, "PacketPlayOutScoreboardScore"),
                        new PacketInfo<>(0x4D, PacketPlayServerSpawnPosition.class, "PacketPlayOutSpawnPosition"),
                        new PacketInfo<>(0x4E, PacketPlayServerUpdateTime.class, "PacketPlayOutUpdateTime"),
                        new PacketInfo<>(0x4F, PacketPlayServerTitle.class, "PacketPlayOutTitle"),
                        // Todo find NMS name
                        new PacketInfo<>(0x50, PacketPlayServerSoundEffectEntity.class, "PacketPlayOutEntitySoundEffect"),
                        new PacketInfo<>(0x51, PacketPlayServerSoundEffectCustom.class, "PacketPlayOutCustomSoundEffect"),
                        // Todo find NMS name
                        new PacketInfo<>(0x52, PacketPlayServerSoundEffectStop.class, "PacketPlayOutStopSound"),

                        new PacketInfo<>(0x53, PacketPlayServerPlayerListHeaderFooter.class, "PacketPlayOutPlayListHeaderFooter"),
                        new PacketInfo<>(0x54, PacketPlayServerMetadataResponse.class, "PacketPlayOutMetadataResponse"),
                        new PacketInfo<>(0x55, PacketPlayServerCollect.class, "PacketPlayOutCollect"),
                        new PacketInfo<>(0x56, PacketPlayServerEntityTeleport.class, "PacketPlayOutEntityTeleport"),
                        // Todo find NMS name
                        new PacketInfo<>(0x57, PacketPlayServerAdvancements.class, "PacketPlayOutAdvancements"),
                        new PacketInfo<>(0x58, PacketPlayServerUpdateAttributes.class,  "PacketPlayOutUpdateAttributes"),
                        new PacketInfo<>(0x59, PacketPlayServerEntityEffect.class,"PacketPlayOutEntityEffect"),
                        // Todo find NMS name
                        new PacketInfo<>(0x5A, PacketPlayServerCraftingRecipeDeclare.class, "PacketPlayOutDeclareRecipes"),
                        // Todo find NMS name
                        // AKA Tags
                        new PacketInfo<>(0x5B, PacketPlayServerMetadata.class, "PacketPlayOutMetadata"),
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
                        new PacketInfo<>(0x01, PacketLoginClientEncryptionBegin.class, "PacketLoginInEncryptionBegin"),
                        // Todo find NMS name
                        new PacketInfo<>(0x02, PacketLoginClientPluginRequest.class, "PacketLoginInPluginRequest")
                    },

                new PacketInfo[] {
                        new PacketInfo<>(0x00, PacketLoginServerDisconnect.class, "PacketLoginOutDisconnect"),
                        new PacketInfo<>(0x01, PacketLoginServerEncryptionBegin.class, "PacketLoginOutEncryptionBegin"),
                        new PacketInfo<>(0x02, PacketLoginServerSuccess.class, "PacketLoginOutSuccess"),
                        new PacketInfo<>(0x03, PacketLoginServerSetCompression.class, "PacketLoginOutSetCompression"),
                        // Todo find NMS name
                        new PacketInfo<>(0x04, PacketLoginServerPluginResponse.class, "PacketLoginPluginResponse")
                }
            )
    );


    private final PacketPair packetPair;


    @Override
    @SneakyThrows
    public Packet<?> getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) {
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
