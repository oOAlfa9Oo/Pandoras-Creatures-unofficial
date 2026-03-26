package andrews.pandoras_creatures.util;

import andrews.pandoras_creatures.entities.bases.IAnimatedEntity;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * Utility class for sending network packets.
 * Provides helper methods for common network operations.
 */
public class NetworkUtil {

    /**
     * Send animation sync packet to all players tracking the entity
     * @param entity The animated entity
     * @param animationIndex The animation index to play (-1 to reset)
     */
    public static void sendAnimationPacket(Entity entity, int animationIndex) {
        if (!entity.level().isClientSide()) {
            applyServerAnimationState(entity, animationIndex);
            PacketDistributor.sendToPlayersTrackingEntity(
                    entity,
                    new AnimationPayload(entity.getId(), animationIndex)
            );
        }
    }

    /**
     * Send animation sync packet to all players tracking the entity
     * @param entity The animated entity (must implement IAnimatedEntity)
     * @param animation The animation to play
     */
    public static void sendAnimationPacket(Entity entity, Animation animation) {
        if (!entity.level().isClientSide() && entity instanceof IAnimatedEntity animatedEntity) {
            Animation[] animations = animatedEntity.getAnimations();
            int index = -1;
            for (int i = 0; i < animations.length; i++) {
                if (animations[i] == animation) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                applyServerAnimationState(entity, index);
                PacketDistributor.sendToPlayersTrackingEntity(
                        entity,
                        new AnimationPayload(entity.getId(), index)
                );
            }
        }
    }

    private static void applyServerAnimationState(Entity entity, int animationIndex) {
        if (!(entity instanceof IAnimatedEntity animatedEntity)) {
            return;
        }

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
}
