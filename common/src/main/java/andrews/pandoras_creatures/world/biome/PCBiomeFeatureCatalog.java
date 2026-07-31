package andrews.pandoras_creatures.world.biome;

import java.util.List;

public final class PCBiomeFeatureCatalog {
    private static final List<FeatureDefinition> DEFINITIONS = List.of(
            new FeatureDefinition("dhania_feature", List.of("minecraft:swamp"), false, "pandoras_creatures:dhania", "vegetal_decoration"),
            new FeatureDefinition("hill_bloom_feature", List.of("minecraft:windswept_hills", "minecraft:windswept_gravelly_hills", "minecraft:windswept_forest"), false, "pandoras_creatures:hill_bloom", "vegetal_decoration"),
            new FeatureDefinition("horse_tail_feature", List.of("minecraft:plains", "minecraft:sunflower_plains"), false, "pandoras_creatures:horse_tail", "vegetal_decoration")
    );

    private PCBiomeFeatureCatalog() {
    }

    public static List<FeatureDefinition> definitions() {
        return DEFINITIONS;
    }

    public record FeatureDefinition(String name, List<String> biomes, boolean collapseSingleBiome,
                                    String featureId, String step) {
    }
}
