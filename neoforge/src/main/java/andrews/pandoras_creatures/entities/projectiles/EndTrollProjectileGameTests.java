package andrews.pandoras_creatures.entities.projectiles;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.test.PCGameTestSerialization;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.phys.EntityHitResult;

public final class EndTrollProjectileGameTests {
    private static final BlockPos OWNER_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(7, 2, 3);

    private EndTrollProjectileGameTests() {
    }

    public static void projectileSaveDataRestoresOwnerTargetAndMotion(GameTestHelper helper) {
        EndTrollEntity owner = helper.spawn(PCEntities.END_TROLL.get(), OWNER_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        EndTrollBulletPoisonEntity bullet = new EndTrollBulletPoisonEntity(helper.getLevel(), owner, target, Direction.Axis.X);

        bullet.steps = 7;
        bullet.targetDeltaX = 0.25D;
        bullet.targetDeltaY = 0.5D;
        bullet.targetDeltaZ = -0.75D;

        CompoundTag savedData = new CompoundTag();
        savedData = PCGameTestSerialization.save(helper, bullet);

        EndTrollBulletPoisonEntity restored = new EndTrollBulletPoisonEntity(PCEntities.END_TROLL_BULLET_POISON.get(), helper.getLevel());
        PCGameTestSerialization.load(helper, restored, savedData);

        helper.assertTrue(owner.getUUID().equals(restored.ownerUniqueId), "Restored projectile should preserve owner UUID");
        helper.assertTrue(target.getUUID().equals(restored.targetUniqueId), "Restored projectile should preserve target UUID");
        helper.assertValueEqual(restored.steps, 7, "restored projectile step count");
        helper.assertTrue(restored.direction != null, "Restored projectile should preserve direction");
        helper.assertTrue(restored.ownerBlockPos != null, "Restored projectile should preserve owner block position");
        helper.assertTrue(restored.targetBlockPos != null, "Restored projectile should preserve target block position");
        helper.succeed();
    }

    public static void poisonBulletHitAppliesEffectAndDiscards(GameTestHelper helper) {
        EndTrollEntity owner = helper.spawn(PCEntities.END_TROLL.get(), OWNER_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        EndTrollBulletPoisonEntity bullet = new EndTrollBulletPoisonEntity(helper.getLevel(), owner, target, Direction.Axis.X);

        bullet.bulletHit(new EntityHitResult(target));

        helper.assertTrue(target.hasEffect(MobEffects.POISON), "Poison bullet should apply poison to the target");
        helper.assertTrue(bullet.isRemoved(), "Poison bullet should be discarded after hitting an entity");
        helper.succeed();
    }
}
