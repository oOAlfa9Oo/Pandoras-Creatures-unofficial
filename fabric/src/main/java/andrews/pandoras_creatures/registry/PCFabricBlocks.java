package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.registry.block.PCBlockBootstrap;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap;
import net.minecraft.world.item.DyeColor;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.function.Supplier;

public final class PCFabricBlocks {
    private static Map<String, Supplier<? extends Block>> registeredBlocks = Map.of();
    private static Map<String, Supplier<? extends Block>> registeredEndTrollBoxBlocks = Map.of();

    private PCFabricBlocks() {
    }

    public static void register() {
        if (!registeredBlocks.isEmpty()) {
            return;
        }

        registeredBlocks = PCBlockBootstrap.registerSimpleBlocks((id, factory) -> {
            Block block = factory.get();
            Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Reference.MODID, id), block);
            return () -> block;
        });

        registeredEndTrollBoxBlocks = PCEndTrollBoxBootstrap.registerBlocks((id, factory) -> {
            Block block = factory.get();
            Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Reference.MODID, id), block);
            return () -> block;
        });
    }

    public static Block getSimpleBlock(String id) {
        Supplier<? extends Block> supplier = registeredBlocks.get(id);
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown shared simple block id: " + id);
        }

        return supplier.get();
    }

    public static Block getEndTrollBox(DyeColor color) {
        Supplier<? extends Block> supplier = registeredEndTrollBoxBlocks.get(PCEndTrollBoxBootstrap.blockId(color));
        if (supplier == null) {
            throw new IllegalArgumentException("Unknown end troll box color: " + color);
        }

        return supplier.get();
    }

    public static Block[] getEndTrollBoxBlockArray() {
        return registeredEndTrollBoxBlocks.values().stream()
                .map(Supplier::get)
                .toArray(Block[]::new);
    }

    public static DyeColor getEndTrollBoxColor(Item item) {
        for (DyeColor color : andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette.orderedColors()) {
            if (item == getEndTrollBox(color).asItem()) {
                return color;
            }
        }
        return item == getEndTrollBox(null).asItem() ? null : null;
    }
}

