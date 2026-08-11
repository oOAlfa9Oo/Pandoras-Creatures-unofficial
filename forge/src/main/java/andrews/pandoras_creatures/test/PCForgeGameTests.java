package andrews.pandoras_creatures.test;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;

// 1.21.5 elimino el sistema de GameTest por anotaciones; los tests ahora se registran en
// Registries.TEST_FUNCTION igual que cualquier otro registro vanilla, asi que Forge los expone
// via el RegisterEvent generico (mismo patron que PCForgeItems/PCForgeBlocks).
public final class PCForgeGameTests {
    private PCForgeGameTests() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeGameTests::registerFunctions);
    }

    private static void registerFunctions(RegisterEvent event) {
        if (!event.getRegistryKey().equals(Registries.TEST_FUNCTION)) {
            return;
        }

        event.register(Registries.TEST_FUNCTION, helper -> PCGameTestCatalog.definitions().forEach(
                definition -> helper.register(id(definition.path()), definition.test())
        ));
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }
}
