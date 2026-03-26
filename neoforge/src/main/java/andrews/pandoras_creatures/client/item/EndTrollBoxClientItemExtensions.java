package andrews.pandoras_creatures.client.item;

import andrews.pandoras_creatures.client.renderer.tile.PCItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

@OnlyIn(Dist.CLIENT)
public final class EndTrollBoxClientItemExtensions implements IClientItemExtensions {
    public static final EndTrollBoxClientItemExtensions INSTANCE = new EndTrollBoxClientItemExtensions();

    private EndTrollBoxClientItemExtensions() {
    }

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return PCItemRenderer.getInstance();
    }
}
