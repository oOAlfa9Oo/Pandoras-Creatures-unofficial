package andrews.pandoras_creatures.forge.platform;

import andrews.pandoras_creatures.platform.EntityBridge;
import andrews.pandoras_creatures.entities.bases.IAnimatedEntity;
import andrews.pandoras_creatures.forge.network.PCForgeNetwork;
import andrews.pandoras_creatures.network.AnimationSync;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

final class ForgeEntityBridge implements EntityBridge {
    static final ForgeEntityBridge INSTANCE = new ForgeEntityBridge();

    private ForgeEntityBridge() {
    }

    @Override
    public void syncAnimation(Entity entity, Animation animation) {
        if (entity.level().isClientSide() || !(entity instanceof IAnimatedEntity animatedEntity)) {
            return;
        }

        int animationIndex = AnimationSync.findAnimationIndex(animatedEntity, animation);
        if (animationIndex < 0) {
            return;
        }

        animatedEntity.setPlayingAnimation(animation);
        animatedEntity.setAnimationTick(0);
        PCForgeNetwork.sendToTrackingEntityAndSelf(entity, new AnimationPayload(entity.getId(), animationIndex));
    }

    @Override
    public int getExperienceDrop(Mob entity, Player attackingPlayer, int originalExperience) {
        return originalExperience;
    }

    @Override
    public boolean canEntityGrief(Entity entity) {
        return true;
    }
}
