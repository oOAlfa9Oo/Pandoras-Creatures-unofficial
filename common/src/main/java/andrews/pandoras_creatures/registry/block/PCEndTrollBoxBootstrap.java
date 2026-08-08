package andrews.pandoras_creatures.registry.block;

import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import andrews.pandoras_creatures.registry.bootstrap.SharedRegistryRegistrar;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import org.jetbrains.annotations.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class PCEndTrollBoxBootstrap {
    private PCEndTrollBoxBootstrap() {
    }

    public static <H extends Supplier<? extends Block>> Map<String, H> registerBlocks(SharedRegistryRegistrar<Block, H> registrar) {
        LinkedHashMap<String, H> registeredBlocks = new LinkedHashMap<>();

        register(registeredBlocks, registrar, null);
        PCEndTrollBoxPalette.orderedColors().forEach(color -> register(registeredBlocks, registrar, color));

        return Collections.unmodifiableMap(registeredBlocks);
    }

    public static String blockId(@Nullable DyeColor color) {
        return color == null ? PCBlockIds.END_TROLL_BOX : PCBlockIds.endTrollBox(color.getName());
    }

    public static List<String> blockIds() {
        LinkedHashMap<String, String> ids = new LinkedHashMap<>();
        ids.put(blockId(null), blockId(null));
        PCEndTrollBoxPalette.orderedColors().forEach(color -> ids.put(blockId(color), blockId(color)));
        return List.copyOf(ids.keySet());
    }

    public static Block sourceBlock(@Nullable DyeColor color) {
        if (color == null) {
            return Blocks.SHULKER_BOX;
        }

        return switch (color) {
            case WHITE -> Blocks.WHITE_SHULKER_BOX;
            case ORANGE -> Blocks.ORANGE_SHULKER_BOX;
            case MAGENTA -> Blocks.MAGENTA_SHULKER_BOX;
            case LIGHT_BLUE -> Blocks.LIGHT_BLUE_SHULKER_BOX;
            case YELLOW -> Blocks.YELLOW_SHULKER_BOX;
            case LIME -> Blocks.LIME_SHULKER_BOX;
            case PINK -> Blocks.PINK_SHULKER_BOX;
            case GRAY -> Blocks.GRAY_SHULKER_BOX;
            case LIGHT_GRAY -> Blocks.LIGHT_GRAY_SHULKER_BOX;
            case CYAN -> Blocks.CYAN_SHULKER_BOX;
            case PURPLE -> Blocks.PURPLE_SHULKER_BOX;
            case BLUE -> Blocks.BLUE_SHULKER_BOX;
            case BROWN -> Blocks.BROWN_SHULKER_BOX;
            case GREEN -> Blocks.GREEN_SHULKER_BOX;
            case RED -> Blocks.RED_SHULKER_BOX;
            case BLACK -> Blocks.BLACK_SHULKER_BOX;
        };
    }

    private static <H extends Supplier<? extends Block>> void register(Map<String, H> blocks,
            SharedRegistryRegistrar<Block, H> registrar,
            @Nullable DyeColor color) {
        String id = blockId(color);
        Block sourceBlock = sourceBlock(color);
        blocks.put(id, registrar.register(id, () -> new EndTrollBoxBlock(color, BlockBehaviour.Properties.ofFullCopy(sourceBlock))));
    }
}

