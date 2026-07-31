package andrews.pandoras_creatures.world.structures.end_prison;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EndPrisonBehaviorRulesTest {
    @Test
    void officialShipContractStaysStable() {
        assertEquals("minecraft:end_city/ship", EndPrisonBehaviorRules.VANILLA_SHIP_TEMPLATE);
        assertEquals(3, EndPrisonBehaviorRules.SHIP_CHANCE_BOUND);
        assertTrue(EndPrisonBehaviorRules.shouldAddShip(0));
        assertFalse(EndPrisonBehaviorRules.shouldAddShip(1));
        assertFalse(EndPrisonBehaviorRules.shouldAddShip(2));
    }

    @Test
    void officialEndTrollStateStaysStableAcrossStructureRotations() {
        assertEquals(18.27531668920085D, EndPrisonBehaviorRules.END_TROLL_X);
        assertEquals(11.0D, EndPrisonBehaviorRules.END_TROLL_Y);
        assertEquals(20.87783590069225D, EndPrisonBehaviorRules.END_TROLL_Z);
        assertEquals(200.0F, EndPrisonBehaviorRules.END_TROLL_HEALTH);
        assertEquals(-87.47659F, EndPrisonBehaviorRules.rotatedEndTrollYaw("NONE"), 0.0001F);
        assertEquals(2.523407F, EndPrisonBehaviorRules.rotatedEndTrollYaw("CLOCKWISE_90"), 0.0001F);
        assertEquals(92.52341F, EndPrisonBehaviorRules.rotatedEndTrollYaw("CLOCKWISE_180"), 0.0001F);
        assertEquals(182.5234F, EndPrisonBehaviorRules.rotatedEndTrollYaw("COUNTERCLOCKWISE_90"), 0.0001F);
    }
}
