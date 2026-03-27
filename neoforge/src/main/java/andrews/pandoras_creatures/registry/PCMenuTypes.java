package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.menu.BufflonMenu;
import andrews.pandoras_creatures.menu.EndTrollBoxMenu;
import andrews.pandoras_creatures.menu.PCMenuIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class PCMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(Registries.MENU, Reference.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<BufflonMenu>> BUFFLON =
            MENU_TYPES.register(PCMenuIds.BUFFLON, () ->
                    IMenuTypeExtension.create((windowId, inv, data) -> new BufflonMenu(windowId, inv, data.readInt())));

    public static final DeferredHolder<MenuType<?>, MenuType<EndTrollBoxMenu>> END_TROLL_BOX =
            MENU_TYPES.register(PCMenuIds.END_TROLL_BOX, () ->
                    IMenuTypeExtension.create((windowId, inv, data) -> new EndTrollBoxMenu(windowId, inv)));

    private PCMenuTypes() {
    }
}
