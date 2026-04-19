package andrews.pandoras_creatures.registry.block;

import andrews.pandoras_creatures.content.block.ArachnonCrystalBlock;
import andrews.pandoras_creatures.content.block.PCPlantBlock;
import andrews.pandoras_creatures.registry.bootstrap.SharedRegistryRegistrar;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
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
                () -> new ArachnonCrystalBlock(properties(PCBlockIds.ARACHNON_CRYSTAL, BlockBehaviour.Properties.of()
                        .strength(1.5F, 6.0F)
                        .lightLevel(state -> 9)
                        .requiresCorrectToolForDrops()
                        .noOcclusion()))));

        registeredBlocks.put(PCBlockIds.HORSETAIL, registrar.register(PCBlockIds.HORSETAIL,
                () -> new PCPlantBlock(properties(PCBlockIds.HORSETAIL, BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                        .noCollision()
                        .noOcclusion()))));

        registeredBlocks.put(PCBlockIds.DHANIA, registrar.register(PCBlockIds.DHANIA,
                () -> new PCPlantBlock(properties(PCBlockIds.DHANIA, BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                        .noCollision()
                        .noOcclusion()))));

        registeredBlocks.put(PCBlockIds.HILL_BLOOM, registrar.register(PCBlockIds.HILL_BLOOM,
                () -> new PCPlantBlock(properties(PCBlockIds.HILL_BLOOM, BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                        .noCollision()
                        .noOcclusion()))));

        registeredBlocks.put(PCBlockIds.PANDORIC_SHARD, registrar.register(PCBlockIds.PANDORIC_SHARD,
                () -> new Block(properties(PCBlockIds.PANDORIC_SHARD, BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)
                        .noOcclusion()
                        .lightLevel(state -> 10)))));

        return Collections.unmodifiableMap(registeredBlocks);
    }

    public static BlockBehaviour.Properties properties(String id, BlockBehaviour.Properties properties) {
        return properties.setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Reference.MODID, id)));
    }
}
