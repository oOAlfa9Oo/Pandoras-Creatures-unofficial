package andrews.pandoras_creatures.entities.projectiles;

import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.forge.registry.PCForgeEntities;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

@GameTestHolder(Reference.MODID)
@PrefixGameTestTemplate(false)
public final class EndTrollProjectileGameTests {
    private static final String END_TROLL_PROJECTILE_BATCH = "end_troll_projectiles";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos OWNER_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(7, 2, 3);

    private EndTrollProjectileGameTests() {
    }

    @GameTest(template = SHARED_TEMPLATE, batch = END_TROLL_PROJECTILE_BATCH)
    public static void projectileSaveDataRestoresOwnerTargetAndMotion(GameTestHelper helper) {
        EndTrollEntity owner = helper.spawn(PCForgeEntities.endTroll(), OWNER_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        EndTrollBulletPoisonEntity bullet = new EndTrollBulletPoisonEntity(helper.getLevel(), owner, target, Direction.Axis.X);

        bullet.steps = 7;
        bullet.targetDeltaX = 0.25D;
        bullet.targetDeltaY = 0.5D;
        bullet.targetDeltaZ = -0.75D;

        CompoundTag savedData = new CompoundTag();
        bullet.addAdditionalSaveData(savedData);

        EndTrollBulletPoisonEntity restored = new EndTrollBulletPoisonEntity(PCForgeEntities.endTrollBulletPoison(), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        helper.assertTrue(owner.getUUID().equals(restored.ownerUniqueId), "Restored projectile should preserve owner UUID");
        helper.assertTrue(target.getUUID().equals(restored.targetUniqueId), "Restored projectile should preserve target UUID");
        helper.assertTrue((restored.steps) == (7), "restored projectile step count" + ": expected " + (7) + ", got " + (restored.steps));
        helper.assertTrue(restored.direction != null, "Restored projectile should preserve direction");
        helper.assertTrue(restored.ownerBlockPos != null, "Restored projectile should preserve owner block position");
        helper.assertTrue(restored.targetBlockPos != null, "Restored projectile should preserve target block position");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = END_TROLL_PROJECTILE_BATCH)
    public static void poisonBulletHitAppliesEffectAndDiscards(GameTestHelper helper) {
        EndTrollEntity owner = helper.spawn(PCForgeEntities.endTroll(), OWNER_POS);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);
        EndTrollBulletPoisonEntity bullet = new EndTrollBulletPoisonEntity(helper.getLevel(), owner, target, Direction.Axis.X);

        bullet.bulletHit(new EntityHitResult(target));

        helper.assertTrue(target.hasEffect(MobEffects.POISON), "Poison bullet should apply poison to the target");
        helper.assertTrue(bullet.isRemoved(), "Poison bullet should be discarded after hitting an entity");
        helper.succeed();
    }
}
