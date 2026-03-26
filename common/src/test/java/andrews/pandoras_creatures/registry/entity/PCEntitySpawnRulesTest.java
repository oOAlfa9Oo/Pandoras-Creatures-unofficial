package andrews.pandoras_creatures.registry.entity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCEntitySpawnRulesTest {
    @Test
    void seahorseRequiresWater() {
        assertTrue(PCEntitySpawnRules.canSpawnSeahorse(true));
        assertFalse(PCEntitySpawnRules.canSpawnSeahorse(false));
    }

    @Test
    void crabSpawnRequiresAllowedHeightAndSurface() {
        assertTrue(PCEntitySpawnRules.canSpawnCrab(true, false, 60, true, false));
        assertFalse(PCEntitySpawnRules.canSpawnCrab(true, false, 71, true, false));
        assertFalse(PCEntitySpawnRules.canSpawnCrab(false, false, 60, false, false));
    }

    @Test
    void arachnonSpawnRequiresDarknessAndHostileDifficulty() {
        assertTrue(PCEntitySpawnRules.canSpawnArachnon(true, 7));
        assertFalse(PCEntitySpawnRules.canSpawnArachnon(true, 8));
        assertFalse(PCEntitySpawnRules.canSpawnArachnon(false, 0));
    }

    @Test
    void acidicArchvineRequiresAllConditions() {
        assertTrue(PCEntitySpawnRules.canSpawnAcidicArchvine(
                true,
                true,
                false,
                70,
                true,
                true,
                true,
                true));

        assertFalse(PCEntitySpawnRules.canSpawnAcidicArchvine(
                true,
                true,
                false,
                50,
                true,
                true,
                true,
                true));

        assertFalse(PCEntitySpawnRules.canSpawnAcidicArchvine(
                true,
                false,
                true,
                60,
                true,
                true,
                false,
                true));
    }

    @Test
    void bufflonSpawnRequiresBrightGrass() {
        assertTrue(PCEntitySpawnRules.canSpawnBufflon(9, true));
        assertFalse(PCEntitySpawnRules.canSpawnBufflon(8, true));
        assertFalse(PCEntitySpawnRules.canSpawnBufflon(10, false));
    }
}
