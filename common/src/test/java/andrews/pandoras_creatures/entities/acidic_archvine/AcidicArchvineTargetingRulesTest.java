package andrews.pandoras_creatures.entities.acidic_archvine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AcidicArchvineTargetingRulesTest {
    @Test
    void appliesDistanceThresholds() {
        assertTrue(AcidicArchvineTargetingRules.isProtectedByPlantHat(true));
        assertTrue(AcidicArchvineTargetingRules.hasValidTarget(true, true, false, false));
        assertFalse(AcidicArchvineTargetingRules.hasValidTarget(true, true, true, false));
        assertFalse(AcidicArchvineTargetingRules.hasValidTarget(true, true, false, true));
        assertFalse(AcidicArchvineTargetingRules.hasValidTarget(true, false, false, false));
        assertTrue(AcidicArchvineTargetingRules.isWithinFollowDistance(9.0D, 3.0D));
        assertFalse(AcidicArchvineTargetingRules.isWithinFollowDistance(10.0D, 3.0D));
        assertTrue(AcidicArchvineTargetingRules.canHoldTarget(1.5D));
        assertFalse(AcidicArchvineTargetingRules.canHoldTarget(1.51D));
        assertTrue(AcidicArchvineTargetingRules.shouldPullTarget(1.01D));
        assertFalse(AcidicArchvineTargetingRules.shouldPullTarget(1.0D));
        assertTrue(AcidicArchvineTargetingRules.shouldTeleportTarget(0.8D));
        assertFalse(AcidicArchvineTargetingRules.shouldTeleportTarget(0.55D));
    }

    @Test
    void pullMultiplierGetsWeakerWithDistance() {
        double near = AcidicArchvineTargetingRules.calculatePullMultiplier(1.1D);
        double far = AcidicArchvineTargetingRules.calculatePullMultiplier(4.0D);

        assertTrue(near > 0.0D);
        assertTrue(far > 0.0D);
        assertTrue(near > far);
    }
}
