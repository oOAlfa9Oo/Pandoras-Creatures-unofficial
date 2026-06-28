package andrews.pandoras_creatures.registry.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PCEntitySpawnRulesTest {

    // Constantes reales tomadas de PCEntitySpawnRules (fijadas, no supuestas):
    // beach Y [56,70], warm ocean Y [30,60], arachnon brightness <= 7,
    // bufflon brightness >= 9, jungle archvine Y >= 62, nether archvine Y >= 38.

    @Test
    void seahorseRequiresWater() {
        assertTrue(PCEntitySpawnRules.canSpawnSeahorse(true));
        assertFalse(PCEntitySpawnRules.canSpawnSeahorse(false));
    }

    @Test
    void crabBeachHeightRespectsBothEdges() {
        boolean inBeach = true, inWarmOcean = false, sand = true, grass = false, airOrWater = true;
        // Borde inferior 56 inclusive
        assertFalse(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, 55, sand, grass, airOrWater));
        assertTrue(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, 56, sand, grass, airOrWater));
        // Borde superior 70 inclusive
        assertTrue(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, 70, sand, grass, airOrWater));
        assertFalse(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, 71, sand, grass, airOrWater));
    }

    @Test
    void crabWarmOceanHeightRespectsBothEdges() {
        boolean inBeach = false, inWarmOcean = true, sand = true, grass = false, airOrWater = true;
        // Borde inferior 30 inclusive
        assertFalse(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, 29, sand, grass, airOrWater));
        assertTrue(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, 30, sand, grass, airOrWater));
        // Borde superior 60 inclusive
        assertTrue(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, 60, sand, grass, airOrWater));
        assertFalse(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, 61, sand, grass, airOrWater));
    }

    @Test
    void crabRequiresSandOrGrassAndSpawnableBlock() {
        boolean inBeach = true, inWarmOcean = false;
        int validY = 60;
        // Superficie: arena o pasto basta; ninguno falla
        assertTrue(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, validY, true, false, true));
        assertTrue(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, validY, false, true, true));
        assertFalse(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, validY, false, false, true));
        // Bloque de aparición debe ser aire o agua
        assertFalse(PCEntitySpawnRules.canSpawnCrab(inBeach, inWarmOcean, validY, true, false, false));
    }

    @Test
    void arachnonSpawnRespectsBrightnessEdgeAndDifficulty() {
        assertTrue(PCEntitySpawnRules.canSpawnArachnon(true, 7));   // borde superior permitido
        assertFalse(PCEntitySpawnRules.canSpawnArachnon(true, 8));  // un paso por encima
        assertFalse(PCEntitySpawnRules.canSpawnArachnon(false, 0)); // dificultad no hostil
    }

    @Test
    void bufflonSpawnRespectsBrightnessEdgeAndSurface() {
        assertTrue(PCEntitySpawnRules.canSpawnBufflon(9, true));    // borde inferior permitido
        assertFalse(PCEntitySpawnRules.canSpawnBufflon(8, true));   // un paso por debajo
        assertFalse(PCEntitySpawnRules.canSpawnBufflon(10, false)); // sin pasto debajo
    }

    @Test
    void acidicArchvineJungleHeightRespectsExactEdge() {
        boolean hostile = true, jungle = true, nether = false;
        boolean atAir = true, aboveAir = true, ceiling = true, shaft = true;
        // Frontera jungla Y >= 62
        assertFalse(PCEntitySpawnRules.canSpawnAcidicArchvine(hostile, jungle, nether, 61, atAir, aboveAir, ceiling, shaft));
        assertTrue(PCEntitySpawnRules.canSpawnAcidicArchvine(hostile, jungle, nether, 62, atAir, aboveAir, ceiling, shaft));
    }

    @Test
    void acidicArchvineNetherHeightRespectsExactEdge() {
        boolean hostile = true, jungle = false, nether = true;
        boolean atAir = true, aboveAir = true, ceiling = true, shaft = true;
        // Frontera Nether Y >= 38 (rama antes sin testear)
        assertFalse(PCEntitySpawnRules.canSpawnAcidicArchvine(hostile, jungle, nether, 37, atAir, aboveAir, ceiling, shaft));
        assertTrue(PCEntitySpawnRules.canSpawnAcidicArchvine(hostile, jungle, nether, 38, atAir, aboveAir, ceiling, shaft));
    }

    @Test
    void acidicArchvineRequiresEveryStructuralCondition() {
        boolean jungle = true, nether = false;
        int validY = 70;
        // Todas verdaderas: aparece
        assertTrue(PCEntitySpawnRules.canSpawnAcidicArchvine(true, jungle, nether, validY, true, true, true, true));
        // Cada condición estructural en false debe bloquear
        assertFalse(PCEntitySpawnRules.canSpawnAcidicArchvine(false, jungle, nether, validY, true, true, true, true));
        assertFalse(PCEntitySpawnRules.canSpawnAcidicArchvine(true, jungle, nether, validY, false, true, true, true));
        assertFalse(PCEntitySpawnRules.canSpawnAcidicArchvine(true, jungle, nether, validY, true, false, true, true));
        assertFalse(PCEntitySpawnRules.canSpawnAcidicArchvine(true, jungle, nether, validY, true, true, false, true));
        assertFalse(PCEntitySpawnRules.canSpawnAcidicArchvine(true, jungle, nether, validY, true, true, true, false));
    }
}
