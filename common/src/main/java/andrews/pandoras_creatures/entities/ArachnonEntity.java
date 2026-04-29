package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.entities.arachnon.ArachnonAttackRules;
import andrews.pandoras_creatures.entities.arachnon.ArachnonSpawnTuning;
import andrews.pandoras_creatures.entities.bases.AnimatedMonsterEntity;
import andrews.pandoras_creatures.entities.goals.arachnon.MeleeAttackGoalWithRange;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.sound.PCSoundCatalog;
import andrews.pandoras_creatures.util.animation.Animation;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class ArachnonEntity extends AnimatedMonsterEntity {
    private int attackTimer;

    public ArachnonEntity(EntityType<? extends ArachnonEntity> type, Level level) {
        super(type, level);
    }

    public ArachnonEntity(Level level, double posX, double posY, double posZ) {
        this(PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.ARACHNON), level);
        this.moveTo(posX, posY, posZ);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoalWithRange(this, 0.48D, false, 10.0F));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.48D));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(PandorasCreaturesCommon.platform().registry().item(PCItemIds.ARACHNON_SPAWN_EGG));
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[0];
    }

    @Override
    public int getExperienceReward() {
        this.xpReward = (int) ((float) this.xpReward * 5.0F);
        return super.getExperienceReward();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.attackTimer = ArachnonAttackRules.tickAttackTimer(this.attackTimer);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        this.attackTimer = ArachnonAttackRules.ATTACK_TIMER_TICKS;
        this.level().broadcastEntityEvent(this, ArachnonAttackRules.ATTACK_EVENT_ID);
        boolean flag = target.hurt(this.damageSources().mobAttack(this), (float) ArachnonAttackRules.attackDamageFromRoll(this.random.nextInt(5)));
        return flag;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == ArachnonAttackRules.ATTACK_EVENT_ID) {
            this.attackTimer = ArachnonAttackRules.ATTACK_TIMER_TICKS;
        } else {
            super.handleEntityEvent(id);
        }
    }

    public int getAttackTimer() {
        return this.attackTimer;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return ArachnonSpawnTuning.maxSpawnGroup();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.ARACHNON_AMBIENT);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.ARACHNON_HURT);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return PandorasCreaturesCommon.platform().registry().sound(PCSoundCatalog.ARACHNON_DEATH);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.5F, 0.4F);
    }

}
