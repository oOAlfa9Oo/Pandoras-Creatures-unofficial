package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.crafting.EndTrollBoxColoringRecipe;
import andrews.pandoras_creatures.crafting.EndTrollBoxRecipe;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PCRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Reference.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<EndTrollBoxRecipe>> END_TROLL_BOX_RECIPE =
            RECIPE_SERIALIZERS.register("end_troll_box", () -> new EndTrollBoxRecipe.Serializer());

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<EndTrollBoxColoringRecipe>> END_TROLL_BOX_COLORING_RECIPE =
            RECIPE_SERIALIZERS.register("end_troll_box_coloring", () -> new EndTrollBoxColoringRecipe.Serializer());
}
