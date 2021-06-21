package ac.artemis.packet.wrapper;

import ac.artemis.packet.wrapper.client.*;
import ac.artemis.packet.wrapper.server.*;

import java.util.HashMap;
import java.util.Map;

public class PacketRepository {
    private final PacketCache repository = new PacketCache();

    public void init() {
        // Base packet
        repository.put(0, PacketHandshakeClientSetProtocol.class);

        // Client bound login packets
        repository.put(1000, PacketLoginClientEncryptionBegin.class);
        repository.put(1001, PacketLoginClientStart.class);

        // Regular packets (starts at 3 don't ask why)
        repository.put(1, PacketPlayClientAbilities.class);
        repository.put(2, PacketPlayClientArmAnimation.class);
        repository.put(3, PacketPlayClientBlockMetadata.class);
        repository.put(4, PacketPlayClientBlockPlace.class);
        repository.put(5, PacketPlayClientBoatMove.class);
        repository.put(6, PacketPlayClientBookEdit.class);
        repository.put(7, PacketPlayClientChat.class);
        repository.put(8, PacketPlayClientCommand.class);
        repository.put(9, PacketPlayClientConfirmTeleport.class);
        repository.put(10, PacketPlayClientCustomPayload.class);
        repository.put(11, PacketPlayClientDifficultyLock.class);
        repository.put(12, PacketPlayClientDifficultySet.class);
        repository.put(13, PacketPlayClientEffectBeaconSet.class);
        repository.put(14, PacketPlayClientEnchantItem.class);
        repository.put(15, PacketPlayClientEntityAction.class);
        repository.put(16, PacketPlayClientEntityMetadata.class);
        repository.put(17, PacketPlayClientFlying.class);
        repository.put(18, PacketPlayClientGenerateStructure.class);
        repository.put(19, PacketPlayClientItemHeldSlot.class);
        repository.put(20, PacketPlayClientItemName.class);
        repository.put(21, PacketPlayClientItemPick.class);
        repository.put(22, PacketPlayClientKeepAlive.class);
        repository.put(23, PacketPlayClientLook.class);
        repository.put(24, PacketPlayClientPosition.class);
        repository.put(25, PacketPlayClientPositionLook.class);
        repository.put(26, PacketPlayClientRecipeBookData.class);
        repository.put(27, PacketPlayClientRecipeBookState.class);
        repository.put(28, PacketPlayClientRecipeDisplay.class);
        repository.put(29, PacketPlayClientRecipePrepareGrid.class);
        repository.put(30, PacketPlayClientResourcePackStatus.class);
        repository.put(31, PacketPlayClientSetCreativeSlot.class);
        repository.put(32, PacketPlayClientSettings.class);
        repository.put(33, PacketPlayClientSpectate.class);
        repository.put(34, PacketPlayClientSteerVehicle.class);
        repository.put(35, PacketPlayClientTabAdvancement.class);
        repository.put(36, PacketPlayClientTabComplete.class);
        repository.put(37, PacketPlayClientTradeSelect.class);
        repository.put(38, PacketPlayClientTransaction.class);
        repository.put(39, PacketPlayClientUpdateCommandBlock.class);
        repository.put(40, PacketPlayClientUpdateJigsawBlock.class);
        repository.put(41, PacketPlayClientUpdateMinecart.class);
        repository.put(42, PacketPlayClientUpdateSign.class);
        repository.put(43, PacketPlayClientUpdateStructureBlock.class);
        repository.put(44, PacketPlayClientUseEntity.class);
        repository.put(45, PacketPlayClientVehicleMove.class);
        repository.put(46, PacketPlayClientWindowClick.class);
        repository.put(47, PacketPlayClientWindowClose.class);
        repository.put(48, PacketPlayClientWindowHorse.class);

        // Client bound status
        repository.put(2001, PacketStatusClientPing.class);
        repository.put(2002, PacketStatusClientStart.class);


        // Server bound login
        repository.put(3001, PacketLoginOutDisconnect.class);
        repository.put(3002, PacketLoginClientEncryptionBegin.class);
        repository.put(3003, PacketLoginOutSetCompression.class);
        repository.put(3004, PacketLoginOutSuccess.class);

        repository.put(4001, PacketPlayServerAbilities.class);
        repository.put(4002, PacketPlayServerAdvancement.class);
        repository.put(4003, PacketPlayServerAdvancementProgress.class);
        repository.put(4004, PacketPlayServerAnimation.class);
        repository.put(4005, PacketPlayServerBed.class);
        repository.put(4006, PacketPlayServerBlockAction.class);
        repository.put(4007, PacketPlayServerBlockBreakAnimation.class);
        repository.put(4008, PacketPlayServerBlockChange.class);
        repository.put(4009, PacketPlayServerBlockChangeMulti.class);
        repository.put(4010, PacketPlayServerBookOpen.class);
        repository.put(4011, PacketPlayServerBossBar.class);
        repository.put(4012, PacketPlayServerCamera.class);
        repository.put(4013, PacketPlayServerChat.class);
        repository.put(4014, PacketPlayServerChunkLoad.class);
        repository.put(4015, PacketPlayServerChunkLoadBulk.class);
        repository.put(4016, PacketPlayServerChunkUnload.class);
        repository.put(4017, PacketPlayServerCollect.class);
        repository.put(4018, PacketPlayServerCombatEvent.class);
        repository.put(4019, PacketPlayServerCommandsDeclare.class);
        repository.put(4020, PacketPlayServerCompression.class);
        repository.put(4021, PacketPlayServerCustomPayload.class);
        repository.put(4022, PacketPlayServerDifficulty.class);
        repository.put(4023, PacketPlayServerEntityEffectRemove.class);
        repository.put(4024, PacketPlayServerEntityEquipment.class);
        repository.put(4025, PacketPlayServerEntityHeadRotation.class);
        repository.put(4026, PacketPlayServerEntityMetadata.class);
        repository.put(4027, PacketPlayServerEntityMount.class);
        repository.put(4028, PacketPlayServerEntityRelLook.class);
        repository.put(4029, PacketPlayServerEntityRelMove.class);
        repository.put(4030, PacketPlayServerEntityRelMoveLook.class);
        repository.put(4031, PacketPlayServerEntityStatus.class);
        repository.put(4032, PacketPlayServerEntityTeleport.class);
        repository.put(4033, PacketPlayServerEntityVelocity.class);
        repository.put(4034, PacketPlayServerExperience.class);
        repository.put(4035, PacketPlayServerExplosion.class);
        repository.put(4036, PacketPlayServerGameStateChange.class);
        repository.put(4037, PacketPlayServerItemHeldSlot.class);
        repository.put(4038, PacketPlayServerKeepAlive.class);
        repository.put(4039, PacketPlayServerKickDisconnect.class);
        repository.put(4040, PacketPlayServerLogin.class);
        repository.put(4041, PacketPlayServerMap.class);
        repository.put(4042, PacketPlayServerMetadata.class);
        repository.put(4043, PacketPlayServerMetadataResponse.class);
        repository.put(4044, PacketPlayServerOpenSignEditor.class);
        repository.put(4045, PacketPlayServerPlayerDig.class);
        repository.put(4046, PacketPlayServerPlayerFace.class);
        repository.put(4047, PacketPlayServerPlayerInfo.class);
        repository.put(4048, PacketPlayServerPlayerListHeaderFooter.class);
        repository.put(4049, PacketPlayServerPosition.class);
        repository.put(4050, PacketPlayServerRecipeDeclare.class);
        repository.put(4051, PacketPlayServerRecipeResponse.class);
        repository.put(4052, PacketPlayServerRecipeUnlock.class);
        repository.put(4053, PacketPlayServerResourcePackSend.class);
        repository.put(4054, PacketPlayServerRespawn.class);
        repository.put(4055, PacketPlayServerScoreboardObjective.class);
        repository.put(4056, PacketPlayServerScoreboardObjectiveDisplay.class);
        repository.put(4057, PacketPlayServerScoreboardScore.class);
        repository.put(4058, PacketPlayServerScoreboardTeam.class);
        repository.put(4059, PacketPlayServerSetCooldown.class);
        repository.put(4060, PacketPlayServerSetPassengers.class);
        repository.put(4061, PacketPlayServerSetSlot.class);
        repository.put(4062, PacketPlayServerSoundEffectCustom.class);
        repository.put(4063, PacketPlayServerSoundEffectEntity.class);
        repository.put(4064, PacketPlayServerSoundEffectNamed.class);
        repository.put(4065, PacketPlayServerSoundEffectStop.class);
        repository.put(4066, PacketPlayServerSpawnEntityExperienceOrb.class);
        repository.put(4067, PacketPlayServerSpawnEntityLiving.class);
        repository.put(4068, PacketPlayServerSpawnEntityPainting.class);
        repository.put(4069, PacketPlayServerSpawnEntityWeather.class);
        repository.put(4070, PacketPlayServerSpawnObject.class);
        repository.put(4071, PacketPlayServerSpawnPosition.class);
        repository.put(4072, PacketPlayServerStatistic.class);
        repository.put(4073, PacketPlayServerTabComplete.class);
        repository.put(4074, PacketPlayServerTileEntityData.class);
        repository.put(4075, PacketPlayServerTitle.class);
        repository.put(4076, PacketPlayServerTradeList.class);
        repository.put(4077, PacketPlayServerTransaction.class);
        repository.put(4078, PacketPlayServerUpdateAttributes.class);
        repository.put(4079, PacketPlayServerUpdateHealth.class);
        repository.put(4080, PacketPlayServerUpdateLight.class);
        repository.put(4081, PacketPlayServerUpdateMetadata.class);
        repository.put(4082, PacketPlayClientUpdateSign.class);
        repository.put(4083, PacketPlayServerUpdateViewDistance.class);
        repository.put(4084, PacketPlayServerUpdateViewPosition.class);
        repository.put(4085, PacketPlayServerVehicleMove.class);
        repository.put(4086, PacketPlayServerWindowClose.class);
        repository.put(4087, PacketPlayServerWindowData.class);
        repository.put(4088, PacketPlayServerWindowItems.class);
        repository.put(4089, PacketPlayServerWindowOpen.class);
        repository.put(4090, PacketPlayServerWorldBorder.class);
        repository.put(4091, PacketPlayServerWorldEvent.class);
        repository.put(4092, PacketPlayServerWorldParticles.class);

        repository.put(5001, PacketStatusServerInfo.class);
        repository.put(5002, PacketStatusServerPong.class);
    }
}
