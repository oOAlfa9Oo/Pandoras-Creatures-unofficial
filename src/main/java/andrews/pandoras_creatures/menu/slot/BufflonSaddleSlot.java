package andrews.pandoras_creatures.menu.slot;

import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.registry.PCItems;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BufflonSaddleSlot extends Slot {
    private final BufflonEntity bufflonEntity;

    public BufflonSaddleSlot(BufflonEntity bufflonEntity, Container inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.bufflonEntity = bufflonEntity;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean mayPickup(Player player) {
        return !bufflonEntity.isVehicle();
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(PCItems.BUFFLON_SADDLE.get());
    }
}
