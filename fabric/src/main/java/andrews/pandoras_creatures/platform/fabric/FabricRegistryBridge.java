package andrews.pandoras_creatures.platform.fabric;

import andrews.pandoras_creatures.platform.RegistryBridge;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
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

final class FabricRegistryBridge implements RegistryBridge {
    static final FabricRegistryBridge INSTANCE = new FabricRegistryBridge();

    private FabricRegistryBridge() {
    }

    @Override
    public String namespace() {
        return Reference.MODID;
    }

    @Override
    public Item item(String path) {
        return BuiltInRegistries.ITEM.get(id(path));
    }

    @Override
    public Block block(String path) {
        return BuiltInRegistries.BLOCK.get(id(path));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Entity> EntityType<T> entityType(String path) {
        return (EntityType<T>) BuiltInRegistries.ENTITY_TYPE.get(id(path));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BlockEntity> BlockEntityType<T> blockEntityType(String path) {
        return (BlockEntityType<T>) BuiltInRegistries.BLOCK_ENTITY_TYPE.get(id(path));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractContainerMenu> MenuType<T> menuType(String path) {
        return (MenuType<T>) BuiltInRegistries.MENU.get(id(path));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends net.minecraft.world.item.crafting.Recipe<?>> RecipeSerializer<T> recipeSerializer(String path) {
        return (RecipeSerializer<T>) BuiltInRegistries.RECIPE_SERIALIZER.get(id(path));
    }

    @Override
    public SoundEvent sound(String path) {
        return BuiltInRegistries.SOUND_EVENT.get(id(path));
    }

    @Override
    public StructureType<?> structureType(String path) {
        return BuiltInRegistries.STRUCTURE_TYPE.get(id(path));
    }

    @Override
    public StructurePieceType structurePieceType(String path) {
        return BuiltInRegistries.STRUCTURE_PIECE.get(id(path));
    }
}
