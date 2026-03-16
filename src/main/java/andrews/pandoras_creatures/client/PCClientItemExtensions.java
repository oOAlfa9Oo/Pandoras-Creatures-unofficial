package andrews.pandoras_creatures.client;

import andrews.pandoras_creatures.client.renderer.tile.PCItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

@OnlyIn(Dist.CLIENT)
public class PCClientItemExtensions implements IClientItemExtensions {
    public static final PCClientItemExtensions INSTANCE = new PCClientItemExtensions();

    private PCClientItemExtensions() {}

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return PCItemRenderer.getInstance();
    }
}
