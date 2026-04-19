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

public class EndTrollBulletWitherEntity extends AbstractEndTrollBulletEntity {
    private static final DustParticleOptions BLACK_PARTICLE = new DustParticleOptions(0x000000, 1.0F);

    public EndTrollBulletWitherEntity(EntityType<? extends EndTrollBulletWitherEntity> type, Level level) {
        super(type, level);
    }

    public EndTrollBulletWitherEntity(Level level, LivingEntity owner, net.minecraft.world.entity.Entity target, Direction.Axis directionAxis) {
        super(PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.END_TROLL_BULLET_WITHER), level, owner, target, directionAxis);
    }

    @Override
    protected ParticleOptions getParticleType() {
        return BLACK_PARTICLE;
    }

    @Override
    protected void onEntityHit(LivingEntity target) {
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 1));
    }
}
