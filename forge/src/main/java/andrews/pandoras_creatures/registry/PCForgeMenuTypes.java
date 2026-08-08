package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.forge.client.network.ForgeBufflonMenuOpenClientHandler;
import andrews.pandoras_creatures.menu.BufflonMenu;
import andrews.pandoras_creatures.menu.EndTrollBoxMenu;
import andrews.pandoras_creatures.menu.PCMenuIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class PCForgeMenuTypes {
    private static boolean registered;

    private PCForgeMenuTypes() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeMenuTypes::registerMenuTypes);
    }

    public static MenuType<BufflonMenu> bufflon() {
        return menuType(PCMenuIds.BUFFLON);
    }

    public static MenuType<EndTrollBoxMenu> endTrollBox() {
        return menuType(PCMenuIds.END_TROLL_BOX);
    }

    @SuppressWarnings("unchecked")
    private static <T extends net.minecraft.world.inventory.AbstractContainerMenu> MenuType<T> menuType(String id) {
        ResourceLocation menuId = Reference.id(id);
        MenuType<?> value = BuiltInRegistries.MENU.get(menuId);
        if (value == null) {
            throw new IllegalArgumentException("Unknown forge menu type id: " + menuId);
        }
        return (MenuType<T>) value;
    }

    private static void registerMenuTypes(RegisterEvent event) {
        if (registered || !event.getRegistryKey().equals(ForgeRegistries.Keys.MENU_TYPES)) {
            return;
        }

        registered = true;
        event.register(ForgeRegistries.Keys.MENU_TYPES, helper -> {
            // 1.20.2+: ClientboundOpenScreenPacket ya no lleva datos extra (NetworkHooks.openScreen
            // fue eliminado); `data` llega null aqui. El entityId se sincroniza aparte via
            // BufflonMenuOpenPayload, enviado antes de openMenu() -- ver ForgeMenuBridge.
            helper.register(
                    Reference.id(PCMenuIds.BUFFLON),
                    IForgeMenuType.create((windowId, inventory, data) -> new BufflonMenu(windowId, inventory, ForgeBufflonMenuOpenClientHandler.consumePendingEntityId()))
            );
            helper.register(
                    Reference.id(PCMenuIds.END_TROLL_BOX),
                    new MenuType<>(EndTrollBoxMenu::new, FeatureFlags.DEFAULT_FLAGS)
            );
        });
    }
}

