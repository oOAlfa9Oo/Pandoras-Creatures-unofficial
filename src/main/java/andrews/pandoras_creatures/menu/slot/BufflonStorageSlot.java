package andrews.pandoras_creatures.menu.slot;

import andrews.pandoras_creatures.entities.BufflonEntity;
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
        if (bufflonEntity.hasBackAttachment()) {
            // If the Bufflon Entity has a Small Storage
            if (bufflonEntity.getBackAttachmentType() == 2) {
                return index < 29;
            }
            // If the Bufflon Entity has a Large Storage
            if (bufflonEntity.getBackAttachmentType() == 3) {
                return true;
            }
            return false;
        }
        return false;
    }
}
