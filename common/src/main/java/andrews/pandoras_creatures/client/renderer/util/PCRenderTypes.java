package andrews.pandoras_creatures.client.renderer.util;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class PCRenderTypes extends RenderStateShard {

    public PCRenderTypes(String name, Runnable setupTask, Runnable clearTask) {
        super(name, setupTask, clearTask);
    }

    public static RenderType getEmissiveEntity(ResourceLocation texture) {
        return RenderType.eyes(texture);
    }
}
