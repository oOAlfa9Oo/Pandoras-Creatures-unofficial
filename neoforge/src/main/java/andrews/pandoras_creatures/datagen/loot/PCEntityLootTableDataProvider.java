package andrews.pandoras_creatures.datagen.loot;

import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class PCEntityLootTableDataProvider implements DataProvider {
    private static final String ENTITY_TABLE_TYPE = "minecraft:entity";
    private static final String ITEM_ENTRY_TYPE = "minecraft:item";
    private static final String LOOTING_ENCHANTMENT = "minecraft:looting";
    private static final EntityLootDefinition[] DEFINITIONS = new EntityLootDefinition[]{
            new EntityLootDefinition(PCEntityIds.ACIDIC_ARCHVINE, List.of(
                    new LootPoolDefinition("pool_acidic_archvine_1", 1.0D, 1.0D,
                            new ItemDropDefinition(PCItems.ACIDIC_ARCHVINE_TONGUE.get(), 1.0D, 1.0D, false, false, 0.0D, 0.0D))
            )),
            new EntityLootDefinition(PCEntityIds.ARACHNON, List.of(
                    new LootPoolDefinition("pool_arachnon_1", 1.0D, 1.0D,
                            new ItemDropDefinition(PCBlocksAndVanilla.ARACHNON_CRYSTAL_ID, 1.0D, 1.0D, false, true, 0.0D, 1.0D))
            )),
            new EntityLootDefinition(PCEntityIds.BUFFLON, List.of(
                    new LootPoolDefinition("pool_bufflon_1", 1.0D, 1.0D,
                            new ItemDropDefinition(PCItems.BUFFLON_HIDE.get(), 1.0D, 3.0D, false, true, 0.0D, 1.0D)),
                    new LootPoolDefinition("pool_bufflon_2", 1.0D, 1.0D,
                            new ItemDropDefinition(PCItems.BUFFLON_BEEF.get(), 1.0D, 4.0D, true, true, 0.0D, 1.0D))
            )),
            new EntityLootDefinition(PCEntityIds.CRAB, List.of(
                    new LootPoolDefinition("pool_crab_1", 1.0D, 1.0D,
                            new ItemDropDefinition(PCItems.CRAB_MEAT.get(), 1.0D, 2.0D, true, true, 0.0D, 1.0D))
            )),
            new EntityLootDefinition(PCEntityIds.END_TROLL, List.of(
                    new LootPoolDefinition("pool_end_troll_1", 1.0D, 1.0D,
                            new ItemDropDefinition(PCItems.END_TROLL_SKIN.get(), 16.0D, 18.0D, false, true, 1.0D, 2.0D))
            )),
            new EntityLootDefinition(PCEntityIds.HELLHOUND, List.of(
                    new LootPoolDefinition("pool_hellhound_1", 1.0D, 1.0D,
                            new ItemDropDefinition(ResourceLocation.withDefaultNamespace("bone"), 1.0D, 3.0D, false, true, 0.0D, 1.0D))
            )),
            new EntityLootDefinition(PCEntityIds.SEAHORSE, List.of(
                    new LootPoolDefinition("pool_seahorse_1", 1.0D, 1.0D,
                            new ItemDropDefinition(PCItems.SEAHORSE.get(), 1.0D, 1.0D, true, false, 0.0D, 0.0D))
            ))
    };

    private final PackOutput.PathProvider lootPathProvider;

    public PCEntityLootTableDataProvider(PackOutput output) {
        this.lootPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "loot_table/entities");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (EntityLootDefinition definition : DEFINITIONS) {
            futures.add(DataProvider.saveStable(cachedOutput, createEntityLootTable(definition), lootPathProvider.json(id(definition.name()))));
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Entity Loot Tables";
    }

    private static JsonObject createEntityLootTable(EntityLootDefinition definition) {
        JsonObject root = new JsonObject();
        root.addProperty("type", ENTITY_TABLE_TYPE);

        JsonArray pools = new JsonArray();
        for (LootPoolDefinition poolDefinition : definition.pools()) {
            JsonObject pool = new JsonObject();
            pool.addProperty("name", poolDefinition.name());
            pool.addProperty("rolls", 1);

            JsonArray entries = new JsonArray();
            entries.add(createItemEntry(poolDefinition.drop()));
            pool.add("entries", entries);
            pools.add(pool);
        }

        root.add("pools", pools);
        return root;
    }

    private static JsonObject createItemEntry(ItemDropDefinition definition) {
        JsonObject entry = new JsonObject();
        entry.addProperty("type", ITEM_ENTRY_TYPE);
        entry.addProperty("name", definition.itemId().toString());

        JsonArray functions = new JsonArray();
        functions.add(createSetCountFunction(definition.minCount(), definition.maxCount()));
        if (definition.smeltWhenOnFire()) {
            functions.add(createFurnaceSmeltFunction());
        }
        if (definition.affectedByLooting()) {
            functions.add(createLootingFunction(definition.lootingMin(), definition.lootingMax()));
        }
        entry.add("functions", functions);
        return entry;
    }

    private static JsonObject createSetCountFunction(double min, double max) {
        JsonObject function = new JsonObject();
        function.addProperty("function", "minecraft:set_count");
        function.add("count", createUniformNumberProvider(min, max));
        return function;
    }

    private static JsonObject createLootingFunction(double min, double max) {
        JsonObject function = new JsonObject();
        function.addProperty("function", "minecraft:enchanted_count_increase");
        function.addProperty("enchantment", LOOTING_ENCHANTMENT);
        function.add("count", createUniformNumberProvider(min, max));
        return function;
    }

    private static JsonObject createFurnaceSmeltFunction() {
        JsonObject function = new JsonObject();
        function.addProperty("function", "minecraft:furnace_smelt");

        JsonObject condition = new JsonObject();
        condition.addProperty("condition", "minecraft:entity_properties");
        condition.addProperty("entity", "this");

        JsonObject flags = new JsonObject();
        flags.addProperty("is_on_fire", true);
        JsonObject predicate = new JsonObject();
        predicate.add("flags", flags);
        condition.add("predicate", predicate);

        JsonArray conditions = new JsonArray();
        conditions.add(condition);
        function.add("conditions", conditions);
        return function;
    }

    private static JsonObject createUniformNumberProvider(double min, double max) {
        JsonObject count = new JsonObject();
        count.addProperty("type", "minecraft:uniform");
        count.addProperty("min", min);
        count.addProperty("max", max);
        return count;
    }

    private static ResourceLocation id(String path) {
        return new ResourceLocation(Reference.MODID, path);
    }

    private record EntityLootDefinition(String name, List<LootPoolDefinition> pools) {
    }

    private record LootPoolDefinition(String name, double minRolls, double maxRolls, ItemDropDefinition drop) {
    }

    private record ItemDropDefinition(ResourceLocation itemId, double minCount, double maxCount, boolean smeltWhenOnFire,
                                      boolean affectedByLooting, double lootingMin, double lootingMax) {
        private ItemDropDefinition(Item item, double minCount, double maxCount, boolean smeltWhenOnFire,
                                   boolean affectedByLooting, double lootingMin, double lootingMax) {
            this(BuiltInRegistries.ITEM.getKey(item), minCount, maxCount, smeltWhenOnFire, affectedByLooting, lootingMin, lootingMax);
        }
    }

    private static final class PCBlocksAndVanilla {
        private static final ResourceLocation ARACHNON_CRYSTAL_ID = id(PCBlockIds.ARACHNON_CRYSTAL);

        private PCBlocksAndVanilla() {
        }
    }
}

