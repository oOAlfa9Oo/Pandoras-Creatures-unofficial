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
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import org.jetbrains.annotations.Nullable;

public class EndTrollBoxColoringRecipe extends ShapelessRecipe {
    public EndTrollBoxColoringRecipe(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients) {
        super(group, category, result, ingredients);
    }

    @Override
    public ItemStack assemble(CraftingContainer input, RegistryAccess registries) {
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
                if (slotStack.hasTag()) {
                    coloredEndTrollBox.setTag(slotStack.getTag().copy());
                }
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
        // 1.20.4: Recipe.CODEC hace RECIPE_SERIALIZER.byNameCodec().dispatch(...). Cuando el
        // codec de esta rama del dispatch no es reconocible como "map-shaped" (p.ej. porque se
        // le aplico un Codec.xmap encima, que lo envuelve en un tipo opaco), Mojang cae a un
        // fallback que espera un campo anidado "value" en vez de fusionar los campos al nivel
        // superior del JSON -- produce "Not a JSON object: null" porque ese campo no existe.
        // PCMapCodecs.assumeMap() preserva la forma "map" del codec vanilla antes del xmap,
        // para que el dispatch siga fusionando los campos igual que con el tipo vanilla.
        private static final Codec<EndTrollBoxColoringRecipe> CODEC = PCMapCodecs.assumeMap(VANILLA.codec()).xmap(
                recipe -> new EndTrollBoxColoringRecipe(recipe.getGroup(), recipe.category(), recipe.getResultItem(RegistryAccess.EMPTY), recipe.getIngredients()),
                recipe -> recipe
        ).codec();

        @Override
        public Codec<EndTrollBoxColoringRecipe> codec() {
            return CODEC;
        }

        @Nullable
        @Override
        public EndTrollBoxColoringRecipe fromNetwork(FriendlyByteBuf buffer) {
            ShapelessRecipe recipe = VANILLA.fromNetwork(buffer);
            return recipe == null ? null : new EndTrollBoxColoringRecipe(recipe.getGroup(), recipe.category(), recipe.getResultItem(RegistryAccess.EMPTY), recipe.getIngredients());
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, EndTrollBoxColoringRecipe recipe) {
            VANILLA.toNetwork(buffer, recipe);
        }
    }
}
