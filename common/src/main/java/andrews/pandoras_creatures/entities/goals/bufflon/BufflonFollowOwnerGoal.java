package andrews.pandoras_creatures.entities.goals.bufflon;

import andrews.pandoras_creatures.entities.BufflonEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;

import java.util.EnumSet;

public class BufflonFollowOwnerGoal extends Goal {
    protected final BufflonEntity bufflonEntity;
    protected final LevelReader level;
    private final double followSpeed;
    private final PathNavigation navigator;
    private final float maxDist;
    private final float minDist;
    private LivingEntity owner;
    private int timeToRecalcPath;
    private float oldWaterCost;

    public BufflonFollowOwnerGoal(BufflonEntity bufflonEntity, double followSpeedIn, float minDistIn, float maxDistIn) {
        this.bufflonEntity = bufflonEntity;
        this.level = bufflonEntity.level();
        this.followSpeed = followSpeedIn;
        this.navigator = bufflonEntity.getNavigation();
        this.minDist = minDistIn;
        this.maxDist = maxDistIn;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        if (!(bufflonEntity.getNavigation() instanceof GroundPathNavigation) && !(bufflonEntity.getNavigation() instanceof FlyingPathNavigation)) {
            throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
        }
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.bufflonEntity.getOwner();
        if (livingentity == null) {
            return false;
        } else if (livingentity instanceof Player player && player.isSpectator()) {
            return false;
        } else if (this.bufflonEntity.isSitting()) {
            return false;
        } else if (!this.bufflonEntity.isFollowingOwner()) {
            return false;
        } else if (this.bufflonEntity.distanceToSqr(livingentity) < (double) (this.minDist * this.minDist)) {
            return false;
        } else {
            this.owner = livingentity;
            return true;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return !this.navigator.isDone()
                && this.bufflonEntity.distanceToSqr(this.owner) > (double) (this.maxDist * this.maxDist)
                && !this.bufflonEntity.isSitting()
                && this.bufflonEntity.isFollowingOwner();
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.bufflonEntity.getPathfindingMalus(PathType.WATER);
        this.bufflonEntity.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    public void stop() {
        this.owner = null;
        this.navigator.stop();
        this.bufflonEntity.setPathfindingMalus(PathType.WATER, this.oldWaterCost);
    }

    @Override
    public void tick() {
        this.bufflonEntity.getLookControl().setLookAt(this.owner, 10.0F, (float) this.bufflonEntity.getMaxHeadXRot());
        if (!this.bufflonEntity.isSitting() && this.bufflonEntity.isFollowingOwner() && --this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            if (!this.navigator.moveTo(this.owner, this.followSpeed)
                    && !this.bufflonEntity.isLeashed()
                    && !this.bufflonEntity.isPassenger()
                    && !(this.bufflonEntity.distanceToSqr(this.owner) < 144.0D)) {
                int i = Mth.floor(this.owner.getX()) - 2;
                int j = Mth.floor(this.owner.getZ()) - 2;
                int k = Mth.floor(this.owner.getBoundingBox().minY);

                for (int l = 0; l <= 4; ++l) {
                    for (int i1 = 0; i1 <= 4; ++i1) {
                        if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.canTeleportToBlock(new BlockPos(i + l, k - 1, j + i1))) {
                            this.bufflonEntity.snapTo((double) ((float) (i + l) + 0.5F), (double) k, (double) ((float) (j + i1) + 0.5F), this.bufflonEntity.getYRot(), this.bufflonEntity.getXRot());
                            this.navigator.stop();
                            return;
                        }
                    }
                }
            }
        }
    }

    protected boolean canTeleportToBlock(BlockPos pos) {
        BlockState blockstate = this.level.getBlockState(pos);
        return blockstate.isValidSpawn(this.level, pos, this.bufflonEntity.getType()) && this.level.isEmptyBlock(pos.above()) && this.level.isEmptyBlock(pos.above(2));
    }
}
