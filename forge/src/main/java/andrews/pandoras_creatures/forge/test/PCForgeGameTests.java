package andrews.pandoras_creatures.forge.test;

import andrews.pandoras_creatures.test.PCGameTestCatalog;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.RegisterEvent;

public final class PCForgeGameTests {
    private PCForgeGameTests() {
    }

    public static void register(BusGroup modEventBus) {
        RegisterEvent.getBus(modEventBus).addListener(PCForgeGameTests::registerFunctions);
    }

    private static void registerFunctions(RegisterEvent event) {
        if (!event.getRegistryKey().equals(Registries.TEST_FUNCTION)) {
            return;
        }

        event.register(Registries.TEST_FUNCTION, helper -> PCGameTestCatalog.definitions().forEach(
                definition -> helper.register(id(definition.path()), definition.test())
        ));
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, path);
    }
}