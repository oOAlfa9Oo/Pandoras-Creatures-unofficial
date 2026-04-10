package andrews.pandoras_creatures.forge.content.item;

import andrews.pandoras_creatures.content.item.EndTrollBoxItem;
import andrews.pandoras_creatures.forge.client.item.ForgeEndTrollBoxClientExtensions;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public final class ForgeEndTrollBoxItem extends EndTrollBoxItem {
    public ForgeEndTrollBoxItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(ForgeEndTrollBoxClientExtensions.INSTANCE);
    }
}
