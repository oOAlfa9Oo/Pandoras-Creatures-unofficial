package andrews.pandoras_creatures.world.biome;

import java.util.List;

public final class PCBiomeSpawnCatalog {
    private static final List<SpawnDefinition> DEFINITIONS = List.of(
            new SpawnDefinition("acidic_archvine_jungle_spawns", List.of("#minecraft:is_jungle"), true, "pandoras_creatures:acidic_archvine", 140, 1, 1),
            new SpawnDefinition("acidic_archvine_nether_spawns", List.of("#minecraft:is_nether"), true, "pandoras_creatures:acidic_archvine", 55, 1, 1),
            new SpawnDefinition("arachnon_spawns", List.of("minecraft:plains", "minecraft:windswept_hills", "minecraft:windswept_gravelly_hills"), false, "pandoras_creatures:arachnon", 20, 1, 1),
            new SpawnDefinition("bufflon_spawns", List.of("minecraft:snowy_plains", "minecraft:frozen_river", "minecraft:snowy_slopes"), false, "pandoras_creatures:bufflon", 3, 1, 1),
            new SpawnDefinition("crab_spawns", List.of("minecraft:beach", "minecraft:warm_ocean"), false, "pandoras_creatures:crab", 400, 2, 5),
            new SpawnDefinition("hellhound_spawns", List.of("minecraft:nether_wastes", "minecraft:soul_sand_valley"), false, "pandoras_creatures:hellhound", 30, 3, 6),
            new SpawnDefinition("seahorse_ocean_spawns", List.of("minecraft:ocean", "minecraft:lukewarm_ocean", "minecraft:deep_ocean", "minecraft:deep_lukewarm_ocean"), false, "pandoras_creatures:seahorse", 10, 2, 3),
            new SpawnDefinition("seahorse_warm_spawns", List.of("minecraft:warm_ocean"), true, "pandoras_creatures:seahorse", 25, 3, 6)
    );

    private PCBiomeSpawnCatalog() {
    }

    public static List<SpawnDefinition> definitions() {
        return DEFINITIONS;
    }

    public record SpawnDefinition(String name, List<String> biomes, boolean collapseSingleBiome,
                                  String entityTypeId, int weight, int minCount, int maxCount) {
    }
}
