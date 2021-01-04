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
public enum EnumProtocol_404 implements EnumProtocol {
    HANDSHAKE(
            new PacketPair(
                new PacketInfo[]{
                        new PacketInfo(0x00, PacketHandshakeClientSetProtocol.class, "PacketHandshakingInSetProtocol"),
                        // Todo double check NMS name
                        new PacketInfo(0xFE, PacketHandshakeLegacyServerListPing.class, "PacketHandshakingLegacyServerListPing")
                },
                new PacketInfo[]{}
            )
    ),

    PLAY(
            new PacketPair(
                new PacketInfo[] {
                        new PacketInfo(0x00, PacketPlayClientConfirmTeleport.class, "PacketPlayInTeleportAccept"),
                        // Todo figure out NMS name for this
                        new PacketInfo(0x01, PacketPlayClientBlockMetadataQuery.class, "PacketPlayInQueryBlockNBT"),
                        new PacketInfo(0x02, PacketPlayClientChat.class, "PacketPlayInChat"),
                        new PacketInfo(0x03, PacketPlayClientClientCommand.class,"PacketPlayInClientCommand"),
                        new PacketInfo(0x04, PacketPlayClientSettings.class,"PacketPlayInSettings"),
                        new PacketInfo(0x05, PacketPlayClientTabComplete.class, "PacketPlayInTabComplete"),
                        new PacketInfo(0x06, PacketPlayClientTransaction.class,"PacketPlayInTransaction"),
                        new PacketInfo(0x07, PacketPlayClientEnchantItem.class,"PacketPlayInEnchantItem"),
                        new PacketInfo(0x08, PacketPlayClientWindowClick.class,"PacketPlayInWindowClick"),
                        new PacketInfo(0x09, PacketPlayClientWindowClose.class,"PacketPlayInCloseWindow"),
                        // AKA Plugin Message
                        new PacketInfo(0x0A, PacketPlayClientCustomPayload.class,"PacketPlayInCustomPayload"),
                        // Todo find NMS name
                        new PacketInfo(0x0B, PacketPlayClientBookEdit.class, "PacketPlayInEditBook"),
                        // Todo find NMS name
                        new PacketInfo(0x0C, PacketPlayClientEntityMetadataQuery.class, "PacketPlayInQueryEntityNBT"),
                        new PacketInfo(0x0D, PacketPlayClientUseEntity.class, "PacketPlayInUseEntity"),
                        new PacketInfo(0x0E, PacketPlayClientKeepAlive.class, "PacketPlayInKeepAlive"),
                        new PacketInfo(0x0F, PacketPlayClientFlying.class, "PacketPlayInFlying"),
                        new PacketInfo(0x10, PacketPlayClientFlying.PacketPlayClientPosition.class,"PacketPlayInPosition"),
                        new PacketInfo(0x11, PacketPlayClientFlying.PacketPlayClientPositionLook.class,"PacketPlayInPositionLook"),
                        new PacketInfo(0x12, PacketPlayClientFlying.PacketPlayClientLook.class,"PacketPlayInLook"),
                        new PacketInfo(0x13, PacketPlayClientVehicleMove.class,"PacketPlayInVehicleMove"),
                        new PacketInfo(0x14, PacketPlayClientBoatMove.class, "PacketPlayInSteerBoat"),
                        // Todo find NMS name
                        new PacketInfo(0x15, PacketPlayClientItemPick.class, "PacketPlayInPickItem"),
                        // Todo find NMS name
                        new PacketInfo(0x16, PacketPlayClientCraftingPrepareGrid.class, "PacketPlayInCraftingPrepareGrid"),
                        new PacketInfo(0x17, PacketPlayClientAbilities.class,"PacketPlayInAbilities"),
                        new PacketInfo(0x18, PacketPlayClientBlockDig.class,"PacketPlayInBlockDig"),
                        new PacketInfo(0x19, PacketPlayClientEntityAction.class,"PacketPlayInEntityAction"),
                        new PacketInfo(0x1A, PacketPlayClientSteerVehicle.class,"PacketPlayInSteerVehicle"),
                        new PacketInfo(0x1B, PacketPlayClientCraftingBookData.class, "PacketPlayInCraftBookData"),
                        // Todo find NMS name
                        new PacketInfo(0x1C, PacketPlayClientItemName.class, "PacketPlayInNameItem"),
                        new PacketInfo(0x1D, PacketPlayClientResourcePackStatus.class, "PacketPlayInResourcePackStatus"),
                        new PacketInfo(0x1E, PacketPlayClientAdvancementTab.class, "PacketPlayInAdvancementTab"),
                        // Todo find NMS name
                        new PacketInfo(0x1F, PacketPlayClientTradeSelect.class, "PacketPlayInSelectTrade"),
                        new PacketInfo(0x20, PacketPlayClientEffectBeaconSet.class, "PacketPlayInSetBeaconEffect"),
                        new PacketInfo(0x21, PacketPlayClientHeldItemSlot.class,"PacketPlayInHeldItemSlot"),
                        // Todo find NMS name
                        new PacketInfo(0x22, PacketPlayClientUpdateCommandBlock.class,"PacketPlayInUpdateCommandBlock"),
                        // Todo find NMS name
                        new PacketInfo(0x23, PacketPlayClientUpdateCommandMinecart.class, "PacketPlayInUpdateCommandBlockMinecart"),
                        new PacketInfo(0x24, PacketPlayClientSetCreativeSlot.class,"PacketPlayInSetCreativeSlot"),
                        // Todo find NMS name
                        new PacketInfo(0x25, PacketPlayClientUpdateStructureBlock.class, "PacketPlayInUpdateStructureBlock"),
                        new PacketInfo(0x26, PacketPlayClientUpdateSign.class,"PacketPlayInUpdateSign"),
                        new PacketInfo(0x27, PacketPlayClientArmAnimation.class,"PacketPlayInArmAnimation"),
                        new PacketInfo(0x28, PacketPlayClientSpectate.class, "PacketPlayInSpectate"),
                        new PacketInfo(0x29, PacketPlayClientBlockPlace.class,"PacketPlayInBlockPlace"),
                        new PacketInfo(0x2A, PacketPlayClientItemUse.class, "PacketPlayInUseItem")

                },
                new PacketInfo[] {
                        new PacketInfo(0x00, PacketPlayServerSpawnObject.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo(0x01, PacketPlayServerSpawnEntityExperienceOrb.class, "PacketPlayOutSpawnEntityExperienceOrb"),
                        /*
                         * Corresponds to wiki.vg's 'global entity'
                         */
                        new PacketInfo(0x02, PacketPlayServerSpawnEntityWeather.class, "PacketPlayOutSpawnEntity"),
                        new PacketInfo(0x03, PacketPlayServerSpawnEntityLiving.class, "PacketPlayOutSpawnEntityLiving"),
                        new PacketInfo(0x04, PacketPlayServerSpawnEntityPainting.class, "PacketPlayOutSpawnEntityPainting"),
                        new PacketInfo(0x05, PacketPlayServerSpawnNamedEntity.class, "PacketPlayOutNamedEntitySpawn"),
                        new PacketInfo(0x06, PacketPlayServerAnimation.class, "PacketPlayOutAnimation"),
                        new PacketInfo(0x07, PacketPlayServerStatistic.class, "PacketPlayOutStatistic"),
                        new PacketInfo(0x08, PacketPlayServerBlockBreakAnimation.class, "PacketPlayOutBlockBreakAnimation"),
                        new PacketInfo(0x09, PacketPlayServerTileEntityData.class, "PacketPlayOutTileEntityData"),
                        new PacketInfo(0x0A, PacketPlayServerBlockAction.class, "PacketPlayOutBlockAction"),
                        new PacketInfo(0x0B, PacketPlayServerBlockChange.class, "PacketPlayOutBlockChange"),
                        new PacketInfo(0x0C, PacketPlayServerBossBar.class, "PacketPlayOutBoss"),
                        new PacketInfo(0x0D, PacketPlayServerServerDifficulty.class, "PacketPlayOutServerDifficulty"),
                        new PacketInfo(0x0E, PacketPlayServerChat.class, "PacketPlayOutChat"),
                        new PacketInfo(0x0F, PacketPlayServerBlockChangeMulti.class, "PacketPlayOutMultiBlockChange"),
                        new PacketInfo(0x10, PacketPlayServerTabComplete.class, "PacketPlayOutTabComplete"),
                        // Todo figure out NMS name for this
                        new PacketInfo(0x11, PacketPlayServerCommandsDeclare.class, "PacketPlayOutDeclareCommands"),
                        new PacketInfo(0x12, PacketPlayServerTransaction.class, "PacketPlayOutTransaction"),
                        new PacketInfo(0x13, PacketPlayClientWindowClose.class, "PacketPlayInCloseWindow"),
                        new PacketInfo(0x14, PacketPlayServerWindowOpen.class, "PacketPlayOutOpenWindow"),
                        new PacketInfo(0x15, PacketPlayServerWindowItems.class, "PacketPlayOutWindowItems"),
                        new PacketInfo(0x16, PacketPlayServerWindowData.class, "PacketPlayOutWindowData"),
                        new PacketInfo(0x17, PacketPlayServerSetSlot.class, "PacketPlayOutSetSlot"),
                        new PacketInfo(0x18, PacketPlayServerSetCooldown.class, "PacketPlayOutSetCooldown"),
                        new PacketInfo(0x19, PacketPlayServerCustomPayload.class, "PacketPlayOutCustomPayload"),
                        new PacketInfo(0x1A, PacketPlayServerSoundEffectNamed.class, "PacketPlayOutNameSoundEffect"),
                        new PacketInfo(0x1B, PacketPlayServerKickDisconnect.class, "PacketPlayOutKickDisconnect"),
                        new PacketInfo(0x1C, PacketPlayServerEntityStatus.class, "PacketPlayOutEntityStatus"),
                        // Todo figure out NMS name for this, AKA NBT Query Response
                        new PacketInfo(0x1D, PacketPlayServerMetadataResponse.class, "PacketPlayOutMetadataResponse"),
                        new PacketInfo(0x1E, PacketPlayServerExplosion.class, "PacketPlayOutExplosion"),
                        new PacketInfo(0x1F, PacketPlayServerChunkUnload.class, "PacketPlayOutUnloadChunk"),
                        new PacketInfo(0x20, PacketPlayServerGameStateChange.class, "PacketPlayOutGameStateChange"),
                        new PacketInfo(0x21, PacketPlayServerKeepAlive.class, "PacketPlayOutKeepAlive"),
                        // AKA Chunk Data
                        new PacketInfo(0x22, PacketPlayServerChunkLoad.class, "PacketPlayOutMap"),
                        // AKA Effect
                        new PacketInfo(0x23, PacketPlayServerWorldEvent.class, "PacketPlayOutWorldEvent"),
                        // AKA Particles
                        new PacketInfo(0x24, PacketPlayServerWorldParticles.class, "PacketPlayOutWorldParticles"),
                        // AKA Join Game
                        new PacketInfo(0x25, PacketPlayServerLogin.class, "PacketPlayOutLogin"),
                        // AKA Map
                        new PacketInfo(0x26, PacketPlayServerMap.class, "PacketPlayOutMap"),
                        new PacketInfo(0x27, PacketPlayServerEntity.class, "PacketPlayOutEntity"),
                        new PacketInfo(0x28, PacketPlayServerEntity.PacketPlayServerRelEntityMove.class, "PacketPlayOutRelEntityMove"),
                        new PacketInfo(0x29, PacketPlayServerEntity.PacketPlayServerRelEntityMoveLook.class, "PacketPlayOutRelEntityMoveLook"),
                        new PacketInfo(0x2A, PacketPlayServerEntity.PacketPlayServerEntityLook.class, "PacketPlayOutEntityLook"),
                        new PacketInfo(0x2B, PacketPlayClientVehicleMove.class, "PacketPlayInVehicleMove"),
                        new PacketInfo(0x2C, PacketPlayServerOpenSignEditor.class, "PacketPlayOutOpenSignEditor"),
                        // Todo figure out the NMS name for this
                        new PacketInfo(0x2D, PacketPlayServerCraftingRecipeResponse.class, "PacketPlayOutCraftingResponse"),
                        new PacketInfo(0x2E, PacketPlayServerAbilities.class, "PacketPlayOutAbilities"),
                        new PacketInfo(0x2F, PacketPlayServerCombatEvent.class, "PacketPlayOutCombatEvent"),
                        // AKA Packet List Item
                        new PacketInfo(0x30, PacketPlayServerPlayerInfo.class, "PacketPlayOutPlayerInfo"),
                        // Todo figure out NMS name for this
                        new PacketInfo(0x31, PacketPlayServerPlayerFace.class, "PacketPlayOutFacePlayer"),
                        new PacketInfo(0x32, PacketPlayServerPosition.class, "PacketPlayOutPosition"),
                        new PacketInfo(0x33, PacketPlayServerBed.class, "PacketPlayOutBed"),
                        new PacketInfo(0x34, PacketPlayServerCraftingRecipeUnlock.class, "PacketPlayOutUnlockRecipe"),
                        new PacketInfo(0x35, PacketPlayServerEntityDestroy.class, "PacketPlayOutEntityDestroy"),
                        new PacketInfo(0x36, PacketPlayServerEntityEffectRemove.class, "PacketPlayOutRemoveEntityEffect"),
                        new PacketInfo(0x37, PacketPlayServerResourcePackSend.class, "PacketPlayOutResourcePackSend"),
                        new PacketInfo(0x38, PacketPlayServerRespawn.class, "PacketPlayOutRespawn"),
                        new PacketInfo(0x39, PacketPlayServerEntityHeadRotation.class, "PacketPlayOutEntityHeadRotation"),
                        // Todo figure out the NMS name for this
                        new PacketInfo(0x3A, PacketPlayServerAdvancementProgress.class, "PacketPlayOutAdvancementProgress"),
                        new PacketInfo(0x3B, PacketPlayServerWorldBorder.class, "PacketPlayOutWorldBorder"),
                        new PacketInfo(0x3C, PacketPlayServerCamera.class, "PacketPlayOutCamera"),
                        new PacketInfo(0x3D, PacketPlayServerHeldItemSlot.class, "PacketPlayOutHeldItemSlot"),
                        new PacketInfo(0x3E, PacketPlayServerScoreboardDisplayObjective.class, "PacketPlayOutScoreboardDisplayObjective"),
                        new PacketInfo(0x3F, PacketPlayServerEntityMetadata.class, "PacketPlayOutEntityMetadata"),
                        new PacketInfo(0x40, PacketPlayServerEntityAttach.class, "PacketPlayOutAttachEntity"),
                        new PacketInfo(0x41, PacketPlayServerEntityVelocity.class, "PacketPlayOutEntityVelocity"),
                        new PacketInfo(0x42, PacketPlayServerEntityEquipment.class, "PacketPlayOutEntityEquipment"),
                        new PacketInfo(0x43, PacketPlayServerExperience.class, "PacketPlayOutExperience"),
                        new PacketInfo(0x44, PacketPlayServerUpdateHealth.class, "PacketPlayOutUpdateHealth"),
                        new PacketInfo(0x45, PacketPlayServerScoreboardObjective.class, "PacketPlayOutScoreboardObjective"),
                        new PacketInfo(0x46, PacketPlayServerSetPassengers.class, "PacketPlayOutMount"),
                        new PacketInfo(0x47, PacketPlayServerScoreboardTeam.class, "PacketPlayOutScoreboardTeam"),
                        new PacketInfo(0x48, PacketPlayServerScoreboardScore.class, "PacketPlayOutScoreboardScore"),
                        new PacketInfo(0x49, PacketPlayServerSpawnPosition.class, "PacketPlayOutSpawnPosition"),
                        new PacketInfo(0x4A, PacketPlayServerUpdateTime.class, "PacketPlayOutUpdateTime"),
                        new PacketInfo(0x4B, PacketPlayServerTitle.class, "PacketPlayOutTitle"),
                        // Todo find NMS name
                        new PacketInfo(0x4C, PacketPlayServerSoundEffectStop.class, "PacketPlayOutStopSound"),
                        new PacketInfo(0x4D, PacketPlayServerSoundEffectCustom.class, "PacketPlayOutCustomSoundEffect"),
                        // Todo find NMS name
                        new PacketInfo(0x4E, PacketPlayServerSoundEffectEntity.class, "PacketPlayOutEntitySoundEffect"),
                        new PacketInfo(0x4F, PacketPlayServerPlayerListHeaderFooter.class, "PacketPlayOutPlayListHeaderFooter"),
                        new PacketInfo(0x50, PacketPlayServerCollect.class, "PacketPlayOutCollect"),
                        new PacketInfo(0x51, PacketPlayServerEntityTeleport.class, "PacketPlayOutEntityTeleport"),
                        // Todo find NMS name
                        new PacketInfo(0x52, PacketPlayServerAdvancements.class, "PacketPlayOutAdvancements"),
                        new PacketInfo(0x53, PacketPlayServerEntityMetadata.class,  "PacketPlayOutEntityMetadata"),
                        new PacketInfo(0x54, PacketPlayServerEntityEffect.class,"PacketPlayOutEntityEffect"),
                        // Todo find NMS name
                        new PacketInfo(0x55, PacketPlayServerCraftingRecipeDeclare.class, "PacketPlayOutDeclareRecipes"),
                        // Todo find NMS name
                        // AKA Tags
                        new PacketInfo(0x56, PacketPlayServerMetadata.class, "PacketPlayOutMetadata"),
                        new PacketInfo(0x57, PacketPlayServerUpdateLight.class, "PacketPlayOutUpdateLight")
                }
            )
    ),

    STATUS(new PacketPair(
                new PacketInfo[] {
                        new PacketInfo(0x00, PacketStatusClientStart.class, "PacketStatusInStart"),
                        new PacketInfo(0x01, PacketStatusClientPing.class, "PacketStatusInPing")
                },

                new PacketInfo[] {
                        new PacketInfo(0x00, PacketStatusServerInfoServer.class, "PacketStatusOutServerInfo"),
                        new PacketInfo(0x01, PacketStatusServerPing.class, "PacketStatusOutPong")
                }
            )
    ),
    LOGIN(
            new PacketPair(
                new PacketInfo[] {
                        new PacketInfo(0x00, PacketLoginClientStart.class, "PacketLoginInStart"),
                        new PacketInfo(0x01, PacketLoginClientEncryptionBegin.class, "PacketLoginInEncryptionBegin"),
                        // Todo find NMS name
                        new PacketInfo(0x02, PacketLoginClientPluginRequest.class, "PacketLoginInPluginRequest")
                    },

                new PacketInfo[] {
                        new PacketInfo(0x00, PacketLoginServerDisconnect.class, "PacketLoginOutDisconnect"),
                        new PacketInfo(0x01, PacketLoginServerEncryptionBegin.class, "PacketLoginOutEncryptionBegin"),
                        new PacketInfo(0x02, PacketLoginServerSuccess.class, "PacketLoginOutSuccess"),
                        new PacketInfo(0x03, PacketLoginServerSetCompression.class, "PacketLoginOutSetCompression"),
                        // Todo find NMS name
                        new PacketInfo(0x04, PacketLoginServerPluginResponse.class, "PacketLoginPluginResponse")
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
