package andrews.pandoras_creatures.crafting;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import andrews.pandoras_creatures.registry.PCTags;
import andrews.pandoras_creatures.registry.recipe.PCRecipeIds;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class EndTrollBoxRecipe extends ShapedRecipe {
    private final String ourGroup;
    private final CraftingBookCategory ourCategory;
    private final ShapedRecipePattern ourPattern;
    private final ItemStackTemplate ourResult;

    public EndTrollBoxRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result) {
        this(group, category, pattern, ItemStackTemplate.fromNonEmptyStack(result));
    }

    public EndTrollBoxRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStackTemplate result) {
        super(new Recipe.CommonInfo(true), new CraftingRecipe.CraftingBookInfo(category, group), pattern, result);
        this.ourGroup = group;
        this.ourCategory = category;
        this.ourPattern = pattern;
        this.ourResult = result;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        final ItemStack craftingResult = super.assemble(input);
        boolean shulkerPresent = false;

        for (int i = 0; i < input.size(); i++) {
            final ItemStack slotStack = input.getItem(i);
            if (!slotStack.isEmpty() && slotStack.is(PCTags.Items.VANILLA_SHULKER_BOXES)) {
                if (shulkerPresent) {
                    return ItemStack.EMPTY;
                }
                shulkerPresent = true;

                Block block = Block.byItem(slotStack.getItem());
                if (block instanceof ShulkerBoxBlock shulkerBox) {
                    DyeColor color = shulkerBox.getColor();
                    return slotStack.transmuteCopy(EndTrollBoxBlock.getBlockByColor(color), 1);
                }
            }
        }
        return craftingResult;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public RecipeSerializer<ShapedRecipe> getSerializer() {
        return (RecipeSerializer) PandorasCreaturesCommon.platform().registry().recipeSerializer(PCRecipeIds.END_TROLL_BOX);
    }

    public static RecipeSerializer<EndTrollBoxRecipe> serializer() {
        return new RecipeSerializer<>(Serializer.CODEC, Serializer.STREAM_CODEC);
    }

    public static final class Serializer {
        public static final MapCodec<EndTrollBoxRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.ourGroup),
                        CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(r -> r.ourCategory),
                        ShapedRecipePattern.MAP_CODEC.forGetter(r -> r.ourPattern),
                        ItemStackTemplate.CODEC.fieldOf("result").forGetter(r -> r.ourResult)
                ).apply(instance, EndTrollBoxRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, EndTrollBoxRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        ByteBufCodecs.STRING_UTF8, r -> r.ourGroup,
                        CraftingBookCategory.STREAM_CODEC, r -> r.ourCategory,
                        ShapedRecipePattern.STREAM_CODEC, r -> r.ourPattern,
                        ItemStackTemplate.STREAM_CODEC, r -> r.ourResult,
                        EndTrollBoxRecipe::new
                );

        private Serializer() {
        }
    }
}
