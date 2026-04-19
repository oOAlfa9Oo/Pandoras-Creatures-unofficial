package andrews.pandoras_creatures.client.bootstrap;

import andrews.pandoras_creatures.client.renderer.tile.PCItemRenderer;
import andrews.pandoras_creatures.mixin.client.SpecialModelRenderersAccessor;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.Identifier;

public final class PCFabricSpecialModelRegistry {
    private static final Identifier END_TROLL_BOX = Identifier.fromNamespaceAndPath(Reference.MODID, "end_troll_box");

    private PCFabricSpecialModelRegistry() {
    }

    public static void registerAll() {
        SpecialModelRenderersAccessor.pandorasCreatures$getIdMapper().put(END_TROLL_BOX, PCItemRenderer.Unbaked.MAP_CODEC);
    }
}
