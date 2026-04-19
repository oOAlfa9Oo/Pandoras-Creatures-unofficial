package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.test.PCGameTestSerialization;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public final class CrabGameTests {
    private static final BlockPos CRAB_POS = new BlockPos(3, 2, 3);

    private CrabGameTests() {
    }

    public static void saveDataRestoresVariant(GameTestHelper helper) {
        CrabEntity crab = new CrabEntity(PCEntities.CRAB.get(), helper.getLevel());
        crab.setCrabType(2);

        CompoundTag savedData = new CompoundTag();
        savedData = PCGameTestSerialization.save(helper, crab);

        CrabEntity restored = new CrabEntity(PCEntities.CRAB.get(), helper.getLevel());
        PCGameTestSerialization.load(helper, restored, savedData);

        helper.assertValueEqual(restored.getCrabType(), 2, "restored crab type");
        helper.succeed();
    }

    public static void bucketTagRestoresVariant(GameTestHelper helper) {
        CrabEntity crab = helper.spawn(PCEntities.CRAB.get(), CRAB_POS);
        crab.setCrabType(2);

        ItemStack bucket = crab.getBucketItemStack();
        crab.saveToBucketTag(bucket);

        CustomData customData = bucket.get(DataComponents.BUCKET_ENTITY_DATA);
        helper.assertTrue(customData != null, "Crab bucket should contain entity data");

        CrabEntity restored = new CrabEntity(PCEntities.CRAB.get(), helper.getLevel());
        restored.loadFromBucketTag(customData.copyTag());

        helper.assertValueEqual(restored.getCrabType(), 2, "bucket crab type");
        helper.succeed();
    }
}
