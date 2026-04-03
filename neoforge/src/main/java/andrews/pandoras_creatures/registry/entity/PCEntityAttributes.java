package andrews.pandoras_creatures.registry.entity;

import andrews.pandoras_creatures.registry.PCEntities;
import andrews.pandoras_creatures.registry.entity.PCEntityBootstrap;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public final class PCEntityAttributes {
    private PCEntityAttributes() {
    }

    public static void registerAll(EntityAttributeCreationEvent event) {
        event.put(PCEntities.ARACHNON.get(), PCEntityBootstrap.arachnonAttributes().build());

        event.put(PCEntities.HELLHOUND.get(), PCEntityBootstrap.hellhoundAttributes().build());

        event.put(PCEntities.CRAB.get(), PCEntityBootstrap.crabAttributes().build());

        event.put(PCEntities.SEAHORSE.get(), PCEntityBootstrap.seahorseAttributes().build());

        event.put(PCEntities.ACIDIC_ARCHVINE.get(), PCEntityBootstrap.acidicArchvineAttributes().build());

        event.put(PCEntities.BUFFLON.get(), PCEntityBootstrap.bufflonAttributes().build());

        event.put(PCEntities.END_TROLL.get(), PCEntityBootstrap.endTrollAttributes().build());
    }
}
