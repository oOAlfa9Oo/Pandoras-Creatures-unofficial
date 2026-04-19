package andrews.pandoras_creatures.crafting;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import andrews.pandoras_creatures.registry.PCTags;
import andrews.pandoras_creatures.registry.recipe.PCRecipeIds;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class EndTrollBoxColoringRecipe extends ShapelessRecipe {
    private final String ourGroup;
    private final CraftingBookCategory ourCategory;
    private final ItemStackTemplate ourResult;
    private final NonNullList<Ingredient> ourIngredients;

    public EndTrollBoxColoringRecipe(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients) {
        this(group, category, ItemStackTemplate.fromNonEmptyStack(result), ingredients);
    }

    public EndTrollBoxColoringRecipe(String group, CraftingBookCategory category, ItemStackTemplate result, NonNullList<Ingredient> ingredients) {
        super(new Recipe.CommonInfo(true), new CraftingRecipe.CraftingBookInfo(category, group), result, ingredients);
        this.ourGroup = group;
        this.ourCategory = category;
        this.ourResult = result;
        this.ourIngredients = ingredients;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        boolean endTrollBoxPresent = false;
        boolean dyeItemPresent = false;
        DyeColor colorItem = null;

        for (int i = 0; i < input.size(); i++) {
            final ItemStack slotStack = input.getItem(i);
            if (!slotStack.isEmpty() && slotStack.getItem() instanceof DyeItem dyeItem) {
                if (dyeItemPresent) {
                    return ItemStack.EMPTY;
                }
                dyeItemPresent = true;
                colorItem = dyeColorFromItem(dyeItem);
            }
        }

        for (int i = 0; i < input.size(); i++) {
            final ItemStack slotStack = input.getItem(i);
            if (!slotStack.isEmpty() && slotStack.is(PCTags.Items.END_TROLL_BOXES)) {
                if (endTrollBoxPresent) {
                    return ItemStack.EMPTY;
                }
                endTrollBoxPresent = true;
                return slotStack.transmuteCopy(EndTrollBoxBlock.getBlockByColor(colorItem), 1);
            }
        }

        return super.assemble(input);
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return (RecipeSerializer) PandorasCreaturesCommon.platform().registry().recipeSerializer(PCRecipeIds.END_TROLL_BOX_COLORING);
    }

    private static DyeColor dyeColorFromItem(DyeItem dyeItem) {
        if (dyeItem == Items.WHITE_DYE) return DyeColor.WHITE;
        if (dyeItem == Items.ORANGE_DYE) return DyeColor.ORANGE;
        if (dyeItem == Items.MAGENTA_DYE) return DyeColor.MAGENTA;
        if (dyeItem == Items.LIGHT_BLUE_DYE) return DyeColor.LIGHT_BLUE;
        if (dyeItem == Items.YELLOW_DYE) return DyeColor.YELLOW;
        if (dyeItem == Items.LIME_DYE) return DyeColor.LIME;
        if (dyeItem == Items.PINK_DYE) return DyeColor.PINK;
        if (dyeItem == Items.GRAY_DYE) return DyeColor.GRAY;
        if (dyeItem == Items.LIGHT_GRAY_DYE) return DyeColor.LIGHT_GRAY;
        if (dyeItem == Items.CYAN_DYE) return DyeColor.CYAN;
        if (dyeItem == Items.PURPLE_DYE) return DyeColor.PURPLE;
        if (dyeItem == Items.BLUE_DYE) return DyeColor.BLUE;
        if (dyeItem == Items.BROWN_DYE) return DyeColor.BROWN;
        if (dyeItem == Items.GREEN_DYE) return DyeColor.GREEN;
        if (dyeItem == Items.RED_DYE) return DyeColor.RED;
        return DyeColor.BLACK;
    }

    public static RecipeSerializer<EndTrollBoxColoringRecipe> serializer() {
        return new RecipeSerializer<>(Serializer.CODEC, Serializer.STREAM_CODEC);
    }

    public static final class Serializer {
        public static final MapCodec<EndTrollBoxColoringRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.ourGroup),
                        CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(r -> r.ourCategory),
                        ItemStackTemplate.CODEC.fieldOf("result").forGetter(r -> r.ourResult),
                        Ingredient.CODEC.listOf()
                                .fieldOf("ingredients")
                                .xmap(list -> {
                                    NonNullList<Ingredient> nonnull = NonNullList.create();
                                    nonnull.addAll(list);
                                    return nonnull;
                                }, list -> list)
                                .forGetter(r -> r.ourIngredients)
                ).apply(instance, EndTrollBoxColoringRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, EndTrollBoxColoringRecipe> STREAM_CODEC =
                StreamCodec.of(
                        (buf, recipe) -> {
                            ByteBufCodecs.STRING_UTF8.encode(buf, recipe.ourGroup);
                            CraftingBookCategory.STREAM_CODEC.encode(buf, recipe.ourCategory);
                            ItemStackTemplate.STREAM_CODEC.encode(buf, recipe.ourResult);
                            buf.writeVarInt(recipe.ourIngredients.size());
                            for (Ingredient ingredient : recipe.ourIngredients) {
                                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
                            }
                        },
                        buf -> {
                            String group = ByteBufCodecs.STRING_UTF8.decode(buf);
                            CraftingBookCategory category = CraftingBookCategory.STREAM_CODEC.decode(buf);
                            ItemStackTemplate result = ItemStackTemplate.STREAM_CODEC.decode(buf);
                            int count = buf.readVarInt();
                            NonNullList<Ingredient> ingredients = NonNullList.create();
                            for (int i = 0; i < count; i++) {
                                ingredients.add(Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
                            }
                            return new EndTrollBoxColoringRecipe(group, category, result, ingredients);
                        }
                );

        private Serializer() {
        }
    }
}
