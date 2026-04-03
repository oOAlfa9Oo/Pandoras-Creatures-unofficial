package andrews.pandoras_creatures.client.bufflon;

import andrews.pandoras_creatures.entities.bufflon.BufflonAccess;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

/**
 * Shared rules for redirecting the player inventory key to the Bufflon menu while mounted.
 */
public final class BufflonRiderInventoryRules {
    private BufflonRiderInventoryRules() {
    }

    public static boolean shouldOpenMountedBufflonInventory(Player player) {
        return getMountedBufflon(player) != null;
    }

    public static BufflonAccess getMountedBufflon(Player player) {
        if (player == null || !player.isPassenger()) {
            return null;
        }

        Entity vehicle = player.getVehicle();
        if (!(vehicle instanceof BufflonAccess bufflon)) {
            return null;
        }

        return bufflon.isBufflonTamed() ? bufflon : null;
    }
}
