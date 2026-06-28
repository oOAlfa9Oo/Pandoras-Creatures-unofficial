package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.content.item.ItemArachnonHammer;
import andrews.pandoras_creatures.content.item.ItemCrabBucket;
import andrews.pandoras_creatures.content.item.ItemSeahorseBucket;
import andrews.pandoras_creatures.content.item.PCSpawnEggItem;
import andrews.pandoras_creatures.forge.content.item.ForgeEndTrollBoxItem;
import andrews.pandoras_creatures.forge.content.item.ForgePlantHatItem;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxBootstrap;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class PCForgeItems {
    private static final FoodProperties CRAB_MEAT_RAW = food(2, 0.1F);
    private static final FoodProperties CRAB_MEAT_COOKED = food(5, 0.6F);
    private static final FoodProperties SEAHORSE_RAW = food(1, 0.1F);
    private static final FoodProperties SEAHORSE_COOKED = food(3, 0.4F);
    private static final FoodProperties BUFFLON_BEEF_RAW = food(4, 0.3F);
    private static final FoodProperties BUFFLON_BEEF_COOKED = food(9, 0.9F);

    private static boolean registered;

    private PCForgeItems() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeItems::registerItems);
    }

    public static boolean hasItem(String id) {
        return BuiltInRegistries.ITEM.containsKey(Reference.id(id));
    }

    public static Item getItem(String id) {
        ResourceLocation itemId = Reference.id(id);
        Item value = BuiltInRegistries.ITEM.get(itemId);
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
            registerFood(helper, PCItemIds.CRAB_MEAT, CRAB_MEAT_RAW);
            registerFood(helper, PCItemIds.CRAB_MEAT_COOKED, CRAB_MEAT_COOKED);
            registerFood(helper, PCItemIds.SEAHORSE, SEAHORSE_RAW);
            registerFood(helper, PCItemIds.SEAHORSE_COOKED, SEAHORSE_COOKED);
            registerBasic(helper, PCItemIds.ACIDIC_ARCHVINE_TONGUE);
            registerBasic(helper, PCItemIds.HERB_BUNDLE);
            registerFood(helper, PCItemIds.BUFFLON_BEEF, BUFFLON_BEEF_RAW);
            registerFood(helper, PCItemIds.BUFFLON_BEEF_COOKED, BUFFLON_BEEF_COOKED);
            registerBasic(helper, PCItemIds.BUFFLON_HIDE);
            registerSingleStack(helper, PCItemIds.BUFFLON_SADDLE);
            registerSingleStack(helper, PCItemIds.BUFFLON_PLAYER_SEATS);
            registerSingleStack(helper, PCItemIds.BUFFLON_SMALL_STORAGE);
            registerSingleStack(helper, PCItemIds.BUFFLON_LARGE_STORAGE);
            registerBasic(helper, PCItemIds.END_TROLL_SKIN);
            registerCustom(helper, PCItemIds.ARACHNON_HAMMER, new ItemArachnonHammer());
            registerCustom(helper, PCItemIds.PLANT_HAT, new ForgePlantHatItem());
            registerBlockItem(helper, andrews.pandoras_creatures.registry.block.PCBlockIds.ARACHNON_CRYSTAL);
            registerBlockItem(helper, andrews.pandoras_creatures.registry.block.PCBlockIds.HORSETAIL);
            registerBlockItem(helper, andrews.pandoras_creatures.registry.block.PCBlockIds.DHANIA);
            registerBlockItem(helper, andrews.pandoras_creatures.registry.block.PCBlockIds.HILL_BLOOM);
            registerBlockItem(helper, andrews.pandoras_creatures.registry.block.PCBlockIds.PANDORIC_SHARD);
            for (String id : PCEndTrollBoxBootstrap.blockIds()) {
                registerCustom(helper, id, createForgeEndTrollBoxItem(id));
            }
            registerCustom(helper, PCItemIds.CRAB_BUCKET, new ItemCrabBucket());
            registerCustom(helper, PCItemIds.SEAHORSE_BUCKET, new ItemSeahorseBucket());
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
        helper.register(Reference.id(id), new Item(new Item.Properties()));
    }

    private static void registerSingleStack(RegisterEvent.RegisterHelper<Item> helper, String id) {
        helper.register(Reference.id(id), new Item(new Item.Properties().stacksTo(1)));
    }

    private static void registerFood(RegisterEvent.RegisterHelper<Item> helper, String id, FoodProperties foodProperties) {
        helper.register(Reference.id(id), new Item(new Item.Properties().food(foodProperties)));
    }

    private static void registerBlockItem(RegisterEvent.RegisterHelper<Item> helper, String id) {
        helper.register(
                Reference.id(id),
                new BlockItem(PCForgeBlocks.getSimpleBlock(id), new Item.Properties())
        );
    }

    private static void registerCustom(RegisterEvent.RegisterHelper<Item> helper, String id, Item item) {
        helper.register(Reference.id(id), item);
    }

    private static Item createForgeEndTrollBoxItem(String id) {
        return new ForgeEndTrollBoxItem(
                PCForgeBlocks.getEndTrollBox(PCEndTrollBoxPalette.colorForBlockName(id)),
                new Item.Properties().stacksTo(1).fireResistant()
        );
    }

    private static void registerSpawnEgg(
            RegisterEvent.RegisterHelper<Item> helper,
            PCSpawnEggPalette palette,
            java.util.function.Supplier<? extends net.minecraft.world.entity.EntityType<?>> entityTypeSupplier) {
        helper.register(
                Reference.id(palette.itemName()),
                new PCSpawnEggItem(entityTypeSupplier, palette.primaryColor(), palette.secondaryColor(), new Item.Properties())
        );
    }

    private static FoodProperties food(int nutrition, float saturationModifier) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationMod(saturationModifier)
                .build();
    }
}

