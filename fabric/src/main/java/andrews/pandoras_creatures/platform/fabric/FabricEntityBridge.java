package andrews.pandoras_creatures.platform.fabric;

import andrews.pandoras_creatures.entities.bases.IAnimatedEntity;
import andrews.pandoras_creatures.network.AnimationSync;
import andrews.pandoras_creatures.network.PCPayloadIds;
import andrews.pandoras_creatures.platform.EntityBridge;
import andrews.pandoras_creatures.util.animation.Animation;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;

final class FabricEntityBridge implements EntityBridge {
    static final FabricEntityBridge INSTANCE = new FabricEntityBridge();

    private FabricEntityBridge() {
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

        for (var player : PlayerLookup.tracking(entity)) {
            FriendlyByteBuf payload = PacketByteBufs.create();
            payload.writeInt(entity.getId());
            payload.writeInt(animationIndex);
            ServerPlayNetworking.send(player, PCPayloadIds.id(PCPayloadIds.ANIMATION), payload);
        }
    }

    @Override
    public int getExperienceDrop(Mob entity, Player attackingPlayer, int originalExperience) {
        return originalExperience;
    }

    @Override
    public boolean canEntityGrief(Entity entity) {
        return entity.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
    }
}
