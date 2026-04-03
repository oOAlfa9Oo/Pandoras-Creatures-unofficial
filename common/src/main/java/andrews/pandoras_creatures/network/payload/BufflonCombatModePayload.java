package andrews.pandoras_creatures.network.payload;

import andrews.pandoras_creatures.network.PCPayloadIds;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record BufflonCombatModePayload(int entityId, boolean combatMode) implements CustomPacketPayload {
    public static final Type<BufflonCombatModePayload> TYPE =
            new Type<>(PCPayloadIds.id(PCPayloadIds.BUFFLON_COMBAT_MODE));

    public static final StreamCodec<RegistryFriendlyByteBuf, BufflonCombatModePayload> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT, BufflonCombatModePayload::entityId,
                    ByteBufCodecs.BOOL, BufflonCombatModePayload::combatMode,
                    BufflonCombatModePayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
