package andrews.pandoras_creatures.datagen.worldgen;

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

public final class PCPlacedFeatureDataProvider implements DataProvider {
    private static final CountPlacedFeatureDefinition[] COUNT_FEATURES = new CountPlacedFeatureDefinition[]{
            new CountPlacedFeatureDefinition("dhania", "pandoras_creatures:dhania", 3),
            new CountPlacedFeatureDefinition("hill_bloom", "pandoras_creatures:hill_bloom", 3),
            new CountPlacedFeatureDefinition("horse_tail", "pandoras_creatures:horse_tail", 3)
    };

    private final PackOutput.PathProvider placedFeaturePathProvider;

    public PCPlacedFeatureDataProvider(PackOutput output) {
        this.placedFeaturePathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/placed_feature");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (CountPlacedFeatureDefinition definition : COUNT_FEATURES) {
            futures.add(DataProvider.saveStable(cachedOutput, createCountPlacedFeature(definition), placedFeaturePathProvider.json(id(definition.name()))));
        }
        futures.add(DataProvider.saveStable(cachedOutput, createSimplePlacedFeature("minecraft:chorus_plant"), placedFeaturePathProvider.json(id("end_prison_chorus_plant"))));
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Placed Features";
    }

    private static JsonObject createCountPlacedFeature(CountPlacedFeatureDefinition definition) {
        JsonObject root = new JsonObject();
        root.addProperty("feature", definition.featureId());

        JsonArray placement = new JsonArray();
        JsonObject count = new JsonObject();
        count.addProperty("type", "minecraft:count");
        count.addProperty("count", definition.count());
        placement.add(count);

        JsonObject inSquare = new JsonObject();
        inSquare.addProperty("type", "minecraft:in_square");
        placement.add(inSquare);

        JsonObject heightmap = new JsonObject();
        heightmap.addProperty("type", "minecraft:heightmap");
        heightmap.addProperty("heightmap", "MOTION_BLOCKING_NO_LEAVES");
        placement.add(heightmap);

        JsonObject biome = new JsonObject();
        biome.addProperty("type", "minecraft:biome");
        placement.add(biome);

        root.add("placement", placement);
        return root;
    }

    private static JsonObject createSimplePlacedFeature(String featureId) {
        JsonObject root = new JsonObject();
        root.addProperty("feature", featureId);
        root.add("placement", new JsonArray());
        return root;
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }

    private record CountPlacedFeatureDefinition(String name, String featureId, int count) {
    }
}
