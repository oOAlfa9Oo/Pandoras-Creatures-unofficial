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
        assertTrue(EndTrollBehaviorRules.shouldTryScream(true, true, false, true, true, false, 9.0D, 0));
        assertFalse(EndTrollBehaviorRules.shouldTryScream(true, true, true, true, true, false, 9.0D, 0));
        assertFalse(EndTrollBehaviorRules.shouldTryScream(true, true, false, true, true, false, 12.0D, 0));
        assertTrue(EndTrollBehaviorRules.shouldTryShoot(true, true, false, true, 399.0D, 0));
        assertFalse(EndTrollBehaviorRules.shouldTryShoot(true, true, false, true, 625.0D, 0));
        assertFalse(EndTrollBehaviorRules.shouldTryShoot(true, true, true, true, 399.0D, 0));
    }

    @Test
    void combatTargetValidationAndMeleeContinuationStayStable() {
        assertTrue(EndTrollBehaviorRules.isHostileDifficulty(true));
        assertFalse(EndTrollBehaviorRules.isHostileDifficulty(false));
        assertTrue(EndTrollBehaviorRules.hasValidCombatTarget(true, true, false));
        assertFalse(EndTrollBehaviorRules.hasValidCombatTarget(true, true, true));
        assertFalse(EndTrollBehaviorRules.hasValidCombatTarget(true, false, false));
        assertFalse(EndTrollBehaviorRules.hasValidCombatTarget(false, true, false));

        assertTrue(EndTrollBehaviorRules.shouldContinueMeleeAttack(true, true, false, true, true, true, false));
        assertTrue(EndTrollBehaviorRules.shouldContinueMeleeAttack(true, true, false, true, true, false, true));
        assertTrue(EndTrollBehaviorRules.shouldContinueMeleeAttack(true, true, true, true, true, false, false));
        assertFalse(EndTrollBehaviorRules.shouldContinueMeleeAttack(true, true, false, true, true, false, false));
        assertFalse(EndTrollBehaviorRules.shouldContinueMeleeAttack(false, true, false, false, true, true, true));
        assertTrue(EndTrollBehaviorRules.shouldAbortCombat(false, true, true));
        assertTrue(EndTrollBehaviorRules.shouldAbortCombat(true, false, true));
        assertFalse(EndTrollBehaviorRules.shouldAbortCombat(true, true, true));
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
