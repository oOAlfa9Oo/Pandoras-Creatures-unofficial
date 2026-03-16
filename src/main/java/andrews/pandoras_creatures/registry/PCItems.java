package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.objects.items.*;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PCItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Reference.MODID);
    public static final List<DeferredHolder<Item, ? extends Item>> SPAWN_EGGS = new ArrayList<>();

    // Items
    public static final DeferredHolder<Item, Item> CRAB_MEAT = ITEMS.register("crab_meat",
            () -> new Item(new Item.Properties().food(PCFoods.CRAB_MEAT_RAW)));

    public static final DeferredHolder<Item, Item> CRAB_MEAT_COOKED = ITEMS.register("crab_meat_cooked",
            () -> new Item(new Item.Properties().food(PCFoods.CRAB_MEAT_COOKED)));

    public static final DeferredHolder<Item, Item> SEAHORSE = ITEMS.register("seahorse",
            () -> new Item(new Item.Properties().food(PCFoods.SEAHORSE_RAW)));

    public static final DeferredHolder<Item, Item> SEAHORSE_COOKED = ITEMS.register("seahorse_cooked",
            () -> new Item(new Item.Properties().food(PCFoods.SEAHORSE_COOKED)));

    public static final DeferredHolder<Item, Item> ACIDIC_ARCHVINE_TONGUE = ITEMS.register("acidic_archvine_tongue",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> HERB_BUNDLE = ITEMS.register("herb_bundle",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> BUFFLON_BEEF = ITEMS.register("bufflon_beef",
            () -> new Item(new Item.Properties().food(PCFoods.BUFFLON_BEEF_RAW)));

    public static final DeferredHolder<Item, Item> BUFFLON_BEEF_COOKED = ITEMS.register("bufflon_beef_cooked",
            () -> new Item(new Item.Properties().food(PCFoods.BUFFLON_BEEF_COOKED)));

    public static final DeferredHolder<Item, Item> BUFFLON_HIDE = ITEMS.register("bufflon_hide",
            () -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item, Item> BUFFLON_SADDLE = ITEMS.register("bufflon_saddle",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, Item> BUFFLON_PLAYER_SEATS = ITEMS.register("bufflon_player_seats",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, Item> BUFFLON_SMALL_STORAGE = ITEMS.register("bufflon_small_storage",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, Item> BUFFLON_LARGE_STORAGE = ITEMS.register("bufflon_large_storage",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, Item> END_TROLL_SKIN = ITEMS.register("end_troll_skin",
            () -> new Item(new Item.Properties()));

    // Custom Items
    public static final DeferredHolder<Item, Item> ARACHNON_HAMMER = ITEMS.register("arachnon_hammer",
            ItemArachnonHammer::new);

    public static final DeferredHolder<Item, Item> CRAB_BUCKET = ITEMS.register("crab_bucket",
            ItemCrabBucket::new);

    public static final DeferredHolder<Item, Item> SEAHORSE_BUCKET = ITEMS.register("seahorse_bucket",
            ItemSeahorseBucket::new);

    public static final DeferredHolder<Item, Item> PLANT_HAT = ITEMS.register("plant_hat",
            ItemPlantHat::new);

    // Spawn Eggs - Using SpawnEggItem with deferred entity type resolution
    // NeoForge 1.21.1 automatically registers color handlers for SpawnEggItem
    public static final DeferredHolder<Item, SpawnEggItem> ARACHNON_SPAWN_EGG = registerSpawnEgg("arachnon_spawn_egg",
            PCEntities.ARACHNON, 5394534, 12257023);

    public static final DeferredHolder<Item, SpawnEggItem> HELLHOUND_SPAWN_EGG = registerSpawnEgg("hellhound_spawn_egg",
            PCEntities.HELLHOUND, 0xf5f3f0, 0xfc750d);

    public static final DeferredHolder<Item, SpawnEggItem> CRAB_SPAWN_EGG = registerSpawnEgg("crab_spawn_egg",
            PCEntities.CRAB, 0xf79811, 0xffde3b);

    public static final DeferredHolder<Item, SpawnEggItem> SEAHORSE_SPAWN_EGG = registerSpawnEgg("seahorse_spawn_egg",
            PCEntities.SEAHORSE, 0x38d1d1, 0xd98f27);

    public static final DeferredHolder<Item, SpawnEggItem> ACIDIC_ARCHVINE_SPAWN_EGG = registerSpawnEgg("acidic_archvine_spawn_egg",
            PCEntities.ACIDIC_ARCHVINE, 0x14661f, 0x7b34ad);

    public static final DeferredHolder<Item, SpawnEggItem> BUFFLON_SPAWN_EGG = registerSpawnEgg("bufflon_spawn_egg",
            PCEntities.BUFFLON, 0x4f3914, 0x1a1d29);

    public static final DeferredHolder<Item, SpawnEggItem> END_TROLL_SPAWN_EGG = registerSpawnEgg("end_troll_spawn_egg",
            PCEntities.END_TROLL, 0x2a234d, 0x4db4bf);

    private static <T extends Mob> DeferredHolder<Item, SpawnEggItem> registerSpawnEgg(String name,
            Supplier<? extends EntityType<T>> entityType,
            int primaryColor, int secondaryColor) {
        DeferredHolder<Item, SpawnEggItem> egg = ITEMS.register(name,
                () -> new PCSpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Properties()));
        SPAWN_EGGS.add(egg);
        return egg;
    }

    public static void setupItemProperties() {
        // Item model properties are now handled differently in 1.21
        // Custom model predicates should be registered via RegisterClientReloadListenersEvent
        // or through JSON model files with item_model_predicates
    }
}
