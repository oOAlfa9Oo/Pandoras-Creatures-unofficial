package andrews.pandoras_creatures.datagen.loot;

import andrews.pandoras_creatures.registry.PCItems;
import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class PCChestInjectionLootTableDataProvider implements DataProvider {
    private static final LootInjectionDefinition[] DEFINITIONS = new LootInjectionDefinition[]{
            new LootInjectionDefinition("bufflon_desert_pyramid", 1.0D, 1.0D, 5.0D, 80),
            new LootInjectionDefinition("bufflon_end_city", 1.0D, 1.0D, 3.0D, 88),
            new LootInjectionDefinition("bufflon_jungle_temple", 1.0D, 2.0D, 6.0D, 76),
            new LootInjectionDefinition("bufflon_nether_bridge", 1.0D, 2.0D, 4.0D, 68),
            new LootInjectionDefinition("bufflon_simple_dungeon", 1.0D, 2.0D, 7.0D, 72)
    };
    private static final Item[] BUFFLON_CHEST_ITEMS = new Item[]{
            PCItems.BUFFLON_SADDLE.get(),
            PCItems.BUFFLON_PLAYER_SEATS.get(),
            PCItems.BUFFLON_SMALL_STORAGE.get(),
            PCItems.BUFFLON_LARGE_STORAGE.get()
    };

    private final PackOutput.PathProvider lootPathProvider;

    public PCChestInjectionLootTableDataProvider(PackOutput output) {
        this.lootPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "loot_table/injections");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (LootInjectionDefinition definition : DEFINITIONS) {
            futures.add(DataProvider.saveStable(cachedOutput, createInjectionLootTable(definition), lootPathProvider.json(id(definition.name()))));
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Chest Injection Loot Tables";
    }

    private static JsonObject createInjectionLootTable(LootInjectionDefinition definition) {
        JsonObject root = new JsonObject();
        JsonArray pools = new JsonArray();
        JsonObject pool = new JsonObject();
        pool.add("rolls", createUniformNumberProvider(definition.minRolls(), definition.maxRolls()));

        JsonArray entries = new JsonArray();
        for (Item item : BUFFLON_CHEST_ITEMS) {
            JsonObject entry = new JsonObject();
            entry.addProperty("type", "minecraft:item");
            entry.addProperty("weight", definition.itemWeight());
            entry.addProperty("name", BuiltInRegistries.ITEM.getKey(item).toString());
            entries.add(entry);
        }

        JsonObject emptyEntry = new JsonObject();
        emptyEntry.addProperty("type", "empty");
        emptyEntry.addProperty("weight", definition.emptyWeight());
        entries.add(emptyEntry);

        pool.add("entries", entries);
        pools.add(pool);
        root.add("pools", pools);
        return root;
    }

    private static JsonObject createUniformNumberProvider(double min, double max) {
        JsonObject provider = new JsonObject();
        provider.addProperty("type", "minecraft:uniform");
        provider.addProperty("min", min);
        provider.addProperty("max", max);
        return provider;
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, path);
    }

    private record LootInjectionDefinition(String name, double minRolls, double maxRolls, double itemWeight,
                                           int emptyWeight) {
    }
}
