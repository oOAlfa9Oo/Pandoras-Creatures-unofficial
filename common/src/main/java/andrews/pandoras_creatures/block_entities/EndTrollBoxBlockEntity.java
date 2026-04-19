package andrews.pandoras_creatures.block_entities;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.content.block.EndTrollBoxBlock;
import andrews.pandoras_creatures.lang.PCLanguageKeys;
import andrews.pandoras_creatures.menu.EndTrollBoxMenu;
import andrews.pandoras_creatures.registry.block.PCBlockEntityIds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

import javax.annotation.Nullable;
import java.util.stream.IntStream;

public class EndTrollBoxBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private static final int[] SLOTS = IntStream.range(0, 54).toArray();
    private NonNullList<ItemStack> items = NonNullList.withSize(54, ItemStack.EMPTY);
    private int openCount;
    public AnimationStatus animationStatus = AnimationStatus.CLOSED;
    private float progress;
    private float progressOld;
    @Nullable
    private DyeColor color;
    private boolean needsColorFromWorld;

    public EndTrollBoxBlockEntity(@Nullable DyeColor colorIn, BlockPos pos, BlockState state) {
        super(PandorasCreaturesCommon.platform().registry().blockEntityType(PCBlockEntityIds.END_TROLL_BOX), pos, state);
        this.color = colorIn;
    }

    public EndTrollBoxBlockEntity(BlockPos pos, BlockState state) {
        this(null, pos, state);
        this.needsColorFromWorld = true;
    }

    public static void tick(net.minecraft.world.level.Level level, BlockPos pos, BlockState state, EndTrollBoxBlockEntity blockEntity) {
        blockEntity.updateAnimation();
    }

    protected void updateAnimation() {
        this.progressOld = this.progress;
        switch (this.animationStatus) {
            case CLOSED:
                this.progress = 0.0F;
                break;
            case OPENING:
                this.progress += 0.1F;
                if (this.progress >= 1.0F) {
                    this.animationStatus = AnimationStatus.OPENED;
                    this.progress = 1.0F;
                    this.updateSurroundingBlocks();
                }
                break;
            case CLOSING:
                this.progress -= 0.1F;
                if (this.progress <= 0.0F) {
                    this.animationStatus = AnimationStatus.CLOSED;
                    this.progress = 0.0F;
                    this.updateSurroundingBlocks();
                }
                break;
            case OPENED:
                this.progress = 1.0F;
        }
    }

    public AnimationStatus getAnimationStatus() {
        return this.animationStatus;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.openCount = type;
            if (type == 0) {
                this.animationStatus = AnimationStatus.CLOSING;
                this.updateSurroundingBlocks();
            }
            if (type == 1) {
                this.animationStatus = AnimationStatus.OPENING;
                this.updateSurroundingBlocks();
            }
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(ContainerUser player) {
        if (player.getLivingEntity() == null || !player.getLivingEntity().isSpectator()) {
            if (this.openCount < 0) {
                this.openCount = 0;
            }
            ++this.openCount;
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
            if (this.openCount == 1) {
                this.level.playSound(null, this.worldPosition, SoundEvents.SHULKER_BOX_OPEN, SoundSource.BLOCKS, 0.5F, this.level.getRandom().nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    @Override
    public void stopOpen(ContainerUser player) {
        if (player.getLivingEntity() == null || !player.getLivingEntity().isSpectator()) {
            --this.openCount;
            this.level.blockEvent(this.worldPosition, this.getBlockState().getBlock(), 1, this.openCount);
            if (this.openCount <= 0) {
                this.level.playSound(null, this.worldPosition, SoundEvents.SHULKER_BOX_CLOSE, SoundSource.BLOCKS, 0.5F, this.level.getRandom().nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    private void updateSurroundingBlocks() {
        this.getBlockState().updateNeighbourShapes(this.getLevel(), this.getBlockPos(), 3);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable(PCLanguageKeys.END_TROLL_BOX_CONTAINER);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.loadFromTag(input);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        this.saveToTag(output);
    }

    public void loadFromTag(ValueInput input) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(input)) {
            ContainerHelper.loadAllItems(input, this.items);
        }
    }

    public void saveToTag(ValueOutput output) {
        if (!this.trySaveLootTable(output)) {
            ContainerHelper.saveAllItems(output, this.items, false);
        }
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, Direction direction) {
        // Prevent placing shulker boxes or end troll boxes inside
        Block block = Block.byItem(itemStack.getItem());
        if (block instanceof ShulkerBoxBlock || block instanceof EndTrollBoxBlock) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    public float getProgress(float partialTicks) {
        return Mth.lerp(partialTicks, this.progressOld, this.progress);
    }

    @Nullable
    public DyeColor getColor() {
        if (this.needsColorFromWorld) {
            this.needsColorFromWorld = false;
            if (this.level != null) {
                Block block = this.level.getBlockState(this.worldPosition).getBlock();
                if (block instanceof EndTrollBoxBlock endTrollBoxBlock) {
                    this.color = endTrollBoxBlock.getColor();
                }
            }
        }
        return this.color;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new EndTrollBoxMenu(id, player, this);
    }

    public boolean isBoxClosed() {
        return this.animationStatus == AnimationStatus.CLOSED;
    }

    public enum AnimationStatus {
        CLOSED, OPENING, OPENED, CLOSING
    }
}
