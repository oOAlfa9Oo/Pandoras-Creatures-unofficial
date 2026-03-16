package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PCSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, Reference.MODID);

    // Arachnon
    public static final DeferredHolder<SoundEvent, SoundEvent> ARACHNON_AMBIENT =
            registerSound("entity.arachnon.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARACHNON_HURT =
            registerSound("entity.arachnon.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ARACHNON_DEATH =
            registerSound("entity.arachnon.death");

    // Hellhound
    public static final DeferredHolder<SoundEvent, SoundEvent> HELLHOUND_AMBIENT =
            registerSound("entity.hellhound.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> HELLHOUND_HURT =
            registerSound("entity.hellhound.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> HELLHOUND_DEATH =
            registerSound("entity.hellhound.death");

    // Crab
    public static final DeferredHolder<SoundEvent, SoundEvent> CRAB_HURT =
            registerSound("entity.crab.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> CRAB_DEATH =
            registerSound("entity.crab.death");

    // Acidic Archvine
    public static final DeferredHolder<SoundEvent, SoundEvent> ACIDIC_ARCHVINE_ATTACK =
            registerSound("entity.acidic_archvine.attack");

    // Bufflon
    public static final DeferredHolder<SoundEvent, SoundEvent> BUFFLON_AMBIENT =
            registerSound("entity.bufflon.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> BUFFLON_HURT =
            registerSound("entity.bufflon.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> BUFFLON_DEATH =
            registerSound("entity.bufflon.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> BUFFLON_ATTACK =
            registerSound("entity.bufflon.attack");

    // End Troll
    public static final DeferredHolder<SoundEvent, SoundEvent> END_TROLL_SCREAM =
            registerSound("entity.end_troll.scream");
    public static final DeferredHolder<SoundEvent, SoundEvent> END_TROLL_ATTACK =
            registerSound("entity.end_troll.attack");
    public static final DeferredHolder<SoundEvent, SoundEvent> END_TROLL_DEATH =
            registerSound("entity.end_troll.death");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Reference.MODID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
