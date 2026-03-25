package andrews.pandoras_creatures.entities.goals.end_troll;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.entities.end_troll.EndTrollBehaviorRules;
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
        LivingEntity livingentity = goalOwner.getTarget();
        return EndTrollBehaviorRules.shouldTryScream(
                goalOwner.level().getDifficulty() != Difficulty.PEACEFUL,
                EndTrollBehaviorRules.isValidCombatTarget(livingentity),
                goalOwner.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION),
                !goalOwner.isWorldRemote(),
                this.goalOwner.getNavigation().isDone(),
                livingentity == null ? Double.MAX_VALUE : this.goalOwner.distanceTo(livingentity),
                goalOwner.getScreamCooldown()
        );
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
                goalOwner.resetScreamCooldown();
            }
        }
    }
}
