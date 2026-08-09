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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;

import java.util.Optional;

public class EndTrollBoxRecipe extends ShapedRecipe {
    public EndTrollBoxRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification) {
        super(group, category, pattern, result, showNotification);
    }

    @Override
    public ItemStack assemble(CraftingContainer input, net.minecraft.core.HolderLookup.Provider registries) {
        final ItemStack craftingResult = super.assemble(input, registries);
        boolean shulkerPresent = false;

        for (int i = 0; i < input.getContainerSize(); i++) {
            final ItemStack slotStack = input.getItem(i);
            if (!slotStack.isEmpty() && slotStack.is(PCTags.Items.VANILLA_SHULKER_BOXES)) {
                if (shulkerPresent) {
                    return ItemStack.EMPTY;
                }
                shulkerPresent = true;

                Block block = Block.byItem(slotStack.getItem());
                if (block instanceof ShulkerBoxBlock shulkerBox) {
                    DyeColor color = shulkerBox.getColor();
                    if (color != null) {
                        ItemStack coloredEndTrollBox = new ItemStack(EndTrollBoxBlock.getBlockByColor(color));
                        coloredEndTrollBox.applyComponents(slotStack.getComponentsPatch());
                        return coloredEndTrollBox;
                    }
                    craftingResult.applyComponents(slotStack.getComponentsPatch());
                }
            }
        }
        return craftingResult;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return PandorasCreaturesCommon.platform().registry().recipeSerializer(PCRecipeIds.END_TROLL_BOX);
    }

    public static class Serializer implements RecipeSerializer<EndTrollBoxRecipe> {
        private static final ShapedRecipe.Serializer VANILLA = new ShapedRecipe.Serializer();
        // 1.20.6: ver EndTrollBoxColoringRecipe.Serializer -- codec() ya devuelve MapCodec<T>
        // directamente, y se agrega streamCodec() en vez de fromNetwork/toNetwork.
        private static final MapCodec<EndTrollBoxRecipe> CODEC = VANILLA.codec().xmap(
                recipe -> new EndTrollBoxRecipe(recipe.getGroup(), recipe.category(), new ShapedRecipePattern(recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), Optional.empty()), recipe.getResultItem(RegistryAccess.EMPTY), recipe.showNotification()),
                recipe -> recipe
        );
        private static final StreamCodec<RegistryFriendlyByteBuf, EndTrollBoxRecipe> STREAM_CODEC = VANILLA.streamCodec().map(
                recipe -> new EndTrollBoxRecipe(recipe.getGroup(), recipe.category(), new ShapedRecipePattern(recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), Optional.empty()), recipe.getResultItem(RegistryAccess.EMPTY), recipe.showNotification()),
                recipe -> recipe
        );

        @Override
        public MapCodec<EndTrollBoxRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, EndTrollBoxRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
