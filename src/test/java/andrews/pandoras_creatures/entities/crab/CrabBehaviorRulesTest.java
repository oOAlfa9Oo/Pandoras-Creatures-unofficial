package andrews.pandoras_creatures.entities.crab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CrabBehaviorRulesTest {
    @Test
    void determinesUnderwaterAndPartyState() {
        assertTrue(CrabBehaviorRules.shouldBeUnderwater(true));
        assertFalse(CrabBehaviorRules.shouldBeUnderwater(false));
        assertTrue(CrabBehaviorRules.shouldKeepPartying(true, true, true));
        assertFalse(CrabBehaviorRules.shouldKeepPartying(true, false, true));
    }

    @Test
    void detectsHatName() {
        assertTrue(CrabBehaviorRules.showsHat("fredrick"));
        assertFalse(CrabBehaviorRules.showsHat("other"));
    }
}
