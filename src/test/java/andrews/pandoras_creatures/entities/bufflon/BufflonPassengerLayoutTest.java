package andrews.pandoras_creatures.entities.bufflon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BufflonPassengerLayoutTest {

    @Test
    void firstPassengerGetsUnsaddledAdjustment() {
        BufflonPassengerOffset offset = BufflonPassengerLayout.getOffset(0, false, false, 0.1F);

        assertEquals(0.95F, offset.x());
        assertEquals(2.12F, offset.y(), 0.0001F);
        assertFalse(offset.lockYaw());
    }

    @Test
    void rearPassengerUsesMovingBounceMultiplier() {
        BufflonPassengerOffset offset = BufflonPassengerLayout.getOffset(1, true, true, 0.5F);

        assertEquals(-0.9F, offset.x());
        assertEquals(1.4F, offset.y(), 0.0001F);
        assertTrue(offset.lockYaw());
    }

    @Test
    void outOfRangePassengerDefaultsToOrigin() {
        BufflonPassengerOffset offset = BufflonPassengerLayout.getOffset(99, true, false, 0.5F);

        assertEquals(0.0F, offset.x());
        assertEquals(0.0F, offset.y());
        assertFalse(offset.lockYaw());
    }
}
