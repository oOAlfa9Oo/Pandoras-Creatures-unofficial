package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.content.block.ArachnonCrystalBlock;
import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import andrews.pandoras_creatures.content.block.PCPlantBlock;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PCBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, Reference.MODID);
    private static final List<DeferredHolder<Block, Block>> END_TROLL_BOX_BLOCKS = new ArrayList<>();
    private static final Map<DyeColor, DeferredHolder<Block, Block>> COLORED_END_TROLL_BOXES = new EnumMap<>(DyeColor.class);

    // Simple blocks
    public static final DeferredHolder<Block, Block> ARACHNON_CRYSTAL = registerBlock(PCBlockIds.ARACHNON_CRYSTAL,
            () -> new ArachnonCrystalBlock(BlockBehaviour.Properties.of()
                    .strength(1.5F, 6.0F)
                    .lightLevel(state -> 9)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()));

    public static final DeferredHolder<Block, Block> HORSETAIL = registerBlock(PCBlockIds.HORSETAIL,
            () -> new PCPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .noCollission()
                    .noOcclusion()));

    public static final DeferredHolder<Block, Block> DHANIA = registerBlock(PCBlockIds.DHANIA,
            () -> new PCPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .noCollission()
                    .noOcclusion()));

    public static final DeferredHolder<Block, Block> HILL_BLOOM = registerBlock(PCBlockIds.HILL_BLOOM,
            () -> new PCPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .noCollission()
                    .noOcclusion()));

    // End Troll Boxes - Using custom EndTrollBoxBlock and EndTrollBoxItem
    public static final DeferredHolder<Block, Block> END_TROLL_BOX = registerEndTrollBox(
            PCBlockIds.END_TROLL_BOX,
            null,
            Blocks.SHULKER_BOX
    );

    public static final DeferredHolder<Block, Block> WHITE_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.WHITE, Blocks.WHITE_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> ORANGE_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.ORANGE, Blocks.ORANGE_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> MAGENTA_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.MAGENTA, Blocks.MAGENTA_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> LIGHT_BLUE_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> YELLOW_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.YELLOW, Blocks.YELLOW_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> LIME_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.LIME, Blocks.LIME_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> PINK_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.PINK, Blocks.PINK_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> GRAY_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.GRAY, Blocks.GRAY_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> LIGHT_GRAY_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> CYAN_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.CYAN, Blocks.CYAN_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> PURPLE_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.PURPLE, Blocks.PURPLE_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> BLUE_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.BLUE, Blocks.BLUE_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> BROWN_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.BROWN, Blocks.BROWN_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> GREEN_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.GREEN, Blocks.GREEN_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> RED_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.RED, Blocks.RED_SHULKER_BOX);

    public static final DeferredHolder<Block, Block> BLACK_END_TROLL_BOX = registerColoredEndTrollBox(DyeColor.BLACK, Blocks.BLACK_SHULKER_BOX);

    // Pandoric Shard - TODO: Implement PandoricShardBlock class
    public static final DeferredHolder<Block, Block> PANDORIC_SHARD = registerBlock(PCBlockIds.PANDORIC_SHARD,
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)
                    .noOcclusion()
                    .lightLevel(state -> 10)));

    private static DeferredHolder<Block, Block> registerBlock(String name, Supplier<Block> block) {
        return BLOCKS.register(name, block);
    }

    private static DeferredHolder<Block, Block> registerColoredEndTrollBox(DyeColor color, Block sourceBlock) {
        return registerEndTrollBox(PCBlockIds.endTrollBox(color.getName()), color, sourceBlock);
    }

    private static DeferredHolder<Block, Block> registerEndTrollBox(String name, @Nullable DyeColor color, Block sourceBlock) {
        return registerTrackedEndTrollBox(color, name,
                () -> new EndTrollBoxBlock(color, BlockBehaviour.Properties.ofFullCopy(sourceBlock)));
    }

    private static DeferredHolder<Block, Block> registerTrackedEndTrollBox(@Nullable DyeColor color, String name, Supplier<Block> block) {
        DeferredHolder<Block, Block> blockHolder = BLOCKS.register(name, block);
        END_TROLL_BOX_BLOCKS.add(blockHolder);
        if (color != null) {
            COLORED_END_TROLL_BOXES.put(color, blockHolder);
        }
        return blockHolder;
    }

    public static List<Block> getEndTrollBoxBlocks() {
        return END_TROLL_BOX_BLOCKS.stream()
                .map(DeferredHolder::get)
                .toList();
    }

    public static Block[] getEndTrollBoxBlockArray() {
        return END_TROLL_BOX_BLOCKS.stream()
                .map(DeferredHolder::get)
                .toArray(Block[]::new);
    }

    public static Item[] getEndTrollBoxItems() {
        return END_TROLL_BOX_BLOCKS.stream()
                .map(holder -> holder.get().asItem())
                .toArray(Item[]::new);
    }

    public static Block getEndTrollBox(@Nullable DyeColor color) {
        if (color == null) {
            return END_TROLL_BOX.get();
        }

        DeferredHolder<Block, Block> holder = COLORED_END_TROLL_BOXES.get(color);
        return holder != null ? holder.get() : END_TROLL_BOX.get();
    }

    @Nullable
    public static DyeColor getEndTrollBoxColor(Item item) {
        if (item == END_TROLL_BOX.get().asItem()) {
            return null;
        }

        for (Map.Entry<DyeColor, DeferredHolder<Block, Block>> entry : COLORED_END_TROLL_BOXES.entrySet()) {
            if (item == entry.getValue().get().asItem()) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static boolean isEndTrollBoxItem(Item item) {
        return item == END_TROLL_BOX.get().asItem() || getEndTrollBoxColor(item) != null;
    }

    public static void acceptEndTrollBoxes(CreativeModeTab.Output output) {
        END_TROLL_BOX_BLOCKS.forEach(holder -> output.accept(holder.get()));
    }
}
