package andrews.pandoras_creatures.entities.end_troll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EndTrollBehaviorRulesTest {
    @Test
    void transformRequiresCloseTargetWhileNotStanding() {
        assertTrue(EndTrollBehaviorRules.shouldTryTransform(false, true, 149.0D));
        assertFalse(EndTrollBehaviorRules.shouldTryTransform(true, true, 100.0D));
        assertFalse(EndTrollBehaviorRules.shouldTryTransform(false, false, 100.0D));
    }

    @Test
    void screamAndShootRespectCooldownsAndState() {
        assertTrue(EndTrollBehaviorRules.shouldTryScream(true, true, true, true, false, 9.0D, 0));
        assertFalse(EndTrollBehaviorRules.shouldTryScream(true, true, true, true, false, 12.0D, 0));
        assertTrue(EndTrollBehaviorRules.shouldTryShoot(true, true, false, true, 0));
        assertFalse(EndTrollBehaviorRules.shouldTryShoot(true, true, true, true, 0));
    }

    @Test
    void cooldownAndDamageRulesStayStable() {
        assertEquals(0, EndTrollBehaviorRules.tickCooldown(0));
        assertEquals(9, EndTrollBehaviorRules.tickCooldown(10));
        assertEquals(15, EndTrollBehaviorRules.getPunchDamage(false, 3));
        assertEquals(19, EndTrollBehaviorRules.getPunchDamage(true, 5));
    }

    @Test
    void punchAnimationSelectionIsDeterministicPerRoll() {
        assertEquals(EndTrollPunchAnimation.RIGHT, EndTrollBehaviorRules.selectPunchAnimation(0));
        assertEquals(EndTrollPunchAnimation.LEFT, EndTrollBehaviorRules.selectPunchAnimation(1));
        assertEquals(EndTrollPunchAnimation.DOUBLE, EndTrollBehaviorRules.selectPunchAnimation(2));
        assertEquals(EndTrollPunchAnimation.RIGHT, EndTrollBehaviorRules.selectPunchAnimation(3));
    }

    @Test
    void worldInteractionRulesStayStable() {
        assertTrue(EndTrollBehaviorRules.shouldBreakChorusThisTick(10));
        assertFalse(EndTrollBehaviorRules.shouldBreakChorusThisTick(11));
        assertTrue(EndTrollBehaviorRules.shouldLaunchFallingBlock(false, 4));
        assertFalse(EndTrollBehaviorRules.shouldLaunchFallingBlock(true, 4));
        assertTrue(EndTrollBehaviorRules.shouldDropDestroyedBlock(true, 1));
        assertTrue(EndTrollBehaviorRules.shouldDropDestroyedBlock(false, 3));
        assertFalse(EndTrollBehaviorRules.shouldDropDestroyedBlock(false, 2));
    }
}
