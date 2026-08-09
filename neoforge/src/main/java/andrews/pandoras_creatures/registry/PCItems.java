package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.content.item.EndTrollBoxItem;
import andrews.pandoras_creatures.content.item.PCSpawnEggItem;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.item.PCItemBootstrap;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
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
import java.util.Map;
import java.util.function.Supplier;

public final class PCItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, Reference.MODID);
    private static final Map<String, DeferredHolder<Item, ? extends Item>> SHARED_ITEMS =
            PCItemBootstrap.registerCoreItems((id, factory) -> ITEMS.register(id, factory::get));
    private static final Map<String, DeferredHolder<Item, ? extends Item>> SHARED_SIMPLE_BLOCK_ITEMS =
            PCItemBootstrap.registerSimpleBlockItems((id, factory) -> ITEMS.register(id, factory::get),
                    id -> () -> PCBlocks.getSimpleBlock(id));
    private static final Map<String, DeferredHolder<Item, ? extends Item>> SHARED_END_TROLL_BOX_ITEMS =
            PCItemBootstrap.registerEndTrollBoxItems((id, factory) -> ITEMS.register(id, factory::get),
                    id -> () -> PCBlocks.getEndTrollBox(PCEndTrollBoxPalette.colorForBlockName(id)));
    private static final Map<String, DeferredHolder<Item, ? extends Item>> SHARED_BUCKET_ITEMS =
            PCItemBootstrap.registerAquaticBucketItems((id, factory) -> ITEMS.register(id, factory::get));
    private static final Map<String, DeferredHolder<Item, ? extends Item>> SHARED_PORTABLE_SPAWN_EGGS =
            PCItemBootstrap.registerPortableSpawnEggItems(
                    (id, factory) -> ITEMS.register(id, factory::get),
                    entityId -> switch (entityId) {
                        case PCEntityIds.ARACHNON -> PCEntities.ARACHNON;
                        case PCEntityIds.HELLHOUND -> PCEntities.HELLHOUND;
                        case PCEntityIds.CRAB -> PCEntities.CRAB;
                        case PCEntityIds.SEAHORSE -> PCEntities.SEAHORSE;
                        case PCEntityIds.ACIDIC_ARCHVINE -> PCEntities.ACIDIC_ARCHVINE;
                        case PCEntityIds.BUFFLON -> PCEntities.BUFFLON;
                        case PCEntityIds.END_TROLL -> PCEntities.END_TROLL;
                        default -> null;
                    });

    // Items
    public static final DeferredHolder<Item, Item> CRAB_MEAT = sharedItem(PCItemIds.CRAB_MEAT);

    public static final DeferredHolder<Item, Item> CRAB_MEAT_COOKED = sharedItem(PCItemIds.CRAB_MEAT_COOKED);

    public static final DeferredHolder<Item, Item> SEAHORSE = sharedItem(PCItemIds.SEAHORSE);

    public static final DeferredHolder<Item, Item> SEAHORSE_COOKED = sharedItem(PCItemIds.SEAHORSE_COOKED);

    public static final DeferredHolder<Item, Item> ACIDIC_ARCHVINE_TONGUE = sharedItem(PCItemIds.ACIDIC_ARCHVINE_TONGUE);

    public static final DeferredHolder<Item, Item> HERB_BUNDLE = sharedItem(PCItemIds.HERB_BUNDLE);

    public static final DeferredHolder<Item, Item> BUFFLON_BEEF = sharedItem(PCItemIds.BUFFLON_BEEF);

    public static final DeferredHolder<Item, Item> BUFFLON_BEEF_COOKED = sharedItem(PCItemIds.BUFFLON_BEEF_COOKED);

    public static final DeferredHolder<Item, Item> BUFFLON_HIDE = sharedItem(PCItemIds.BUFFLON_HIDE);

    public static final DeferredHolder<Item, Item> BUFFLON_SADDLE = sharedItem(PCItemIds.BUFFLON_SADDLE);

    public static final DeferredHolder<Item, Item> BUFFLON_PLAYER_SEATS = sharedItem(PCItemIds.BUFFLON_PLAYER_SEATS);

    public static final DeferredHolder<Item, Item> BUFFLON_SMALL_STORAGE = sharedItem(PCItemIds.BUFFLON_SMALL_STORAGE);

    public static final DeferredHolder<Item, Item> BUFFLON_LARGE_STORAGE = sharedItem(PCItemIds.BUFFLON_LARGE_STORAGE);

    public static final DeferredHolder<Item, Item> END_TROLL_SKIN = sharedItem(PCItemIds.END_TROLL_SKIN);

    // Custom Items
    public static final DeferredHolder<Item, Item> ARACHNON_HAMMER = sharedItem(PCItemIds.ARACHNON_HAMMER);

    public static final DeferredHolder<Item, ? extends Item> CRAB_BUCKET = sharedBucketItem(PCItemIds.CRAB_BUCKET);

    public static final DeferredHolder<Item, ? extends Item> SEAHORSE_BUCKET = sharedBucketItem(PCItemIds.SEAHORSE_BUCKET);

    public static final DeferredHolder<Item, ? extends Item> PLANT_HAT = sharedItem(PCItemIds.PLANT_HAT);

    // Spawn Eggs - Using SpawnEggItem with deferred entity type resolution
    // NeoForge 1.21.1 automatically registers color handlers for SpawnEggItem
    public static final DeferredHolder<Item, SpawnEggItem> ARACHNON_SPAWN_EGG = sharedPortableSpawnEgg(PCItemIds.ARACHNON_SPAWN_EGG);

    public static final DeferredHolder<Item, SpawnEggItem> HELLHOUND_SPAWN_EGG = sharedPortableSpawnEgg(PCItemIds.HELLHOUND_SPAWN_EGG);

    public static final DeferredHolder<Item, SpawnEggItem> CRAB_SPAWN_EGG = sharedPortableSpawnEgg(PCItemIds.CRAB_SPAWN_EGG);

    public static final DeferredHolder<Item, SpawnEggItem> SEAHORSE_SPAWN_EGG = sharedPortableSpawnEgg(PCItemIds.SEAHORSE_SPAWN_EGG);

    public static final DeferredHolder<Item, SpawnEggItem> ACIDIC_ARCHVINE_SPAWN_EGG = sharedPortableSpawnEgg(PCItemIds.ACIDIC_ARCHVINE_SPAWN_EGG);

    public static final DeferredHolder<Item, SpawnEggItem> BUFFLON_SPAWN_EGG = sharedPortableSpawnEgg(PCItemIds.BUFFLON_SPAWN_EGG);

    public static final DeferredHolder<Item, SpawnEggItem> END_TROLL_SPAWN_EGG = sharedPortableSpawnEgg(PCItemIds.END_TROLL_SPAWN_EGG);

    private static final List<DeferredHolder<Item, SpawnEggItem>> SPAWN_EGGS = List.of(
            ARACHNON_SPAWN_EGG,
            HELLHOUND_SPAWN_EGG,
            CRAB_SPAWN_EGG,
            SEAHORSE_SPAWN_EGG,
            ACIDIC_ARCHVINE_SPAWN_EGG,
            END_TROLL_SPAWN_EGG,
            BUFFLON_SPAWN_EGG
    );
    private static final List<DeferredHolder<Item, ? extends Item>> BLOCK_ITEM_REGISTRATIONS = registerBlockItems();

    private PCItems() {
    }

    @SuppressWarnings("unchecked")
    private static <T extends Item> DeferredHolder<Item, T> sharedItem(String id) {
        return (DeferredHolder<Item, T>) SHARED_ITEMS.get(id);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Item> DeferredHolder<Item, T> sharedBucketItem(String id) {
        return (DeferredHolder<Item, T>) SHARED_BUCKET_ITEMS.get(id);
    }

    @SuppressWarnings("unchecked")
    private static DeferredHolder<Item, SpawnEggItem> sharedPortableSpawnEgg(String id) {
        return (DeferredHolder<Item, SpawnEggItem>) SHARED_PORTABLE_SPAWN_EGGS.get(id);
    }

    private static DeferredHolder<Item, Item> registerBasicItem(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties().setId(PCItemIds.key(name))));
    }

    private static <T extends Item> DeferredHolder<Item, T> registerCustomItem(String name, Supplier<T> factory) {
        return ITEMS.register(name, factory);
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

        for (String id : SHARED_END_TROLL_BOX_ITEMS.keySet()) {
            blockItems.add(SHARED_END_TROLL_BOX_ITEMS.get(id));
        }

        return List.copyOf(blockItems);
    }

    private static DeferredHolder<Item, BlockItem> registerBlockItem(String name, Supplier<? extends Block> blockSupplier) {
        return ITEMS.register(name, () -> new BlockItem(blockSupplier.get(), new Item.Properties().setId(PCItemIds.key(name))));
    }

}
