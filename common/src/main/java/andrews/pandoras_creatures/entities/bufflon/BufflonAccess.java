package andrews.pandoras_creatures.entities.bufflon;

import net.minecraft.world.Container;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

/**
 * Shared Bufflon view contract for menu/screen code that should compile in common
 * without depending on a loader-specific entity implementation.
 */
public interface BufflonAccess extends BufflonHandle {
    int getBufflonId();

    LivingEntity getBufflonLivingEntity();

    Container getBufflonContainer();

    BufflonBackAttachmentType getBufflonBackAttachment();

    boolean isBufflonVehicle();

    int getBufflonPassengerCount();

    boolean isBufflonSaddled();

    boolean hasBufflonBackAttachment();

    boolean isBufflonSitting();

    boolean isBufflonFollowingOwner();

    boolean isBufflonInCombatMode();

    boolean isBufflonOwnedBy(Player player);

    int getBufflonTickCount();
}
