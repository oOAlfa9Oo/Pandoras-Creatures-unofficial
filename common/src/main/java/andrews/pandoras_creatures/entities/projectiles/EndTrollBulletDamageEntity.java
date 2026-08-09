package andrews.pandoras_creatures.entities.projectiles;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import net.minecraft.util.ARGB;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class EndTrollBulletDamageEntity extends AbstractEndTrollBulletEntity {
    private static final DustParticleOptions RED_PARTICLE = new DustParticleOptions(ARGB.colorFromFloat(1.0F, 141/255f, 0, 0), 1.0F);

    public EndTrollBulletDamageEntity(EntityType<? extends EndTrollBulletDamageEntity> type, Level level) {
        super(type, level);
    }

    public EndTrollBulletDamageEntity(Level level, LivingEntity owner, net.minecraft.world.entity.Entity target, Direction.Axis directionAxis) {
        super(PandorasCreaturesCommon.platform().registry().entityType(PCEntityIds.END_TROLL_BULLET_DAMAGE), level, owner, target, directionAxis);
    }

    @Override
    protected ParticleOptions getParticleType() {
        return RED_PARTICLE;
    }

    @Override
    protected void onEntityHit(LivingEntity target) {
        // Additional damage
        target.hurt(this.damageSources().mobProjectile(this, this.owner), 8.0F);
    }
}
