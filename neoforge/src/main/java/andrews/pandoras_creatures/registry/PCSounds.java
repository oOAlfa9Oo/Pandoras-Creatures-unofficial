package andrews.pandoras_creatures.registry;

import andrews.pandoras_creatures.registry.sound.PCSoundCatalog;
import andrews.pandoras_creatures.registry.sound.PCSoundBootstrap;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.function.Supplier;

public final class PCSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(Registries.SOUND_EVENT, Reference.MODID);
    private static final Map<String, DeferredHolder<SoundEvent, SoundEvent>> REGISTERED_SOUNDS =
            PCSoundBootstrap.registerAll((id, factory) -> SOUNDS.register(id, factory::get));

    // Arachnon
    public static final Supplier<? extends SoundEvent> ARACHNON_AMBIENT =
            sound(PCSoundCatalog.ARACHNON_AMBIENT);
    public static final Supplier<? extends SoundEvent> ARACHNON_HURT =
            sound(PCSoundCatalog.ARACHNON_HURT);
    public static final Supplier<? extends SoundEvent> ARACHNON_DEATH =
            sound(PCSoundCatalog.ARACHNON_DEATH);

    // Hellhound
    public static final Supplier<? extends SoundEvent> HELLHOUND_AMBIENT =
            sound(PCSoundCatalog.HELLHOUND_AMBIENT);
    public static final Supplier<? extends SoundEvent> HELLHOUND_HURT =
            sound(PCSoundCatalog.HELLHOUND_HURT);
    public static final Supplier<? extends SoundEvent> HELLHOUND_DEATH =
            sound(PCSoundCatalog.HELLHOUND_DEATH);

    // Crab
    public static final Supplier<? extends SoundEvent> CRAB_HURT =
            sound(PCSoundCatalog.CRAB_HURT);
    public static final Supplier<? extends SoundEvent> CRAB_DEATH =
            sound(PCSoundCatalog.CRAB_DEATH);

    // Acidic Archvine
    public static final Supplier<? extends SoundEvent> ACIDIC_ARCHVINE_ATTACK =
            sound(PCSoundCatalog.ACIDIC_ARCHVINE_ATTACK);

    // Bufflon
    public static final Supplier<? extends SoundEvent> BUFFLON_AMBIENT =
            sound(PCSoundCatalog.BUFFLON_AMBIENT);
    public static final Supplier<? extends SoundEvent> BUFFLON_HURT =
            sound(PCSoundCatalog.BUFFLON_HURT);
    public static final Supplier<? extends SoundEvent> BUFFLON_DEATH =
            sound(PCSoundCatalog.BUFFLON_DEATH);
    public static final Supplier<? extends SoundEvent> BUFFLON_ATTACK =
            sound(PCSoundCatalog.BUFFLON_ATTACK);

    // End Troll
    public static final Supplier<? extends SoundEvent> END_TROLL_SCREAM =
            sound(PCSoundCatalog.END_TROLL_SCREAM);
    public static final Supplier<? extends SoundEvent> END_TROLL_ATTACK =
            sound(PCSoundCatalog.END_TROLL_ATTACK);
    public static final Supplier<? extends SoundEvent> END_TROLL_DEATH =
            sound(PCSoundCatalog.END_TROLL_DEATH);

    private PCSounds() {
    }

    private static Supplier<? extends SoundEvent> sound(String id) {
        return REGISTERED_SOUNDS.get(id);
    }
}
