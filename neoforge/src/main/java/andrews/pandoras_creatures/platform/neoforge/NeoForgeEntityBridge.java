package andrews.pandoras_creatures.platform.neoforge;

import andrews.pandoras_creatures.entities.bases.IAnimatedEntity;
import andrews.pandoras_creatures.network.AnimationSync;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.platform.EntityBridge;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.network.PacketDistributor;

final class NeoForgeEntityBridge implements EntityBridge {
    static final NeoForgeEntityBridge INSTANCE = new NeoForgeEntityBridge();

    private NeoForgeEntityBridge() {
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
        PacketDistributor.sendToPlayersTrackingEntity(entity, new AnimationPayload(entity.getId(), animationIndex));
    }

    @Override
    public int getExperienceDrop(Mob entity, Player attackingPlayer, int originalExperience) {
        return EventHooks.getExperienceDrop(entity, attackingPlayer, originalExperience);
    }

    @Override
    public boolean canEntityGrief(Entity entity) {
        return entity.level() instanceof net.minecraft.server.level.ServerLevel serverLevel
                && EventHooks.canEntityGrief(serverLevel, entity);
    }
}
