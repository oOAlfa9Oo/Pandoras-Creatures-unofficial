package andrews.pandoras_creatures.platform;

import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

/**
 * Loader-specific hooks for shared entity behavior that still needs runtime integration.
 */
public interface EntityBridge {
    void syncAnimation(Entity entity, Animation animation);

    int getExperienceDrop(Mob entity, Player attackingPlayer, int originalExperience);
}
