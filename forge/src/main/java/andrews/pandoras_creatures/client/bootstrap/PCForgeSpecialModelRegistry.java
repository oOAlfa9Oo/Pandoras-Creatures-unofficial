package andrews.pandoras_creatures.forge.client.bootstrap;

import andrews.pandoras_creatures.client.renderer.tile.PCItemRenderer;
import andrews.pandoras_creatures.util.Reference;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;

import java.lang.reflect.Field;

public final class PCForgeSpecialModelRegistry {
    private static final Identifier END_TROLL_BOX = Identifier.fromNamespaceAndPath(Reference.MODID, "end_troll_box");

    private PCForgeSpecialModelRegistry() {
    }

    public static void registerAll() {
        specialModelMapper().put(END_TROLL_BOX, PCItemRenderer.Unbaked.MAP_CODEC);
    }

    @SuppressWarnings("unchecked")
    private static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends SpecialModelRenderer.Unbaked<?>>> specialModelMapper() {
        try {
            Field field = SpecialModelRenderers.class.getDeclaredField("ID_MAPPER");
            field.setAccessible(true);
            return (ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends SpecialModelRenderer.Unbaked<?>>>) field.get(null);
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Unable to register Forge special model renderer for " + END_TROLL_BOX, exception);
        }
    }
}
