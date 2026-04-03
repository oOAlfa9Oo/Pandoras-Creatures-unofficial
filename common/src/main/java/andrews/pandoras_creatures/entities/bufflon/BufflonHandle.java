package andrews.pandoras_creatures.entities.bufflon;

import net.minecraft.server.level.ServerPlayer;

/**
 * Shared interaction contract for loader adapters that need to operate on a Bufflon instance
 * without depending on the loader-specific entity class.
 */
public interface BufflonHandle {
    void openBufflonMenu(ServerPlayer player);

    boolean isBufflonTamed();

    boolean isBufflonOwnedBy(ServerPlayer player);

    void setBufflonOrderedToSit(boolean shouldSit);

    void setBufflonFollowingOwner(boolean shouldFollow);

    void setBufflonCombatMode(boolean combatMode);
}
