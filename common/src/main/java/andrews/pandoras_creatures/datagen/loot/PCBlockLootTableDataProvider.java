package andrews.pandoras_creatures.datagen.loot;

import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
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

public final class PCBlockLootTableDataProvider implements DataProvider {
    private final PackOutput.PathProvider lootPathProvider;

    public PCBlockLootTableDataProvider(PackOutput output) {
        this.lootPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "loot_table/blocks");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        addSimpleBlockLoot(futures, cachedOutput, PCBlockIds.ARACHNON_CRYSTAL);
        addSimpleBlockLoot(futures, cachedOutput, PCBlockIds.HORSETAIL);
        addSimpleBlockLoot(futures, cachedOutput, PCBlockIds.DHANIA);
        addSimpleBlockLoot(futures, cachedOutput, PCBlockIds.HILL_BLOOM);
        addContainerBlockLoot(futures, cachedOutput, PCBlockIds.END_TROLL_BOX);
        for (var color : PCEndTrollBoxPalette.orderedColors()) {
            addContainerBlockLoot(futures, cachedOutput, PCEndTrollBoxPalette.blockName(color));
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Block Loot Tables";
    }

    private void addSimpleBlockLoot(List<CompletableFuture<?>> futures, CachedOutput cachedOutput, String blockId) {
        futures.add(DataProvider.saveStable(cachedOutput, createSimpleDropTable(blockId), lootPathProvider.json(id(blockId))));
    }

    private void addContainerBlockLoot(List<CompletableFuture<?>> futures, CachedOutput cachedOutput, String blockId) {
        futures.add(DataProvider.saveStable(cachedOutput, createContainerDropTable(blockId), lootPathProvider.json(id(blockId))));
    }

    private static JsonObject createSimpleDropTable(String blockId) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "minecraft:block");
        JsonObject pool = new JsonObject();
        pool.addProperty("rolls", 1);

        JsonObject entry = new JsonObject();
        entry.addProperty("type", "minecraft:item");
        entry.addProperty("name", id(blockId).toString());

        JsonArray entries = new JsonArray();
        entries.add(entry);
        pool.add("entries", entries);

        JsonArray pools = new JsonArray();
        pools.add(pool);
        root.add("pools", pools);
        return root;
    }

    private static JsonObject createContainerDropTable(String blockId) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "minecraft:block");

        JsonObject copyComponents = new JsonObject();
        copyComponents.addProperty("function", "minecraft:copy_components");
        JsonArray include = new JsonArray();
        include.add("minecraft:custom_name");
        include.add("minecraft:container");
        include.add("minecraft:lock");
        include.add("minecraft:container_loot");
        copyComponents.add("include", include);
        copyComponents.addProperty("source", "block_entity");

        JsonArray functions = new JsonArray();
        functions.add(copyComponents);

        JsonObject entry = new JsonObject();
        entry.addProperty("type", "minecraft:item");
        entry.add("functions", functions);
        entry.addProperty("name", id(blockId).toString());

        JsonArray entries = new JsonArray();
        entries.add(entry);

        JsonObject pool = new JsonObject();
        pool.addProperty("bonus_rolls", 0.0D);
        pool.add("entries", entries);
        pool.addProperty("rolls", 1.0D);

        JsonArray pools = new JsonArray();
        pools.add(pool);
        root.add("pools", pools);
        return root;
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }
}
