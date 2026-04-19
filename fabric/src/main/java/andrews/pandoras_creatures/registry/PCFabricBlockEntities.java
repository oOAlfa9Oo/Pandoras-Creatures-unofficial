package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.block_entities.EndTrollBoxBlockEntity;
import andrews.pandoras_creatures.registry.block.PCBlockEntityIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.util.Set;

public final class PCFabricBlockEntities {
    public static BlockEntityType<EndTrollBoxBlockEntity> END_TROLL_BOX;

    private static boolean initialized;

    private PCFabricBlockEntities() {
    }

    public static void register() {
        if (initialized) {
            return;
        }
        initialized = true;

        END_TROLL_BOX = Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                Identifier.fromNamespaceAndPath(Reference.MODID, PCBlockEntityIds.END_TROLL_BOX),
                createBlockEntityType(EndTrollBoxBlockEntity::new, PCFabricBlocks.getEndTrollBoxBlockArray())
        );
    }

    @SuppressWarnings("unchecked")
    private static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(
            BlockEntityFactory<? extends T> factory,
            Block... blocks
    ) {
        try {
            Class<?> supplierClass = Class.forName("net.minecraft.world.level.block.entity.BlockEntityType$BlockEntitySupplier");
            Object supplier = Proxy.newProxyInstance(
                    supplierClass.getClassLoader(),
                    new Class<?>[]{supplierClass},
                    (proxy, method, args) -> factory.create((net.minecraft.core.BlockPos) args[0], (BlockState) args[1])
            );
            Constructor<BlockEntityType> constructor = BlockEntityType.class.getDeclaredConstructor(supplierClass, Set.class);
            constructor.setAccessible(true);
            return (BlockEntityType<T>) constructor.newInstance(supplier, Set.of(blocks));
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Unable to create block entity type for " + PCBlockEntityIds.END_TROLL_BOX, exception);
        }
    }

    @FunctionalInterface
    private interface BlockEntityFactory<T extends BlockEntity> {
        T create(net.minecraft.core.BlockPos pos, BlockState state);
    }
}
