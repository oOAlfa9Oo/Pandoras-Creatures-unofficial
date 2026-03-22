package andrews.pandoras_creatures.entities.bufflon;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.PlayerTeam;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Shared ownership helpers for Bufflon tame state and owner-derived relations.
 */
public final class BufflonOwnership {
    private BufflonOwnership() {
    }

    public static boolean isOwnedBy(@Nullable UUID ownerId, @Nullable Player player) {
        return BufflonOwnershipRules.isOwnedBy(ownerId, player == null ? null : player.getUUID());
    }

    public static boolean isOwnedBy(@Nullable UUID ownerId, @Nullable UUID playerId) {
        return BufflonOwnershipRules.isOwnedBy(ownerId, playerId);
    }

    @Nullable
    public static LivingEntity resolveOwner(Level level, @Nullable UUID ownerId) {
        try {
            return ownerId == null ? null : level.getPlayerByUUID(ownerId);
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }

    public static boolean isOwner(@Nullable LivingEntity entity, @Nullable LivingEntity owner) {
        return entity != null && entity == owner;
    }

    public static boolean shouldUseOwnerRelations(boolean isTamed, boolean hasOwner) {
        return BufflonOwnershipRules.shouldUseOwnerRelations(isTamed, hasOwner);
    }

    @Nullable
    public static PlayerTeam getInheritedTeam(boolean isTamed, @Nullable LivingEntity owner) {
        return shouldUseOwnerRelations(isTamed, owner != null) ? owner.getTeam() : null;
    }

    public static boolean isAlliedTo(boolean isTamed, @Nullable LivingEntity owner, Entity entity) {
        return shouldUseOwnerRelations(isTamed, owner != null) && (entity == owner || owner.isAlliedTo(entity));
    }

    public static boolean shouldSendDeathMessage(boolean serverSide, boolean showDeathMessages, boolean ownerIsServerPlayer) {
        return BufflonOwnershipRules.shouldSendDeathMessage(serverSide, showDeathMessages, ownerIsServerPlayer);
    }

    @Nullable
    public static ServerPlayer getDeathMessageRecipient(boolean serverSide, boolean showDeathMessages, @Nullable LivingEntity owner) {
        if (shouldSendDeathMessage(serverSide, showDeathMessages, owner instanceof ServerPlayer)) {
            return (ServerPlayer) owner;
        }
        return null;
    }
}
