package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.renderer.special.EndTrollBoxSpecialRenderer;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;

public final class PCClientSpecialModelRendererRegistry {
    private PCClientSpecialModelRendererRegistry() {
    }

    public static void registerAll(RegisterSpecialModelRendererEvent event) {
        event.register(EndTrollBoxSpecialRenderer.Unbaked.ID, EndTrollBoxSpecialRenderer.Unbaked.MAP_CODEC);
    }
}
