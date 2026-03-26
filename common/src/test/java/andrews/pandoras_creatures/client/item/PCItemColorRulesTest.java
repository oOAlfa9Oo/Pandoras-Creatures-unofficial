package andrews.pandoras_creatures.client.item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PCItemColorRulesTest {
    @Test
    void addsOpaqueAlphaWithoutChangingRgbChannels() {
        assertEquals(0xFF336699, PCItemColorRules.withOpaqueAlpha(0x00336699));
        assertEquals(0xFFFFFFFF, PCItemColorRules.withOpaqueAlpha(0x00FFFFFF));
    }
}
