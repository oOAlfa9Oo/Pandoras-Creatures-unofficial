package andrews.pandoras_creatures.datagen.worldgen;

import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public final class PCStructureDataProvider implements DataProvider {
    private final PackOutput.PathProvider structurePathProvider;

    public PCStructureDataProvider(PackOutput output) {
        this.structurePathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/structure");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return DataProvider.saveStable(cachedOutput, createEndPrisonStructure(), structurePathProvider.json(id("end_prison")));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Structures";
    }

    private static JsonObject createEndPrisonStructure() {
        JsonObject root = new JsonObject();
        root.addProperty("type", "pandoras_creatures:end_prison");
        root.addProperty("start_pool", "pandoras_creatures:end_prison");
        root.addProperty("biomes", "#pandoras_creatures:has_structure/end_prison");
        root.add("spawn_overrides", new JsonObject());
        root.addProperty("step", "surface_structures");
        root.addProperty("terrain_adaptation", "none");
        return root;
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }
}
