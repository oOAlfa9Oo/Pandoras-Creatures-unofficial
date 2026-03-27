package andrews.pandoras_creatures.datagen.worldgen;

import andrews.pandoras_creatures.registry.structure.PCStructureIds;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public final class PCStructureDataProvider implements DataProvider {
    private final PackOutput.PathProvider structurePathProvider;

    public PCStructureDataProvider(PackOutput output) {
        this.structurePathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/structure");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return DataProvider.saveStable(cachedOutput, createEndPrisonStructure(), structurePathProvider.json(PCStructureIds.id(PCStructureIds.END_PRISON)));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Structures";
    }

    private static JsonObject createEndPrisonStructure() {
        JsonObject root = new JsonObject();
        root.addProperty("type", PCStructureIds.qualified(PCStructureIds.END_PRISON));
        root.addProperty("start_pool", PCStructureIds.qualified(PCStructureIds.END_PRISON));
        root.addProperty("biomes", PCStructureIds.tagReference(PCStructureIds.HAS_STRUCTURE_END_PRISON));
        root.add("spawn_overrides", new JsonObject());
        root.addProperty("step", "surface_structures");
        root.addProperty("terrain_adaptation", "none");
        return root;
    }
}
