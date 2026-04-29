package andrews.pandoras_creatures.registry.block;

import andrews.pandoras_creatures.content.block.ArachnonCrystalBlock;
import andrews.pandoras_creatures.content.block.PCPlantBlock;
import andrews.pandoras_creatures.registry.bootstrap.SharedRegistryRegistrar;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Shared block registration bootstrap for simple blocks that do not require loader-owned block entity wiring.
 */
public final class PCBlockBootstrap {
    private static final List<String> SIMPLE_BLOCK_IDS = List.of(
            PCBlockIds.ARACHNON_CRYSTAL,
            PCBlockIds.HORSETAIL,
            PCBlockIds.DHANIA,
            PCBlockIds.HILL_BLOOM,
            PCBlockIds.PANDORIC_SHARD
    );

    private PCBlockBootstrap() {
    }

    public static List<String> simpleBlockIds() {
        return SIMPLE_BLOCK_IDS;
    }

    public static <H extends Supplier<? extends Block>> Map<String, H> registerSimpleBlocks(SharedRegistryRegistrar<Block, H> registrar) {
        LinkedHashMap<String, H> registeredBlocks = new LinkedHashMap<>();

        registeredBlocks.put(PCBlockIds.ARACHNON_CRYSTAL, registrar.register(PCBlockIds.ARACHNON_CRYSTAL,
                () -> new ArachnonCrystalBlock(BlockBehaviour.Properties.of()
                        .strength(1.5F, 6.0F)
                        .lightLevel(state -> 9)
                        .requiresCorrectToolForDrops()
                        .noOcclusion())));

        registeredBlocks.put(PCBlockIds.HORSETAIL, registrar.register(PCBlockIds.HORSETAIL,
                () -> new PCPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                        .noCollission()
                        .noOcclusion())));

        registeredBlocks.put(PCBlockIds.DHANIA, registrar.register(PCBlockIds.DHANIA,
                () -> new PCPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                        .noCollission()
                        .noOcclusion())));

        registeredBlocks.put(PCBlockIds.HILL_BLOOM, registrar.register(PCBlockIds.HILL_BLOOM,
                () -> new PCPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                        .noCollission()
                        .noOcclusion())));

        registeredBlocks.put(PCBlockIds.PANDORIC_SHARD, registrar.register(PCBlockIds.PANDORIC_SHARD,
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                        .noOcclusion()
                        .lightLevel(state -> 10))));

        return Collections.unmodifiableMap(registeredBlocks);
    }
}
