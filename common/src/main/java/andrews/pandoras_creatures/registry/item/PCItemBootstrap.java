package andrews.pandoras_creatures.registry.item;

import andrews.pandoras_creatures.content.item.ItemCrabBucket;
import andrews.pandoras_creatures.content.item.EndTrollBoxItem;
import andrews.pandoras_creatures.content.item.ItemArachnonHammer;
import andrews.pandoras_creatures.content.item.ItemPlantHat;
import andrews.pandoras_creatures.content.item.ItemSeahorseBucket;
import andrews.pandoras_creatures.content.item.PCSpawnEggItem;
import andrews.pandoras_creatures.registry.block.PCBlockBootstrap;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap;
import andrews.pandoras_creatures.registry.PCFoods;
import andrews.pandoras_creatures.registry.bootstrap.SharedRegistryRegistrar;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Shared item registration bootstrap consumed by loader adapters.
 */
public final class PCItemBootstrap {
    private PCItemBootstrap() {
    }

    public static <H extends Supplier<? extends Item>> Map<String, H> registerCoreItems(SharedRegistryRegistrar<Item, H> registrar) {
        LinkedHashMap<String, H> registeredItems = new LinkedHashMap<>();

        registerFoodItem(registeredItems, registrar, PCItemIds.CRAB_MEAT, PCFoods.CRAB_MEAT_RAW);
        registerFoodItem(registeredItems, registrar, PCItemIds.CRAB_MEAT_COOKED, PCFoods.CRAB_MEAT_COOKED);
        registerFoodItem(registeredItems, registrar, PCItemIds.SEAHORSE, PCFoods.SEAHORSE_RAW);
        registerFoodItem(registeredItems, registrar, PCItemIds.SEAHORSE_COOKED, PCFoods.SEAHORSE_COOKED);
        registerBasicItem(registeredItems, registrar, PCItemIds.ACIDIC_ARCHVINE_TONGUE);
        registerBasicItem(registeredItems, registrar, PCItemIds.HERB_BUNDLE);
        registerFoodItem(registeredItems, registrar, PCItemIds.BUFFLON_BEEF, PCFoods.BUFFLON_BEEF_RAW);
        registerFoodItem(registeredItems, registrar, PCItemIds.BUFFLON_BEEF_COOKED, PCFoods.BUFFLON_BEEF_COOKED);
        registerBasicItem(registeredItems, registrar, PCItemIds.BUFFLON_HIDE);
        registerSingleStackItem(registeredItems, registrar, PCItemIds.BUFFLON_SADDLE);
        registerSingleStackItem(registeredItems, registrar, PCItemIds.BUFFLON_PLAYER_SEATS);
        registerSingleStackItem(registeredItems, registrar, PCItemIds.BUFFLON_SMALL_STORAGE);
        registerSingleStackItem(registeredItems, registrar, PCItemIds.BUFFLON_LARGE_STORAGE);
        registerBasicItem(registeredItems, registrar, PCItemIds.END_TROLL_SKIN);
        registerCustomItem(registeredItems, registrar, PCItemIds.ARACHNON_HAMMER, ItemArachnonHammer::new);
        registerCustomItem(registeredItems, registrar, PCItemIds.PLANT_HAT, ItemPlantHat::new);

        return Collections.unmodifiableMap(registeredItems);
    }

    public static <H extends Supplier<? extends Item>> Map<String, H> registerSimpleBlockItems(
            SharedRegistryRegistrar<Item, H> registrar,
            Function<String, Supplier<? extends Block>> blockLookup) {
        LinkedHashMap<String, H> registeredItems = new LinkedHashMap<>();

        for (String id : PCBlockBootstrap.simpleBlockIds()) {
            Supplier<? extends Block> blockSupplier = blockLookup.apply(id);
            if (blockSupplier == null) {
                throw new IllegalArgumentException("No block supplier available for simple block item id: " + id);
            }

            registeredItems.put(id, registrar.register(id, () -> new BlockItem(blockSupplier.get(),
                    new Item.Properties().setId(PCItemIds.key(id)))));
        }

        return Collections.unmodifiableMap(registeredItems);
    }

    public static <H extends Supplier<? extends Item>> Map<String, H> registerEndTrollBoxItems(
            SharedRegistryRegistrar<Item, H> registrar,
            Function<String, Supplier<? extends Block>> blockLookup) {
        LinkedHashMap<String, H> registeredItems = new LinkedHashMap<>();

        for (String id : PCEndTrollBoxBootstrap.blockIds()) {
            registerEndTrollBoxItem(registeredItems, registrar, blockLookup, id);
        }

        return Collections.unmodifiableMap(registeredItems);
    }

