package andrews.pandoras_creatures.forge.network;

import andrews.pandoras_creatures.forge.client.network.ForgeAnimationPayloadClientHandler;
import andrews.pandoras_creatures.forge.client.network.ForgeBufflonMenuOpenClientHandler;
import andrews.pandoras_creatures.forge.network.payload.BufflonMenuOpenPayload;
import andrews.pandoras_creatures.network.PCPayloadIds;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public final class PCForgeNetwork {
    private static final int PROTOCOL_VERSION = 1;
    // 1.20.2: NetworkRegistry.newSimpleChannel se reemplazo por ChannelBuilder (SimpleChannel se
    // mueve de net.minecraftforge.network.simple a net.minecraftforge.network). Verificado por
    // fuente real (ChannelBuilder.java) del jar de Forge 48.1.0, no supuesto.
    private static final SimpleChannel CHANNEL = ChannelBuilder.named(PCPayloadIds.id("play"))
            .networkProtocolVersion(PROTOCOL_VERSION)
            .acceptedVersions(Channel.VersionTest.exact(PROTOCOL_VERSION))
            .simpleChannel();

    static {
        int index = 0;
        CHANNEL.messageBuilder(AnimationPayload.class, index++, net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeInt(payload.animationIndex());
                })
                .decoder(PCForgeNetwork::decodeAnimationPayload)
                .consumerMainThread((payload, context) -> {
                    ForgeAnimationPayloadClientHandler.handle(payload);
                    context.setPacketHandled(true);
                })
                .add();

        CHANNEL.messageBuilder(BufflonInventoryPayload.class, index++, net.minecraftforge.network.NetworkDirection.PLAY_TO_SERVER)
                .encoder((payload, buf) -> buf.writeInt(payload.entityId()))
                .decoder(buf -> new BufflonInventoryPayload(buf.readInt()))
                .consumerMainThread((payload, context) -> ForgeBufflonPayloadHandlers.handleInventoryRequest(payload.entityId(), context))
                .add();

        CHANNEL.messageBuilder(BufflonSitPayload.class, index++, net.minecraftforge.network.NetworkDirection.PLAY_TO_SERVER)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeBoolean(payload.shouldSit());
                })
                .decoder(buf -> new BufflonSitPayload(buf.readInt(), buf.readBoolean()))
                .consumerMainThread((payload, context) -> ForgeBufflonPayloadHandlers.handleSitRequest(payload.entityId(), payload.shouldSit(), context))
                .add();

        CHANNEL.messageBuilder(BufflonFollowPayload.class, index++, net.minecraftforge.network.NetworkDirection.PLAY_TO_SERVER)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeBoolean(payload.shouldFollow());
                })
                .decoder(buf -> new BufflonFollowPayload(buf.readInt(), buf.readBoolean()))
                .consumerMainThread((payload, context) -> ForgeBufflonPayloadHandlers.handleFollowRequest(payload.entityId(), payload.shouldFollow(), context))
                .add();

        CHANNEL.messageBuilder(BufflonCombatModePayload.class, index++, net.minecraftforge.network.NetworkDirection.PLAY_TO_SERVER)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeBoolean(payload.combatMode());
                })
                .decoder(buf -> new BufflonCombatModePayload(buf.readInt(), buf.readBoolean()))
                .consumerMainThread((payload, context) -> ForgeBufflonPayloadHandlers.handleCombatModeRequest(payload.entityId(), payload.combatMode(), context))
                .add();

        // Ver BufflonMenuOpenPayload: sustituye a NetworkHooks.openScreen (eliminado en Forge 1.20.2+).
        CHANNEL.messageBuilder(BufflonMenuOpenPayload.class, index, net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT)
                .encoder((payload, buf) -> buf.writeInt(payload.entityId()))
                .decoder(buf -> new BufflonMenuOpenPayload(buf.readInt()))
                .consumerMainThread((payload, context) -> {
                    ForgeBufflonMenuOpenClientHandler.handle(payload.entityId());
                    context.setPacketHandled(true);
                })
                .add();
    }

    private PCForgeNetwork() {
    }

    public static void initialize() {
        // Trigger static channel construction during mod bootstrap.
    }

    public static void sendToServer(Object payload) {
        // 1.20.2: SimpleChannel/Channel ya no expone sendToServer(msg); se usa send(msg, target)
        // con PacketDistributor.SERVER.noArg() (verificado en Channel.java/PacketDistributor.java).
        // El orden de argumentos tambien se invirtio: antes send(target, msg), ahora send(msg, target).
        CHANNEL.send(payload, PacketDistributor.SERVER.noArg());
    }

    public static void sendToTrackingEntityAndSelf(Entity entity, Object payload) {
        CHANNEL.send(payload, PacketDistributor.TRACKING_ENTITY_AND_SELF.with(entity));
    }

    public static void sendToPlayer(ServerPlayer player, Object payload) {
        CHANNEL.send(payload, PacketDistributor.PLAYER.with(player));
    }

    private static AnimationPayload decodeAnimationPayload(FriendlyByteBuf buf) {
        return new AnimationPayload(buf.readInt(), buf.readInt());
    }
}
