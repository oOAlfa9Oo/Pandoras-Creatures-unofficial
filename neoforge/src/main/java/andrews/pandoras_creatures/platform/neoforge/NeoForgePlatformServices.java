package andrews.pandoras_creatures.platform.neoforge;

import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.menu.BufflonMenu;
import andrews.pandoras_creatures.platform.EntityBridge;
import andrews.pandoras_creatures.platform.MenuBridge;
import andrews.pandoras_creatures.platform.NetworkBridge;
import andrews.pandoras_creatures.platform.PlatformServices;
import andrews.pandoras_creatures.platform.RegistryBridge;
import andrews.pandoras_creatures.platform.SidedHooks;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import andrews.pandoras_creatures.util.Reference;
import andrews.pandoras_creatures.util.NetworkUtil;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.SimpleMenuProvider;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.network.PacketDistributor;

public final class NeoForgePlatformServices implements PlatformServices {
    public static final NeoForgePlatformServices INSTANCE = new NeoForgePlatformServices();

    private static final RegistryBridge REGISTRY = new RegistryBridge() {
        @Override
        public String namespace() {
            return Reference.MODID;
        }

        @Override
        public Item item(String path) {
            return BuiltInRegistries.ITEM.get(id(path));
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T extends Entity> EntityType<T> entityType(String path) {
            return (EntityType<T>) BuiltInRegistries.ENTITY_TYPE.get(id(path));
        }

        @Override
        public SoundEvent sound(String path) {
            ResourceLocation id = id(path);
            return BuiltInRegistries.SOUND_EVENT.get(id);
        }
    };
    private static final NetworkBridge NETWORK = new NetworkBridge() {
        @Override
        public String channelNamespace() {
            return Reference.MODID;
        }

        @Override
        public void requestBufflonInventory(int entityId) {
            PacketDistributor.sendToServer(new BufflonInventoryPayload(entityId));
        }

        @Override
        public void requestBufflonSit(int entityId, boolean shouldSit) {
            PacketDistributor.sendToServer(new BufflonSitPayload(entityId, shouldSit));
        }

        @Override
        public void requestBufflonFollow(int entityId, boolean shouldFollow) {
            PacketDistributor.sendToServer(new BufflonFollowPayload(entityId, shouldFollow));
        }

        @Override
        public void requestBufflonCombatMode(int entityId, boolean combatMode) {
            PacketDistributor.sendToServer(new BufflonCombatModePayload(entityId, combatMode));
        }
    };
    private static final MenuBridge MENUS = new MenuBridge() {
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
    };
    private static final EntityBridge ENTITIES = new EntityBridge() {
        @Override
        public void syncAnimation(Entity entity, Animation animation) {
            NetworkUtil.sendAnimationPacket(entity, animation);
        }

        @Override
        public int getExperienceDrop(Mob entity, Player attackingPlayer, int originalExperience) {
            return EventHooks.getExperienceDrop(entity, attackingPlayer, originalExperience);
        }
    };
    private static final SidedHooks SIDED_HOOKS = () -> FMLEnvironment.dist.isClient();

    private NeoForgePlatformServices() {
    }

    @Override
    public String loaderName() {
        return "neoforge";
    }

    @Override
    public RegistryBridge registry() {
        return REGISTRY;
    }

    @Override
    public NetworkBridge network() {
        return NETWORK;
    }

    @Override
    public MenuBridge menus() {
        return MENUS;
    }

    @Override
    public EntityBridge entities() {
        return ENTITIES;
    }

    @Override
    public SidedHooks sidedHooks() {
        return SIDED_HOOKS;
    }
}
