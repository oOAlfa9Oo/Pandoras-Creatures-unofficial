package andrews.pandoras_creatures.entities.projectiles;

import andrews.pandoras_creatures.test.PCGameTestAssertions;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.phys.EntityHitResult;

public final class EndTrollProjectileGameTests {
    private static final String END_TROLL_PROJECTILE_BATCH = "end_troll_projectiles";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos OWNER_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(7, 2, 3);

    private EndTrollProjectileGameTests() {
    }

    public static void projectileSaveDataRestoresOwnerTargetAndMotion(GameTestHelper helper) {
        EndTrollEntity owner = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), OWNER_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        EndTrollBulletPoisonEntity bullet = new EndTrollBulletPoisonEntity(helper.getLevel(), owner, target, Direction.Axis.X);

        bullet.steps = 7;
        bullet.targetDeltaX = 0.25D;
        bullet.targetDeltaY = 0.5D;
        bullet.targetDeltaZ = -0.75D;

        CompoundTag savedData = new CompoundTag();
        bullet.addAdditionalSaveData(savedData);

        EndTrollBulletPoisonEntity restored = new EndTrollBulletPoisonEntity(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL_BULLET_POISON), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        PCGameTestAssertions.assertTrue(helper, owner.getUUID().equals(restored.ownerUniqueId), "Restored projectile should preserve owner UUID");
        PCGameTestAssertions.assertTrue(helper, target.getUUID().equals(restored.targetUniqueId), "Restored projectile should preserve target UUID");
        PCGameTestAssertions.assertValueEqual(helper, restored.steps, 7, "restored projectile step count");
        PCGameTestAssertions.assertTrue(helper, restored.direction != null, "Restored projectile should preserve direction");
        PCGameTestAssertions.assertTrue(helper, restored.ownerBlockPos != null, "Restored projectile should preserve owner block position");
        PCGameTestAssertions.assertTrue(helper, restored.targetBlockPos != null, "Restored projectile should preserve target block position");
        helper.succeed();
    }

    public static void poisonBulletHitAppliesEffectAndDiscards(GameTestHelper helper) {
        EndTrollEntity owner = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.END_TROLL), OWNER_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        EndTrollBulletPoisonEntity bullet = new EndTrollBulletPoisonEntity(helper.getLevel(), owner, target, Direction.Axis.X);

        bullet.bulletHit(new EntityHitResult(target));

        PCGameTestAssertions.assertTrue(helper, target.hasEffect(MobEffects.POISON), "Poison bullet should apply poison to the target");
        PCGameTestAssertions.assertTrue(helper, bullet.isRemoved(), "Poison bullet should be discarded after hitting an entity");
        helper.succeed();
    }
}
