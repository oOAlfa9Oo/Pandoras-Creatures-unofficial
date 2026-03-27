package andrews.pandoras_creatures.entities.seahorse;

import andrews.pandoras_creatures.lang.PCLanguageKeys;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeahorseVariantCatalogTest {
    @Test
    void normalizesOutOfRangeValues() {
        assertEquals(SeahorseVariantCatalog.DEFAULT_TYPE, SeahorseVariantCatalog.normalizeType(0));
        assertEquals(SeahorseVariantCatalog.DEFAULT_TYPE, SeahorseVariantCatalog.normalizeType(99));
        assertEquals(SeahorseVariantCatalog.DEFAULT_SIZE, SeahorseVariantCatalog.normalizeSize(0));
        assertEquals(SeahorseVariantCatalog.DEFAULT_SIZE, SeahorseVariantCatalog.normalizeSize(99));
    }

    @Test
    void exposesTooltipKeysForVariantAndSize() {
        assertEquals(PCLanguageKeys.chat("seahorseBucketTooltip.chromatic"), SeahorseVariantCatalog.variantTooltipKey(5));
        assertEquals(PCLanguageKeys.chat("seahorseBucketTooltip.big"), SeahorseVariantCatalog.sizeTooltipKey(4));
    }

    @Test
    void mapsRandomRollsIntoValidRanges() {
        assertEquals(1, SeahorseVariantCatalog.randomTypeId(0));
        assertEquals(10, SeahorseVariantCatalog.randomTypeId(9));
        assertEquals(1, SeahorseVariantCatalog.randomSizeId(0));
        assertEquals(5, SeahorseVariantCatalog.randomSizeId(4));
    }
}
