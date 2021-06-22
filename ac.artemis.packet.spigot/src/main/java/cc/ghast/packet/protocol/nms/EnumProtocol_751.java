package cc.ghast.packet.protocol.nms;

import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.EnumProtocol;
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
public enum EnumProtocol_751 implements EnumProtocol {
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
                        // Todo figure out NMS name for this
                        new PacketInfo<>(0x01, GPacketPlayClientBlockMetadataQuery.class, "PacketPlayInQueryBlockNBT"),
                        // Todo NMS name
                        new PacketInfo<>(0x02, PacketPlayClientDifficultySet.class, "PacketPlayInSetDifficulty"),
                        new PacketInfo<>(0x03, GPacketPlayClientChat.class, "PacketPlayInChat"),
                        new PacketInfo<>(0x04, GPacketPlayClientClientCommand.class,"PacketPlayInClientCommand"),
                        new PacketInfo<>(0x05, GPacketPlayClientSettings.class,"PacketPlayInSettings"),
                        new PacketInfo<>(0x06, GPacketPlayClientTabComplete.class, "PacketPlayInTabComplete"),
                        new PacketInfo<>(0x07, GPacketPlayClientTransaction.class,"PacketPlayInTransaction"),
                        // Todo NMS name
                        new PacketInfo<>(0x08, GPacketPlayClientWindowButton.class,"PacketPlayInWindowClickButton"),
                        new PacketInfo<>(0x09, GPacketPlayClientWindowClick.class,"PacketPlayInWindowClick"),
                        new PacketInfo<>(0x0A, GPacketPlayClientWindowClose.class,"PacketPlayInCloseWindow"),
                        // AKA Plugin Message
                        new PacketInfo<>(0x0B, GPacketPlayClientCustomPayload.class,"PacketPlayInCustomPayload"),
                        // Todo find NMS name
                        new PacketInfo<>(0x0C, GPacketPlayClientBookEdit.class, "PacketPlayInEditBook"),
                        // Todo find NMS name
                        new PacketInfo<>(0x0D, PacketPlayClientEntityMetadataQuery.class, "PacketPlayInQueryEntityNBT"),
                        new PacketInfo<>(0x0E, GPacketPlayClientUseEntity.class, "PacketPlayInUseEntity"),
                        new PacketInfo<>(0x0F, PacketPlayClientGenerateStructure.class, "PacketPlayInJigsawGenerate"),
                        new PacketInfo<>(0x10, GPacketPlayClientKeepAlive.class, "PacketPlayInKeepAlive"),
                        // Todo find NMS name
                        new PacketInfo<>(0x11, PacketPlayClientDifficultyLock.class, "PacketPlayInLockDifficulty"),
                        new PacketInfo<>(0x12, GPacketPlayClientFlying.PacketPlayClientPosition.class,"PacketPlayInPosition"),
                        new PacketInfo<>(0x13, GPacketPlayClientFlying.PacketPlayClientPositionLook.class,"PacketPlayInPositionLook"),
                        new PacketInfo<>(0x14, GPacketPlayClientFlying.PacketPlayClientLook.class,"PacketPlayInLook"),
                        new PacketInfo<>(0x15, GPacketPlayClientFlying.class, "PacketPlayInFlying"),

