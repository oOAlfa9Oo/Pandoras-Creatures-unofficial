package andrews.pandoras_creatures.platform;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * Menu-facing bridge for shared code.
 */
public interface MenuBridge {
    void openBufflonMenu(ServerPlayer player, int entityId, Component title);
}
