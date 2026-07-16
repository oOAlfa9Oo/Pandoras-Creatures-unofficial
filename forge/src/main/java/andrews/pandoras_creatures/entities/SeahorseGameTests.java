package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.forge.registry.PCForgeEntities;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;

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
        SeahorseEntity seahorse = new SeahorseEntity(PCForgeEntities.seahorse(), helper.getLevel());
        seahorse.setSeahorseType(8);
        seahorse.setSeahorseSize(5);

        CompoundTag savedData = new CompoundTag();
        seahorse.addAdditionalSaveData(savedData);

        SeahorseEntity restored = new SeahorseEntity(PCForgeEntities.seahorse(), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        helper.assertTrue((restored.getSeahorseType()) == (8), "restored seahorse type" + ": expected " + (8) + ", got " + (restored.getSeahorseType()));
        helper.assertTrue((restored.getSeahorseSize()) == (5), "restored seahorse size" + ": expected " + (5) + ", got " + (restored.getSeahorseSize()));
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = SEAHORSE_BATCH)
    public static void bucketTagRestoresVariantAndSize(GameTestHelper helper) {
        SeahorseEntity seahorse = helper.spawn(PCForgeEntities.seahorse(), SEAHORSE_POS);
        seahorse.setSeahorseType(6);
        seahorse.setSeahorseSize(4);

        ItemStack bucket = seahorse.getBucketItemStack();
        seahorse.saveToBucketTag(bucket);

        // 1.20.1: los datos del bucket viven en el NBT del stack (no hay data components)
        CompoundTag bucketTag = bucket.getTag();
        helper.assertTrue(bucketTag != null, "Seahorse bucket should contain entity data");

        SeahorseEntity restored = new SeahorseEntity(PCForgeEntities.seahorse(), helper.getLevel());
        restored.loadFromBucketTag(bucketTag);

        helper.assertTrue((restored.getSeahorseType()) == (6), "bucket seahorse type" + ": expected " + (6) + ", got " + (restored.getSeahorseType()));
        helper.assertTrue((restored.getSeahorseSize()) == (4), "bucket seahorse size" + ": expected " + (4) + ", got " + (restored.getSeahorseSize()));
        helper.succeed();
    }
}
