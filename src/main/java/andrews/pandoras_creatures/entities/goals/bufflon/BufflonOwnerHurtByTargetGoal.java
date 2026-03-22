package andrews.pandoras_creatures.entities.goals.bufflon;

import andrews.pandoras_creatures.entities.BufflonEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

import java.util.EnumSet;

public class BufflonOwnerHurtByTargetGoal extends TargetGoal {
    private final BufflonEntity bufflonEntity;
    private LivingEntity attacker;
    private int timestamp;

    public BufflonOwnerHurtByTargetGoal(BufflonEntity bufflonEntity) {
        super(bufflonEntity, false);
        this.bufflonEntity = bufflonEntity;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        if (this.bufflonEntity.canProtectOwner()) {
            LivingEntity livingentity = this.bufflonEntity.getOwner();
            if (livingentity == null) {
                return false;
            } else {
                this.attacker = livingentity.getLastHurtByMob();
                int i = livingentity.getLastHurtByMobTimestamp();
                return i != this.timestamp && this.canAttack(this.attacker, TargetingConditions.DEFAULT) && this.bufflonEntity.shouldAttackEntity(this.attacker, livingentity);
            }
        } else {
            return false;
        }
    }

    @Override
    public void start() {
        this.mob.setTarget(this.attacker);
        LivingEntity livingentity = this.bufflonEntity.getOwner();
        if (livingentity != null) {
            this.timestamp = livingentity.getLastHurtByMobTimestamp();
        }

        super.start();
    }
}
