package andrews.pandoras_creatures.forge.content.item;

import andrews.pandoras_creatures.content.item.ItemPlantHat;
import andrews.pandoras_creatures.forge.client.item.ForgePlantHatClientItemExtensions;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public final class ForgePlantHatItem extends ItemPlantHat {
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(ForgePlantHatClientItemExtensions.INSTANCE);
    }
}
