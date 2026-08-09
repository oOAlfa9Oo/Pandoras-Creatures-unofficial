package andrews.pandoras_creatures.crafting;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import andrews.pandoras_creatures.registry.PCTags;
import andrews.pandoras_creatures.registry.recipe.PCRecipeIds;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class EndTrollBoxColoringRecipe extends ShapelessRecipe {
    public EndTrollBoxColoringRecipe(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients) {
        super(group, category, result, ingredients);
    }

    @Override
    public ItemStack assemble(CraftingContainer input, net.minecraft.core.HolderLookup.Provider registries) {
        boolean endTrollBoxPresent = false;
        boolean dyeItemPresent = false;
        DyeColor colorItem = null;

        for (int i = 0; i < input.getContainerSize(); i++) {
            final ItemStack slotStack = input.getItem(i);
            if (!slotStack.isEmpty() && slotStack.getItem() instanceof DyeItem dyeItem) {
                if (dyeItemPresent) {
                    return ItemStack.EMPTY;
                }
                dyeItemPresent = true;
                colorItem = dyeItem.getDyeColor();
            }
        }

        for (int i = 0; i < input.getContainerSize(); i++) {
            final ItemStack slotStack = input.getItem(i);
            if (!slotStack.isEmpty() && slotStack.is(PCTags.Items.END_TROLL_BOXES)) {
                if (endTrollBoxPresent || colorItem == null) {
                    return ItemStack.EMPTY;
                }
                endTrollBoxPresent = true;
                ItemStack coloredEndTrollBox = new ItemStack(EndTrollBoxBlock.getBlockByColor(colorItem));
                coloredEndTrollBox.applyComponents(slotStack.getComponentsPatch());
                return coloredEndTrollBox;
            }
        }

        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PandorasCreaturesCommon.platform().registry().recipeSerializer(PCRecipeIds.END_TROLL_BOX_COLORING);
    }

    public static class Serializer implements RecipeSerializer<EndTrollBoxColoringRecipe> {
        private static final ShapelessRecipe.Serializer VANILLA = new ShapelessRecipe.Serializer();
        // 1.20.6: RecipeSerializer.codec() ahora devuelve MapCodec<T> directamente (antes
        // Codec<T>), asi que ya no hace falta el workaround de PCMapCodecs (ver 1.20.4) para
        // que Recipe.CODEC (RECIPE_SERIALIZER.byNameCodec().dispatch(...)) fusione los campos
        // al nivel superior del JSON. Tambien se agrega streamCodec() (reemplaza fromNetwork/
        // toNetwork por separado), delegando en el StreamCodec vanilla via .map().
        private static final MapCodec<EndTrollBoxColoringRecipe> CODEC = VANILLA.codec().xmap(
                recipe -> new EndTrollBoxColoringRecipe(recipe.getGroup(), recipe.category(), recipe.getResultItem(RegistryAccess.EMPTY), recipe.getIngredients()),
                recipe -> recipe
        );
        private static final StreamCodec<RegistryFriendlyByteBuf, EndTrollBoxColoringRecipe> STREAM_CODEC = VANILLA.streamCodec().map(
                recipe -> new EndTrollBoxColoringRecipe(recipe.getGroup(), recipe.category(), recipe.getResultItem(RegistryAccess.EMPTY), recipe.getIngredients()),
                recipe -> recipe
        );

        @Override
        public MapCodec<EndTrollBoxColoringRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, EndTrollBoxColoringRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
