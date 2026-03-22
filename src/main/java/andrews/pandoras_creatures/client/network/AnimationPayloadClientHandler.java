package andrews.pandoras_creatures.client.network;

import andrews.pandoras_creatures.entities.bases.IAnimatedEntity;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * Client-only handler for animation synchronization payloads.
 */
@OnlyIn(Dist.CLIENT)
public final class AnimationPayloadClientHandler {

    private AnimationPayloadClientHandler() {
    }

    public static void handle(AnimationPayload payload) {
        Level level = Minecraft.getInstance().level;
        if (level == null) {
            return;
        }

        Entity entity = level.getEntity(payload.entityId());
        if (!(entity instanceof IAnimatedEntity animatedEntity)) {
            return;
        }

        if (payload.animationIndex() == -1) {
            animatedEntity.resetAnimation();
            animatedEntity.setAnimationTick(0);
            return;
        }

        Animation[] animations = animatedEntity.getAnimations();
        if (payload.animationIndex() >= 0 && payload.animationIndex() < animations.length) {
            animatedEntity.setPlayingAnimation(animations[payload.animationIndex()]);
            animatedEntity.setAnimationTick(0);
        }
    }
}
