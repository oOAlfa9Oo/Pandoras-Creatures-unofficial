package andrews.pandoras_creatures.datagen.loot;

import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class PCLootModifierDataProvider implements DataProvider {
    private static final LootModifierDefinition[] DEFINITIONS = new LootModifierDefinition[]{
            new LootModifierDefinition("bufflon_desert_pyramid", "minecraft:chests/desert_pyramid"),
            new LootModifierDefinition("bufflon_end_city", "minecraft:chests/end_city_treasure"),
            new LootModifierDefinition("bufflon_jungle_temple", "minecraft:chests/jungle_temple"),
            new LootModifierDefinition("bufflon_nether_bridge", "minecraft:chests/nether_bridge"),
            new LootModifierDefinition("bufflon_simple_dungeon", "minecraft:chests/simple_dungeon")
    };

    private final PackOutput.PathProvider lootModifierPathProvider;

    public PCLootModifierDataProvider(PackOutput output) {
        this.lootModifierPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "loot_modifiers");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (LootModifierDefinition definition : DEFINITIONS) {
            futures.add(DataProvider.saveStable(cachedOutput, createLootModifier(definition), lootModifierPathProvider.json(id(definition.name()))));
        }
        futures.add(DataProvider.saveStable(cachedOutput, createGlobalLootModifierList(), lootModifierPathProvider.json(ResourceLocation.fromNamespaceAndPath("neoforge", "global_loot_modifiers"))));
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Loot Modifiers";
    }

    private static JsonObject createLootModifier(LootModifierDefinition definition) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "neoforge:add_table");

        JsonObject condition = new JsonObject();
        condition.addProperty("condition", "neoforge:loot_table_id");
        condition.addProperty("loot_table_id", definition.targetLootTableId());
        JsonArray conditions = new JsonArray();
        conditions.add(condition);
        root.add("conditions", conditions);
        root.addProperty("table", id("injections/" + definition.name()).toString());
        return root;
    }

    private static JsonObject createGlobalLootModifierList() {
        JsonObject root = new JsonObject();
        root.addProperty("replace", false);
        JsonArray entries = new JsonArray();
        for (LootModifierDefinition definition : DEFINITIONS) {
            entries.add(id(definition.name()).toString());
        }
        root.add("entries", entries);
        return root;
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }

    private record LootModifierDefinition(String name, String targetLootTableId) {
    }
}
