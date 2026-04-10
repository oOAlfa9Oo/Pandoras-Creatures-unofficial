package andrews.pandoras_creatures.forge.network;

import andrews.pandoras_creatures.forge.client.network.ForgeAnimationPayloadClientHandler;
import andrews.pandoras_creatures.network.PCPayloadIds;
import andrews.pandoras_creatures.network.payload.AnimationPayload;
import andrews.pandoras_creatures.network.payload.BufflonCombatModePayload;
import andrews.pandoras_creatures.network.payload.BufflonFollowPayload;
import andrews.pandoras_creatures.network.payload.BufflonInventoryPayload;
import andrews.pandoras_creatures.network.payload.BufflonSitPayload;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public final class PCForgeNetwork {
    private static final int PROTOCOL_VERSION = 1;
    private static final SimpleChannel CHANNEL = ChannelBuilder
            .named(PCPayloadIds.id("play"))
            .acceptedVersions(Channel.VersionTest.exact(PROTOCOL_VERSION))
            .networkProtocolVersion(PROTOCOL_VERSION)
            .simpleChannel();

    static {
        int index = 0;
        CHANNEL.messageBuilder(AnimationPayload.class, index++)
                .direction(PacketFlow.CLIENTBOUND)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeInt(payload.animationIndex());
                })
                .decoder(PCForgeNetwork::decodeAnimationPayload)
                .consumerMainThread((payload, context) -> ForgeAnimationPayloadClientHandler.handle(payload))
                .add();

        CHANNEL.messageBuilder(BufflonInventoryPayload.class, index++)
                .direction(PacketFlow.SERVERBOUND)
                .encoder((payload, buf) -> buf.writeInt(payload.entityId()))
                .decoder(buf -> new BufflonInventoryPayload(buf.readInt()))
                .consumerMainThread((payload, context) -> ForgeBufflonPayloadHandlers.handleInventoryRequest(payload.entityId(), context))
                .add();

        CHANNEL.messageBuilder(BufflonSitPayload.class, index++)
                .direction(PacketFlow.SERVERBOUND)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeBoolean(payload.shouldSit());
                })
                .decoder(buf -> new BufflonSitPayload(buf.readInt(), buf.readBoolean()))
                .consumerMainThread((payload, context) -> ForgeBufflonPayloadHandlers.handleSitRequest(payload.entityId(), payload.shouldSit(), context))
                .add();

        CHANNEL.messageBuilder(BufflonFollowPayload.class, index++)
                .direction(PacketFlow.SERVERBOUND)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeBoolean(payload.shouldFollow());
                })
                .decoder(buf -> new BufflonFollowPayload(buf.readInt(), buf.readBoolean()))
                .consumerMainThread((payload, context) -> ForgeBufflonPayloadHandlers.handleFollowRequest(payload.entityId(), payload.shouldFollow(), context))
                .add();

        CHANNEL.messageBuilder(BufflonCombatModePayload.class, index)
                .direction(PacketFlow.SERVERBOUND)
                .encoder((payload, buf) -> {
                    buf.writeInt(payload.entityId());
                    buf.writeBoolean(payload.combatMode());
                })
                .decoder(buf -> new BufflonCombatModePayload(buf.readInt(), buf.readBoolean()))
                .consumerMainThread((payload, context) -> ForgeBufflonPayloadHandlers.handleCombatModeRequest(payload.entityId(), payload.combatMode(), context))
                .add();

        CHANNEL.build();
    }

    private PCForgeNetwork() {
    }

    public static void initialize() {
        // Trigger static channel construction during mod bootstrap.
    }

    public static void sendToServer(Object payload) {
        CHANNEL.send(payload, PacketDistributor.SERVER.noArg());
    }

    public static void sendToTrackingEntityAndSelf(Entity entity, Object payload) {
        CHANNEL.send(payload, PacketDistributor.TRACKING_ENTITY_AND_SELF.with(entity));
    }

    private static AnimationPayload decodeAnimationPayload(FriendlyByteBuf buf) {
        return new AnimationPayload(buf.readInt(), buf.readInt());
    }
}
