package andrews.pandoras_creatures.datagen.worldgen;

import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public final class PCStructureSetDataProvider implements DataProvider {
    private final PackOutput.PathProvider structureSetPathProvider;

    public PCStructureSetDataProvider(PackOutput output) {
        this.structureSetPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/structure_set");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        return DataProvider.saveStable(cachedOutput, createEndPrisonStructureSet(), structureSetPathProvider.json(id("end_prison")));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Structure Sets";
    }

    private static JsonObject createEndPrisonStructureSet() {
        JsonObject root = new JsonObject();

        JsonArray structures = new JsonArray();
        JsonObject structure = new JsonObject();
        structure.addProperty("structure", "pandoras_creatures:end_prison");
        structure.addProperty("weight", 1);
        structures.add(structure);
        root.add("structures", structures);

        JsonObject placement = new JsonObject();
        placement.addProperty("type", "minecraft:random_spread");
        placement.addProperty("spacing", 32);
        placement.addProperty("separation", 26);
        placement.addProperty("salt", 43650246);

        JsonObject exclusionZone = new JsonObject();
        exclusionZone.addProperty("other_set", "minecraft:end_cities");
        exclusionZone.addProperty("chunk_count", 6);
        placement.add("exclusion_zone", exclusionZone);

        root.add("placement", placement);
        return root;
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }
}
