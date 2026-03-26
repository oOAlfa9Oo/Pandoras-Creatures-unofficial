package andrews.pandoras_creatures.entities.hellhound;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HellhoundVariantCatalogTest {
    @Test
    void normalizesUnknownTypesAndDetectsWitherVariant() {
        assertEquals(HellhoundVariantCatalog.DEFAULT_TYPE, HellhoundVariantCatalog.normalizeType(0));
        assertEquals(HellhoundVariantCatalog.DEFAULT_TYPE, HellhoundVariantCatalog.normalizeType(99));
        assertEquals(HellhoundVariantCatalog.WITHER_TYPE, HellhoundVariantCatalog.normalizeType(HellhoundVariantCatalog.WITHER_TYPE));
        assertFalse(HellhoundVariantCatalog.isWitherType(HellhoundVariantCatalog.DEFAULT_TYPE));
        assertTrue(HellhoundVariantCatalog.isWitherType(HellhoundVariantCatalog.WITHER_TYPE));
    }

    @Test
    void createsTextureLocationsPerVariant() {
        assertEquals("textures/entity/hellhound/hellhound_1.png", HellhoundVariantCatalog.texturePathString(0));
        assertEquals("textures/entity/hellhound/hellhound_2.png", HellhoundVariantCatalog.texturePathString(2));
    }
}
