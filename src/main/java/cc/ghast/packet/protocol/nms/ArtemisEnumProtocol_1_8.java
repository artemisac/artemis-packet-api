package cc.ghast.packet.protocol.nms;

import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.play.client.*;
import cc.ghast.packet.wrapper.packet.play.server.*;
import cc.ghast.packet.protocol.EnumProtocolDirection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ArtemisEnumProtocol_1_8 {

    @RequiredArgsConstructor
    @Getter
    public enum Handshake {
        HANDSHAKING("PacketHandshakingInSetProtocol", EnumProtocolDirection.IN);
        private final String id;
        private final EnumProtocolDirection direction;
    }


    public static class Play {

        @Getter
        public enum Client {
            KEEP_ALIVE("PacketPlayInKeepAlive", PacketPlayClientKeepAlive.class),
            CHAT_MESSAGE("PacketPlayInChat", PacketPlayClientChat.class),
            USE_ENTITY("PacketPlayInUseEntity", PacketPlayClientUseEntity.class),
            FLYING("PacketPlayInFlying", PacketPlayClientFlying.class),
            POSITION("PacketPlayInFlying$PacketPlayInPosition", PacketPlayClientFlying.PacketPlayClientPosition.class),
            LOOK("PacketPlayInFlying$PacketPlayInLook", PacketPlayClientFlying.PacketPlayClientLook.class),
            POSITION_LOOK("PacketPlayInFlying$PacketPlayInPositionLook", PacketPlayClientFlying.PacketPlayClientPositionLook.class),
            BLOCK_DIG("PacketPlayInBlockDig", PacketPlayClientBlockDig.class),
            BLOCK_PLACE("PacketPlayInBlockPlace", PacketPlayClientBlockPlace.class),
            HELD_ITEM(0x09, PacketPlayClientHeldItemSlot.class),
            ANIMATION(0x0A, PacketPlayClientArmAnimation.class),
            ENTITY_ACTION(0x0B, PacketPlayClientEntityAction.class),
            STEER_VEHICLE(0x0C, PacketPlayClientSteerVehicle.class),
            CLOSE_WINDOW(0x0D, PacketPlayClientCloseWindow.class),
            CLICK_WINDOW(0x0E, PacketPlayClientWindowClick.class),
            TRANSACTION(0x0F, PacketPlayClientTransaction.class),
            CREATIVE_ACTION(0x10, PacketPlayClientSetCreativeSlot.class),
            ENCHANT_ITEM(0x11, PacketPlayClientEnchantItem.class),
            UPDATE_SIGN(0x12, PacketPlayClientUpdateSign.class),
            ABILITIES(0x13, PacketPlayClientAbilities.class),
            TAB_COMPLETE(0x14, PacketPlayClientTabComplete.class),
            CLIENT_SETTINGS(0x15, PacketPlayClientSettings.class),
            CLIENT_STATUS(0x16, PacketPlayClientClientCommand.class),
            CUSTOM_PAYLOAD(0x17, PacketPlayClientCustomPayload.class),
            SPECTATE(0x18, PacketPlayClientSpectate.class),
            RESOURCE_PACK(0x19, PacketPlayClientResourcePackStatus.class);
            private final String id;
            private final EnumProtocolDirection direction;
            private final Class<? extends Packet> clazz;

            Client(String id, Class<? extends Packet> clazz) {
                this.id = id;
                this.direction = EnumProtocolDirection.IN;
                this.clazz = clazz;
            }
        }

        @Getter
        public enum Server {
            KEEP_ALIVE(0x00, PacketPlayServerKeepAlive.class),
            JOIN_GAME(0x01, PacketPlayServerLogin.class),
            CHAT(0x02, PacketPlayServerChat.class),
            UPDATE_TIME(0x03, PacketPlayServerUpdateTime.class),
            ENTITY_EQUIPMENT(0x04, PacketPlayServerEntityEquipment.class),
            SPAWN_POSITION(0x05, PacketPlayServerSpawnPosition.class),
            UPDATE_HEALTH(0x06, PacketPlayServerUpdateHealth.class),
            RESPAWN(0x07, PacketPlayServerRespawn.class),
            POSITION(0x08, PacketPlayServerPosition.class),
            HELD_ITEM(0x09, PacketPlayServerHeldItemSlot.class),
            USE_BED(0x0A, PacketPlayServerBed.class),
            ANIMATION(0x0B, PacketPlayServerAnimation.class),
            NAMED_ENTITY_SPAWN(0x0C, PacketPlayServerNamedEntitySpawn.class),
            COLLECT_ITEM(0x0D, PacketPlayServerCollect.class),

            // Not sure what's supposed to go here mhm
            ENTITY_SPAWN(0x0E, PacketPlayServerNamedEntitySpawn.class),

            
            ENTITY_LIVING_SPAWN(0x0F, PacketPlayServerSpawnEntityLiving.class),
            ENTITY_PAINTING_SPAWN(0x10, PacketPlayServerSpawnEntityPainting.class),
            ENTITY_EXPERIENCE_SPAWN(0x11, PacketPlayServerSpawnEntityExperienceOrb.class),
            ENTITY_VELOCITY(0x12, PacketPlayServerEntityVelocity.class),
            ENTITY_DESTROY(0x13, PacketPlayServerEntityDestroy.class),
            ENTITY(0x14, PacketPlayServerEntity.class),
            ENTITY_RELATIVE_MOVE(0x15, PacketPlayServerEntity.PacketPlayServerRelEntityMove.class),
            ENTITY_LOOK(0x16, PacketPlayServerEntity.PacketPlayServerEntityLook.class),
            ENTITY_RELATIVE_MOVE_LOOK(0x17, PacketPlayServerEntity.PacketPlayServerRelEntityMoveLook.class),
            ENTITY_TELEPORT(0x18, PacketPlayServerEntityTeleport.class),
            ENTITY_HEAD_LOOK(0x19, PacketPlayServerEntityHeadRotation.class),
            ENTITY_STATUS(0x1A, PacketPlayServerEntityStatus.class),
            ENTITY_ATTACH(0x1B, PacketPlayServerAttachEntity.class),
            ENTITY_METADATA(0x1C, PacketPlayServerEntityMetadata.class),
            ENTITY_EFFECT(0x1D, PacketPlayServerEntityEffect.class),
            ENTITY_EFFECT_REMOVE(0x1E, PacketPlayServerRemoveEntityEffect.class),
            SET_EXPERIENCE(0x1F, PacketPlayServerExperience.class),
            ENTITY_PROPERTIES(0x20, PacketPlayServerUpdateAttributes.class),
            CHUNK_DATA(0x21, PacketPlayServerMapChunk.class),
            MULTI_BLOCK_CHANGE(0x22, PacketPlayServerMultiBlockChange.class),
            BLOCK_CHANGE(0x23, PacketPlayServerBlockChange.class),
            BLOCK_ACTION(0x24, PacketPlayServerBlockAction.class),
            BLOCK_BREAK(0x25, PacketPlayServerBlockBreakAnimation.class),
            CHUNK_DATA_BULK(0x26, PacketPlayServerMapChunkBulk.class),
            EXPLOSION(0x27, PacketPlayServerExplosion.class),
            EFFECT(0x28, PacketPlayServerWorldEvent.class),
            EFFECT_SOUND(0x29, PacketPlayServerNamedSoundEffect.class),
            EFFECT_PARTICLE(0x2A, PacketPlayServerWorldParticles.class),
            CHANGE_STATE(0x2B, PacketPlayServerGameStateChange.class),
            ENTITY_WEATHER_SPAWN(0x2C, PacketPlayServerSpawnEntityWeather.class),
            WINDOW_OPEN(0x2D, PacketPlayServerOpenWindow.class),
            WINDOW_CLOSE(0x2E, PacketPlayServerCloseWindow.class),
            SET_SLOT(0x2F, PacketPlayServerSetSlot.class),
            WINDOW_ITEMS(0x30, PacketPlayServerWindowItems.class),
            WINDOW_PROPERTY(0x31, PacketPlayServerWindowData.class),
            TRANSACTION(0x32, PacketPlayServerTransaction.class),
            UPDATE_SIGN(0x33, PacketPlayServerUpdateSign.class),
            MAP(0x34, PacketPlayServerMap.class),
            TILE_ENTITY_DATA(0x35, PacketPlayServerTileEntityData.class),
            OPEN_SIGN_EDITOR(0x36, PacketPlayServerOpenSignEditor.class),
            STATISTIC(0x37, PacketPlayServerStatistic.class),
            PLAYER_LIST(0x38, PacketPlayServerPlayerInfo.class),
            ABILITIES(0x39, PacketPlayServerAbilities.class),
            TAB_COMPLETE(0x3A, PacketPlayServerTabComplete.class),
            SCOREBOARD_OBJECTIVE(0x3B, PacketPlayServerScoreboardObjective.class),
            SCOREBOARD_SCORE(0x3C, PacketPlayServerScoreboardScore.class),
            SCOREBOARD_DISPLAY(0x3D, PacketPlayServerScoreboardDisplayObjective.class),
            SCOREBOARD_TEAMS(0x3E, PacketPlayServerScoreboardTeam.class),
            CUSTOM_PAYLOAD(0x3F, PacketPlayServerCustomPayload.class),
            DISCONNECT(0x40, PacketPlayServerKickDisconnect.class),
            DIFFICULTY(0x41, PacketPlayServerServerDifficulty.class),
            COMBAT_EVENT(0x42, PacketPlayServerCombatEvent.class),
            CAMERA(0x43, PacketPlayServerCamera.class),
            WORLD_BORDER(0x44, PacketPlayServerWorldBorder.class),
            TITLE(0x45, PacketPlayServerTitle.class),
            SET_COMPRESSION(0x46, PacketPlayServerSetCompression.class),
            PLAYER_LIST_HEADER_FOOTER(0x47, PacketPlayServerPlayerListHeaderFooter.class),
            RESOURCE_PACK_SEND(0x48, PacketPlayServerResourcePackSend.class),
            UPDATE_ENTITY_NBT(0x49, PacketPlayServerUpdateEntityNBT.class);


            private final int id;
            private final EnumProtocolDirection direction;
            private final Class<? extends Packet> clazz;

            Server(int id, Class<? extends Packet> clazz) {
                this.id = id;
                this.direction = EnumProtocolDirection.OUT;
                this.clazz = clazz;
            }
        }
    }
}
