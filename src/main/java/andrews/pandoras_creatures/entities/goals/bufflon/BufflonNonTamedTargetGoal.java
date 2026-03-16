package andrews.pandoras_creatures.entities.goals.bufflon;

import andrews.pandoras_creatures.entities.BufflonEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

public class BufflonNonTamedTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
    private final BufflonEntity bufflonEntity;

    public BufflonNonTamedTargetGoal(BufflonEntity bufflonEntity, Class<T> target, boolean longTerm) {
        super(bufflonEntity, target, 10, longTerm, false, null);
        this.bufflonEntity = bufflonEntity;
    }

    @Override
    public boolean canUse() {
        return !this.bufflonEntity.isTamed() && !this.bufflonEntity.isVehicle() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        if (this.targetConditions != null) {
            if (this.mob.isVehicle()) {
                return false;
            } else {
                return this.targetConditions.test(this.mob, this.target);
            }
        } else {
            return super.canContinueToUse();
        }
    }
}
