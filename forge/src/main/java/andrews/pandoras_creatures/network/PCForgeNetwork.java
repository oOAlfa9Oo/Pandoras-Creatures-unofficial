package andrews.pandoras_creatures.forge.network;

import andrews.pandoras_creatures.forge.client.network.ForgeAnimationPayloadClientHandler;
import andrews.pandoras_creatures.network.PCPayloadIds;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public final class PCForgeNetwork {
    private static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            PCPayloadIds.id("play"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    static {
        int index = 0;
        CHANNEL.messageBuilder(AnimationPayload.class, index++, net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeInt(payload.animationIndex());
                })
                .decoder(PCForgeNetwork::decodeAnimationPayload)
                .consumerMainThread((payload, contextSupplier) -> {
                    ForgeAnimationPayloadClientHandler.handle(payload);
                    contextSupplier.get().setPacketHandled(true);
                })
                .add();

        CHANNEL.messageBuilder(BufflonInventoryPayload.class, index++, net.minecraftforge.network.NetworkDirection.PLAY_TO_SERVER)
                .encoder((payload, buf) -> buf.writeInt(payload.entityId()))
                .decoder(buf -> new BufflonInventoryPayload(buf.readInt()))
                .consumerMainThread((payload, contextSupplier) -> ForgeBufflonPayloadHandlers.handleInventoryRequest(payload.entityId(), contextSupplier))
                .add();

        CHANNEL.messageBuilder(BufflonSitPayload.class, index++, net.minecraftforge.network.NetworkDirection.PLAY_TO_SERVER)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeBoolean(payload.shouldSit());
                })
                .decoder(buf -> new BufflonSitPayload(buf.readInt(), buf.readBoolean()))
                .consumerMainThread((payload, contextSupplier) -> ForgeBufflonPayloadHandlers.handleSitRequest(payload.entityId(), payload.shouldSit(), contextSupplier))
                .add();

        CHANNEL.messageBuilder(BufflonFollowPayload.class, index++, net.minecraftforge.network.NetworkDirection.PLAY_TO_SERVER)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeBoolean(payload.shouldFollow());
                })
                .decoder(buf -> new BufflonFollowPayload(buf.readInt(), buf.readBoolean()))
                .consumerMainThread((payload, contextSupplier) -> ForgeBufflonPayloadHandlers.handleFollowRequest(payload.entityId(), payload.shouldFollow(), contextSupplier))
                .add();

        CHANNEL.messageBuilder(BufflonCombatModePayload.class, index, net.minecraftforge.network.NetworkDirection.PLAY_TO_SERVER)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeBoolean(payload.combatMode());
                })
                .decoder(buf -> new BufflonCombatModePayload(buf.readInt(), buf.readBoolean()))
                .consumerMainThread((payload, contextSupplier) -> ForgeBufflonPayloadHandlers.handleCombatModeRequest(payload.entityId(), payload.combatMode(), contextSupplier))
                .add();
    }

    private PCForgeNetwork() {
    }

    public static void initialize() {
        // Trigger static channel construction during mod bootstrap.
    }

    public static void sendToServer(Object payload) {
        CHANNEL.sendToServer(payload);
    }

    public static void sendToTrackingEntityAndSelf(Entity entity, Object payload) {
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), payload);
    }

    private static AnimationPayload decodeAnimationPayload(FriendlyByteBuf buf) {
        return new AnimationPayload(buf.readInt(), buf.readInt());
    }
}
