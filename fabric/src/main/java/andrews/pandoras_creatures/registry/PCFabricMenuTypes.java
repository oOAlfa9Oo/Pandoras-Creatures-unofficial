package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.menu.BufflonMenu;
import andrews.pandoras_creatures.menu.EndTrollBoxMenu;
import andrews.pandoras_creatures.menu.PCMenuIds;
import andrews.pandoras_creatures.util.Reference;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public final class PCFabricMenuTypes {
    public static MenuType<BufflonMenu> BUFFLON;
    public static MenuType<EndTrollBoxMenu> END_TROLL_BOX;

    private static boolean initialized;

    private PCFabricMenuTypes() {
    }

    public static void register() {
        if (initialized) {
            return;
        }
        initialized = true;

        BUFFLON = Registry.register(
                BuiltInRegistries.MENU,
                Reference.id(PCMenuIds.BUFFLON),
                new ExtendedScreenHandlerType<>((syncId, inventory, entityId) -> new BufflonMenu(syncId, inventory, entityId), ByteBufCodecs.VAR_INT.cast())
        );

        END_TROLL_BOX = Registry.register(
                BuiltInRegistries.MENU,
                Reference.id(PCMenuIds.END_TROLL_BOX),
                new MenuType<>(EndTrollBoxMenu::new, FeatureFlags.DEFAULT_FLAGS)
        );
    }
}

