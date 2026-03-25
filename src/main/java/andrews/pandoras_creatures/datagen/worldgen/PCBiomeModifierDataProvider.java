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

public final class PCBiomeModifierDataProvider implements DataProvider {
    private static final SpawnBiomeModifierDefinition[] SPAWN_DEFINITIONS = new SpawnBiomeModifierDefinition[]{
            new SpawnBiomeModifierDefinition("acidic_archvine_crimson_forest_spawns", List.of("minecraft:crimson_forest"), true, "pandoras_creatures:acidic_archvine", 10, 1, 1),
            new SpawnBiomeModifierDefinition("acidic_archvine_jungle_spawns", List.of("#minecraft:is_jungle"), true, "pandoras_creatures:acidic_archvine", 110, 1, 1),
            new SpawnBiomeModifierDefinition("acidic_archvine_nether_wastes_spawns", List.of("minecraft:nether_wastes"), true, "pandoras_creatures:acidic_archvine", 30, 1, 1),
            new SpawnBiomeModifierDefinition("arachnon_spawns", List.of("minecraft:plains", "minecraft:windswept_hills", "minecraft:windswept_gravelly_hills"), false, "pandoras_creatures:arachnon", 20, 1, 1),
            new SpawnBiomeModifierDefinition("bufflon_spawns", List.of("minecraft:snowy_plains", "minecraft:frozen_river", "minecraft:snowy_slopes"), false, "pandoras_creatures:bufflon", 3, 1, 1),
            new SpawnBiomeModifierDefinition("crab_spawns", List.of("minecraft:beach", "minecraft:warm_ocean"), false, "pandoras_creatures:crab", 400, 2, 5),
            new SpawnBiomeModifierDefinition("hellhound_spawns", List.of("minecraft:nether_wastes", "minecraft:soul_sand_valley"), false, "pandoras_creatures:hellhound", 30, 3, 6),
            new SpawnBiomeModifierDefinition("seahorse_ocean_spawns", List.of("minecraft:ocean", "minecraft:lukewarm_ocean", "minecraft:deep_ocean", "minecraft:deep_lukewarm_ocean"), false, "pandoras_creatures:seahorse", 10, 2, 3),
            new SpawnBiomeModifierDefinition("seahorse_warm_spawns", List.of("minecraft:warm_ocean"), true, "pandoras_creatures:seahorse", 25, 3, 6)
    };
    private static final FeatureBiomeModifierDefinition[] FEATURE_DEFINITIONS = new FeatureBiomeModifierDefinition[]{
            new FeatureBiomeModifierDefinition("dhania_feature", List.of("minecraft:swamp"), false, "pandoras_creatures:dhania", "vegetal_decoration"),
            new FeatureBiomeModifierDefinition("hill_bloom_feature", List.of("minecraft:windswept_hills", "minecraft:windswept_gravelly_hills", "minecraft:windswept_forest"), false, "pandoras_creatures:hill_bloom", "vegetal_decoration"),
            new FeatureBiomeModifierDefinition("horse_tail_feature", List.of("minecraft:plains", "minecraft:sunflower_plains"), false, "pandoras_creatures:horse_tail", "vegetal_decoration")
    };

    private final PackOutput.PathProvider biomeModifierPathProvider;

    public PCBiomeModifierDataProvider(PackOutput output) {
        this.biomeModifierPathProvider = output.createPathProvider(PackOutput.Target.DATA_PACK, "neoforge/biome_modifier");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (SpawnBiomeModifierDefinition definition : SPAWN_DEFINITIONS) {
            futures.add(DataProvider.saveStable(cachedOutput, createSpawnModifier(definition), biomeModifierPathProvider.json(id(definition.name()))));
        }
        for (FeatureBiomeModifierDefinition definition : FEATURE_DEFINITIONS) {
            futures.add(DataProvider.saveStable(cachedOutput, createFeatureModifier(definition), biomeModifierPathProvider.json(id(definition.name()))));
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Biome Modifiers";
    }

    private static JsonObject createSpawnModifier(SpawnBiomeModifierDefinition definition) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "neoforge:add_spawns");
        root.add("biomes", createBiomeSelector(definition.biomes(), definition.collapseSingleBiome()));

        JsonObject spawner = new JsonObject();
        spawner.addProperty("type", definition.entityTypeId());
        spawner.addProperty("weight", definition.weight());
        spawner.addProperty("minCount", definition.minCount());
        spawner.addProperty("maxCount", definition.maxCount());

        JsonArray spawners = new JsonArray();
        spawners.add(spawner);
        root.add("spawners", spawners);
        return root;
    }

    private static JsonObject createFeatureModifier(FeatureBiomeModifierDefinition definition) {
        JsonObject root = new JsonObject();
        root.addProperty("type", "neoforge:add_features");
        root.add("biomes", createBiomeSelector(definition.biomes(), definition.collapseSingleBiome()));
        root.addProperty("features", definition.featureId());
        root.addProperty("step", definition.step());
        return root;
    }

    private static com.google.gson.JsonElement createBiomeSelector(List<String> biomes, boolean collapseSingleBiome) {
        if (collapseSingleBiome && biomes.size() == 1) {
            return new com.google.gson.JsonPrimitive(biomes.getFirst());
        }

        JsonArray array = new JsonArray();
        biomes.forEach(array::add);
        return array;
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }

    private record SpawnBiomeModifierDefinition(String name, List<String> biomes, boolean collapseSingleBiome,
                                                String entityTypeId, int weight, int minCount, int maxCount) {
    }

    private record FeatureBiomeModifierDefinition(String name, List<String> biomes, boolean collapseSingleBiome,
                                                  String featureId, String step) {
    }
}
