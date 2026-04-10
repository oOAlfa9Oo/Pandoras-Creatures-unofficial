package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.crafting.EndTrollBoxColoringRecipe;
import andrews.pandoras_creatures.crafting.EndTrollBoxRecipe;
import andrews.pandoras_creatures.registry.recipe.PCRecipeIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;

public final class PCForgeRecipeSerializers {
    private PCForgeRecipeSerializers() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeRecipeSerializers::registerRecipeSerializers);
    }

    private static void registerRecipeSerializers(RegisterEvent event) {
        if (!event.getRegistryKey().equals(Registries.RECIPE_SERIALIZER)) {
            return;
        }

        register(event, PCRecipeIds.END_TROLL_BOX, new EndTrollBoxRecipe.Serializer());
        register(event, PCRecipeIds.END_TROLL_BOX_COLORING, new EndTrollBoxColoringRecipe.Serializer());
    }

    private static <T extends RecipeSerializer<?>> void register(RegisterEvent event, String path, T serializer) {
        event.register(Registries.RECIPE_SERIALIZER, helper ->
                helper.register(ResourceLocation.fromNamespaceAndPath(Reference.MODID, path), serializer));
    }
}
