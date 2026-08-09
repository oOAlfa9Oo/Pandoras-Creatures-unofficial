package andrews.pandoras_creatures.content.item;

import andrews.pandoras_creatures.client.item.PlantHatClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

/**
 * NeoForge 20.6.139 no trae RegisterClientExtensionsEvent (agregado en versiones mas nuevas);
 * el mecanismo real de esta version es Item.initializeClient(Consumer), invocado automaticamente
 * por Item#initClient() en el constructor (verificado via javap -c contra neoforge-20.6.139-minecraft.jar).
 */
public final class NeoForgePlantHatItem extends ItemPlantHat {
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(PlantHatClientItemExtensions.INSTANCE);
    }
}
