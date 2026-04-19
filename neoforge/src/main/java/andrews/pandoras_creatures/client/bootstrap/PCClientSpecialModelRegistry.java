package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.renderer.tile.PCItemRenderer;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;

public final class PCClientSpecialModelRegistry {
    private static final Identifier END_TROLL_BOX = Identifier.fromNamespaceAndPath(Reference.MODID, "end_troll_box");

    private PCClientSpecialModelRegistry() {
    }

    public static void registerAll(RegisterSpecialModelRendererEvent event) {
        event.register(END_TROLL_BOX, PCItemRenderer.Unbaked.MAP_CODEC);
    }
}
