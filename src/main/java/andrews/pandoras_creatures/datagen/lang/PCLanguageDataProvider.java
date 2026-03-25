package andrews.pandoras_creatures.datagen.lang;

import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public final class PCLanguageDataProvider implements DataProvider {
    private final PackOutput.PathProvider languagePathProvider;

    public PCLanguageDataProvider(PackOutput output) {
        this.languagePathProvider = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "lang");
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        JsonObject translations = new JsonObject();
        buildEnglishTranslations().forEach(translations::addProperty);
        return DataProvider.saveStable(cachedOutput, translations, languagePathProvider.json(id("en_us")));
    }

    @Override
    public String getName() {
        return "Pandoras Creatures Language (en_us)";
    }

    private static Map<String, String> buildEnglishTranslations() {
        Map<String, String> translations = new LinkedHashMap<>();

        translations.put("itemGroup.pandoras_creatures", "Pandoras Creatures");

        for (PCSpawnEggPalette palette : PCSpawnEggPalette.values()) {
            translations.put(itemKey(palette.itemName()), entityName(palette.entityName()) + " Spawn Egg");
        }

        translations.put("item.pandoras_creatures.acidic_archvine_spawn_egg.tooltip", "#c7Needs to be placed under\n#c7Jungle Leaves or Netherrack");
        translations.put("item.pandoras_creatures.arachnon_hammer.tooltip", "#c7This tool will break\n#c7blocks in a 3x3 radius");
        translations.put("item.pandoras_creatures.plant_hat.tooltip", "#c7Wearing this hat makes you\n#c7invisible to Acidic Archvines");
        translations.put("block.pandoras_creatures.end_troll_box.tooltip", "and %d more...");

        translations.put(itemKey("arachnon_hammer"), "Arachnon Hammer");
        translations.put(itemKey("crab_meat"), "Crab Meat");
        translations.put(itemKey("crab_meat_cooked"), "Cooked Crab Meat");
        translations.put(itemKey("crab_bucket"), "Bucket of Crab");
        translations.put(itemKey("seahorse_bucket"), "Bucket of Seahorse");
        translations.put(itemKey("seahorse"), "Raw Seahorse");
        translations.put(itemKey("seahorse_cooked"), "Cooked Seahorse");
        translations.put(itemKey("acidic_archvine_tongue"), "Acidic Archvine Tongue");
        translations.put(itemKey("herb_bundle"), "Herb Bundle");
        translations.put(itemKey("bufflon_beef"), "Bufflon Beef");
        translations.put(itemKey("bufflon_beef_cooked"), "Cooked Bufflon Beef");
        translations.put(itemKey("bufflon_hide"), "Bufflon Hide");
        translations.put(itemKey("bufflon_saddle"), "Bufflon Saddle");
        translations.put(itemKey("bufflon_player_seats"), "Bufflon Player Seats");
        translations.put(itemKey("bufflon_small_storage"), "Bufflon Small Storage");
        translations.put(itemKey("bufflon_large_storage"), "Bufflon Large Storage");
        translations.put(itemKey("plant_hat"), "Plant Hat");
        translations.put(itemKey("end_troll_skin"), "End Troll Skin");

        translations.put(blockKey("arachnon_crystal"), "Arachnon Crystal");
        translations.put(blockKey("horsetail"), "Horsetail");
        translations.put(blockKey("dhania"), "Dhania");
        translations.put(blockKey("hill_bloom"), "Hill Bloom");
        translations.put(blockKey("end_troll_box"), "End Troll Box");
        for (DyeColor color : PCEndTrollBoxPalette.orderedColors()) {
            translations.put(blockKey(PCEndTrollBoxPalette.blockName(color)), colorName(color) + " End Troll Box");
        }
        translations.put(blockKey("pandoric_shard"), "Pandoric Shard");

        translations.put(entityKey("arachnon"), "Arachnon");
        translations.put(entityKey("hellhound"), "Hellhound");
        translations.put(entityKey("crab"), "Crab");
        translations.put(entityKey("seahorse"), "Seahorse");
        translations.put(entityKey("acidic_archvine"), "Acidic Archvine");
        translations.put(entityKey("bufflon"), "Bufflon");
        translations.put(entityKey("end_troll"), "End Troll");
        translations.put(entityKey("end_troll_bullet_poison"), "Poison End Troll Bullet");
        translations.put(entityKey("end_troll_bullet_wither"), "Wither End Troll Bullet");
        translations.put(entityKey("end_troll_bullet_damage"), "Damage End Troll Bullet");

        translations.put("container.pandoras_creatures.end_troll_box", "End Troll Box");

        translations.put("gui.button.pandoras_creatures.discord", "Discord");
        translations.put("gui.button.pandoras_creatures.curseforge", "CurseForge");
        translations.put("gui.button.pandoras_creatures.youtube", "YouTube");
        translations.put("gui.button.pandoras_creatures.twitch", "Twitch");
        translations.put("gui.button.pandoras_creatures.patreon", "Patreon");
        translations.put("gui.button.pandoras_creatures.bufflon.sit", "Sit");
        translations.put("gui.button.pandoras_creatures.bufflon.follow", "Follow");
        translations.put("gui.button.pandoras_creatures.bufflon.move_freely", "Move Freely");
        translations.put("gui.button.pandoras_creatures.bufflon.combat", "Combat Mode");
        translations.put("gui.button.pandoras_creatures.bufflon.peaceful", "Peaceful Mode");

        translations.put("chat.pandoras_creatures.invalidJarDownload", "[here]");
        translations.put("chat.pandoras_creatures.invalidJarDownloadTooltip", "Click to open the download page.");
        translations.put("chat.pandoras_creatures.invalidJarStopModReposts", "[here]");
        translations.put("chat.pandoras_creatures.invalidJarStopModRepostsTooltip", "Click to open Stop Mod Reposts page.");
        translations.put("chat.pandoras_creatures.invalidJar", "Pandoras Creatures has detected an invalid jar file. Please click %s to open the official download page or click %s to learn more about illegal mod reposts.");
        translations.put("chat.pandoras_creatures.newVersionDownload", "[here]");
        translations.put("chat.pandoras_creatures.newVersionDownloadTooltip", "Click to open the download page.");
        translations.put("chat.pandoras_creatures.newVersion", "There is a new version of Pandoras Creatures available. Please click %s to open the official download page.");
        translations.put("chat.pandoras_creatures.failedCheck", "Pandoras Creatures was not able to check for new versions.");

        translations.put("chat.pandoras_creatures.crabBucketTooltip.sea", "Sea Crab");
        translations.put("chat.pandoras_creatures.crabBucketTooltip.tropical", "Tropical Crab");

        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.verySmall", "Size: Very Small");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.small", "Size: Small");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.normal", "Size: Normal");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.big", "Size: Big");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.veryBig", "Size: Very Big");

        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.orange", "Orange Seahorse");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.green", "Green Seahorse");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.red", "Red Seahorse");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.yellow", "Yellow Seahorse");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.chromatic", "Chromatic Seahorse");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.cyan", "Cyan Seahorse");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.purple", "Purple Seahorse");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.pink", "Pink Seahorse");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.lime", "Lime Seahorse");
        translations.put("chat.pandoras_creatures.seahorseBucketTooltip.ghost", "Ghost Seahorse");

        // Keep the current sounds.json behavior without forcing a subtitle-key migration on all locales yet.
        translations.put("Arachnon Roar", "Arachnon Roar");
        translations.put("Arachnon Hurt", "Arachnon Hurt");
        translations.put("Arachnon Death", "Arachnon Death");
        translations.put("Hellhound Bark", "Hellhound Bark");
        translations.put("Hellhound Hurt", "Hellhound Hurt");
        translations.put("Hellhound Death", "Hellhound Death");
        translations.put("Crab Hurt", "Crab Hurt");
        translations.put("Crab Death", "Crab Death");
        translations.put("Acidic Archvine Attack", "Acidic Archvine Attack");
        translations.put("Heavy Bufflon Breath", "Heavy Bufflon Breath");
        translations.put("Bufflon Hurt", "Bufflon Hurt");
        translations.put("Bufflon Death", "Bufflon Death");
        translations.put("Bufflon Attack", "Bufflon Attack");
        translations.put("End Troll Scream", "End Troll Scream");
        translations.put("End Troll Attack Roar", "End Troll Attack Roar");
        translations.put("End Troll Death Sound", "End Troll Death Sound");

        return translations;
    }

    private static String itemKey(String path) {
        return "item." + Reference.MODID + "." + path;
    }

    private static String blockKey(String path) {
        return "block." + Reference.MODID + "." + path;
    }

    private static String entityKey(String path) {
        return "entity." + Reference.MODID + "." + path;
    }

    private static String entityName(String entityName) {
        return switch (entityName) {
            case "arachnon" -> "Arachnon";
            case "hellhound" -> "Hellhound";
            case "crab" -> "Crab";
            case "seahorse" -> "Seahorse";
            case "acidic_archvine" -> "Acidic Archvine";
            case "bufflon" -> "Bufflon";
            case "end_troll" -> "End Troll";
            default -> entityName;
        };
    }

    private static String colorName(DyeColor color) {
        return switch (color) {
            case LIGHT_BLUE -> "Light Blue";
            case LIGHT_GRAY -> "Light Gray";
            default -> capitalize(color.getName());
        };
    }

    private static String capitalize(String value) {
        if (value.isEmpty()) {
            return value;
        }
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(Reference.MODID, path);
    }
}
