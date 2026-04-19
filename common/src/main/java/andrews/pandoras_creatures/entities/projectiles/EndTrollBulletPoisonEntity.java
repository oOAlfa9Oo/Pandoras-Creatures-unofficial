package andrews.pandoras_creatures.entities.projectiles;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EndTrollBulletPoisonEntity extends AbstractEndTrollBulletEntity {
    private static final DustParticleOptions GREEN_PARTICLE = new DustParticleOptions(0x00FF00, 1.0F);

    public EndTrollBulletPoisonEntity(EntityType<? extends EndTrollBulletPoisonEntity> type, Level level) {
        super(type, level);
    }

    public EndTrollBulletPoisonEntity(Level level, LivingEntity owner, net.minecraft.world.entity.Entity target, Direction.Axis directionAxis) {
        super(PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.END_TROLL_BULLET_POISON), level, owner, target, directionAxis);
    }

    @Override
    protected ParticleOptions getParticleType() {
        return GREEN_PARTICLE;
    }

    @Override
    protected void onEntityHit(LivingEntity target) {
        target.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1));
    }
}