    public static <H extends Supplier<? extends Item>> Map<String, H> registerAquaticBucketItems(SharedRegistryRegistrar<Item, H> registrar) {
        LinkedHashMap<String, H> registeredItems = new LinkedHashMap<>();
        registerCustomItem(registeredItems, registrar, PCItemIds.CRAB_BUCKET, ItemCrabBucket::new);
        registerCustomItem(registeredItems, registrar, PCItemIds.SEAHORSE_BUCKET, ItemSeahorseBucket::new);
        return Collections.unmodifiableMap(registeredItems);
    }

    public static <H extends Supplier<? extends Item>> Map<String, H> registerPortableSpawnEggItems(
            SharedRegistryRegistrar<Item, H> registrar,
            Function<String, Supplier<? extends EntityType<?>>> entityTypeLookup) {
        return registerPortableSpawnEggItems(registrar, entityTypeLookup, portableSpawnEggPalettes());
    }

    public static <H extends Supplier<? extends Item>> Map<String, H> registerPortableSpawnEggItems(
            SharedRegistryRegistrar<Item, H> registrar,
            Function<String, Supplier<? extends EntityType<?>>> entityTypeLookup,
            Iterable<PCSpawnEggPalette> palettes) {
        LinkedHashMap<String, H> registeredItems = new LinkedHashMap<>();

        for (PCSpawnEggPalette palette : palettes) {
            Supplier<? extends EntityType<?>> entityTypeSupplier = entityTypeLookup.apply(palette.entityName());
            if (entityTypeSupplier == null) {
                throw new IllegalArgumentException("No entity type supplier available for spawn egg: " + palette.entityName());
            }

            registeredItems.put(palette.itemName(), registrar.register(
                    palette.itemName(),
                    () -> new PCSpawnEggItem(entityTypeSupplier, palette.primaryColor(), palette.secondaryColor(),
                            new Item.Properties().setId(PCItemIds.key(palette.itemName())))
            ));
        }

        return Collections.unmodifiableMap(registeredItems);
    }

    public static List<PCSpawnEggPalette> portableSpawnEggPalettes() {
        return List.of(
                PCSpawnEggPalette.ARACHNON,
                PCSpawnEggPalette.HELLHOUND,
                PCSpawnEggPalette.CRAB,
                PCSpawnEggPalette.SEAHORSE,
                PCSpawnEggPalette.ACIDIC_ARCHVINE,
                PCSpawnEggPalette.BUFFLON,
                PCSpawnEggPalette.END_TROLL
        );
    }

    private static <H extends Supplier<? extends Item>> void registerBasicItem(Map<String, H> items,
            SharedRegistryRegistrar<Item, H> registrar,
            String id) {
        items.put(id, registrar.register(id, () -> new Item(new Item.Properties().setId(PCItemIds.key(id)))));
    }

    private static <H extends Supplier<? extends Item>> void registerFoodItem(Map<String, H> items,
            SharedRegistryRegistrar<Item, H> registrar,
            String id,
            FoodProperties foodProperties) {
        items.put(id, registrar.register(id, () -> new Item(new Item.Properties().food(foodProperties).setId(PCItemIds.key(id)))));
    }

    private static <H extends Supplier<? extends Item>> void registerSingleStackItem(Map<String, H> items,
            SharedRegistryRegistrar<Item, H> registrar,
            String id) {
        items.put(id, registrar.register(id, () -> new Item(new Item.Properties().stacksTo(1).setId(PCItemIds.key(id)))));
    }

    private static <H extends Supplier<? extends Item>, T extends Item> void registerCustomItem(Map<String, H> items,
            SharedRegistryRegistrar<Item, H> registrar,
            String id,
            Supplier<T> factory) {
        items.put(id, registrar.register(id, factory));
    }

    private static <H extends Supplier<? extends Item>> void registerEndTrollBoxItem(Map<String, H> items,
            SharedRegistryRegistrar<Item, H> registrar,
            Function<String, Supplier<? extends Block>> blockLookup,
            String id) {
        Supplier<? extends Block> blockSupplier = blockLookup.apply(id);
        if (blockSupplier == null) {
            throw new IllegalArgumentException("No block supplier available for end troll box item id: " + id);
        }

        items.put(id, registrar.register(id, () -> new EndTrollBoxItem(blockSupplier.get(),
                new Item.Properties().stacksTo(1).fireResistant().setId(PCItemIds.key(id)))));
    }
}
