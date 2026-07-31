package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.crafting.EndTrollBoxColoringRecipe;
import andrews.pandoras_creatures.crafting.EndTrollBoxRecipe;
import andrews.pandoras_creatures.registry.recipe.PCRecipeIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public final class PCFabricRecipeSerializers {
    private PCFabricRecipeSerializers() {
    }

    public static void register() {
        register(PCRecipeIds.END_TROLL_BOX, new EndTrollBoxRecipe.Serializer());
        register(PCRecipeIds.END_TROLL_BOX_COLORING, new EndTrollBoxColoringRecipe.Serializer());
    }

    private static <T extends RecipeSerializer<?>> T register(String path, T serializer) {
        return Registry.register(
                BuiltInRegistries.RECIPE_SERIALIZER,
                ResourceLocation.fromNamespaceAndPath(Reference.MODID, path),
                serializer);
    }
}