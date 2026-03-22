package andrews.pandoras_creatures.entities.hellhound;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HellhoundCombatRulesTest {
    @Test
    void normalVariantUsesBaseDamageAndNoWitherDrops() {
        int damage = HellhoundCombatRules.attackDamageFromRoll(HellhoundVariantCatalog.DEFAULT_TYPE, 2);

        assertTrue(damage >= 2 && damage <= 4);
        assertFalse(HellhoundCombatRules.appliesWither(HellhoundVariantCatalog.DEFAULT_TYPE));
        assertEquals(0, HellhoundCombatRules.witherDurationTicks(HellhoundVariantCatalog.DEFAULT_TYPE));
        assertEquals(0, HellhoundCombatRules.coalDropCountFromRoll(HellhoundVariantCatalog.DEFAULT_TYPE, 3));
    }

    @Test
    void witherVariantUsesBoostedDamageAndCoalDrops() {
        int damage = HellhoundCombatRules.attackDamageFromRoll(HellhoundVariantCatalog.WITHER_TYPE, 4);
        int coalDropCount = HellhoundCombatRules.coalDropCountFromRoll(HellhoundVariantCatalog.WITHER_TYPE, 3);

        assertTrue(damage >= 4 && damage <= 8);
        assertTrue(HellhoundCombatRules.appliesWither(HellhoundVariantCatalog.WITHER_TYPE));
        assertEquals(60, HellhoundCombatRules.witherDurationTicks(HellhoundVariantCatalog.WITHER_TYPE));
        assertTrue(coalDropCount >= 1 && coalDropCount <= 4);
    }
}
