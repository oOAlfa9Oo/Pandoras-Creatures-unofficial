package andrews.pandoras_creatures.test;

import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.Component;

// 1.21.5 cambio GameTestHelper#assertTrue/assertFalse/assertValueEqual de mensaje String a
// Component; este wrapper deja los call-sites existentes (String) intactos en vez de tocar
// decenas de asserts uno por uno.
public final class PCGameTestAssertions {
    private PCGameTestAssertions() {
    }

    public static void assertTrue(GameTestHelper helper, boolean condition, String message) {
        helper.assertTrue(condition, Component.literal(message));
    }

    public static void assertFalse(GameTestHelper helper, boolean condition, String message) {
        helper.assertFalse(condition, Component.literal(message));
    }

    public static <N> void assertValueEqual(GameTestHelper helper, N actual, N expected, String message) {
        helper.assertValueEqual(actual, expected, Component.literal(message));
    }
}
