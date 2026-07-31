package andrews.pandoras_creatures.world.biome;

import andrews.pandoras_creatures.entities.arachnon.ArachnonSpawnTuning;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCBiomeSpawnCatalogTest {
    @Test
    void arachnonUsesOfficialMountainAndPlainsBiomes() {
        PCBiomeSpawnCatalog.SpawnDefinition definition = PCBiomeSpawnCatalog.definitions().stream()
                .filter(candidate -> "pandoras_creatures:arachnon".equals(candidate.entityTypeId()))
                .findFirst()
                .orElse(null);

        assertNotNull(definition);
        assertEquals(List.of("minecraft:plains", "minecraft:windswept_hills", "minecraft:windswept_gravelly_hills"),
                definition.biomes());
        assertTrue(!definition.collapseSingleBiome());
    }

    @Test
    void arachnonUsesUpdatedWeightAndSmallSpawnGroup() {
        PCBiomeSpawnCatalog.SpawnDefinition definition = PCBiomeSpawnCatalog.definitions().stream()
                .filter(candidate -> "pandoras_creatures:arachnon".equals(candidate.entityTypeId()))
                .findFirst()
                .orElse(null);

        assertNotNull(definition);
        assertEquals(ArachnonSpawnTuning.overworldSpawnWeight(), definition.weight());
        assertEquals(ArachnonSpawnTuning.minSpawnGroup(), definition.minCount());
        assertEquals(ArachnonSpawnTuning.maxSpawnGroup(), definition.maxCount());
    }
}
