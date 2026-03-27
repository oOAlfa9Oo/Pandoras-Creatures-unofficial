package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.content.item.EndTrollBoxItem;
import andrews.pandoras_creatures.content.item.ItemArachnonHammer;
import andrews.pandoras_creatures.content.item.ItemCrabBucket;
import andrews.pandoras_creatures.content.item.ItemPlantHat;
import andrews.pandoras_creatures.content.item.ItemSeahorseBucket;
import andrews.pandoras_creatures.content.item.PCSpawnEggItem;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class PCItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Reference.MODID);

    // Items
    public static final DeferredHolder<Item, Item> CRAB_MEAT = registerFoodItem(PCItemIds.CRAB_MEAT, PCFoods.CRAB_MEAT_RAW);

    public static final DeferredHolder<Item, Item> CRAB_MEAT_COOKED = registerFoodItem(PCItemIds.CRAB_MEAT_COOKED, PCFoods.CRAB_MEAT_COOKED);

    public static final DeferredHolder<Item, Item> SEAHORSE = registerFoodItem(PCItemIds.SEAHORSE, PCFoods.SEAHORSE_RAW);

    public static final DeferredHolder<Item, Item> SEAHORSE_COOKED = registerFoodItem(PCItemIds.SEAHORSE_COOKED, PCFoods.SEAHORSE_COOKED);

    public static final DeferredHolder<Item, Item> ACIDIC_ARCHVINE_TONGUE = registerBasicItem(PCItemIds.ACIDIC_ARCHVINE_TONGUE);

    public static final DeferredHolder<Item, Item> HERB_BUNDLE = registerBasicItem(PCItemIds.HERB_BUNDLE);

    public static final DeferredHolder<Item, Item> BUFFLON_BEEF = registerFoodItem(PCItemIds.BUFFLON_BEEF, PCFoods.BUFFLON_BEEF_RAW);

    public static final DeferredHolder<Item, Item> BUFFLON_BEEF_COOKED = registerFoodItem(PCItemIds.BUFFLON_BEEF_COOKED, PCFoods.BUFFLON_BEEF_COOKED);

    public static final DeferredHolder<Item, Item> BUFFLON_HIDE = registerBasicItem(PCItemIds.BUFFLON_HIDE);

    public static final DeferredHolder<Item, Item> BUFFLON_SADDLE = registerSingleStackItem(PCItemIds.BUFFLON_SADDLE);

    public static final DeferredHolder<Item, Item> BUFFLON_PLAYER_SEATS = registerSingleStackItem(PCItemIds.BUFFLON_PLAYER_SEATS);

    public static final DeferredHolder<Item, Item> BUFFLON_SMALL_STORAGE = registerSingleStackItem(PCItemIds.BUFFLON_SMALL_STORAGE);

    public static final DeferredHolder<Item, Item> BUFFLON_LARGE_STORAGE = registerSingleStackItem(PCItemIds.BUFFLON_LARGE_STORAGE);

    public static final DeferredHolder<Item, Item> END_TROLL_SKIN = registerBasicItem(PCItemIds.END_TROLL_SKIN);

    // Custom Items
    public static final DeferredHolder<Item, ItemArachnonHammer> ARACHNON_HAMMER = registerCustomItem(PCItemIds.ARACHNON_HAMMER, ItemArachnonHammer::new);

    public static final DeferredHolder<Item, ItemCrabBucket> CRAB_BUCKET = registerCustomItem(PCItemIds.CRAB_BUCKET, ItemCrabBucket::new);

    public static final DeferredHolder<Item, ItemSeahorseBucket> SEAHORSE_BUCKET = registerCustomItem(PCItemIds.SEAHORSE_BUCKET, ItemSeahorseBucket::new);

    public static final DeferredHolder<Item, ItemPlantHat> PLANT_HAT = registerCustomItem(PCItemIds.PLANT_HAT, ItemPlantHat::new);

    // Spawn Eggs - Using SpawnEggItem with deferred entity type resolution
    // NeoForge 1.21.1 automatically registers color handlers for SpawnEggItem
    public static final DeferredHolder<Item, SpawnEggItem> ARACHNON_SPAWN_EGG = registerSpawnEgg(PCItemIds.ARACHNON_SPAWN_EGG, PCSpawnEggPalette.ARACHNON, PCEntities.ARACHNON);

    public static final DeferredHolder<Item, SpawnEggItem> HELLHOUND_SPAWN_EGG = registerSpawnEgg(PCItemIds.HELLHOUND_SPAWN_EGG, PCSpawnEggPalette.HELLHOUND, PCEntities.HELLHOUND);

    public static final DeferredHolder<Item, SpawnEggItem> CRAB_SPAWN_EGG = registerSpawnEgg(PCItemIds.CRAB_SPAWN_EGG, PCSpawnEggPalette.CRAB, PCEntities.CRAB);

    public static final DeferredHolder<Item, SpawnEggItem> SEAHORSE_SPAWN_EGG = registerSpawnEgg(PCItemIds.SEAHORSE_SPAWN_EGG, PCSpawnEggPalette.SEAHORSE, PCEntities.SEAHORSE);

    public static final DeferredHolder<Item, SpawnEggItem> ACIDIC_ARCHVINE_SPAWN_EGG = registerSpawnEgg(PCItemIds.ACIDIC_ARCHVINE_SPAWN_EGG, PCSpawnEggPalette.ACIDIC_ARCHVINE, PCEntities.ACIDIC_ARCHVINE);

    public static final DeferredHolder<Item, SpawnEggItem> BUFFLON_SPAWN_EGG = registerSpawnEgg(PCItemIds.BUFFLON_SPAWN_EGG, PCSpawnEggPalette.BUFFLON, PCEntities.BUFFLON);

    public static final DeferredHolder<Item, SpawnEggItem> END_TROLL_SPAWN_EGG = registerSpawnEgg(PCItemIds.END_TROLL_SPAWN_EGG, PCSpawnEggPalette.END_TROLL, PCEntities.END_TROLL);

    private static final List<DeferredHolder<Item, SpawnEggItem>> SPAWN_EGGS = List.of(
            ARACHNON_SPAWN_EGG,
            HELLHOUND_SPAWN_EGG,
            CRAB_SPAWN_EGG,
            SEAHORSE_SPAWN_EGG,
            ACIDIC_ARCHVINE_SPAWN_EGG,
            BUFFLON_SPAWN_EGG,
            END_TROLL_SPAWN_EGG
    );
    private static final List<DeferredHolder<Item, ? extends Item>> BLOCK_ITEM_REGISTRATIONS = registerBlockItems();

    private PCItems() {
    }

    private static DeferredHolder<Item, Item> registerBasicItem(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    private static DeferredHolder<Item, Item> registerFoodItem(String name, FoodProperties foodProperties) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().food(foodProperties)));
    }

    private static DeferredHolder<Item, Item> registerSingleStackItem(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().stacksTo(1)));
    }

    private static <T extends Item> DeferredHolder<Item, T> registerCustomItem(String name, Supplier<T> factory) {
        return ITEMS.register(name, factory);
    }

    private static <T extends Mob> DeferredHolder<Item, SpawnEggItem> registerSpawnEgg(String name,
            PCSpawnEggPalette palette,
            Supplier<? extends EntityType<T>> entityType) {
        return ITEMS.register(name,
                () -> new PCSpawnEggItem(entityType, palette.primaryColor(), palette.secondaryColor(), new Item.Properties()));
    }

    public static List<DeferredHolder<Item, SpawnEggItem>> getSpawnEggs() {
        return SPAWN_EGGS;
    }

    public static void setupItemProperties() {
        // Item model properties are now handled differently in 1.21
        // Custom model predicates should be registered via RegisterClientReloadListenersEvent
        // or through JSON model files with item_model_predicates
    }

    private static List<DeferredHolder<Item, ? extends Item>> registerBlockItems() {
        List<DeferredHolder<Item, ? extends Item>> blockItems = new ArrayList<>();

        blockItems.add(registerBlockItem(PCBlockIds.ARACHNON_CRYSTAL, PCBlocks.ARACHNON_CRYSTAL));
        blockItems.add(registerBlockItem(PCBlockIds.HORSETAIL, PCBlocks.HORSETAIL));
        blockItems.add(registerBlockItem(PCBlockIds.DHANIA, PCBlocks.DHANIA));
        blockItems.add(registerBlockItem(PCBlockIds.HILL_BLOOM, PCBlocks.HILL_BLOOM));
        blockItems.add(registerBlockItem(PCBlockIds.PANDORIC_SHARD, PCBlocks.PANDORIC_SHARD));

        blockItems.add(registerEndTrollBoxItem(PCBlockIds.END_TROLL_BOX, PCBlocks.END_TROLL_BOX));
        for (DyeColor color : PCEndTrollBoxPalette.orderedColors()) {
            String blockId = PCEndTrollBoxPalette.blockName(color);
            blockItems.add(registerEndTrollBoxItem(blockId, () -> PCBlocks.getEndTrollBox(color)));
        }

        return List.copyOf(blockItems);
    }

    private static DeferredHolder<Item, BlockItem> registerBlockItem(String name, Supplier<? extends Block> blockSupplier) {
        return ITEMS.register(name, () -> new BlockItem(blockSupplier.get(), new Item.Properties()));
    }

    private static DeferredHolder<Item, EndTrollBoxItem> registerEndTrollBoxItem(String name, Supplier<? extends Block> blockSupplier) {
        return ITEMS.register(name,
                () -> new EndTrollBoxItem(blockSupplier.get(), new Item.Properties().stacksTo(1).fireResistant()));
    }
}
