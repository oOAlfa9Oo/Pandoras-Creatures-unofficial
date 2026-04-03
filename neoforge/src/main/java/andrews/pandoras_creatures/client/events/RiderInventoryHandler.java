package andrews.pandoras_creatures.client.events;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.client.bufflon.BufflonRiderInventoryRules;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;

/**
 * Handles opening the Bufflon inventory when the player presses E while riding.
 * Intercepts the normal inventory screen opening and replaces it with the Bufflon inventory.
 */
@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class RiderInventoryHandler {

    @SubscribeEvent
    public static void onOpenInventory(ScreenEvent.Opening event) {
        if (event.getScreen() instanceof InventoryScreen) {
            var localPlayer = Minecraft.getInstance().player;
            var bufflon = BufflonRiderInventoryRules.getMountedBufflon(localPlayer);
            if (bufflon != null) {
                event.setCanceled(true);
                PandorasCreaturesCommon.platform().network().requestBufflonInventory(bufflon.getBufflonId());
            }
        }
    }
}
