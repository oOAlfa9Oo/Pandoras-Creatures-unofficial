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

import org.jetbrains.annotations.Nullable;

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
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        this.saveToNBT(tag);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.loadFromNBT(tag);
    }

    private CompoundTag saveToNBT(CompoundTag compound) {
        CompoundTag shardNBT = new CompoundTag();
        shardNBT.putInt("ShardSize", this.getShardSize());
        shardNBT.putInt("ShardVariant", this.getShardVariant());
        compound.put("PandoricShardValues", shardNBT);
        return compound;
    }

    private void loadFromNBT(CompoundTag compound) {
        CompoundTag shardNBT = compound.getCompound("PandoricShardValues");
        shardSize = shardNBT.getInt("ShardSize");
        shardVariant = shardNBT.getInt("ShardVariant");
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

