package andrews.pandoras_creatures.test;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public final class PCGameTestRegistry {
    private PCGameTestRegistry() {
    }

    public static <T extends Entity> EntityType<T> entityType(String id) {
        return PandorasCreaturesCommon.platform().registry().entityType(id);
    }

    public static Item item(String id) {
        return PandorasCreaturesCommon.platform().registry().item(id);
    }
}
