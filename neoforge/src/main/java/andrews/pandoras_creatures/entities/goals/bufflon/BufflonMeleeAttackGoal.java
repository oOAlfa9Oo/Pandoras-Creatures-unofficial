package andrews.pandoras_creatures.entities.goals.bufflon;

import andrews.pandoras_creatures.entities.BufflonEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.player.Player;

public class BufflonMeleeAttackGoal extends MeleeAttackGoal {

    public BufflonMeleeAttackGoal(PathfinderMob creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
    }

    @Override
    public boolean canUse() {
        if (this.mob instanceof BufflonEntity) {
            if (this.mob.level().getDifficulty() == Difficulty.PEACEFUL && this.mob.getTarget() != null && this.mob.getTarget() instanceof Player) {
                return false;
            }
        }
        return super.canUse();
    }
}
