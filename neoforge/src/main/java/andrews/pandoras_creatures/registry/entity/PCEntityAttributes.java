package andrews.pandoras_creatures.registry.entity;

import andrews.pandoras_creatures.registry.PCEntities;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public final class PCEntityAttributes {
    private PCEntityAttributes() {
    }

    public static void registerAll(EntityAttributeCreationEvent event) {
        event.put(PCEntities.ARACHNON.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 80.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2D)
                .build());

        event.put(PCEntities.HELLHOUND.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 14.0D)
                .build());

        event.put(PCEntities.CRAB.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 6.0D)
                .build());

        event.put(PCEntities.SEAHORSE.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .build());

        event.put(PCEntities.ACIDIC_ARCHVINE.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 36.0D)
                .build());

        event.put(PCEntities.BUFFLON.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 60.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.55D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .build());

        event.put(PCEntities.END_TROLL.get(), createAttributes()
                .add(Attributes.MAX_HEALTH, 200.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 30.0D)
                .build());
    }

    private static AttributeSupplier.Builder createAttributes() {
        // Mob.createMobAttributes keeps FOLLOW_RANGE available for AI goals during entity construction.
        return Mob.createMobAttributes();
    }
}
