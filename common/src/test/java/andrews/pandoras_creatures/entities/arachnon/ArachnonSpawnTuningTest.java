package andrews.pandoras_creatures.entities.arachnon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArachnonSpawnTuningTest {
    @Test
    void overworldSpawnWeightStaysInExpectedRange() {
        assertEquals(20, ArachnonSpawnTuning.overworldSpawnWeight());
    }

    @Test
    void spawnGroupAllowsSmallNaturalPacks() {
        assertEquals(1, ArachnonSpawnTuning.minSpawnGroup());
        assertEquals(1, ArachnonSpawnTuning.maxSpawnGroup());
        assertTrue(ArachnonSpawnTuning.maxSpawnGroup() >= ArachnonSpawnTuning.minSpawnGroup());
    }
}
