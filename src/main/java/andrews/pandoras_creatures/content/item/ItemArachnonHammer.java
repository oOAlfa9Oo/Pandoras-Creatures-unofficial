package andrews.pandoras_creatures.content.item;

import andrews.pandoras_creatures.content.material.PCToolMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.List;

public class ItemArachnonHammer extends PickaxeItem {
    public ItemArachnonHammer() {
        super(PCToolMaterials.ARACHNON_MATERIAL, new Properties()
                .attributes(createAttributes(PCToolMaterials.ARACHNON_MATERIAL, 0, -3.0F)));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("item.pandoras_creatures.arachnon_hammer.tooltip"));
        super.appendHoverText(stack, context, tooltip, flag);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (entity instanceof Player player && !level.isClientSide()) {
            switch (getBlockSideHit(player)) {
                case UP:
                case DOWN:
                    for (int x = -1; x < 2; x++) {
                        for (int z = -1; z < 2; z++) {
                            if (!(x == 0 && z == 0)) {
                                BlockPos blockPos = new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z);
                                BlockState stateIn = level.getBlockState(blockPos);
                                if (canHarvestBlock(level, blockPos, stateIn)) {
                                    processHarvest(level, blockPos, stateIn, stack, player);
                                }
                            }
                        }
                    }
                    break;
                case SOUTH:
                case NORTH:
                    for (int x = -1; x < 2; x++) {
                        for (int y = -1; y < 2; y++) {
                            if (!(x == 0 && y == 0)) {
                                BlockPos blockPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ());
                                BlockState stateIn = level.getBlockState(blockPos);
                                if (canHarvestBlock(level, blockPos, stateIn)) {
                                    processHarvest(level, blockPos, stateIn, stack, player);
                                }
                            }
                        }
                    }
                    break;
                case EAST:
                case WEST:
                    for (int z = -1; z < 2; z++) {
                        for (int y = -1; y < 2; y++) {
                            if (!(z == 0 && y == 0)) {
                                BlockPos blockPos = new BlockPos(pos.getX(), pos.getY() + y, pos.getZ() + z);
                                BlockState stateIn = level.getBlockState(blockPos);
                                if (canHarvestBlock(level, blockPos, stateIn)) {
                                    processHarvest(level, blockPos, stateIn, stack, player);
                                }
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return super.mineBlock(stack, level, state, pos, entity);
    }

    /**
     * Used to check if the blockIn can be broken by this tool
     */
    public boolean canHarvestBlock(Level level, BlockPos position, BlockState blockIn) {
        BlockEntity blockEntity = level.getBlockEntity(position);

        // Makes sure the Block has no Tile Entity
        if (blockEntity != null) {
            return false;
        }
        // Makes sure the Block isn't unbreakable
        if (blockIn.getDestroySpeed(level, position) == -1) {
            return false;
        }
        // Check if this tool is correct for the block
        return this.isCorrectToolForDrops(new ItemStack(this), blockIn);
    }

    /**
     * Used to "mine" a block
     */
    private void processHarvest(Level level, BlockPos pos, BlockState state, ItemStack stack, Player player) {
        // Break the block
        level.destroyBlock(pos, false);
        if (!player.isCreative() && level instanceof ServerLevel serverLevel) {
            // Drop items
            for (ItemStack itemStack : Block.getDrops(state, serverLevel, pos, null, player, stack)) {
                ItemEntity item = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, itemStack);
                level.addFreshEntity(item);
            }
            // Drop experience
            state.spawnAfterBreak(serverLevel, pos, stack, true);
        }
    }

    /**
     * Used to get the side of the block that got harvested
     */
    private Direction getBlockSideHit(Player player) {
        HitResult raycast = rayTraceFromPlayer(player.level(), player, ClipContext.Fluid.NONE);
        if (!player.level().isClientSide() && raycast.getType() == HitResult.Type.BLOCK) {
            Direction face = ((BlockHitResult) raycast).getDirection();
            return face.getOpposite();
        } else {
            return Direction.NORTH;
        }
    }

    /**
     * Casts a ray from the player towards the harvested Block
     */
    private HitResult rayTraceFromPlayer(Level level, Player player, ClipContext.Fluid fluidMode) {
        float pitch = player.getXRot();
        float yaw = player.getYRot();
        Vec3 eyePos = player.getEyePosition(1.0F);
        float f2 = (float) Math.cos(-yaw * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = (float) Math.sin(-yaw * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = (float) -Math.cos(-pitch * ((float) Math.PI / 180F));
        float f5 = (float) Math.sin(-pitch * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double reach = player.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE);
        reach = reach * 2;
        Vec3 endVec = eyePos.add(f6 * reach, f5 * reach, f7 * reach);
        return level.clip(new ClipContext(eyePos, endVec, ClipContext.Block.OUTLINE, fluidMode, player));
    }
}
