package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.registry.sound.PCSoundCatalog;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class PCSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, Reference.MODID);

    // Arachnon
    public static final DeferredHolder<SoundEvent, SoundEvent> ARACHNON_AMBIENT =
            registerSound(PCSoundCatalog.ARACHNON_AMBIENT);
    public static final DeferredHolder<SoundEvent, SoundEvent> ARACHNON_HURT =
            registerSound(PCSoundCatalog.ARACHNON_HURT);
    public static final DeferredHolder<SoundEvent, SoundEvent> ARACHNON_DEATH =
            registerSound(PCSoundCatalog.ARACHNON_DEATH);

    // Hellhound
    public static final DeferredHolder<SoundEvent, SoundEvent> HELLHOUND_AMBIENT =
            registerSound(PCSoundCatalog.HELLHOUND_AMBIENT);
    public static final DeferredHolder<SoundEvent, SoundEvent> HELLHOUND_HURT =
            registerSound(PCSoundCatalog.HELLHOUND_HURT);
    public static final DeferredHolder<SoundEvent, SoundEvent> HELLHOUND_DEATH =
            registerSound(PCSoundCatalog.HELLHOUND_DEATH);

    // Crab
    public static final DeferredHolder<SoundEvent, SoundEvent> CRAB_HURT =
            registerSound(PCSoundCatalog.CRAB_HURT);
    public static final DeferredHolder<SoundEvent, SoundEvent> CRAB_DEATH =
            registerSound(PCSoundCatalog.CRAB_DEATH);

    // Acidic Archvine
    public static final DeferredHolder<SoundEvent, SoundEvent> ACIDIC_ARCHVINE_ATTACK =
            registerSound(PCSoundCatalog.ACIDIC_ARCHVINE_ATTACK);

    // Bufflon
    public static final DeferredHolder<SoundEvent, SoundEvent> BUFFLON_AMBIENT =
            registerSound(PCSoundCatalog.BUFFLON_AMBIENT);
    public static final DeferredHolder<SoundEvent, SoundEvent> BUFFLON_HURT =
            registerSound(PCSoundCatalog.BUFFLON_HURT);
    public static final DeferredHolder<SoundEvent, SoundEvent> BUFFLON_DEATH =
            registerSound(PCSoundCatalog.BUFFLON_DEATH);
    public static final DeferredHolder<SoundEvent, SoundEvent> BUFFLON_ATTACK =
            registerSound(PCSoundCatalog.BUFFLON_ATTACK);

    // End Troll
    public static final DeferredHolder<SoundEvent, SoundEvent> END_TROLL_SCREAM =
            registerSound(PCSoundCatalog.END_TROLL_SCREAM);
    public static final DeferredHolder<SoundEvent, SoundEvent> END_TROLL_ATTACK =
            registerSound(PCSoundCatalog.END_TROLL_ATTACK);
    public static final DeferredHolder<SoundEvent, SoundEvent> END_TROLL_DEATH =
            registerSound(PCSoundCatalog.END_TROLL_DEATH);

    private PCSounds() {
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Reference.MODID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
