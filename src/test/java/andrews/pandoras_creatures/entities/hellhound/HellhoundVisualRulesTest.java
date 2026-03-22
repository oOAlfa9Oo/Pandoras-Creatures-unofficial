package andrews.pandoras_creatures.entities.hellhound;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HellhoundVisualRulesTest {
    @Test
    void normalVariantKeepsBaseFlameAndScale() {
        assertFalse(HellhoundVisualRules.usesSoulFire(HellhoundVariantCatalog.DEFAULT_TYPE));
        assertEquals(10.8D, HellhoundVisualRules.particleY(10.0D, HellhoundVariantCatalog.DEFAULT_TYPE), 1.0E-6D);
        assertFalse(HellhoundVisualRules.showsEyesLayer(HellhoundVariantCatalog.DEFAULT_TYPE));
        assertEquals(1.0F, HellhoundVisualRules.renderScale(HellhoundVariantCatalog.DEFAULT_TYPE));
        assertEquals(0.0F, HellhoundVisualRules.renderYOffset(HellhoundVariantCatalog.DEFAULT_TYPE));
    }

    @Test
    void witherVariantUsesSoulFireEyesAndScale() {
        assertTrue(HellhoundVisualRules.usesSoulFire(HellhoundVariantCatalog.WITHER_TYPE));
        assertEquals(11.0D, HellhoundVisualRules.particleY(10.0D, HellhoundVariantCatalog.WITHER_TYPE), 1.0E-6D);
        assertTrue(HellhoundVisualRules.showsEyesLayer(HellhoundVariantCatalog.WITHER_TYPE));
        assertEquals(1.2F, HellhoundVisualRules.renderScale(HellhoundVariantCatalog.WITHER_TYPE));
        assertEquals(-0.3F, HellhoundVisualRules.renderYOffset(HellhoundVariantCatalog.WITHER_TYPE));
    }
}
