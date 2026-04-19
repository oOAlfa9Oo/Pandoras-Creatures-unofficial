package andrews.pandoras_creatures.datagen.lang;

import andrews.pandoras_creatures.lang.PCLanguageKeys;
import andrews.pandoras_creatures.registry.block.PCBlockIds;
import andrews.pandoras_creatures.registry.block.PCEndTrollBoxPalette;
import andrews.pandoras_creatures.registry.creative.PCCreativeTabIds;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.registry.item.PCItemIds;
import andrews.pandoras_creatures.registry.item.PCSpawnEggPalette;
import andrews.pandoras_creatures.util.Reference;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
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

        translations.put(PCCreativeTabIds.PANDORAS_CREATURES_TRANSLATION_KEY, "Pandoras Creatures");

        for (PCSpawnEggPalette palette : PCSpawnEggPalette.values()) {
            translations.put(PCLanguageKeys.item(palette.itemName()), entityName(palette.entityName()) + " Spawn Egg");
        }

        translations.put(PCLanguageKeys.ACIDIC_ARCHVINE_SPAWN_EGG_TOOLTIP, "#c7Needs to be placed under\n#c7Jungle Leaves or Netherrack");
        translations.put(PCLanguageKeys.ARACHNON_HAMMER_TOOLTIP, "#c7This tool will break\n#c7blocks in a 3x3 radius");
        translations.put(PCLanguageKeys.PLANT_HAT_TOOLTIP, "#c7Wearing this hat makes you\n#c7invisible to Acidic Archvines");
        translations.put(PCLanguageKeys.END_TROLL_BOX_TOOLTIP, "and %d more...");

        translations.put(PCLanguageKeys.item(PCItemIds.ARACHNON_HAMMER), "Arachnon Hammer");
        translations.put(PCLanguageKeys.item(PCItemIds.CRAB_MEAT), "Crab Meat");
        translations.put(PCLanguageKeys.item(PCItemIds.CRAB_MEAT_COOKED), "Cooked Crab Meat");
        translations.put(PCLanguageKeys.item(PCItemIds.CRAB_BUCKET), "Bucket of Crab");
        translations.put(PCLanguageKeys.item(PCItemIds.SEAHORSE_BUCKET), "Bucket of Seahorse");
        translations.put(PCLanguageKeys.item(PCItemIds.SEAHORSE), "Raw Seahorse");
        translations.put(PCLanguageKeys.item(PCItemIds.SEAHORSE_COOKED), "Cooked Seahorse");
        translations.put(PCLanguageKeys.item(PCItemIds.ACIDIC_ARCHVINE_TONGUE), "Acidic Archvine Tongue");
        translations.put(PCLanguageKeys.item(PCItemIds.HERB_BUNDLE), "Herb Bundle");
        translations.put(PCLanguageKeys.item(PCItemIds.BUFFLON_BEEF), "Bufflon Beef");
        translations.put(PCLanguageKeys.item(PCItemIds.BUFFLON_BEEF_COOKED), "Cooked Bufflon Beef");
        translations.put(PCLanguageKeys.item(PCItemIds.BUFFLON_HIDE), "Bufflon Hide");
        translations.put(PCLanguageKeys.item(PCItemIds.BUFFLON_SADDLE), "Bufflon Saddle");
        translations.put(PCLanguageKeys.item(PCItemIds.BUFFLON_PLAYER_SEATS), "Bufflon Player Seats");
        translations.put(PCLanguageKeys.item(PCItemIds.BUFFLON_SMALL_STORAGE), "Bufflon Small Storage");
        translations.put(PCLanguageKeys.item(PCItemIds.BUFFLON_LARGE_STORAGE), "Bufflon Large Storage");
        translations.put(PCLanguageKeys.item(PCItemIds.PLANT_HAT), "Plant Hat");
        translations.put(PCLanguageKeys.item(PCItemIds.END_TROLL_SKIN), "End Troll Skin");

        translations.put(PCLanguageKeys.block(PCBlockIds.ARACHNON_CRYSTAL), "Arachnon Crystal");
        translations.put(PCLanguageKeys.block(PCBlockIds.HORSETAIL), "Horsetail");
        translations.put(PCLanguageKeys.block(PCBlockIds.DHANIA), "Dhania");
        translations.put(PCLanguageKeys.block(PCBlockIds.HILL_BLOOM), "Hill Bloom");
        translations.put(PCLanguageKeys.block(PCBlockIds.END_TROLL_BOX), "End Troll Box");
        for (DyeColor color : PCEndTrollBoxPalette.orderedColors()) {
            translations.put(PCLanguageKeys.block(PCEndTrollBoxPalette.blockName(color)), colorName(color) + " End Troll Box");
        }
        translations.put(PCLanguageKeys.block(PCBlockIds.PANDORIC_SHARD), "Pandoric Shard");

        translations.put(PCLanguageKeys.entity(PCEntityIds.ARACHNON), "Arachnon");
        translations.put(PCLanguageKeys.entity(PCEntityIds.HELLHOUND), "Hellhound");
        translations.put(PCLanguageKeys.entity(PCEntityIds.CRAB), "Crab");
        translations.put(PCLanguageKeys.entity(PCEntityIds.SEAHORSE), "Seahorse");
        translations.put(PCLanguageKeys.entity(PCEntityIds.ACIDIC_ARCHVINE), "Acidic Archvine");
        translations.put(PCLanguageKeys.entity(PCEntityIds.BUFFLON), "Bufflon");
        translations.put(PCLanguageKeys.entity(PCEntityIds.END_TROLL), "End Troll");
        translations.put(PCLanguageKeys.entity(PCEntityIds.END_TROLL_BULLET_POISON), "Poison End Troll Bullet");
        translations.put(PCLanguageKeys.entity(PCEntityIds.END_TROLL_BULLET_WITHER), "Wither End Troll Bullet");
        translations.put(PCLanguageKeys.entity(PCEntityIds.END_TROLL_BULLET_DAMAGE), "Damage End Troll Bullet");
        translations.put("advancement.pandoras_creatures.free_the_end_troll.title", "Freedom for the End Troll");
        translations.put("advancement.pandoras_creatures.free_the_end_troll.description", "Break the End Troll out of its prison");

        translations.put(PCLanguageKeys.END_TROLL_BOX_CONTAINER, "End Troll Box");

        translations.put(PCLanguageKeys.guiButton("discord"), "Discord");
        translations.put(PCLanguageKeys.guiButton("curseforge"), "CurseForge");
        translations.put(PCLanguageKeys.guiButton("youtube"), "YouTube");
        translations.put(PCLanguageKeys.guiButton("twitch"), "Twitch");
        translations.put(PCLanguageKeys.guiButton("patreon"), "Patreon");
        translations.put(PCLanguageKeys.guiButton("bufflon.sit"), "Sit");
        translations.put(PCLanguageKeys.guiButton("bufflon.follow"), "Follow");
        translations.put(PCLanguageKeys.guiButton("bufflon.move_freely"), "Move Freely");
        translations.put(PCLanguageKeys.guiButton("bufflon.combat"), "Combat Mode");
        translations.put(PCLanguageKeys.guiButton("bufflon.peaceful"), "Peaceful Mode");

        translations.put(PCLanguageKeys.chat("invalidJarDownload"), "[here]");
        translations.put(PCLanguageKeys.chat("invalidJarDownloadTooltip"), "Click to open the download page.");
        translations.put(PCLanguageKeys.chat("invalidJarStopModReposts"), "[here]");
        translations.put(PCLanguageKeys.chat("invalidJarStopModRepostsTooltip"), "Click to open Stop Mod Reposts page.");
        translations.put(PCLanguageKeys.chat("invalidJar"), "Pandoras Creatures has detected an invalid jar file. Please click %s to open the official download page or click %s to learn more about illegal mod reposts.");
        translations.put(PCLanguageKeys.chat("newVersionDownload"), "[here]");
        translations.put(PCLanguageKeys.chat("newVersionDownloadTooltip"), "Click to open the download page.");
        translations.put(PCLanguageKeys.chat("newVersion"), "There is a new version of Pandoras Creatures available. Please click %s to open the official download page.");
        translations.put(PCLanguageKeys.chat("failedCheck"), "Pandoras Creatures was not able to check for new versions.");

        translations.put(PCLanguageKeys.chat("crabBucketTooltip.sea"), "Sea Crab");
        translations.put(PCLanguageKeys.chat("crabBucketTooltip.tropical"), "Tropical Crab");

        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.verySmall"), "Size: Very Small");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.small"), "Size: Small");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.normal"), "Size: Normal");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.big"), "Size: Big");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.veryBig"), "Size: Very Big");

        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.orange"), "Orange Seahorse");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.green"), "Green Seahorse");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.red"), "Red Seahorse");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.yellow"), "Yellow Seahorse");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.chromatic"), "Chromatic Seahorse");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.cyan"), "Cyan Seahorse");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.purple"), "Purple Seahorse");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.pink"), "Pink Seahorse");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.lime"), "Lime Seahorse");
        translations.put(PCLanguageKeys.chat("seahorseBucketTooltip.ghost"), "Ghost Seahorse");

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

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(Reference.MODID, path);
    }
}
