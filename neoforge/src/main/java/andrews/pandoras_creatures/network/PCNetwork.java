package andrews.pandoras_creatures.network;

import andrews.pandoras_creatures.util.Reference;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

/**
 * Network handler for Pandoras Creatures using NeoForge's PayloadRegistrar system.
 * This replaces the old SimpleChannel system from Forge 1.16-1.20.
 */
public class PCNetwork {
    public static final String PROTOCOL_VERSION = "1";

    /**
     * Register all network payloads - called from RegisterPayloadHandlersEvent
     */
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(Reference.MODID)
                .versioned(PROTOCOL_VERSION)
                .optional();

        NeoForgePayloadRegistrar.register(registrar);
    }
}
