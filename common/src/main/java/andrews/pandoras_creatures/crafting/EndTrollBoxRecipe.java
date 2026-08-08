package andrews.pandoras_creatures.crafting;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import andrews.pandoras_creatures.registry.PCTags;
import andrews.pandoras_creatures.registry.recipe.PCRecipeIds;
import com.mojang.serialization.Codec;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
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
    public EndTrollBoxRecipe(String group, CraftingBookCategory category, int width, int height, NonNullList<Ingredient> ingredients, ItemStack result, boolean showNotification) {
        super(group, category, width, height, ingredients, result, showNotification);
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
        // 1.20.2: RecipeSerializer paso de fromJson(ResourceLocation,JsonObject) a codec().
        // Se delega en el Codec vanilla y se envuelve el resultado en el tipo propio (xmap).
        private static final Codec<EndTrollBoxRecipe> CODEC = VANILLA.codec().xmap(
                recipe -> new EndTrollBoxRecipe(recipe.getGroup(), recipe.category(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY), recipe.showNotification()),
                recipe -> recipe
        );

        @Override
        public Codec<EndTrollBoxRecipe> codec() {
            return CODEC;
        }

        @Nullable
        @Override
        public EndTrollBoxRecipe fromNetwork(FriendlyByteBuf buffer) {
            ShapedRecipe recipe = VANILLA.fromNetwork(buffer);
            return recipe == null ? null : new EndTrollBoxRecipe(recipe.getGroup(), recipe.category(), recipe.getWidth(), recipe.getHeight(), recipe.getIngredients(), recipe.getResultItem(RegistryAccess.EMPTY), recipe.showNotification());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, EndTrollBoxRecipe recipe) {
            VANILLA.toNetwork(buffer, recipe);
        }
    }
}
