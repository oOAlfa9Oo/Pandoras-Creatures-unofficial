package andrews.pandoras_creatures.entities.end_troll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EndTrollProjectileRulesTest {
    @Test
    void projectileKindSelectionIsDeterministicPerRoll() {
        assertEquals(EndTrollProjectileKind.POISON, EndTrollProjectileRules.selectProjectileKind(0));
        assertEquals(EndTrollProjectileKind.WITHER, EndTrollProjectileRules.selectProjectileKind(1));
        assertEquals(EndTrollProjectileKind.DAMAGE, EndTrollProjectileRules.selectProjectileKind(2));
        assertEquals(EndTrollProjectileKind.POISON, EndTrollProjectileRules.selectProjectileKind(3));
    }

    @Test
    void launchVectorMatchesExpectedAngles() {
        EndTrollProjectileLaunch forward = EndTrollProjectileRules.createLaunchVector(0.0F, 0.0F);
        assertEquals(0.0D, forward.x(), 1.0E-9);
        assertEquals(0.0D, forward.y(), 1.0E-9);
        assertEquals(1.0D, forward.z(), 1.0E-9);

        EndTrollProjectileLaunch left = EndTrollProjectileRules.createLaunchVector(90.0F, 0.0F);
        assertEquals(-1.0D, left.x(), 1.0E-9);
        assertEquals(0.0D, left.y(), 1.0E-9);
        assertEquals(0.0D, left.z(), 1.0E-9);
    }

    @Test
    void stepCountUsesExpectedProgression() {
        assertEquals(10, EndTrollProjectileRules.computeStepCount(0));
        assertEquals(30, EndTrollProjectileRules.computeStepCount(2));
        assertEquals(10, EndTrollProjectileRules.computeStepCount(-1));
    }
}
