package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.content.item.ItemArachnonHammer;
import andrews.pandoras_creatures.content.item.ItemCrabBucket;
import andrews.pandoras_creatures.content.item.ItemPlantHat;
import andrews.pandoras_creatures.content.item.ItemSeahorseBucket;
import andrews.pandoras_creatures.content.item.PCSpawnEggItem;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public final class PCItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Reference.MODID);

    // Items
    public static final DeferredHolder<Item, Item> CRAB_MEAT = registerFoodItem("crab_meat", PCFoods.CRAB_MEAT_RAW);

    public static final DeferredHolder<Item, Item> CRAB_MEAT_COOKED = registerFoodItem("crab_meat_cooked", PCFoods.CRAB_MEAT_COOKED);

    public static final DeferredHolder<Item, Item> SEAHORSE = registerFoodItem("seahorse", PCFoods.SEAHORSE_RAW);

    public static final DeferredHolder<Item, Item> SEAHORSE_COOKED = registerFoodItem("seahorse_cooked", PCFoods.SEAHORSE_COOKED);

    public static final DeferredHolder<Item, Item> ACIDIC_ARCHVINE_TONGUE = registerBasicItem("acidic_archvine_tongue");

    public static final DeferredHolder<Item, Item> HERB_BUNDLE = registerBasicItem("herb_bundle");

    public static final DeferredHolder<Item, Item> BUFFLON_BEEF = registerFoodItem("bufflon_beef", PCFoods.BUFFLON_BEEF_RAW);

    public static final DeferredHolder<Item, Item> BUFFLON_BEEF_COOKED = registerFoodItem("bufflon_beef_cooked", PCFoods.BUFFLON_BEEF_COOKED);

    public static final DeferredHolder<Item, Item> BUFFLON_HIDE = registerBasicItem("bufflon_hide");

    public static final DeferredHolder<Item, Item> BUFFLON_SADDLE = registerSingleStackItem("bufflon_saddle");

    public static final DeferredHolder<Item, Item> BUFFLON_PLAYER_SEATS = registerSingleStackItem("bufflon_player_seats");

    public static final DeferredHolder<Item, Item> BUFFLON_SMALL_STORAGE = registerSingleStackItem("bufflon_small_storage");

    public static final DeferredHolder<Item, Item> BUFFLON_LARGE_STORAGE = registerSingleStackItem("bufflon_large_storage");

    public static final DeferredHolder<Item, Item> END_TROLL_SKIN = registerBasicItem("end_troll_skin");

    // Custom Items
    public static final DeferredHolder<Item, ItemArachnonHammer> ARACHNON_HAMMER = registerCustomItem("arachnon_hammer", ItemArachnonHammer::new);

    public static final DeferredHolder<Item, ItemCrabBucket> CRAB_BUCKET = registerCustomItem("crab_bucket", ItemCrabBucket::new);

    public static final DeferredHolder<Item, ItemSeahorseBucket> SEAHORSE_BUCKET = registerCustomItem("seahorse_bucket", ItemSeahorseBucket::new);

    public static final DeferredHolder<Item, ItemPlantHat> PLANT_HAT = registerCustomItem("plant_hat", ItemPlantHat::new);

    // Spawn Eggs - Using SpawnEggItem with deferred entity type resolution
    // NeoForge 1.21.1 automatically registers color handlers for SpawnEggItem
    public static final DeferredHolder<Item, SpawnEggItem> ARACHNON_SPAWN_EGG = registerSpawnEgg(PCSpawnEggPalette.ARACHNON, PCEntities.ARACHNON);

    public static final DeferredHolder<Item, SpawnEggItem> HELLHOUND_SPAWN_EGG = registerSpawnEgg(PCSpawnEggPalette.HELLHOUND, PCEntities.HELLHOUND);

    public static final DeferredHolder<Item, SpawnEggItem> CRAB_SPAWN_EGG = registerSpawnEgg(PCSpawnEggPalette.CRAB, PCEntities.CRAB);

    public static final DeferredHolder<Item, SpawnEggItem> SEAHORSE_SPAWN_EGG = registerSpawnEgg(PCSpawnEggPalette.SEAHORSE, PCEntities.SEAHORSE);

    public static final DeferredHolder<Item, SpawnEggItem> ACIDIC_ARCHVINE_SPAWN_EGG = registerSpawnEgg(PCSpawnEggPalette.ACIDIC_ARCHVINE, PCEntities.ACIDIC_ARCHVINE);

    public static final DeferredHolder<Item, SpawnEggItem> BUFFLON_SPAWN_EGG = registerSpawnEgg(PCSpawnEggPalette.BUFFLON, PCEntities.BUFFLON);

    public static final DeferredHolder<Item, SpawnEggItem> END_TROLL_SPAWN_EGG = registerSpawnEgg(PCSpawnEggPalette.END_TROLL, PCEntities.END_TROLL);

    private static final List<DeferredHolder<Item, SpawnEggItem>> SPAWN_EGGS = List.of(
            ARACHNON_SPAWN_EGG,
            HELLHOUND_SPAWN_EGG,
            CRAB_SPAWN_EGG,
            SEAHORSE_SPAWN_EGG,
            ACIDIC_ARCHVINE_SPAWN_EGG,
            BUFFLON_SPAWN_EGG,
            END_TROLL_SPAWN_EGG
    );

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

    private static <T extends Mob> DeferredHolder<Item, SpawnEggItem> registerSpawnEgg(PCSpawnEggPalette palette,
            Supplier<? extends EntityType<T>> entityType) {
        return ITEMS.register(palette.itemName(),
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
}
