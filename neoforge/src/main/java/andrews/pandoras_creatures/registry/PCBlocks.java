package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import andrews.pandoras_creatures.registry.block.PCBlockBootstrap;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap;
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
    private static final Map<String, DeferredHolder<Block, ? extends Block>> SIMPLE_BLOCKS =
            PCBlockBootstrap.registerSimpleBlocks((id, factory) -> BLOCKS.register(id, factory::get));
    private static final Map<String, DeferredHolder<Block, ? extends Block>> SHARED_END_TROLL_BOX_BLOCKS =
            PCEndTrollBoxBootstrap.registerBlocks((id, factory) -> BLOCKS.register(id, factory::get));
    private static final List<DeferredHolder<Block, Block>> END_TROLL_BOX_BLOCKS = new ArrayList<>();
    private static final Map<DyeColor, DeferredHolder<Block, Block>> COLORED_END_TROLL_BOXES = new EnumMap<>(DyeColor.class);

    // Simple blocks
    public static final DeferredHolder<Block, Block> ARACHNON_CRYSTAL = simpleBlock(PCBlockIds.ARACHNON_CRYSTAL);

    public static final DeferredHolder<Block, Block> HORSETAIL = simpleBlock(PCBlockIds.HORSETAIL);

    public static final DeferredHolder<Block, Block> DHANIA = simpleBlock(PCBlockIds.DHANIA);

    public static final DeferredHolder<Block, Block> HILL_BLOOM = simpleBlock(PCBlockIds.HILL_BLOOM);

    public static final DeferredHolder<Block, Block> PANDORIC_SHARD = simpleBlock(PCBlockIds.PANDORIC_SHARD);

    // End Troll Boxes - Using custom EndTrollBoxBlock and EndTrollBoxItem
    public static final DeferredHolder<Block, Block> END_TROLL_BOX = sharedEndTrollBox(null);

    public static final DeferredHolder<Block, Block> WHITE_END_TROLL_BOX = sharedEndTrollBox(DyeColor.WHITE);
    public static final DeferredHolder<Block, Block> ORANGE_END_TROLL_BOX = sharedEndTrollBox(DyeColor.ORANGE);
    public static final DeferredHolder<Block, Block> MAGENTA_END_TROLL_BOX = sharedEndTrollBox(DyeColor.MAGENTA);
    public static final DeferredHolder<Block, Block> LIGHT_BLUE_END_TROLL_BOX = sharedEndTrollBox(DyeColor.LIGHT_BLUE);
    public static final DeferredHolder<Block, Block> YELLOW_END_TROLL_BOX = sharedEndTrollBox(DyeColor.YELLOW);
    public static final DeferredHolder<Block, Block> LIME_END_TROLL_BOX = sharedEndTrollBox(DyeColor.LIME);
    public static final DeferredHolder<Block, Block> PINK_END_TROLL_BOX = sharedEndTrollBox(DyeColor.PINK);
    public static final DeferredHolder<Block, Block> GRAY_END_TROLL_BOX = sharedEndTrollBox(DyeColor.GRAY);
    public static final DeferredHolder<Block, Block> LIGHT_GRAY_END_TROLL_BOX = sharedEndTrollBox(DyeColor.LIGHT_GRAY);
    public static final DeferredHolder<Block, Block> CYAN_END_TROLL_BOX = sharedEndTrollBox(DyeColor.CYAN);
    public static final DeferredHolder<Block, Block> PURPLE_END_TROLL_BOX = sharedEndTrollBox(DyeColor.PURPLE);
    public static final DeferredHolder<Block, Block> BLUE_END_TROLL_BOX = sharedEndTrollBox(DyeColor.BLUE);
    public static final DeferredHolder<Block, Block> BROWN_END_TROLL_BOX = sharedEndTrollBox(DyeColor.BROWN);
    public static final DeferredHolder<Block, Block> GREEN_END_TROLL_BOX = sharedEndTrollBox(DyeColor.GREEN);
    public static final DeferredHolder<Block, Block> RED_END_TROLL_BOX = sharedEndTrollBox(DyeColor.RED);
    public static final DeferredHolder<Block, Block> BLACK_END_TROLL_BOX = sharedEndTrollBox(DyeColor.BLACK);

    @SuppressWarnings("unchecked")
    private static DeferredHolder<Block, Block> simpleBlock(String id) {
        return (DeferredHolder<Block, Block>) SIMPLE_BLOCKS.get(id);
    }

    @SuppressWarnings("unchecked")
    private static DeferredHolder<Block, Block> sharedEndTrollBox(@Nullable DyeColor color) {
        String id = PCEndTrollBoxBootstrap.blockId(color);
        DeferredHolder<Block, Block> blockHolder = (DeferredHolder<Block, Block>) SHARED_END_TROLL_BOX_BLOCKS.get(id);
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

    public static Block getSimpleBlock(String id) {
        DeferredHolder<Block, ? extends Block> holder = SIMPLE_BLOCKS.get(id);
        if (holder == null) {
            throw new IllegalArgumentException("Unknown shared simple block id: " + id);
        }

        return holder.get();
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
