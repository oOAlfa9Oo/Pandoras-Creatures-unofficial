package andrews.pandoras_creatures.menu;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.bufflon.BufflonAccess;
import andrews.pandoras_creatures.entities.bufflon.BufflonBackAttachmentItems;
import andrews.pandoras_creatures.entities.bufflon.BufflonInventoryLayout;
import andrews.pandoras_creatures.menu.slot.BufflonBackAttachmentSlot;
import andrews.pandoras_creatures.menu.slot.BufflonSaddleSlot;
import andrews.pandoras_creatures.menu.slot.BufflonStorageSlot;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BufflonMenu extends AbstractContainerMenu {
    private final Container bufflonStorage;
    private final BufflonAccess bufflon;
    private final LivingEntity bufflonEntity;

    public BufflonMenu(int windowId, Inventory playerInventory, int entityId) {
        super(PandorasCreaturesCommon.platform().registry().menuType(PCMenuIds.BUFFLON), windowId);

        Entity entity = playerInventory.player.level().getEntity(entityId);
        if (!(entity instanceof BufflonAccess bufflonAccess)) {
            throw new IllegalStateException("Expected BufflonAccess for menu entity id " + entityId);
        }

        this.bufflon = bufflonAccess;
        this.bufflonEntity = bufflonAccess.getBufflonLivingEntity();
        this.bufflonStorage = bufflonAccess.getBufflonContainer();

        bufflonStorage.startOpen(playerInventory.player);

        this.addSlot(new BufflonSaddleSlot(bufflon, bufflonStorage, BufflonInventoryLayout.SADDLE_SLOT,
                BufflonMenuLayout.SADDLE_SLOT_X, BufflonMenuLayout.SADDLE_SLOT_Y));
        this.addSlot(new BufflonBackAttachmentSlot(bufflon, bufflonStorage, BufflonInventoryLayout.BACK_ATTACHMENT_SLOT,
                BufflonMenuLayout.BACK_ATTACHMENT_SLOT_X, BufflonMenuLayout.BACK_ATTACHMENT_SLOT_Y));

        for (int y = 0; y < BufflonMenuLayout.STORAGE_ROWS; ++y) {
            for (int x = 0; x < BufflonMenuLayout.STORAGE_COLUMNS; ++x) {
                this.addSlot(new BufflonStorageSlot(
                        bufflon,
                        bufflonStorage,
                        BufflonInventoryLayout.FIRST_STORAGE_SLOT + x + y * BufflonMenuLayout.STORAGE_COLUMNS,
                        BufflonMenuLayout.STORAGE_SLOT_X + x * BufflonMenuLayout.SLOT_SPACING,
                        BufflonMenuLayout.STORAGE_SLOT_Y + y * BufflonMenuLayout.SLOT_SPACING
                ));
            }
        }

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

            if (index < this.bufflonStorage.getContainerSize()) {
                if (!this.moveItemStackTo(stackInSlot, this.bufflonStorage.getContainerSize(), this.slots.size(), true)) {
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
        if (!this.slots.get(BufflonInventoryLayout.BACK_ATTACHMENT_SLOT).hasItem()) {
            return BufflonInventoryLayout.EQUIPMENT_SLOT_COUNT;
        }

        ItemStack stack = this.slots.get(BufflonInventoryLayout.BACK_ATTACHMENT_SLOT).getItem();
        return BufflonInventoryLayout.getAccessibleSlotCount(BufflonBackAttachmentItems.getType(stack));
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.bufflonStorage.stopOpen(player);
    }

    public BufflonAccess getBufflon() {
        return bufflon;
    }

    public LivingEntity getBufflonEntity() {
        return bufflonEntity;
    }
}
