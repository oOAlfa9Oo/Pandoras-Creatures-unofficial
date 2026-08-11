package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.test.PCGameTestAssertions;

import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

public final class SeahorseGameTests {
    private static final String SEAHORSE_BATCH = "seahorse";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos SEAHORSE_POS = new BlockPos(3, 2, 3);

    private SeahorseGameTests() {
    }

    public static void saveDataRestoresVariantAndSize(GameTestHelper helper) {
        SeahorseEntity seahorse = new SeahorseEntity(PCGameTestRegistry.entityType(PCEntityIds.SEAHORSE), helper.getLevel());
        seahorse.setSeahorseType(8);
        seahorse.setSeahorseSize(5);

        CompoundTag savedData = new CompoundTag();
        seahorse.addAdditionalSaveData(savedData);

        SeahorseEntity restored = new SeahorseEntity(PCGameTestRegistry.entityType(PCEntityIds.SEAHORSE), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        PCGameTestAssertions.assertValueEqual(helper, restored.getSeahorseType(), 8, "restored seahorse type");
        PCGameTestAssertions.assertValueEqual(helper, restored.getSeahorseSize(), 5, "restored seahorse size");
        helper.succeed();
    }

    public static void bucketTagRestoresVariantAndSize(GameTestHelper helper) {
        SeahorseEntity seahorse = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.SEAHORSE), SEAHORSE_POS);
        seahorse.setSeahorseType(6);
        seahorse.setSeahorseSize(4);

        ItemStack bucket = seahorse.getBucketItemStack();
        seahorse.saveToBucketTag(bucket);

        CustomData customData = bucket.get(DataComponents.BUCKET_ENTITY_DATA);
        PCGameTestAssertions.assertTrue(helper, customData != null, "Seahorse bucket should contain entity data");

        SeahorseEntity restored = new SeahorseEntity(PCGameTestRegistry.entityType(PCEntityIds.SEAHORSE), helper.getLevel());
        restored.loadFromBucketTag(customData.copyTag());

        PCGameTestAssertions.assertValueEqual(helper, restored.getSeahorseType(), 6, "bucket seahorse type");
        PCGameTestAssertions.assertValueEqual(helper, restored.getSeahorseSize(), 4, "bucket seahorse size");
        helper.succeed();
    }
}
