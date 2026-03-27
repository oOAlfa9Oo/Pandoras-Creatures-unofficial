package andrews.pandoras_creatures.platform.neoforge;

import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.menu.BufflonMenu;
import andrews.pandoras_creatures.platform.MenuBridge;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.Entity;

final class NeoForgeMenuBridge implements MenuBridge {
    static final NeoForgeMenuBridge INSTANCE = new NeoForgeMenuBridge();

    private NeoForgeMenuBridge() {
    }

    @Override
    public void openBufflonMenu(ServerPlayer player, int entityId, Component title) {
        Entity entity = player.level().getEntity(entityId);
        if (!(entity instanceof BufflonEntity bufflon)) {
            return;
        }

        player.openMenu(new SimpleMenuProvider(
                (containerId, playerInventory, currentPlayer) -> new BufflonMenu(containerId, playerInventory, bufflon.getId()),
                title
        ), buffer -> buffer.writeInt(bufflon.getId()));
    }
}
