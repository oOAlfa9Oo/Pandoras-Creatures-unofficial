package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.objects.blocks.ArachnonCrystalBlock;
import andrews.pandoras_creatures.objects.blocks.EndTrollBoxBlock;
import andrews.pandoras_creatures.objects.blocks.PCPlantBlock;
import andrews.pandoras_creatures.objects.items.EndTrollBoxItem;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PCBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, Reference.MODID);
    public static final List<DeferredHolder<Block, Block>> END_TROLL_BOX_BLOCKS = new ArrayList<>();

    // Simple blocks
    public static final DeferredHolder<Block, Block> ARACHNON_CRYSTAL = registerBlock("arachnon_crystal",
            () -> new ArachnonCrystalBlock(BlockBehaviour.Properties.of()
                    .strength(1.5F, 6.0F)
                    .lightLevel(state -> 9)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()));

    public static final DeferredHolder<Block, Block> HORSETAIL = registerBlock("horsetail",
            () -> new PCPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .noCollission()
                    .noOcclusion()));

    public static final DeferredHolder<Block, Block> DHANIA = registerBlock("dhania",
            () -> new PCPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .noCollission()
                    .noOcclusion()));

    public static final DeferredHolder<Block, Block> HILL_BLOOM = registerBlock("hill_bloom",
            () -> new PCPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .noCollission()
                    .noOcclusion()));

    // End Troll Boxes - Using custom EndTrollBoxBlock and EndTrollBoxItem
    public static final DeferredHolder<Block, Block> END_TROLL_BOX = registerEndTrollBox("end_troll_box",
            () -> new EndTrollBoxBlock(null, BlockBehaviour.Properties.ofFullCopy(Blocks.SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> WHITE_END_TROLL_BOX = registerEndTrollBox("white_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.WHITE, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> ORANGE_END_TROLL_BOX = registerEndTrollBox("orange_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.ORANGE, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> MAGENTA_END_TROLL_BOX = registerEndTrollBox("magenta_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.MAGENTA, BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> LIGHT_BLUE_END_TROLL_BOX = registerEndTrollBox("light_blue_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> YELLOW_END_TROLL_BOX = registerEndTrollBox("yellow_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.YELLOW, BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> LIME_END_TROLL_BOX = registerEndTrollBox("lime_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.LIME, BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> PINK_END_TROLL_BOX = registerEndTrollBox("pink_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.PINK, BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> GRAY_END_TROLL_BOX = registerEndTrollBox("gray_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.GRAY, BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> LIGHT_GRAY_END_TROLL_BOX = registerEndTrollBox("light_gray_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.LIGHT_GRAY, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> CYAN_END_TROLL_BOX = registerEndTrollBox("cyan_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.CYAN, BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> PURPLE_END_TROLL_BOX = registerEndTrollBox("purple_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.PURPLE, BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> BLUE_END_TROLL_BOX = registerEndTrollBox("blue_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.BLUE, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> BROWN_END_TROLL_BOX = registerEndTrollBox("brown_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.BROWN, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> GREEN_END_TROLL_BOX = registerEndTrollBox("green_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.GREEN, BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> RED_END_TROLL_BOX = registerEndTrollBox("red_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.RED, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SHULKER_BOX)));

    public static final DeferredHolder<Block, Block> BLACK_END_TROLL_BOX = registerEndTrollBox("black_end_troll_box",
            () -> new EndTrollBoxBlock(DyeColor.BLACK, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_SHULKER_BOX)));

    // Pandoric Shard - TODO: Implement PandoricShardBlock class
    public static final DeferredHolder<Block, Block> PANDORIC_SHARD = registerBlock("pandoric_shard",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)
                    .noOcclusion()
                    .lightLevel(state -> 10)));

    private static DeferredHolder<Block, Block> registerBlock(String name, Supplier<Block> block) {
        DeferredHolder<Block, Block> blockHolder = BLOCKS.register(name, block);
        PCItems.ITEMS.register(name, () -> new BlockItem(blockHolder.get(), new Item.Properties()));
        return blockHolder;
    }

    private static DeferredHolder<Block, Block> registerEndTrollBox(String name, Supplier<Block> block) {
        DeferredHolder<Block, Block> blockHolder = BLOCKS.register(name, block);
        PCItems.ITEMS.register(name, () -> new EndTrollBoxItem(blockHolder.get(), new Item.Properties().stacksTo(1).fireResistant()));
        END_TROLL_BOX_BLOCKS.add(blockHolder);
        return blockHolder;
    }
}
