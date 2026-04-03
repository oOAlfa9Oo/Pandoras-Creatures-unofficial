package andrews.pandoras_creatures.network;

import andrews.pandoras_creatures.entities.bases.IAnimatedEntity;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

/**
 * Shared animation sync helper consumed by loader adapters.
 */
public final class AnimationSync {
    private AnimationSync() {
    }

    public static int findAnimationIndex(IAnimatedEntity animatedEntity, Animation animation) {
        Animation[] animations = animatedEntity.getAnimations();
        for (int i = 0; i < animations.length; i++) {
            if (animations[i] == animation) {
                return i;
            }
        }
        return -1;
    }

    public static void apply(IAnimatedEntity animatedEntity, int animationIndex) {
        if (animationIndex == -1) {
            animatedEntity.resetAnimation();
            animatedEntity.setAnimationTick(0);
            return;
        }

        Animation[] animations = animatedEntity.getAnimations();
        if (animationIndex >= 0 && animationIndex < animations.length) {
            animatedEntity.setPlayingAnimation(animations[animationIndex]);
            animatedEntity.setAnimationTick(0);
        }
    }

    public static void apply(Level level, int entityId, int animationIndex) {
        if (level == null) {
            return;
        }

        Entity entity = level.getEntity(entityId);
        if (entity instanceof IAnimatedEntity animatedEntity) {
            apply(animatedEntity, animationIndex);
        }
    }
}
