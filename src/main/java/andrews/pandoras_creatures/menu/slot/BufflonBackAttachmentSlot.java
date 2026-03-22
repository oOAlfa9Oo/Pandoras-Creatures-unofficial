package andrews.pandoras_creatures.menu.slot;

import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentItems;
import andrews.pandoras_creatures.entities.bufflon.BufflonInventoryLayout;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BufflonBackAttachmentSlot extends Slot {
    private final BufflonEntity bufflonEntity;
    private final Container bufflonInventory;

    public BufflonBackAttachmentSlot(BufflonEntity bufflonEntity, Container inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.bufflonEntity = bufflonEntity;
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
        if (bufflonEntity.isVehicle()) {
            if (bufflonEntity.getPassengers().size() > 1) {
                return false;
            }
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
