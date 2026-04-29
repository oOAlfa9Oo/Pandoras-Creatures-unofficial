package andrews.pandoras_creatures.world.biome;

import andrews.pandoras_creatures.entities.arachnon.ArachnonSpawnTuning;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCBiomeSpawnCatalogTest {
    @Test
    void arachnonUsesFullOverworldBiomeTag() {
        PCBiomeSpawnCatalog.SpawnDefinition definition = PCBiomeSpawnCatalog.definitions().stream()
                .filter(candidate -> "pandoras_creatures:arachnon".equals(candidate.entityTypeId()))
                .findFirst()
                .orElse(null);

        assertNotNull(definition);
        assertEquals(1, definition.biomes().size());
        assertEquals("#minecraft:is_overworld", definition.biomes().get(0));
        assertTrue(definition.collapseSingleBiome());
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
