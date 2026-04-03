package andrews.pandoras_creatures.client.events;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.client.bufflon.BufflonRiderInventoryRules;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;

public final class FabricRiderInventoryHandler {
    private static boolean initialized;

    private FabricRiderInventoryHandler() {
    }

    public static void register() {
        if (initialized) {
            return;
        }
        initialized = true;

        ClientTickEvents.END_CLIENT_TICK.register(FabricRiderInventoryHandler::onEndClientTick);
    }

    private static void onEndClientTick(Minecraft minecraft) {
        if (!(minecraft.screen instanceof InventoryScreen) || minecraft.player == null) {
            return;
        }

        var bufflon = BufflonRiderInventoryRules.getMountedBufflon(minecraft.player);
        if (bufflon == null) {
            return;
        }

        minecraft.setScreen(null);
        PandorasCreaturesCommon.platform().network().requestBufflonInventory(bufflon.getBufflonId());
    }
}
