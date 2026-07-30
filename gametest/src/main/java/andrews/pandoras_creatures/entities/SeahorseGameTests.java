package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestSerialization;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public final class SeahorseGameTests {
    private static final BlockPos SEAHORSE_POS = new BlockPos(3, 2, 3);

    private SeahorseGameTests() {
    }

    public static void saveDataRestoresVariantAndSize(GameTestHelper helper) {
        SeahorseEntity seahorse = new SeahorseEntity(PCGameTestRegistry.entityType(PCEntityIds.SEAHORSE), helper.getLevel());
        seahorse.setSeahorseType(8);
        seahorse.setSeahorseSize(5);

        CompoundTag savedData = new CompoundTag();
        savedData = PCGameTestSerialization.save(helper, seahorse);

        SeahorseEntity restored = new SeahorseEntity(PCGameTestRegistry.entityType(PCEntityIds.SEAHORSE), helper.getLevel());
        PCGameTestSerialization.load(helper, restored, savedData);

        helper.assertValueEqual(restored.getSeahorseType(), 8, "restored seahorse type");
        helper.assertValueEqual(restored.getSeahorseSize(), 5, "restored seahorse size");
        helper.succeed();
    }

    public static void bucketTagRestoresVariantAndSize(GameTestHelper helper) {
        SeahorseEntity seahorse = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.SEAHORSE), SEAHORSE_POS);
        seahorse.setSeahorseType(6);
        seahorse.setSeahorseSize(4);

        ItemStack bucket = seahorse.getBucketItemStack();
        seahorse.saveToBucketTag(bucket);

        CustomData customData = bucket.get(DataComponents.BUCKET_ENTITY_DATA);
        helper.assertTrue(customData != null, "Seahorse bucket should contain entity data");

        SeahorseEntity restored = new SeahorseEntity(PCGameTestRegistry.entityType(PCEntityIds.SEAHORSE), helper.getLevel());
        restored.loadFromBucketTag(customData.copyTag());

        helper.assertValueEqual(restored.getSeahorseType(), 6, "bucket seahorse type");
        helper.assertValueEqual(restored.getSeahorseSize(), 4, "bucket seahorse size");
        helper.succeed();
    }
}
