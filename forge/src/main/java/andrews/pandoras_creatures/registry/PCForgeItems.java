package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.content.item.ItemArachnonHammer;
import andrews.pandoras_creatures.content.item.ItemCrabBucket;
import andrews.pandoras_creatures.content.item.ItemSeahorseBucket;
import andrews.pandoras_creatures.content.item.PCSpawnEggItem;
import andrews.pandoras_creatures.forge.content.item.ForgeEndTrollBoxItem;
import andrews.pandoras_creatures.forge.content.item.ForgePlantHatItem;
import andrews.pandoras_creatures.registry.PCFoods;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.item.PCItemBootstrap;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class PCForgeItems {
    private static boolean registered;

    private PCForgeItems() {
    }

    public static void register(BusGroup modEventBus) {
        RegisterEvent.getBus(modEventBus).addListener(PCForgeItems::registerItems);
    }

    public static boolean hasItem(String id) {
        return BuiltInRegistries.ITEM.containsKey(Identifier.fromNamespaceAndPath(Reference.MODID, id));
    }

    public static Item getItem(String id) {
        Identifier itemId = Identifier.fromNamespaceAndPath(Reference.MODID, id);
        Item value = BuiltInRegistries.ITEM.getValue(itemId);
        if (value == null) {
            throw new IllegalArgumentException("Unknown forge item id: " + itemId);
        }
        return value;
    }

    public static Item[] getEndTrollBoxItems() {
        return andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap.blockIds().stream()
                .map(PCForgeItems::getItem)
                .toArray(Item[]::new);
    }

    private static void registerItems(RegisterEvent event) {
        if (registered || !event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)) {
            return;
        }

        registered = true;
        event.register(ForgeRegistries.Keys.ITEMS, helper -> {
            registerFood(helper, PCItemIds.CRAB_MEAT, PCFoods.CRAB_MEAT_RAW);
            registerFood(helper, PCItemIds.CRAB_MEAT_COOKED, PCFoods.CRAB_MEAT_COOKED);
            registerFood(helper, PCItemIds.SEAHORSE, PCFoods.SEAHORSE_RAW);
            registerFood(helper, PCItemIds.SEAHORSE_COOKED, PCFoods.SEAHORSE_COOKED);
            registerBasic(helper, PCItemIds.ACIDIC_ARCHVINE_TONGUE);
            registerBasic(helper, PCItemIds.HERB_BUNDLE);
            registerFood(helper, PCItemIds.BUFFLON_BEEF, PCFoods.BUFFLON_BEEF_RAW);
            registerFood(helper, PCItemIds.BUFFLON_BEEF_COOKED, PCFoods.BUFFLON_BEEF_COOKED);
            registerBasic(helper, PCItemIds.BUFFLON_HIDE);
            registerSingleStack(helper, PCItemIds.BUFFLON_SADDLE);
            registerSingleStack(helper, PCItemIds.BUFFLON_PLAYER_SEATS);
            registerSingleStack(helper, PCItemIds.BUFFLON_SMALL_STORAGE);
            registerSingleStack(helper, PCItemIds.BUFFLON_LARGE_STORAGE);
            registerBasic(helper, PCItemIds.END_TROLL_SKIN);
            registerCustom(helper, PCItemIds.ARACHNON_HAMMER, new ItemArachnonHammer(PCItemBootstrap.properties(PCItemIds.ARACHNON_HAMMER)));
            registerCustom(helper, PCItemIds.PLANT_HAT, new ForgePlantHatItem(PCItemBootstrap.properties(PCItemIds.PLANT_HAT)));
            registerBlockItem(helper, andrews.pandoras_creatures.registry.block.PCBlockIds.ARACHNON_CRYSTAL);
            registerBlockItem(helper, andrews.pandoras_creatures.registry.block.PCBlockIds.HORSETAIL);
            registerBlockItem(helper, andrews.pandoras_creatures.registry.block.PCBlockIds.DHANIA);
            registerBlockItem(helper, andrews.pandoras_creatures.registry.block.PCBlockIds.HILL_BLOOM);
            registerBlockItem(helper, andrews.pandoras_creatures.registry.block.PCBlockIds.PANDORIC_SHARD);
            for (String id : PCEndTrollBoxBootstrap.blockIds()) {
                registerCustom(helper, id, createForgeEndTrollBoxItem(id));
            }
            registerCustom(helper, PCItemIds.CRAB_BUCKET, new ItemCrabBucket(PCItemBootstrap.properties(PCItemIds.CRAB_BUCKET)));
            registerCustom(helper, PCItemIds.SEAHORSE_BUCKET, new ItemSeahorseBucket(PCItemBootstrap.properties(PCItemIds.SEAHORSE_BUCKET)));
            registerSpawnEgg(helper, PCSpawnEggPalette.ACIDIC_ARCHVINE, PCForgeEntities::acidicArchvine);
            registerSpawnEgg(helper, PCSpawnEggPalette.ARACHNON, PCForgeEntities::arachnon);
            registerSpawnEgg(helper, PCSpawnEggPalette.CRAB, PCForgeEntities::crab);
            registerSpawnEgg(helper, PCSpawnEggPalette.SEAHORSE, PCForgeEntities::seahorse);
            registerSpawnEgg(helper, PCSpawnEggPalette.HELLHOUND, PCForgeEntities::hellhound);
            registerSpawnEgg(helper, PCSpawnEggPalette.BUFFLON, PCForgeEntities::bufflon);
            registerSpawnEgg(helper, PCSpawnEggPalette.END_TROLL, PCForgeEntities::endTroll);
        });
    }

    private static void registerBasic(RegisterEvent.RegisterHelper<Item> helper, String id) {
        helper.register(Identifier.fromNamespaceAndPath(Reference.MODID, id), new Item(PCItemBootstrap.properties(id)));
    }

    private static void registerSingleStack(RegisterEvent.RegisterHelper<Item> helper, String id) {
        helper.register(Identifier.fromNamespaceAndPath(Reference.MODID, id), new Item(PCItemBootstrap.properties(id).stacksTo(1)));
    }

    private static void registerFood(RegisterEvent.RegisterHelper<Item> helper, String id, FoodProperties foodProperties) {
        helper.register(Identifier.fromNamespaceAndPath(Reference.MODID, id), new Item(PCItemBootstrap.properties(id).food(foodProperties)));
    }

    private static void registerBlockItem(RegisterEvent.RegisterHelper<Item> helper, String id) {
        helper.register(
                Identifier.fromNamespaceAndPath(Reference.MODID, id),
                new BlockItem(PCForgeBlocks.getSimpleBlock(id), PCItemBootstrap.properties(id))
        );
    }

    private static void registerCustom(RegisterEvent.RegisterHelper<Item> helper, String id, Item item) {
        helper.register(Identifier.fromNamespaceAndPath(Reference.MODID, id), item);
    }

    private static Item createForgeEndTrollBoxItem(String id) {
        return new ForgeEndTrollBoxItem(
                PCForgeBlocks.getEndTrollBox(PCEndTrollBoxPalette.colorForBlockName(id)),
                PCItemBootstrap.properties(id).stacksTo(1).fireResistant()
        );
    }

    private static void registerSpawnEgg(
            RegisterEvent.RegisterHelper<Item> helper,
            PCSpawnEggPalette palette,
            java.util.function.Supplier<? extends net.minecraft.world.entity.EntityType<?>> entityTypeSupplier) {
        helper.register(
                Identifier.fromNamespaceAndPath(Reference.MODID, palette.itemName()),
                new PCSpawnEggItem(entityTypeSupplier, palette.primaryColor(), palette.secondaryColor(), PCItemBootstrap.properties(palette.itemName()))
        );
    }
}
