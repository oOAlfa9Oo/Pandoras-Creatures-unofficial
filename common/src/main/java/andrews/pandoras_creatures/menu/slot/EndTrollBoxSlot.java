package andrews.pandoras_creatures.menu.slot;

import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class EndTrollBoxSlot extends Slot {
    public EndTrollBoxSlot(Container inventory, int slotIndex, int xPosition, int yPosition) {
        super(inventory, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        Block block = Block.byItem(stack.getItem());
        if (block instanceof ShulkerBoxBlock || block instanceof EndTrollBoxBlock) {
            return false;
        }
        return true;
    }
}
