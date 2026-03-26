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

public final class PCConfiguredFeatureDataProvider implements DataProvider {
    private static final FlowerConfiguredFeatureDefinition[] FLOWER_FEATURES = new FlowerConfiguredFeatureDefinition[]{
            new FlowerConfiguredFeatureDefinition("dhania", "pandoras_creatures:dhania"),
            new FlowerConfiguredFeatureDefinition("hill_bloom", "pandoras_creatures:hill_bloom"),
            new FlowerConfiguredFeatureDefinition("horse_tail", "pandoras_creatures:horsetail")
    };

    private final PackOutput.PathProvider configuredFeaturePathProvider;

    public PCConfiguredFeatureDataProvider(PackOutput output) {
        this.configuredFeaturePathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "worldgen/configured_feature");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (FlowerConfiguredFeatureDefinition definition : FLOWER_FEATURES) {
            futures.add(DataProvider.saveStable(cachedOutput, createFlowerFeature(definition), configuredFeaturePathProvider.json(id(definition.name()))));
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Configured Features";
    }

    private static JsonObject createFlowerFeature(FlowerConfiguredFeatureDefinition definition) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "minecraft:flower");

        JsonObject config = new JsonObject();
        config.addProperty("tries", 64);
        config.addProperty("xz_spread", 6);
        config.addProperty("y_spread", 2);

        JsonObject featureHolder = new JsonObject();
        JsonObject feature = new JsonObject();
        feature.addProperty("type", "minecraft:simple_block");

        JsonObject featureConfig = new JsonObject();
        JsonObject toPlace = new JsonObject();
        toPlace.addProperty("type", "minecraft:simple_state_provider");

        JsonObject state = new JsonObject();
        state.addProperty("Name", definition.blockId());
        state.add("Properties", new JsonObject());
        toPlace.add("state", state);

        featureConfig.add("to_place", toPlace);
        feature.add("config", featureConfig);
        featureHolder.add("feature", feature);
        featureHolder.add("placement", new JsonArray());
        config.add("feature", featureHolder);

        root.add("config", config);
        return root;
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }

    private record FlowerConfiguredFeatureDefinition(String name, String blockId) {
    }
}
