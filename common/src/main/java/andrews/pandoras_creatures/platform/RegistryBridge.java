package andrews.pandoras_creatures.platform;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

/**
 * Initial registry-facing bridge for shared code.
 */
public interface RegistryBridge {
    String namespace();

    default ResourceLocation id(String path) {
        return new ResourceLocation(namespace(), path);
    }

    Item item(String path);

    Block block(String path);

    <T extends Entity> EntityType<T> entityType(String path);

    <T extends BlockEntity> BlockEntityType<T> blockEntityType(String path);

    <T extends AbstractContainerMenu> MenuType<T> menuType(String path);

    <T extends net.minecraft.world.item.crafting.Recipe<?>> RecipeSerializer<T> recipeSerializer(String path);

    SoundEvent sound(String path);

    StructureType<?> structureType(String path);

    StructurePieceType structurePieceType(String path);
}

