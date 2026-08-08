package andrews.pandoras_creatures.forge.platform;

import andrews.pandoras_creatures.forge.network.PCForgeNetwork;
import andrews.pandoras_creatures.forge.network.payload.BufflonMenuOpenPayload;
import andrews.pandoras_creatures.menu.BufflonMenu;
import andrews.pandoras_creatures.platform.MenuBridge;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;

final class ForgeMenuBridge implements MenuBridge {
    static final ForgeMenuBridge INSTANCE = new ForgeMenuBridge();

    private ForgeMenuBridge() {
    }

    @Override
    public void openBufflonMenu(ServerPlayer player, int entityId, Component title) {
        // 1.20.2+: NetworkHooks.openScreen (que enviaba datos extra junto con la apertura) fue
        // eliminado sin reemplazo directo en Forge. Se sincroniza entityId a mano con un payload
        // propio ANTES de openMenu(): misma conexion, orden FIFO garantizado. Ver BufflonMenuOpenPayload.
        PCForgeNetwork.sendToPlayer(player, new BufflonMenuOpenPayload(entityId));
        player.openMenu(new SimpleMenuProvider((windowId, inventory, currentPlayer) -> new BufflonMenu(windowId, inventory, entityId), title));
    }
}
