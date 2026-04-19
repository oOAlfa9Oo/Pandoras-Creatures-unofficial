package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.entities.hellhound.HellhoundVariantCatalog;
import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.test.PCGameTestSerialization;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.cow.Cow;

public final class HellhoundGameTests {
    private static final BlockPos HELLHOUND_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(7, 2, 3);

    private HellhoundGameTests() {
    }

    public static void saveDataRestoresVariant(GameTestHelper helper) {
        HellhoundEntity hellhound = new HellhoundEntity(PCEntities.HELLHOUND.get(), helper.getLevel());
        hellhound.setHellhoundType(HellhoundVariantCatalog.WITHER_TYPE);

        CompoundTag savedData = new CompoundTag();
        savedData = PCGameTestSerialization.save(helper, hellhound);

        HellhoundEntity restored = new HellhoundEntity(PCEntities.HELLHOUND.get(), helper.getLevel());
        PCGameTestSerialization.load(helper, restored, savedData);

        helper.assertValueEqual(restored.getHellhoundType(), HellhoundVariantCatalog.WITHER_TYPE, "restored hellhound type");
        helper.succeed();
    }

    public static void witherVariantAttackAppliesWither(GameTestHelper helper) {
        HellhoundEntity hellhound = helper.spawn(PCEntities.HELLHOUND.get(), HELLHOUND_POS);
        hellhound.setHellhoundType(HellhoundVariantCatalog.WITHER_TYPE);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);

        boolean hurt = hellhound.doHurtTarget(helper.getLevel(), target);

        helper.assertTrue(hurt, "Hellhound attack should damage the target");
        helper.assertTrue(target.hasEffect(MobEffects.WITHER), "Wither hellhound should apply wither");
        helper.succeed();
    }
}
