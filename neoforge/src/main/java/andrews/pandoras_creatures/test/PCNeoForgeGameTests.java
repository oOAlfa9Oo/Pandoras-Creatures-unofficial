package andrews.pandoras_creatures.test;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Rotation;
import net.neoforged.neoforge.event.RegisterGameTestsEvent;

public final class PCNeoForgeGameTests {
    private static final Identifier SHARED_TEMPLATE = id("gametest/bufflon_arena");
    private static final int MAX_TICKS = 100;
    private static final int SETUP_TICKS = 0;

    private PCNeoForgeGameTests() {
    }

    public static void register(RegisterGameTestsEvent event) {
        Holder<TestEnvironmentDefinition<?>> defaultEnvironment = event.registerEnvironment(
                id("default"),
                new TestEnvironmentDefinition.AllOf()
        );

        PCGameTestCatalog.definitions().forEach(test -> register(event, defaultEnvironment, test));
    }

    private static void register(
            RegisterGameTestsEvent event,
            Holder<TestEnvironmentDefinition<?>> environment,
            PCGameTestCatalog.TestDefinition test
    ) {
        Identifier id = id(test.path());
        TestData<Holder<TestEnvironmentDefinition<?>>> data = new TestData<>(
                environment,
                SHARED_TEMPLATE,
                MAX_TICKS,
                SETUP_TICKS,
                true,
                Rotation.NONE
        );
        event.registerTest(id, new DirectGameTestInstance(test.path(), data, test.test()));
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, path);
    }
}
