package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.content.block.ArachnonCrystalBlock;
import andrews.pandoras_creatures.content.block.PCPlantBlock;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class PCForgeBlocks {
    private static boolean registered;

    private PCForgeBlocks() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeBlocks::registerBlocks);
    }

    public static Block getSimpleBlock(String id) {
        ResourceLocation blockId = Reference.id(id);
        Block value = BuiltInRegistries.BLOCK.get(blockId);
        if (value == null) {
            throw new IllegalArgumentException("Unknown forge block id: " + blockId);
        }
        return value;
    }

    public static Block[] getSimpleBlockArray() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(getSimpleBlock(PCBlockIds.ARACHNON_CRYSTAL));
        blocks.add(getSimpleBlock(PCBlockIds.HORSETAIL));
        blocks.add(getSimpleBlock(PCBlockIds.DHANIA));
        blocks.add(getSimpleBlock(PCBlockIds.HILL_BLOOM));
        blocks.add(getSimpleBlock(PCBlockIds.PANDORIC_SHARD));
        return blocks.toArray(Block[]::new);
    }

    public static Block getEndTrollBox(@Nullable DyeColor color) {
        ResourceLocation blockId = Reference.id(PCEndTrollBoxBootstrap.blockId(color));
        Block value = BuiltInRegistries.BLOCK.get(blockId);
        if (value == null) {
            throw new IllegalArgumentException("Unknown forge end troll box block id: " + blockId);
        }
        return value;
    }

    public static Block[] getEndTrollBoxBlockArray() {
        return PCEndTrollBoxBootstrap.blockIds().stream()
                .map(PCForgeBlocks::getSimpleBlock)
                .toArray(Block[]::new);
    }

    public static DyeColor getEndTrollBoxColor(Item item) {
        for (DyeColor color : PCEndTrollBoxPalette.orderedColors()) {
            if (item == getEndTrollBox(color).asItem()) {
                return color;
            }
        }
        return item == getEndTrollBox(null).asItem() ? null : null;
    }

    private static void registerBlocks(RegisterEvent event) {
        if (registered || !event.getRegistryKey().equals(ForgeRegistries.Keys.BLOCKS)) {
            return;
        }

        registered = true;
        event.register(ForgeRegistries.Keys.BLOCKS, helper -> {
            helper.register(
                    Reference.id(PCBlockIds.ARACHNON_CRYSTAL),
                    new ArachnonCrystalBlock(BlockBehaviour.Properties.of()
                            .strength(1.5F, 6.0F)
                            .lightLevel(state -> 9)
                            .requiresCorrectToolForDrops()
                            .noOcclusion())
            );
            helper.register(
                    Reference.id(PCBlockIds.HORSETAIL),
                    new PCPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                            .noCollission()
                            .noOcclusion())
            );
            helper.register(
                    Reference.id(PCBlockIds.DHANIA),
                    new PCPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                            .noCollission()
                            .noOcclusion())
            );
            helper.register(
                    Reference.id(PCBlockIds.HILL_BLOOM),
                    new PCPlantBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)
                            .noCollission()
                            .noOcclusion())
            );
            helper.register(
                    Reference.id(PCBlockIds.PANDORIC_SHARD),
                    new Block(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                            .noOcclusion()
                            .lightLevel(state -> 10))
            );
            PCEndTrollBoxBootstrap.registerBlocks((id, factory) -> {
                Block block = factory.get();
                helper.register(Reference.id(id), block);
                return () -> block;
            });
        });
    }
}

