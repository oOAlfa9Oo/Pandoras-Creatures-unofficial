package andrews.pandoras_creatures.client.renderer.util;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;

public class PCRenderTypes extends RenderStateShard {

    public PCRenderTypes(String name, Runnable setupTask, Runnable clearTask) {
        super(name, setupTask, clearTask);
    }

    public static RenderType getEmissiveEntity(Identifier texture) {
        return RenderType.eyes(texture);
    }
}
