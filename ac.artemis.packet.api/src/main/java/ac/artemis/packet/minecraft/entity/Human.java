package ac.artemis.packet.minecraft.entity;

import ac.artemis.packet.minecraft.GameMode;

public interface Human extends LivingEntity {
    String getName();

    GameMode getGameMode();
}
