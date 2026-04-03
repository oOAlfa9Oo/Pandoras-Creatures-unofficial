package andrews.pandoras_creatures.menu.slot;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.bufflon.BufflonAccess;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BufflonSaddleSlot extends Slot {
    private final BufflonAccess bufflon;

    public BufflonSaddleSlot(BufflonAccess bufflon, Container inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
        this.bufflon = bufflon;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean mayPickup(Player player) {
        return !bufflon.isBufflonVehicle();
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(PandorasCreaturesCommon.platform().registry().item(PCItemIds.BUFFLON_SADDLE));
    }
}
