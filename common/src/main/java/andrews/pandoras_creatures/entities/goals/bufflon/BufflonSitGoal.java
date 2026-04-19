package andrews.pandoras_creatures.entities.goals.bufflon;

import andrews.pandoras_creatures.entities.BufflonEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class BufflonSitGoal extends Goal {
    private final BufflonEntity bufflonEntity;
    private boolean isSitting;

    public BufflonSitGoal(BufflonEntity bufflonEntity) {
        this.bufflonEntity = bufflonEntity;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    @Override
    public boolean canContinueToUse() {
        return this.isSitting;
    }

    @Override
    public boolean canUse() {
        if (!this.bufflonEntity.isTamed()) {
            return false;
        } else if (this.bufflonEntity.isInWater()) {
            return false;
        } else if (!this.bufflonEntity.onGround()) {
            return false;
        } else {
            LivingEntity livingentity = this.bufflonEntity.getOwner();
            if (livingentity == null) {
                return true;
            }
            return this.bufflonEntity.distanceToSqr(livingentity) >= 144.0D || livingentity.getLastHurtByMob() == null ? this.isSitting : false;
        }
    }

    @Override
    public void start() {
        this.bufflonEntity.getNavigation().stop();
        this.bufflonEntity.setSitting(true);
    }

    @Override
    public void stop() {
        this.bufflonEntity.setSitting(false);
    }

    public void setSitting(boolean sitting) {
        this.isSitting = sitting;
    }
}
