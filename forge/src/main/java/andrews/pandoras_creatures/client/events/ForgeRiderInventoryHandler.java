package andrews.pandoras_creatures.forge.client.events;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.client.bufflon.BufflonRiderInventoryRules;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public final class ForgeRiderInventoryHandler {
    private ForgeRiderInventoryHandler() {
    }

    @SubscribeEvent
    public static void onOpenInventory(ScreenEvent.Opening event) {
        if (!(event.getNewScreen() instanceof InventoryScreen)) {
            return;
        }

        var localPlayer = Minecraft.getInstance().player;
        var bufflon = BufflonRiderInventoryRules.getMountedBufflon(localPlayer);
        if (bufflon != null) {
            event.setCanceled(true);
            PandorasCreaturesCommon.platform().network().requestBufflonInventory(bufflon.getBufflonId());
        }
    }
}
