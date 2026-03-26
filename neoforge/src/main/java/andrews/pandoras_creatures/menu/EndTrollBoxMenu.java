package andrews.pandoras_creatures.menu;

import andrews.pandoras_creatures.menu.slot.EndTrollBoxSlot;
import andrews.pandoras_creatures.registry.PCMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EndTrollBoxMenu extends AbstractContainerMenu {
    private final Container inventory;

    public EndTrollBoxMenu(int windowId, Inventory playerInv) {
        this(windowId, playerInv, new SimpleContainer(54));
    }

    public EndTrollBoxMenu(int windowId, Inventory playerInv, Container inventory) {
        super(PCMenuTypes.END_TROLL_BOX.get(), windowId);
        checkContainerSize(inventory, 54);
        this.inventory = inventory;
        inventory.startOpen(playerInv.player);

        // End Troll Box Slots (6 rows x 9 columns = 54 slots)
        for (int y = 0; y < 6; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new EndTrollBoxSlot(inventory, x + y * 9, 8 + x * 18, 18 + y * 18));
            }
        }

        // Player Inventory Slots
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlot(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 138 + y * 18));
            }
        }

        // Player Hot Bar Slots
        for (int x = 0; x < 9; ++x) {
            this.addSlot(new Slot(playerInv, x, 8 + x * 18, 196));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            itemstack = stackInSlot.copy();
            if (index < this.inventory.getContainerSize()) {
                if (!this.moveItemStackTo(stackInSlot, this.inventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stackInSlot, 0, this.inventory.getContainerSize(), false)) {
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

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.inventory.stopOpen(player);
    }
}
