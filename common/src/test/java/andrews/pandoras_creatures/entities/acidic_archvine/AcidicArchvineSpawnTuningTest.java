package andrews.pandoras_creatures.entities.acidic_archvine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcidicArchvineSpawnTuningTest {
    @Test
    void companionChanceScalesWithDifficulty() {
        assertEquals(0.25F, AcidicArchvineSpawnTuning.companionChance(1));
        assertEquals(0.5F, AcidicArchvineSpawnTuning.companionChance(2));
        assertEquals(0.75F, AcidicArchvineSpawnTuning.companionChance(3));
        assertEquals(0.0F, AcidicArchvineSpawnTuning.companionChance(0));
    }

    @Test
    void companionAttemptsScaleWithDifficulty() {
        assertEquals(3, AcidicArchvineSpawnTuning.companionAttempts(1));
        assertEquals(5, AcidicArchvineSpawnTuning.companionAttempts(2));
        assertEquals(7, AcidicArchvineSpawnTuning.companionAttempts(3));
        assertEquals(0, AcidicArchvineSpawnTuning.companionAttempts(0));
    }

    @Test
    void companionOnlyTriggersOnNaturalStyleSpawns() {
        assertTrue(AcidicArchvineSpawnTuning.shouldAttemptCompanion(true, false));
        assertTrue(AcidicArchvineSpawnTuning.shouldAttemptCompanion(false, true));
        assertFalse(AcidicArchvineSpawnTuning.shouldAttemptCompanion(false, false));
    }
}
