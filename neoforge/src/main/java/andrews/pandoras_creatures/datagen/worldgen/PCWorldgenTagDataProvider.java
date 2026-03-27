package andrews.pandoras_creatures.datagen.worldgen;

import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class PCWorldgenTagDataProvider implements DataProvider {
    private final PackOutput.PathProvider biomeTagPathProvider;

    public PCWorldgenTagDataProvider(PackOutput output) {
        this.biomeTagPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "tags/worldgen/biome/has_structure");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return DataProvider.saveStable(cachedOutput, createTagJson(List.of(
                "minecraft:end_highlands",
                "minecraft:end_midlands",
                "minecraft:end_barrens",
                "minecraft:small_end_islands"
        )), biomeTagPathProvider.json(PCStructureIds.id(PCStructureIds.END_PRISON)));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Worldgen Tags";
    }

    private static JsonObject createTagJson(List<String> values) {
        JsonObject root = new JsonObject();
        JsonArray jsonValues = new JsonArray();
        values.forEach(jsonValues::add);
        root.add("values", jsonValues);
        return root;
    }
}
