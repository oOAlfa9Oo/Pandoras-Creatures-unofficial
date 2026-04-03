package andrews.pandoras_creatures.menu.slot;

import andrews.pandoras_creatures.entities.bufflon.BufflonAccess;
import andrews.pandoras_creatures.entities.bufflon.BufflonInventoryLayout;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;

public class BufflonStorageSlot extends Slot {
    private final BufflonAccess bufflon;
    private final int index;

    public BufflonStorageSlot(BufflonAccess bufflon, Container inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.bufflon = bufflon;
        this.index = index;
    }

    @Override
    public boolean isActive() {
        return BufflonInventoryLayout.isStorageSlotActive(this.bufflon.getBufflonBackAttachment(), this.index);
    }
}
