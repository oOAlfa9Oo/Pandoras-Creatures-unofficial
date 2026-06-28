package andrews.pandoras_creatures.registry.entity;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class PCEntityTypeFactory {
    private static final int DEFAULT_CLIENT_TRACKING_RANGE = 64;
    private static final int DEFAULT_LIVING_UPDATE_INTERVAL = 3;
    private static final int DEFAULT_PROJECTILE_UPDATE_INTERVAL = 1;

    private PCEntityTypeFactory() {
    }

    public static <T extends Entity> EntityType.Builder<T> living(EntityType.EntityFactory<T> factory,
            MobCategory category,
            float width,
            float height) {
        return configure(EntityType.Builder.of(factory, category), width, height, DEFAULT_LIVING_UPDATE_INTERVAL);
    }

    public static <T extends Entity> EntityType.Builder<T> fireImmuneLiving(EntityType.EntityFactory<T> factory,
            MobCategory category,
            float width,
            float height) {
        return living(factory, category, width, height).fireImmune();
    }

    public static <T extends Entity> EntityType.Builder<T> projectile(EntityType.EntityFactory<T> factory,
            MobCategory category,
            float width,
            float height) {
        return configure(EntityType.Builder.of(factory, category), width, height, DEFAULT_PROJECTILE_UPDATE_INTERVAL);
    }

    public static String entityId(String name) {
        return Reference.id(name).toString();
    }

    private static <T extends Entity> EntityType.Builder<T> configure(EntityType.Builder<T> builder,
            float width,
            float height,
            int updateInterval) {
        return builder.sized(width, height)
                .clientTrackingRange(DEFAULT_CLIENT_TRACKING_RANGE)
                .updateInterval(updateInterval);
    }
}

