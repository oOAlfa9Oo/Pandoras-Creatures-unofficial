package andrews.pandoras_creatures.test;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.Holder;
import net.minecraft.gametest.framework.TestData;
import net.minecraft.gametest.framework.TestEnvironmentDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.neoforged.neoforge.event.RegisterGameTestsEvent;

// 1.21.5 elimino el sistema de GameTest por anotaciones (@GameTest/@GameTestGenerator); ahora los
// tests se registran en Registries.TEST_FUNCTION/TEST_INSTANCE. NeoForge expone un evento propio
// (RegisterGameTestsEvent) para esto.
public final class PCNeoForgeGameTests {
    private static final ResourceLocation SHARED_TEMPLATE = id("gametest/bufflon_arena");
    private static final int MAX_TICKS = 100;
    private static final int SETUP_TICKS = 0;

    private PCNeoForgeGameTests() {
    }

    public static void register(RegisterGameTestsEvent event) {
        Holder<TestEnvironmentDefinition> defaultEnvironment = event.registerEnvironment(
                id("default"),
                new TestEnvironmentDefinition.AllOf()
        );

        PCGameTestCatalog.definitions().forEach(test -> register(event, defaultEnvironment, test));
    }

    private static void register(
            RegisterGameTestsEvent event,
            Holder<TestEnvironmentDefinition> environment,
            PCGameTestCatalog.TestDefinition test
    ) {
        ResourceLocation id = id(test.path());
        TestData<Holder<TestEnvironmentDefinition>> data = new TestData<>(
                environment,
                SHARED_TEMPLATE,
                MAX_TICKS,
                SETUP_TICKS,
                true,
                Rotation.NONE
        );
        event.registerTest(id, new DirectGameTestInstance(test.path(), data, test.test()));
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }
}
