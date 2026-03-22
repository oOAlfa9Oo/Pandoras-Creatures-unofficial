package andrews.pandoras_creatures.entities.bufflon;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Loader-agnostic ownership and owner-relation rules.
 */
public final class BufflonOwnershipRules {
    private BufflonOwnershipRules() {
    }

    public static boolean isOwnedBy(@Nullable UUID ownerId, @Nullable UUID playerId) {
        return ownerId != null && ownerId.equals(playerId);
    }

    public static boolean shouldUseOwnerRelations(boolean isTamed, boolean hasOwner) {
        return isTamed && hasOwner;
    }

    public static boolean shouldSendDeathMessage(boolean serverSide, boolean showDeathMessages, boolean ownerIsServerPlayer) {
        return serverSide && showDeathMessages && ownerIsServerPlayer;
    }
}
