package andrews.pandoras_creatures.entities;

import andrews.pandoras_creatures.test.PCGameTestAssertions;

import andrews.pandoras_creatures.entities.hellhound.HellhoundVariantCatalog;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.test.PCGameTestRegistry;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;

public final class HellhoundGameTests {
    private static final String HELLHOUND_BATCH = "hellhound";
    private static final String SHARED_TEMPLATE = "gametest/bufflon_arena";
    private static final BlockPos HELLHOUND_POS = new BlockPos(3, 2, 3);
    private static final BlockPos TARGET_POS = new BlockPos(7, 2, 3);

    private HellhoundGameTests() {
    }

    public static void saveDataRestoresVariant(GameTestHelper helper) {
        HellhoundEntity hellhound = new HellhoundEntity(PCGameTestRegistry.entityType(PCEntityIds.HELLHOUND), helper.getLevel());
        hellhound.setHellhoundType(HellhoundVariantCatalog.WITHER_TYPE);

        CompoundTag savedData = new CompoundTag();
        hellhound.addAdditionalSaveData(savedData);

        HellhoundEntity restored = new HellhoundEntity(PCGameTestRegistry.entityType(PCEntityIds.HELLHOUND), helper.getLevel());
        restored.readAdditionalSaveData(savedData);

        PCGameTestAssertions.assertValueEqual(helper, restored.getHellhoundType(), HellhoundVariantCatalog.WITHER_TYPE, "restored hellhound type");
        helper.succeed();
    }

    public static void witherVariantAttackAppliesWither(GameTestHelper helper) {
        HellhoundEntity hellhound = helper.spawn(PCGameTestRegistry.entityType(PCEntityIds.HELLHOUND), HELLHOUND_POS);
        hellhound.setHellhoundType(HellhoundVariantCatalog.WITHER_TYPE);
        Cow target = helper.spawn(EntityType.COW, TARGET_POS);

        boolean hurt = hellhound.doHurtTarget((net.minecraft.server.level.ServerLevel) hellhound.level(), target);

        PCGameTestAssertions.assertTrue(helper, hurt, "Hellhound attack should damage the target");
        PCGameTestAssertions.assertTrue(helper, target.hasEffect(MobEffects.WITHER), "Wither hellhound should apply wither");
        helper.succeed();
    }
}
