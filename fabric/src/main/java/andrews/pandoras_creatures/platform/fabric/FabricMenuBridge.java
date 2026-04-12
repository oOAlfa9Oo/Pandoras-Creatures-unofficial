package andrews.pandoras_creatures.platform.fabric;

import andrews.pandoras_creatures.entities.bufflon.BufflonAccess;
import andrews.pandoras_creatures.menu.BufflonMenu;
import andrews.pandoras_creatures.platform.MenuBridge;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;

final class FabricMenuBridge implements MenuBridge {
    static final FabricMenuBridge INSTANCE = new FabricMenuBridge();

    private FabricMenuBridge() {
    }

    @Override
    public void openBufflonMenu(ServerPlayer player, int entityId, Component title) {
        Entity entity = player.level().getEntity(entityId);
        if (!(entity instanceof BufflonAccess bufflon)) {
            return;
        }

        player.openMenu(new ExtendedMenuProvider<Integer>() {
            @Override
            public Integer getScreenOpeningData(ServerPlayer currentPlayer) {
                return bufflon.getBufflonId();
            }

            @Override
            public Component getDisplayName() {
                return title;
            }

            @Override
            public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player currentPlayer) {
                return new BufflonMenu(containerId, inventory, bufflon.getBufflonId());
            }
        });
    }
}
