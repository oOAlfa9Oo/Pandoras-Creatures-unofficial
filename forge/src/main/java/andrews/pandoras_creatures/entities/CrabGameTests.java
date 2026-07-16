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
public final class CrabGameTests {
    private static final String CRAB_BATCH = "crab";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos CRAB_POS = new BlockPos(3, 2, 3);

    private CrabGameTests() {
    }

    @GameTest(template = SHARED_TEMPLATE, batch = CRAB_BATCH)
    public static void saveDataRestoresVariant(GameTestHelper helper) {
        CrabEntity crab = new CrabEntity(PCForgeEntities.crab(), helper.getLevel());
        crab.setCrabType(2);

        CompoundTag savedData = new CompoundTag();
        crab.addAdditionalSaveData(savedData);

        CrabEntity restored = new CrabEntity(PCForgeEntities.crab(), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        helper.assertTrue((restored.getCrabType()) == (2), "restored crab type" + ": expected " + (2) + ", got " + (restored.getCrabType()));
        helper.succeed();
    }

    @GameTest(template = SHARED_TEMPLATE, batch = CRAB_BATCH)
    public static void bucketTagRestoresVariant(GameTestHelper helper) {
        CrabEntity crab = helper.spawn(PCForgeEntities.crab(), CRAB_POS);
        crab.setCrabType(2);

        ItemStack bucket = crab.getBucketItemStack();
        crab.saveToBucketTag(bucket);

        // 1.20.1: los datos del bucket viven en el NBT del stack (no hay data components)
        CompoundTag bucketTag = bucket.getTag();
        helper.assertTrue(bucketTag != null, "Crab bucket should contain entity data");

        CrabEntity restored = new CrabEntity(PCForgeEntities.crab(), helper.getLevel());
        restored.loadFromBucketTag(bucketTag);

        helper.assertTrue((restored.getCrabType()) == (2), "bucket crab type" + ": expected " + (2) + ", got " + (restored.getCrabType()));
        helper.succeed();
    }
}
