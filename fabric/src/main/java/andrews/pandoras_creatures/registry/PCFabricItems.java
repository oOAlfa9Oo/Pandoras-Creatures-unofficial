package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.registry.item.PCItemBootstrap;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Map;
import java.util.function.Supplier;

public final class PCFabricItems {
    private static Map<String, Supplier<? extends Item>> registeredItems = Map.of();
    private static Map<String, Supplier<? extends Item>> registeredSimpleBlockItems = Map.of();
    private static Map<String, Supplier<? extends Item>> registeredEndTrollBoxItems = Map.of();
    private static Map<String, Supplier<? extends Item>> registeredBucketItems = Map.of();
    private static Map<String, Supplier<? extends Item>> registeredPortableSpawnEggItems = Map.of();

    private PCFabricItems() {
    }

    public static void register() {
        if (!registeredItems.isEmpty()) {
            return;
        }

        registeredItems = PCItemBootstrap.registerCoreItems((id, factory) -> {
            Item item = factory.get();
            Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Reference.MODID, id), item);
            return () -> item;
        });

        registeredSimpleBlockItems = PCItemBootstrap.registerSimpleBlockItems((id, factory) -> {
            Item item = factory.get();
            Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Reference.MODID, id), item);
            return () -> item;
        }, id -> () -> PCFabricBlocks.getSimpleBlock(id));

        registeredEndTrollBoxItems = PCItemBootstrap.registerEndTrollBoxItems((id, factory) -> {
            Item item = factory.get();
            Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Reference.MODID, id), item);
            return () -> item;
        }, id -> () -> PCFabricBlocks.getEndTrollBox(andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette.colorForBlockName(id)));

        registeredBucketItems = PCItemBootstrap.registerAquaticBucketItems((id, factory) -> {
            Item item = factory.get();
            Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Reference.MODID, id), item);
            return () -> item;
        });

        registeredPortableSpawnEggItems = PCItemBootstrap.registerPortableSpawnEggItems((id, factory) -> {
            Item item = factory.get();
            Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Reference.MODID, id), item);
            return () -> item;
        }, entityId -> switch (entityId) {
            case "arachnon" -> () -> PCFabricEntities.ARACHNON;
            case "hellhound" -> () -> PCFabricEntities.HELLHOUND;
            case "crab" -> () -> PCFabricEntities.CRAB;
            case "seahorse" -> () -> PCFabricEntities.SEAHORSE;
            case "acidic_archvine" -> () -> PCFabricEntities.ACIDIC_ARCHVINE;
            case "bufflon" -> () -> PCFabricEntities.BUFFLON;
            case "end_troll" -> () -> PCFabricEntities.END_TROLL;
            default -> null;
        });
    }

    public static Item[] getEndTrollBoxItems() {
        return registeredEndTrollBoxItems.values().stream()
                .map(Supplier::get)
                .toArray(Item[]::new);
    }

    public static boolean hasItem(String id) {
        return registeredItems.containsKey(id)
                || registeredSimpleBlockItems.containsKey(id)
                || registeredEndTrollBoxItems.containsKey(id)
                || registeredBucketItems.containsKey(id)
                || registeredPortableSpawnEggItems.containsKey(id);
    }

    public static Item getItem(String id) {
        Supplier<? extends Item> supplier = registeredItems.get(id);
        if (supplier != null) {
            return supplier.get();
        }

        supplier = registeredSimpleBlockItems.get(id);
        if (supplier != null) {
            return supplier.get();
        }

        supplier = registeredEndTrollBoxItems.get(id);
        if (supplier != null) {
            return supplier.get();
        }

        supplier = registeredBucketItems.get(id);
        if (supplier != null) {
            return supplier.get();
        }

        supplier = registeredPortableSpawnEggItems.get(id);
        if (supplier != null) {
            return supplier.get();
        }

        throw new IllegalArgumentException("Unknown fabric item id: " + id);
    }
}

