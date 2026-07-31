package andrews.pandoras_creatures.test;

import net.minecraftforge.event.RegisterGameTestsEvent;

public final class PCForgeGameTests {
    private PCForgeGameTests() {
    }

    public static void register(RegisterGameTestsEvent event) {
        event.register(PCGameTestCatalog.class);
    }
}
