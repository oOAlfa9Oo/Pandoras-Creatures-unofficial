package andrews.pandoras_creatures.datagen.loot;

import andrews.pandoras_creatures.registry.PCBlocks;
import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

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
        addSimpleBlockLoot(futures, cachedOutput, PCBlocks.ARACHNON_CRYSTAL.get());
        addSimpleBlockLoot(futures, cachedOutput, PCBlocks.HORSETAIL.get());
        addSimpleBlockLoot(futures, cachedOutput, PCBlocks.DHANIA.get());
        addSimpleBlockLoot(futures, cachedOutput, PCBlocks.HILL_BLOOM.get());
        addContainerBlockLoot(futures, cachedOutput, PCBlocks.END_TROLL_BOX.get());
        for (Block block : PCBlocks.getEndTrollBoxBlockArray()) {
            if (block != PCBlocks.END_TROLL_BOX.get()) {
                addContainerBlockLoot(futures, cachedOutput, block);
            }
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Block Loot Tables";
    }

    private void addSimpleBlockLoot(List<CompletableFuture<?>> futures, CachedOutput cachedOutput, Block block) {
        futures.add(DataProvider.saveStable(cachedOutput, createSimpleDropTable(block), lootPathProvider.json(blockId(block))));
    }

    private void addContainerBlockLoot(List<CompletableFuture<?>> futures, CachedOutput cachedOutput, Block block) {
        futures.add(DataProvider.saveStable(cachedOutput, createContainerDropTable(block), lootPathProvider.json(blockId(block))));
    }

    private static JsonObject createSimpleDropTable(Block block) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "minecraft:block");
        JsonObject pool = new JsonObject();
        pool.addProperty("rolls", 1);

        JsonObject entry = new JsonObject();
        entry.addProperty("type", "minecraft:item");
        entry.addProperty("name", blockId(block).toString());

        JsonArray entries = new JsonArray();
        entries.add(entry);
        pool.add("entries", entries);

        JsonArray pools = new JsonArray();
        pools.add(pool);
        root.add("pools", pools);
        return root;
    }

    private static JsonObject createContainerDropTable(Block block) {
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
        entry.addProperty("name", blockId(block).toString());

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

    private static Identifier blockId(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, path);
    }
}
