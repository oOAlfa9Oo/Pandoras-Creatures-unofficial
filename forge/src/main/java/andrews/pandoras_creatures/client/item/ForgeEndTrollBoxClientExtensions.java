package andrews.pandoras_creatures.forge.client.item;

import andrews.pandoras_creatures.client.renderer.tile.PCItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public final class ForgeEndTrollBoxClientExtensions implements IClientItemExtensions {
    public static final ForgeEndTrollBoxClientExtensions INSTANCE = new ForgeEndTrollBoxClientExtensions();

    private ForgeEndTrollBoxClientExtensions() {
    }

    @Override
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return PCItemRenderer.getInstance();
    }
}
