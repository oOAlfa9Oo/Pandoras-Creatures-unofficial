package andrews.pandoras_creatures.datagen.worldgen;

import andrews.pandoras_creatures.world.biome.PCBiomeSpawnCatalog;
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
    private static final String NEOFORGE_NAMESPACE = "neoforge";
    private static final String FORGE_NAMESPACE = "forge";
    private static final FeatureBiomeModifierDefinition[] FEATURE_DEFINITIONS = new FeatureBiomeModifierDefinition[]{
            new FeatureBiomeModifierDefinition("dhania_feature", List.of("minecraft:swamp"), false, "pandoras_creatures:dhania", "vegetal_decoration"),
            new FeatureBiomeModifierDefinition("hill_bloom_feature", List.of("minecraft:windswept_hills", "minecraft:windswept_gravelly_hills", "minecraft:windswept_forest"), false, "pandoras_creatures:hill_bloom", "vegetal_decoration"),
            new FeatureBiomeModifierDefinition("horse_tail_feature", List.of("minecraft:plains", "minecraft:sunflower_plains"), false, "pandoras_creatures:horse_tail", "vegetal_decoration")
    };

    private final PackOutput.PathProvider neoforgeBiomeModifierPathProvider;
    private final PackOutput.PathProvider forgeBiomeModifierPathProvider;

    public PCBiomeModifierDataProvider(PackOutput neoforgeOutput, PackOutput forgeSharedOutput) {
        this.neoforgeBiomeModifierPathProvider = neoforgeOutput.createPathProvider(PackOutput.Target.DATA_PACK, NEOFORGE_NAMESPACE + "/biome_modifier");
        this.forgeBiomeModifierPathProvider = forgeSharedOutput.createPathProvider(PackOutput.Target.DATA_PACK, FORGE_NAMESPACE + "/biome_modifier");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for (PCBiomeSpawnCatalog.SpawnDefinition definition : PCBiomeSpawnCatalog.definitions()) {
            futures.add(saveSpawnModifier(cachedOutput, neoforgeBiomeModifierPathProvider, NEOFORGE_NAMESPACE, definition));
            futures.add(saveSpawnModifier(cachedOutput, forgeBiomeModifierPathProvider, FORGE_NAMESPACE, definition));
        }
        for (FeatureBiomeModifierDefinition definition : FEATURE_DEFINITIONS) {
            futures.add(saveFeatureModifier(cachedOutput, neoforgeBiomeModifierPathProvider, NEOFORGE_NAMESPACE, definition));
            futures.add(saveFeatureModifier(cachedOutput, forgeBiomeModifierPathProvider, FORGE_NAMESPACE, definition));
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Biome Modifiers";
    }

    private static CompletableFuture<?> saveSpawnModifier(CachedOutput cachedOutput, PackOutput.PathProvider pathProvider,
                                                          String modifierNamespace, PCBiomeSpawnCatalog.SpawnDefinition definition) {
        return DataProvider.saveStable(cachedOutput, createSpawnModifier(modifierNamespace, definition), pathProvider.json(id(definition.name())));
    }

    private static CompletableFuture<?> saveFeatureModifier(CachedOutput cachedOutput, PackOutput.PathProvider pathProvider,
                                                            String modifierNamespace, FeatureBiomeModifierDefinition definition) {
        return DataProvider.saveStable(cachedOutput, createFeatureModifier(modifierNamespace, definition), pathProvider.json(id(definition.name())));
    }

    private static JsonObject createSpawnModifier(String modifierNamespace, PCBiomeSpawnCatalog.SpawnDefinition definition) {
        JsonObject root = new JsonObject();
        root.addProperty("type", modifierNamespace + ":add_spawns");
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

    private static JsonObject createFeatureModifier(String modifierNamespace, FeatureBiomeModifierDefinition definition) {
        JsonObject root = new JsonObject();
        root.addProperty("type", modifierNamespace + ":add_features");
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

    private record FeatureBiomeModifierDefinition(String name, List<String> biomes, boolean collapseSingleBiome,
                                                  String featureId, String step) {
    }
}
