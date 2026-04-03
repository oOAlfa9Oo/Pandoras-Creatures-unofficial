package andrews.pandoras_creatures.menu.slot;

import andrews.pandoras_creatures.entities.bufflon.BufflonAccess;
import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentItems;
import andrews.pandoras_creatures.entities.bufflon.BufflonInventoryLayout;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BufflonBackAttachmentSlot extends Slot {
    private final BufflonAccess bufflon;
    private final Container bufflonInventory;

    public BufflonBackAttachmentSlot(BufflonAccess bufflon, Container inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.bufflon = bufflon;
        this.bufflonInventory = inventory;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return BufflonBackAttachmentItems.isSupported(stack);
    }

    @Override
    public boolean mayPickup(Player player) {
        if (bufflon.isBufflonVehicle() && bufflon.getBufflonPassengerCount() > 1) {
            return false;
        }
        return hasNoItemsInInventory();
    }

    private boolean hasNoItemsInInventory() {
        for (int i = BufflonInventoryLayout.FIRST_STORAGE_SLOT; i < bufflonInventory.getContainerSize(); i++) {
            if (!bufflonInventory.getItem(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
