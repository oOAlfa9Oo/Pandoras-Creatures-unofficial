package andrews.pandoras_creatures.block_entities;

import andrews.pandoras_creatures.registry.PCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;

public class PandoricShardBlockEntity extends BlockEntity {
    private final RandomSource random = RandomSource.create();
    private final int animationDelay;
    public int shardSize;
    public int shardVariant;

    public PandoricShardBlockEntity(BlockPos pos, BlockState state) {
        super(PCBlockEntities.PANDORIC_SHARD.get(), pos, state);
        this.animationDelay = this.random.nextInt(60);
    }

    public int getAnimationDelay() {
        return this.animationDelay;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        this.saveToNBT(tag);
        return tag;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        this.saveToTag(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.loadFromTag(input);
    }

    private CompoundTag saveToNBT(CompoundTag compound) {
        CompoundTag shardNBT = new CompoundTag();
        shardNBT.putInt("ShardSize", this.getShardSize());
        shardNBT.putInt("ShardVariant", this.getShardVariant());
        compound.put("PandoricShardValues", shardNBT);
        return compound;
    }

    private void loadFromNBT(CompoundTag compound) {
        CompoundTag shardNBT = compound.getCompound("PandoricShardValues").orElse(new CompoundTag());
        shardSize = shardNBT.getInt("ShardSize").orElse(0);
        shardVariant = shardNBT.getInt("ShardVariant").orElse(0);
    }

    private void saveToTag(ValueOutput output) {
        ValueOutput shardOutput = output.child("PandoricShardValues");
        shardOutput.putInt("ShardSize", this.getShardSize());
        shardOutput.putInt("ShardVariant", this.getShardVariant());
    }

    private void loadFromTag(ValueInput input) {
        ValueInput shardInput = input.childOrEmpty("PandoricShardValues");
        shardSize = shardInput.getIntOr("ShardSize", 0);
        shardVariant = shardInput.getIntOr("ShardVariant", 0);
    }

    public int getShardSize() {
        if (shardSize == 0) {
            if (this.level != null && !this.level.isClientSide()) {
                this.setShardSize(3);
            }
            return shardSize;
        } else {
            return shardSize;
        }
    }

    public void setShardSize(int size) {
        shardSize = size;
        setChanged();
    }

    public int getShardVariant() {
        if (shardVariant == 0) {
            if (this.level != null && !this.level.isClientSide()) {
                this.setShardVariant(1);
            }
        }
        return shardVariant;
    }

    public void setShardVariant(int variant) {
        shardVariant = variant;
        setChanged();
    }
}
