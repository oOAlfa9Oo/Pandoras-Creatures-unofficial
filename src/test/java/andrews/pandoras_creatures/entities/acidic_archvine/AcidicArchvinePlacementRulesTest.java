package andrews.pandoras_creatures.entities.acidic_archvine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcidicArchvinePlacementRulesTest {
    @Test
    void resolvesBiomeTypes() {
        assertEquals(AcidicArchvinePlacementRules.DEFAULT_ARCHVINE_TYPE,
                AcidicArchvinePlacementRules.resolveBiomeType("minecraft:jungle"));
        assertEquals(AcidicArchvinePlacementRules.WARPED_ARCHVINE_TYPE,
                AcidicArchvinePlacementRules.resolveBiomeType("minecraft:warped_forest"));
        assertEquals(AcidicArchvinePlacementRules.CRIMSON_ARCHVINE_TYPE,
                AcidicArchvinePlacementRules.resolveBiomeType("minecraft:crimson_forest"));
    }

    @Test
    void resolvesSpawnYOffsetFromCeilingPlacement() {
        assertEquals(-0.5D, AcidicArchvinePlacementRules.resolveSpawnYOffset(true, false));
        assertEquals(0.5D, AcidicArchvinePlacementRules.resolveSpawnYOffset(false, true));
        assertTrue(Double.isNaN(AcidicArchvinePlacementRules.resolveSpawnYOffset(false, false)));
    }

    @Test
    void locksVerticalMotionOnlyWhenAnchoredAbove() {
        assertTrue(AcidicArchvinePlacementRules.shouldLockVerticalMotion(true));
        assertFalse(AcidicArchvinePlacementRules.shouldLockVerticalMotion(false));
    }
}
