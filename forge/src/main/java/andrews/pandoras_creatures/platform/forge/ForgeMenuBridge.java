package andrews.pandoras_creatures.forge.platform;

import andrews.pandoras_creatures.menu.BufflonMenu;
import andrews.pandoras_creatures.platform.MenuBridge;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkHooks;

final class ForgeMenuBridge implements MenuBridge {
    static final ForgeMenuBridge INSTANCE = new ForgeMenuBridge();

    private ForgeMenuBridge() {
    }

    @Override
    public void openBufflonMenu(ServerPlayer player, int entityId, Component title) {
        NetworkHooks.openScreen(
                player,
                new SimpleMenuProvider((windowId, inventory, currentPlayer) -> new BufflonMenu(windowId, inventory, entityId), title),
                buffer -> buffer.writeInt(entityId)
        );
    }
}
