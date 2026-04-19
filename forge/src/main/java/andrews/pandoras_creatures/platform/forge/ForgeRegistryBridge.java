package andrews.pandoras_creatures.forge.platform;

import andrews.pandoras_creatures.platform.RegistryBridge;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

final class ForgeRegistryBridge implements RegistryBridge {
    static final ForgeRegistryBridge INSTANCE = new ForgeRegistryBridge();

    private ForgeRegistryBridge() {
    }

    @Override
    public String namespace() {
        return Reference.MODID;
    }

    @Override
    public Item item(String path) {
        return resolve(BuiltInRegistries.ITEM, "item", path);
    }

    @Override
    public Block block(String path) {
        return resolve(BuiltInRegistries.BLOCK, "block", path);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> EntityType<T> entityType(String path) {
        return (EntityType<T>) resolve(BuiltInRegistries.ENTITY_TYPE, "entity type", path);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> BlockEntityType<T> blockEntityType(String path) {
        return (BlockEntityType<T>) resolve(BuiltInRegistries.BLOCK_ENTITY_TYPE, "block entity type", path);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends AbstractContainerMenu> MenuType<T> menuType(String path) {
        return (MenuType<T>) resolve(BuiltInRegistries.MENU, "menu type", path);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends net.minecraft.world.item.crafting.Recipe<?>> RecipeSerializer<T> recipeSerializer(String path) {
        return (RecipeSerializer<T>) resolve(BuiltInRegistries.RECIPE_SERIALIZER, "recipe serializer", path);
    }

    @Override
    public SoundEvent sound(String path) {
        return resolve(BuiltInRegistries.SOUND_EVENT, "sound", path);
    }

    @Override
    public StructureType<?> structureType(String path) {
        return resolve(BuiltInRegistries.STRUCTURE_TYPE, "structure type", path);
    }

    @Override
    public StructurePieceType structurePieceType(String path) {
        return resolve(BuiltInRegistries.STRUCTURE_PIECE, "structure piece type", path);
    }

    private <T> T resolve(net.minecraft.core.Registry<T> registry, String type, String path) {
        Identifier id = Identifier.fromNamespaceAndPath(Reference.MODID, path);
        T value = registry.getValue(id);
        if (value == null) {
            throw new IllegalArgumentException("Unknown forge " + type + " id: " + id);
        }
        return value;
    }
}
