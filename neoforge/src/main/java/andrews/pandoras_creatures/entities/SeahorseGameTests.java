package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;

@GameTestHolder(Reference.MODID)
@PrefixGameTestTemplate(false)
public final class SeahorseGameTests {
    private static final String SEAHORSE_BATCH = "seahorse";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos SEAHORSE_POS = new BlockPos(3, 2, 3);

    private SeahorseGameTests() {
    }

    @GameTest(template = SHARED_TEMPLATE, batch = SEAHORSE_BATCH)
    public static void saveDataRestoresVariantAndSize(GameTestHelper helper) {
        SeahorseEntity seahorse = new SeahorseEntity(PCEntities.SEAHORSE.get(), helper.getLevel());
        seahorse.setSeahorseType(8);
        seahorse.setSeahorseSize(5);

        CompoundTag savedData = new CompoundTag();
        seahorse.addAdditionalSaveData(savedData);

        SeahorseEntity restored = new SeahorseEntity(PCEntities.SEAHORSE.get(), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        helper.assertValueEqual(restored.getSeahorseType(), 8, "restored seahorse type");
        helper.assertValueEqual(restored.getSeahorseSize(), 5, "restored seahorse size");
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = SEAHORSE_BATCH)
    public static void bucketTagRestoresVariantAndSize(GameTestHelper helper) {
        SeahorseEntity seahorse = helper.spawn(PCEntities.SEAHORSE.get(), SEAHORSE_POS);
        seahorse.setSeahorseType(6);
        seahorse.setSeahorseSize(4);

        ItemStack bucket = seahorse.getBucketItemStack();
        seahorse.saveToBucketTag(bucket);

        CustomData customData = bucket.get(DataComponents.BUCKET_ENTITY_DATA);
        helper.assertTrue(customData != null, "Seahorse bucket should contain entity data");

        SeahorseEntity restored = new SeahorseEntity(PCEntities.SEAHORSE.get(), helper.getLevel());
        restored.loadFromBucketTag(customData.copyTag());

        helper.assertValueEqual(restored.getSeahorseType(), 6, "bucket seahorse type");
        helper.assertValueEqual(restored.getSeahorseSize(), 4, "bucket seahorse size");
        helper.succeed();
    }
}
