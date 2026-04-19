package andrews.pandoras_creatures.client.renderer.util;

import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public final class PCRenderTypes {
    public static RenderType getEmissiveEntity(Identifier texture) {
        return RenderTypes.eyes(texture);
    }

    private PCRenderTypes() {
    }
}
