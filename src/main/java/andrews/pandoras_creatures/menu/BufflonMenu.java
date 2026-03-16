package andrews.pandoras_creatures.menu;

import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.menu.slot.BufflonBackAttachmentSlot;
import andrews.pandoras_creatures.menu.slot.BufflonSaddleSlot;
import andrews.pandoras_creatures.menu.slot.BufflonStorageSlot;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.registry.PCMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BufflonMenu extends AbstractContainerMenu {
    private final Container bufflonStorage;
    private final BufflonEntity bufflonEntity;

    public BufflonMenu(int windowId, Inventory playerInventory, int entityId) {
        super(PCMenuTypes.BUFFLON.get(), windowId);

        this.bufflonEntity = (BufflonEntity) playerInventory.player.level().getEntity(entityId);
        this.bufflonStorage = bufflonEntity.bufflonStorage;

        bufflonStorage.startOpen(playerInventory.player);

        // Bufflon equipment slots.
        this.addSlot(new BufflonSaddleSlot(bufflonEntity, bufflonStorage, 0,
                BufflonMenuLayout.SADDLE_SLOT_X, BufflonMenuLayout.SADDLE_SLOT_Y));
        this.addSlot(new BufflonBackAttachmentSlot(bufflonEntity, bufflonStorage, 1,
                BufflonMenuLayout.BACK_ATTACHMENT_SLOT_X, BufflonMenuLayout.BACK_ATTACHMENT_SLOT_Y));

        // Bufflon storage slots. Background rows render one pixel above/left of these icon positions.
        for (int y = 0; y < BufflonMenuLayout.STORAGE_ROWS; ++y) {
            for (int x = 0; x < BufflonMenuLayout.STORAGE_COLUMNS; ++x) {
                this.addSlot(new BufflonStorageSlot(
                        bufflonEntity,
                        bufflonStorage,
                        x + y * BufflonMenuLayout.STORAGE_COLUMNS + 2,
                        BufflonMenuLayout.STORAGE_SLOT_X + x * BufflonMenuLayout.SLOT_SPACING,
                        BufflonMenuLayout.STORAGE_SLOT_Y + y * BufflonMenuLayout.SLOT_SPACING
                ));
            }
        }

        // Player inventory.
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(
                        playerInventory,
                        x + y * 9 + 9,
                        BufflonMenuLayout.PLAYER_INVENTORY_X + x * BufflonMenuLayout.SLOT_SPACING,
                        BufflonMenuLayout.PLAYER_INVENTORY_Y + y * BufflonMenuLayout.SLOT_SPACING
                ));
            }
        }

        // Player hotbar.
        for (int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(
                    playerInventory,
                    x,
                    BufflonMenuLayout.PLAYER_INVENTORY_X + x * BufflonMenuLayout.SLOT_SPACING,
                    BufflonMenuLayout.HOTBAR_Y
            ));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.bufflonStorage.stillValid(player) && this.bufflonEntity.isAlive() && this.bufflonEntity.distanceTo(player) < 8.0F;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemstack = stackInSlot.copy();

            if (index < (6 * 9) + 2) {
                if (!this.moveItemStackTo(stackInSlot, (6 * 9) + 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stackInSlot, 0, getInventorySizeForAttachments(), false)) {
                return ItemStack.EMPTY;
            }

            if (stackInSlot.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    private int getInventorySizeForAttachments() {
        if (!this.slots.get(1).hasItem()) {
            return 2;
        } else {
            ItemStack stack = this.slots.get(1).getItem();
            if (stack.getItem() == PCItems.BUFFLON_PLAYER_SEATS.get()) {
                return 2;
            } else if (stack.getItem() == PCItems.BUFFLON_SMALL_STORAGE.get()) {
                return (3 * 9) + 2;
            } else if (stack.getItem() == PCItems.BUFFLON_LARGE_STORAGE.get()) {
                return (6 * 9) + 2;
            } else {
                return 2;
            }
        }
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.bufflonStorage.stopOpen(player);
    }

    public BufflonEntity getBufflonEntity() {
        return bufflonEntity;
    }
}
