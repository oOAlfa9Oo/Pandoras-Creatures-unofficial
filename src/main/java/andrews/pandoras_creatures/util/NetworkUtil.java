package andrews.pandoras_creatures.util;

import andrews.pandoras_creatures.entities.bases.IAnimatedEntity;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
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
                PacketDistributor.sendToPlayersTrackingEntity(
                        entity,
                        new AnimationPayload(entity.getId(), index)
                );
            }
        }
    }

    /**
     * Request to open Bufflon inventory (client -> server)
     * @param entityId The Bufflon entity ID
     */
    public static void requestBufflonInventory(int entityId) {
        PacketDistributor.sendToServer(new BufflonInventoryPayload(entityId));
    }

    /**
     * Request to set Bufflon sit state (client -> server)
     * @param entityId The Bufflon entity ID
     * @param shouldSit Whether the Bufflon should sit
     */
    public static void requestBufflonSit(int entityId, boolean shouldSit) {
        PacketDistributor.sendToServer(new BufflonSitPayload(entityId, shouldSit));
    }

    /**
     * Request to set Bufflon follow state (client -> server)
     * @param entityId The Bufflon entity ID
     * @param shouldFollow Whether the Bufflon should follow
     */
    public static void requestBufflonFollow(int entityId, boolean shouldFollow) {
        PacketDistributor.sendToServer(new BufflonFollowPayload(entityId, shouldFollow));
    }

    /**
     * Request to set Bufflon combat mode (client -> server)
     * @param entityId The Bufflon entity ID
     * @param combatMode Whether combat mode should be enabled
     */
    public static void requestBufflonCombatMode(int entityId, boolean combatMode) {
        PacketDistributor.sendToServer(new BufflonCombatModePayload(entityId, combatMode));
    }
}
