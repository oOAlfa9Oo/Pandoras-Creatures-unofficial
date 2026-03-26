package andrews.pandoras_creatures.entities.goals.end_troll;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.entities.end_troll.EndTrollBehaviorRules;
import andrews.pandoras_creatures.util.NetworkUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class EndTrollTransformGoal extends Goal {
    private final EndTrollEntity endTroll;
    private LivingEntity target;

    public EndTrollTransformGoal(EndTrollEntity endTroll) {
        this.endTroll = endTroll;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        LivingEntity livingentity = this.endTroll.getTarget();
        return EndTrollBehaviorRules.shouldTryTransform(
                this.endTroll.isEntityStanding(),
                EndTrollBehaviorRules.isValidCombatTarget(livingentity),
                livingentity == null ? Double.MAX_VALUE : this.endTroll.distanceToSqr(livingentity)
        );
    }

    @Override
    public void start() {
        super.start();
        this.endTroll.getNavigation().stop();
        this.target = this.endTroll.getTarget();
    }

    @Override
    public void stop() {
        super.stop();
        this.target = null;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.target != null) {
            if (this.endTroll.isAnimationPlaying(EndTrollEntity.BLANK_ANIMATION) && !this.endTroll.isWorldRemote()) {
                NetworkUtil.sendAnimationPacket(this.endTroll, EndTrollEntity.TRANSFORM_ANIMATION);
            }
        }
    }
}
