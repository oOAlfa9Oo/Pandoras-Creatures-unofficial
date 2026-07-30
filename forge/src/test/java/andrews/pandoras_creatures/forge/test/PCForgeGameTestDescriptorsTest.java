package andrews.pandoras_creatures.forge.test;

import andrews.pandoras_creatures.test.PCGameTestCatalog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PCForgeGameTestDescriptorsTest {
    @Test
    void everySharedTestHasAForgeDescriptor() {
        ClassLoader classLoader = PCForgeGameTestDescriptorsTest.class.getClassLoader();

        PCGameTestCatalog.definitions().forEach(definition -> {
            String resource = "data/pandoras_creatures/test_instance/" + definition.path() + ".json";
            assertNotNull(classLoader.getResource(resource), () -> "Missing Forge GameTest descriptor: " + resource);
        });
    }
}