                        new PacketInfo<>(0x16, GPacketPlayClientVehicleMove.class,"PacketPlayInVehicleMove"),
                        new PacketInfo<>(0x17, GPacketPlayClientBoatMove.class, "PacketPlayInSteerBoat"),
                        // Todo find NMS name
                        new PacketInfo<>(0x18, PacketPlayClientItemPick.class, "PacketPlayInPickItem"),
                        // Todo find NMS name
                        new PacketInfo<>(0x19, GPacketPlayClientCraftingPrepareGrid.class, "PacketPlayInCraftingPrepareGrid"),
                        new PacketInfo<>(0x1A, GPacketPlayClientAbilities.class,"PacketPlayInAbilities"),
                        new PacketInfo<>(0x1B, GPacketPlayClientBlockDig.class,"PacketPlayInBlockDig"),
                        new PacketInfo<>(0x1C, GPacketPlayClientEntityAction.class,"PacketPlayInEntityAction"),
                        new PacketInfo<>(0x1D, GPacketPlayClientSteerVehicle.class,"PacketPlayInSteerVehicle"),
                        new PacketInfo<>(0x1E, PacketPlayClientRecipeDisplay.class, "PacketPlayInSetDisplayedRecipe"),
                        new PacketInfo<>(0x1F, PacketPlayClientRecipeBookState.class, "PacketPlayInSetRecipeBookState"),
                        // Todo find NMS name
                        new PacketInfo<>(0x20, PacketPlayClientItemName.class, "PacketPlayInNameItem"),
                        new PacketInfo<>(0x21, GPacketPlayClientResourcePackStatus.class, "PacketPlayInResourcePackStatus"),
                        new PacketInfo<>(0x22, GPacketPlayClientAdvancementTab.class, "PacketPlayInAdvancementTab"),
                        // Todo find NMS name
                        new PacketInfo<>(0x23, PacketPlayClientTradeSelect.class, "PacketPlayInSelectTrade"),
                        new PacketInfo<>(0x24, PacketPlayClientEffectBeaconSet.class, "PacketPlayInSetBeaconEffect"),
                        new PacketInfo<>(0x25, GPacketPlayClientHeldItemSlot.class,"PacketPlayInHeldItemSlot"),
                        // Todo find NMS name
                        new PacketInfo<>(0x26, PacketPlayClientUpdateCommandBlock.class,"PacketPlayInUpdateCommandBlock"),
                        // Todo find NMS name
                        new PacketInfo<>(0x27, PacketPlayClientUpdateCommandMinecart.class, "PacketPlayInUpdateCommandBlockMinecart"),
                        new PacketInfo<>(0x28, GPacketPlayClientSetCreativeSlot.class,"PacketPlayInSetCreativeSlot"),
                        // Todo find NMS name
                        new PacketInfo<>(0x29, PacketPlayClientUpdateJigsawBlock.class, "PacketPlayInUpdateJigsawBlock"),
                        // Todo Find NMS name
                        new PacketInfo<>(0x2A, PacketPlayClientUpdateStructureBlock.class, "PacketPlayInUpdateStructureBlock"),
                        new PacketInfo<>(0x2B, GPacketPlayClientUpdateSign.class,"PacketPlayInUpdateSign"),
                        new PacketInfo<>(0x2C, GPacketPlayClientArmAnimation.class,"PacketPlayInArmAnimation"),
                        new PacketInfo<>(0x2D, GPacketPlayClientSpectate.class, "PacketPlayInSpectate"),
                        new PacketInfo<>(0x2E, GPacketPlayClientBlockPlace.class,"PacketPlayInBlockPlace"),
                        new PacketInfo<>(0x2F, GPacketPlayClientItemUse.class, "PacketPlayInUseItem")

                },
                new PacketInfo[] {
                        new PacketInfo<>(0x00, GPacketPlayServerSpawnObject.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo<>(0x01, PacketPlayServerSpawnEntityExperienceOrb.class, "PacketPlayOutSpawnEntityExperienceOrb"),
                        /*
                         * Corresponds to wiki.vg's 'global entity'
                         */
                        new PacketInfo<>(0x02, PacketPlayServerSpawnEntityLiving.class, "PacketPlayOutSpawnEntityLiving"),
                        new PacketInfo<>(0x03, PacketPlayServerSpawnEntityPainting.class, "PacketPlayOutSpawnEntityPainting"),
                        new PacketInfo<>(0x04, GPacketPlayServerSpawnNamedEntity.class, "PacketPlayOutNamedEntitySpawn"),
                        new PacketInfo<>(0x05, GPacketPlayServerAnimation.class, "PacketPlayOutAnimation"),
                        new PacketInfo<>(0x06, PacketPlayServerStatistic.class, "PacketPlayOutStatistic"),
                        new PacketInfo<>(0x07, PacketPlayServerPlayerDigging.class, "PacketPlayOutPlayerDig"),
                        new PacketInfo<>(0x08, GPacketPlayServerBlockBreakAnimation.class, "PacketPlayOutBlockBreakAnimation"),
                        new PacketInfo<>(0x09, PacketPlayServerTileEntityData.class, "PacketPlayOutTileEntityData"),
                        new PacketInfo<>(0x0A, GPacketPlayServerBlockAction.class, "PacketPlayOutBlockAction"),
                        new PacketInfo<>(0x0B, GPacketPlayServerBlockChange.class, "PacketPlayOutBlockChange"),
                        new PacketInfo<>(0x0C, PacketPlayServerBossBar.class, "PacketPlayOutBoss"),
                        new PacketInfo<>(0x0D, PacketPlayServerServerDifficulty.class, "PacketPlayOutServerDifficulty"),
                        new PacketInfo<>(0x0E, GPacketPlayServerChat.class, "PacketPlayOutChat"),
                        new PacketInfo<>(0x0F, GPacketPlayServerBlockChangeMulti.class, "PacketPlayOutMultiBlockChange"),
                        new PacketInfo<>(0x10, GPacketPlayServerTabComplete.class, "PacketPlayOutTabComplete"),
                        // Todo figure out NMS name for this
                        new PacketInfo<>(0x11, PacketPlayServerCommandsDeclare.class, "PacketPlayOutDeclareCommands"),
                        new PacketInfo<>(0x12, GPacketPlayServerTransaction.class, "PacketPlayOutTransaction"),
                        new PacketInfo<>(0x13, PacketPlayClientWindowClose.class, "PacketPlayInCloseWindow"),
                        new PacketInfo<>(0x14, PacketPlayServerWindowItems.class, "PacketPlayOutWindowItems"),
                        new PacketInfo<>(0x15, PacketPlayServerWindowData.class, "PacketPlayOutWindowData"),
                        new PacketInfo<>(0x16, PacketPlayServerSetSlot.class, "PacketPlayOutSetSlot"),
                        new PacketInfo<>(0x17, PacketPlayServerSetCooldown.class, "PacketPlayOutSetCooldown"),
                        new PacketInfo<>(0x18, GPacketPlayServerCustomPayload.class, "PacketPlayOutCustomPayload"),
                        new PacketInfo<>(0x19, PacketPlayServerSoundEffectNamed.class, "PacketPlayOutNameSoundEffect"),
                        new PacketInfo<>(0x1A, GPacketPlayServerKickDisconnect.class, "PacketPlayOutKickDisconnect"),
                        new PacketInfo<>(0x1B, GPacketPlayServerEntityStatus.class, "PacketPlayOutEntityStatus"),
                        // Todo figure out NMS name for this, AKA NBT Query Response
                        new PacketInfo<>(0x1C, GPacketPlayServerExplosion.class, "PacketPlayOutExplosion"),
                        new PacketInfo<>(0x1D, GPacketPlayServerChunkUnload.class, "PacketPlayOutUnloadChunk"),
                        new PacketInfo<>(0x1E, PacketPlayServerGameStateChange.class, "PacketPlayOutGameStateChange"),
                        // Todo find NMS
                        new PacketInfo<>(0x1F, PacketPlayClientWindowHorse.class, "PacketPlayOutOpenHorseWindow"),
                        new PacketInfo<>(0x20, GPacketPlayServerKeepAlive.class, "PacketPlayOutKeepAlive"),
                        // AKA Chunk Data
                        new PacketInfo<>(0x21, GPacketPlayServerChunkLoad.class, "PacketPlayOutMap"),
                        // AKA Effect
                        new PacketInfo<>(0x22, PacketPlayServerWorldEvent.class, "PacketPlayOutWorldEvent"),
                        // AKA Particles
                        new PacketInfo<>(0x23, PacketPlayServerWorldParticles.class, "PacketPlayOutWorldParticles"),
                        new PacketInfo<>(0x24 , PacketPlayServerUpdateLight.class, "PacketPlayOutUpdateLight"),
                        // AKA Join Game
                        new PacketInfo<>(0x25, GPacketPlayServerLogin.class, "PacketPlayOutLogin"),
                        // AKA Map
                        new PacketInfo<>(0x26, PacketPlayServerMap.class, "PacketPlayOutMap"),
                        // Todo find NMS
                        new PacketInfo<>(0x27, PacketPlayServerTradeList.class, "PacketPlayOutTradeList"),
                        new PacketInfo<>(0x28, GPacketPlayServerEntity.GPacketPlayServerRelEntityMove.class, "PacketPlayOutRelEntityMove"),
                        new PacketInfo<>(0x29, GPacketPlayServerEntity.GPacketPlayServerRelEntityMoveLook.class, "PacketPlayOutRelEntityMoveLook"),
                        new PacketInfo<>(0x2A, GPacketPlayServerEntity.GPacketPlayServerEntityLook.class, "PacketPlayOutEntityLook"),
                        new PacketInfo<>(0x2B, GPacketPlayServerEntity.class, "PacketPlayOutEntity"),
                        new PacketInfo<>(0x2C, PacketPlayClientVehicleMove.class, "PacketPlayInVehicleMove"),
                        // Todo find NMS
                        new PacketInfo<>(0x2D, PacketPlayServerBookOpen.class, "PacketPlayInOpenBook"),
                        new PacketInfo<>(0x2E, GPacketPlayServerWindowOpen.class, "PacketPlayOutOpenWindow"),

                        new PacketInfo<>(0x2F, PacketPlayServerOpenSignEditor.class, "PacketPlayOutOpenSignEditor"),
                        // Todo figure out the MS name for this
                        new PacketInfo<>(0x30, PacketPlayServerCraftingRecipeResponse.class, "PacketPlayOutCraftingResponse"),
                        new PacketInfo<>(0x31, GPacketPlayServerAbilities.class, "PacketPlayOutAbilities"),
                        new PacketInfo<>(0x32, PacketPlayServerCombatEvent.class, "PacketPlayOutCombatEvsent"),
                        // AKA Packet List Item
                        new PacketInfo<>(0x33, PacketPlayServerPlayerInfo.class, "PacketPlayOutPlayerInfo"),
                        // Todo figure out NMS name for this
                        new PacketInfo<>(0x34, PacketPlayServerPlayerFace.class, "PacketPlayOutFacePlayer"),
                        new PacketInfo<>(0x35, GPacketPlayServerPosition.class, "PacketPlayOutPosition"),

                        new PacketInfo<>(0x36, PacketPlayServerCraftingRecipeUnlock.class, "PacketPlayOutUnlockRecipe"),
                        new PacketInfo<>(0x37, GPacketPlayServerEntityDestroy.class, "PacketPlayOutEntityDestroy"),
                        new PacketInfo<>(0x38, GPacketPlayServerEntityEffectRemove.class, "PacketPlayOutRemoveEntityEffect"),
                        new PacketInfo<>(0x39, GPacketPlayServerResourcePackSend.class, "PacketPlayOutResourcePackSend"),
                        new PacketInfo<>(0x3A, GPacketPlayServerRespawn.class, "PacketPlayOutRespawn"),
                        new PacketInfo<>(0x3B, GPacketPlayServerEntityHeadRotation.class, "PacketPlayOutEntityHeadRotation"),
                        // Todo figure out the NMS name for this
                        new PacketInfo<>(0x3C, PacketPlayServerAdvancementProgress.class, "PacketPlayOutAdvancementProgress"),
                        new PacketInfo<>(0x3D, PacketPlayServerWorldBorder.class, "PacketPlayOutWorldBorder"),
                        new PacketInfo<>(0x3E, PacketPlayServerCamera.class, "PacketPlayOutCamera"),
                        new PacketInfo<>(0x3F, GPacketPlayServerHeldItemSlot.class, "PacketPlayOutHeldItemSlot"),


                        // Todo figure out the NMS name for this
                        new PacketInfo<>(0x40, PacketPlayServerUpdateViewPosition.class, "PacketPlayOutUpdateViewPosition"),
                        // Todo figure out the NMS name for this
                        new PacketInfo<>(0x41, PacketPlayServerUpdateViewDistance.class, "PacketPlayOutUpdateViewDistance"),
                        new PacketInfo<>(0x42, PacketPlayServerSpawnPosition.class, "PacketPlayOutSpawnPosition"),
                        new PacketInfo<>(0x43, PacketPlayServerScoreboardDisplayObjective.class, "PacketPlayOutScoreboardDisplayObjective"),
                        new PacketInfo<>(0x44, GPacketPlayServerEntityMetadata.class, "PacketPlayOutEntityMetadata"),
                        new PacketInfo<>(0x45, GPacketPlayServerEntityAttach.class, "PacketPlayOutAttachEntity"),
                        new PacketInfo<>(0x46, GPacketPlayServerEntityVelocity.class, "PacketPlayOutEntityVelocity"),
                        new PacketInfo<>(0x47, GPacketPlayServerEntityEquipment.class, "PacketPlayOutEntityEquipment"),
                        new PacketInfo<>(0x4E, PacketPlayServerUpdateTime.class, "PacketPlayOutUpdateTime"),
                        new PacketInfo<>(0x4F, PacketPlayServerTitle.class, "PacketPlayOutTitle"),
                        // Todo find NMS name
                        new PacketInfo<>(0x50, PacketPlayServerSoundEffectEntity.class, "PacketPlayOutEntitySoundEffect"),
                        new PacketInfo<>(0x51, PacketPlayServerSoundEffectCustom.class, "PacketPlayOutCustomSoundEffect"),
                        // Todo find NMS name
                        new PacketInfo<>(0x52, PacketPlayServerSoundEffectStop.class, "PacketPlayOutStopSound"),

                        new PacketInfo<>(0x53, PacketPlayServerPlayerListHeaderFooter.class, "PacketPlayOutPlayListHeaderFooter"),


                        //new PacketInfo<>(0x48, PacketPlayServerExperience.class, "PacketPlayOutExperience"),
                       // new PacketInfo<>(0x49, PacketPlayServerUpdateHealth.class, "PacketPlayOutUpdateHealth"),
                        //new PacketInfo<>(0x4A, PacketPlayServerScoreboardObjective.class, "PacketPlayOutScoreboardObjective"),
                        //new PacketInfo<>(0x4B, PacketPlayServerSetPassengers.class, "PacketPlayOutMount"),
                        //new PacketInfo<>(0x4C, PacketPlayServerScoreboardTeam.class, "PacketPlayOutScoreboardTeam"),
                        //new PacketInfo<>(0x4D, PacketPlayServerScoreboardScore.class, "PacketPlayOutScoreboardScore"),
                        new PacketInfo<>(0x54, PacketPlayServerMetadataResponse.class, "PacketPlayOutMetadataResponse"),
                        new PacketInfo<>(0x55, PacketPlayServerCollect.class, "PacketPlayOutCollect"),
                        new PacketInfo<>(0x56, GPacketPlayServerEntityTeleport.class, "PacketPlayOutEntityTeleport"),
                        // Todo find NMS name
                        new PacketInfo<>(0x57, PacketPlayServerAdvancements.class, "PacketPlayOutAdvancements"),
                        new PacketInfo<>(0x58, GPacketPlayServerUpdateAttributes.class,  "PacketPlayOutUpdateAttributes"),
                        new PacketInfo<>(0x59, GPacketPlayServerEntityEffect.class,"PacketPlayOutEntityEffect"),
                        // Todo find NMS name
                        new PacketInfo<>(0x5A, PacketPlayServerCraftingRecipeDeclare.class, "PacketPlayOutDeclareRecipes"),
                        // Todo find NMS name
                        // AKA Tags
                        new PacketInfo<>(0x5B, PacketPlayServerMetadata.class, "PacketPlayOutMetadata"),
                        // Find NMS Name
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
                        new PacketInfo<>(0x01, GPacketLoginClientEncryptionBegin.class, "PacketLoginInEncryptionBegin"),
                        // Todo find NMS name
                        new PacketInfo<>(0x02, GPacketLoginClientPluginRequest.class, "PacketLoginInPluginRequest")
                    },

                new PacketInfo[] {
                        new PacketInfo<>(0x00, GPacketLoginServerDisconnect.class, "PacketLoginOutDisconnect"),
                        new PacketInfo<>(0x01, GPacketLoginServerEncryptionBegin.class, "PacketLoginOutEncryptionBegin"),
                        new PacketInfo<>(0x02, GPacketLoginServerSuccess.class, "PacketLoginOutSuccess"),
                        new PacketInfo<>(0x03, GPacketLoginServerSetCompression.class, "PacketLoginOutSetCompression"),
                        // Todo find NMS name
                        new PacketInfo<>(0x04, GPacketLoginServerPluginResponse.class, "PacketLoginPluginResponse")
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
