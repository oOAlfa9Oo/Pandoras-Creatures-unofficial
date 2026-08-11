package andrews.pandoras_creatures.gametest;

import andrews.pandoras_creatures.test.PCGameTestAssertions;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.block_entities.EndTrollBoxBlockEntity;
import andrews.pandoras_creatures.menu.EndTrollBoxMenu;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public final class EndTrollBoxGameTests {
    private static final BlockPos BOX_POS = new BlockPos(3, 2, 3);

    private EndTrollBoxGameTests() {
    }

    public static void menuAcceptsNormalAndShiftClickInsertion(GameTestHelper helper) {
        EndTrollBoxBlockEntity box = placeBox(helper);
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        EndTrollBoxMenu menu = new EndTrollBoxMenu(1, player.getInventory(), box);

        menu.setCarried(new ItemStack(Items.DIAMOND, 3));
        menu.clicked(0, 0, ClickType.PICKUP, player);
        PCGameTestAssertions.assertTrue(helper, box.getItem(0).is(Items.DIAMOND) && box.getItem(0).getCount() == 3,
                "Normal click should insert carried items into the End Troll Box");
        PCGameTestAssertions.assertTrue(helper, menu.getCarried().isEmpty(), "Normal insertion should clear the carried stack");

        player.getInventory().setItem(9, new ItemStack(Items.COBBLESTONE, 5));
        ItemStack moved = menu.quickMoveStack(player, 54);
        PCGameTestAssertions.assertTrue(helper, !moved.isEmpty(), "Shift-click should report a moved stack");
        PCGameTestAssertions.assertTrue(helper, contains(box, Items.COBBLESTONE, 5),
                "Shift-click should insert player inventory items into the End Troll Box");

        menu.removed(player);
        helper.succeed();
    }

    public static void menuRejectsNestedPortableBoxes(GameTestHelper helper) {
        EndTrollBoxBlockEntity box = placeBox(helper);
        Player player = helper.makeMockPlayer(GameType.SURVIVAL);
        EndTrollBoxMenu menu = new EndTrollBoxMenu(2, player.getInventory(), box);

        menu.setCarried(new ItemStack(Blocks.SHULKER_BOX));
        menu.clicked(0, 0, ClickType.PICKUP, player);
        PCGameTestAssertions.assertTrue(helper, box.getItem(0).isEmpty(), "End Troll Box should reject nested Shulker Boxes");

        Block trollBoxBlock = PandorasCreaturesCommon.platform().registry().block(PCBlockIds.END_TROLL_BOX);
        menu.setCarried(new ItemStack(trollBoxBlock));
        menu.clicked(1, 0, ClickType.PICKUP, player);
        PCGameTestAssertions.assertTrue(helper, box.getItem(1).isEmpty(), "End Troll Box should reject nested End Troll Boxes");

        menu.removed(player);
        helper.succeed();
    }

    public static void inventorySurvivesBlockEntitySaveAndLoad(GameTestHelper helper) {
        EndTrollBoxBlockEntity box = placeBox(helper);
        box.setItem(0, new ItemStack(Items.EMERALD, 7));
        box.setItem(53, new ItemStack(Items.GOLD_INGOT, 11));

        CompoundTag saved = box.saveToTag(new CompoundTag(), helper.getLevel().registryAccess());
        EndTrollBoxBlockEntity restored = new EndTrollBoxBlockEntity(
                helper.absolutePos(BOX_POS.offset(1, 0, 0)), box.getBlockState());
        restored.loadFromTag(saved, helper.getLevel().registryAccess());

        PCGameTestAssertions.assertTrue(helper, restored.getItem(0).is(Items.EMERALD) && restored.getItem(0).getCount() == 7,
                "Saved End Troll Box should restore its first slot");
        PCGameTestAssertions.assertTrue(helper, restored.getItem(53).is(Items.GOLD_INGOT) && restored.getItem(53).getCount() == 11,
                "Saved End Troll Box should restore its last slot");
        helper.succeed();
    }

    private static EndTrollBoxBlockEntity placeBox(GameTestHelper helper) {
        Block block = PandorasCreaturesCommon.platform().registry().block(PCBlockIds.END_TROLL_BOX);
        helper.setBlock(BOX_POS, block);
        PCGameTestAssertions.assertTrue(helper, helper.getBlockEntity(BOX_POS, EndTrollBoxBlockEntity.class) != null,
                "Placed End Troll Box should create its block entity");
        return helper.getBlockEntity(BOX_POS, EndTrollBoxBlockEntity.class);
    }

    private static boolean contains(EndTrollBoxBlockEntity box, Item item, int count) {
        for (int slot = 0; slot < box.getContainerSize(); slot++) {
            ItemStack stack = box.getItem(slot);
            if (stack.is(item) && stack.getCount() == count) {
                return true;
            }
        }
        return false;
    }
}
