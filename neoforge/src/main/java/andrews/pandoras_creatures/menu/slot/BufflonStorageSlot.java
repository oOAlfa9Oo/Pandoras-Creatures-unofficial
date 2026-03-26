package andrews.pandoras_creatures.menu.slot;

import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.entities.bufflon.BufflonInventoryLayout;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

public class BufflonStorageSlot extends Slot {
    private final BufflonEntity bufflonEntity;
    private final int index;

    public BufflonStorageSlot(BufflonEntity bufflonEntity, Container inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.bufflonEntity = bufflonEntity;
        this.index = index;
    }

    @Override
    public boolean isActive() {
        return BufflonInventoryLayout.isStorageSlotActive(this.bufflonEntity.getBackAttachment(), this.index);
    }
}
