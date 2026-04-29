package andrews.pandoras_creatures.crafting;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import andrews.pandoras_creatures.registry.PCTags;
import andrews.pandoras_creatures.registry.recipe.PCRecipeIds;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import org.jetbrains.annotations.Nullable;

public class EndTrollBoxRecipe extends ShapedRecipe {
    public EndTrollBoxRecipe(ResourceLocation id, String group, CraftingBookCategory category, int width, int height, NonNullList<Ingredient> ingredients, ItemStack result) {
        super(id, group, category, width, height, ingredients, result);
    }

    @Override
    public ItemStack assemble(CraftingContainer input, RegistryAccess registries) {
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
                        if (slotStack.hasTag()) {
                            coloredEndTrollBox.setTag(slotStack.getTag().copy());
                        }
                        return coloredEndTrollBox;
                    }
                    if (slotStack.hasTag()) {
                        craftingResult.setTag(slotStack.getTag().copy());
                    }
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

        @Override
        public EndTrollBoxRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            ShapedRecipe recipe = VANILLA.fromJson(recipeId, json);
            return new EndTrollBoxRecipe(recipeId, recipe.getGroup(), recipe.category(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY));
        }

        @Nullable
        @Override
        public EndTrollBoxRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            ShapedRecipe recipe = VANILLA.fromNetwork(recipeId, buffer);
            return recipe == null ? null : new EndTrollBoxRecipe(recipeId, recipe.getGroup(), recipe.category(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY));
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, EndTrollBoxRecipe recipe) {
            VANILLA.toNetwork(buffer, recipe);
        }
    }
}
