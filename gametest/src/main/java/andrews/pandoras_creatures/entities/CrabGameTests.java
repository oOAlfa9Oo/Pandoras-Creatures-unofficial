package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public final class CrabGameTests {
    private static final String CRAB_BATCH = "crab";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos CRAB_POS = new BlockPos(3, 2, 3);

    private CrabGameTests() {
    }

    public static void saveDataRestoresVariant(GameTestHelper helper) {
        CrabEntity crab = new CrabEntity(PCGameTestRegistry.entityType(PCEntityIds.CRAB), helper.getLevel());
        crab.setCrabType(2);

        CompoundTag savedData = new CompoundTag();
        crab.addAdditionalSaveData(savedData);

        CrabEntity restored = new CrabEntity(PCGameTestRegistry.entityType(PCEntityIds.CRAB), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        helper.assertTrue((restored.getCrabType()) == (2), "restored crab type" + ": expected " + (2) + ", got " + (restored.getCrabType()));
        helper.succeed();
    }

    public static void bucketTagRestoresVariant(GameTestHelper helper) {
        CrabEntity crab = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.CRAB), CRAB_POS);
        crab.setCrabType(2);

        ItemStack bucket = crab.getBucketItemStack();
        crab.saveToBucketTag(bucket);

        // 1.20.1: los datos del bucket viven en el NBT del stack (no hay data components)
        CompoundTag bucketTag = bucket.getTag();
        helper.assertTrue(bucketTag != null, "Crab bucket should contain entity data");

        CrabEntity restored = new CrabEntity(PCGameTestRegistry.entityType(PCEntityIds.CRAB), helper.getLevel());
        restored.loadFromBucketTag(bucketTag);

        helper.assertTrue((restored.getCrabType()) == (2), "bucket crab type" + ": expected " + (2) + ", got " + (restored.getCrabType()));
        helper.succeed();
    }
}
