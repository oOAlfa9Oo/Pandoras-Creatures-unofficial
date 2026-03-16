package andrews.pandoras_creatures.entities.goals.end_troll;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.util.NetworkUtil;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class EndTrollScreamGoal extends Goal {
    private final EndTrollEntity goalOwner;

    public EndTrollScreamGoal(EndTrollEntity goalOwner) {
        this.goalOwner = goalOwner;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (goalOwner.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        LivingEntity livingentity = goalOwner.getTarget();
        if (livingentity != null && livingentity.isAlive() && goalOwner.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION) && !goalOwner.isWorldRemote()) {
            if (!this.goalOwner.getNavigation().isDone() && this.goalOwner.distanceTo(livingentity) < 10) {
                return goalOwner.screamCooldown == 0;
            }
        }
        return false;
    }

    @Override
    public void start() {
        super.start();
        this.goalOwner.getNavigation().stop();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.goalOwner.getTarget() != null) {
            if (this.goalOwner.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION) && !this.goalOwner.isWorldRemote()) {
                NetworkUtil.sendAnimationPacket(this.goalOwner, EndTrollEntity.SCREAM_ANIMATION);
                goalOwner.screamCooldown = 400;
            }
        }
    }
}
