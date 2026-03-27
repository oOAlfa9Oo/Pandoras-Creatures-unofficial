package andrews.pandoras_creatures.entities.crab;

import andrews.pandoras_creatures.lang.PCLanguageKeys;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CrabVariantCatalogTest {
    @Test
    void normalizesAndMapsVariantValues() {
        assertEquals(CrabVariantCatalog.DEFAULT_TYPE, CrabVariantCatalog.normalizeType(0));
        assertEquals(2, CrabVariantCatalog.normalizeType(2));
        assertEquals(1, CrabVariantCatalog.randomTypeId(0));
        assertEquals(2, CrabVariantCatalog.randomTypeId(1));
    }

    @Test
    void exposesTooltipAndTextureData() {
        assertEquals(PCLanguageKeys.chat("crabBucketTooltip.tropical"), CrabVariantCatalog.tooltipKey(2));
        assertTrue(CrabVariantCatalog.texturePath(2).contains("crab_2"));
    }
}
