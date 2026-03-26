package andrews.pandoras_creatures.entities.bufflon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BufflonPassengerMotionTest {

    @Test
    void idleCurveUsesTickBasedBounce() {
        assertEquals(0.05F, BufflonPassengerMotion.getVerticalOffset(false, 0, 10.0F, 1.0F), 0.0001F);
        assertEquals(0.057133F, BufflonPassengerMotion.getVerticalOffset(false, 1, 10.0F, 1.0F), 0.0001F);
    }

    @Test
    void movingCurveUsesWalkAnimationInputs() {
        assertEquals(0.08F, BufflonPassengerMotion.getVerticalOffset(true, 10, 5.0F, 0.0F), 0.0001F);
        assertEquals(0.0280005F, BufflonPassengerMotion.getVerticalOffset(true, 10, 0.0F, 1.0F), 0.0001F);
    }
}
