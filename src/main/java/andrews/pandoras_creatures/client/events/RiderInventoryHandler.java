package andrews.pandoras_creatures.client.events;

import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.network.PacketDistributor;

/**
 * Handles opening the Bufflon inventory when the player presses E while riding.
 * Intercepts the normal inventory screen opening and replaces it with the Bufflon inventory.
 */
@EventBusSubscriber(modid = Reference.MODID, value = Dist.CLIENT)
public class RiderInventoryHandler {

    @SubscribeEvent
    public static void onOpenInventory(ScreenEvent.Opening event) {
        if (event.getScreen() instanceof InventoryScreen) {
            Player localPlayer = Minecraft.getInstance().player;
            if (localPlayer != null && localPlayer.isPassenger() && localPlayer.getVehicle() instanceof BufflonEntity bufflon) {
                if (bufflon.isTamed()) {
                    event.setCanceled(true);
                    // Send a packet to the server to open the Bufflon inventory
                    PacketDistributor.sendToServer(new BufflonInventoryPayload(bufflon.getId()));
                }
            }
        }
    }
}
