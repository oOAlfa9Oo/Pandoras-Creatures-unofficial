package andrews.pandoras_creatures.entities.hellhound;

import net.minecraft.util.RandomSource;

public final class HellhoundCombatRules {
    private static final int NORMAL_DAMAGE_MIN = 2;
    private static final int NORMAL_DAMAGE_ROLL = 3;
    private static final int WITHER_DAMAGE_MIN = 4;
    private static final int WITHER_DAMAGE_ROLL = 5;
    private static final int WITHER_DURATION_TICKS = 60;
    private static final int COAL_DROP_MIN = 1;
    private static final int COAL_DROP_ROLL = 4;

    private HellhoundCombatRules() {
    }

    public static int attackDamage(int typeId, RandomSource random) {
        if (HellhoundVariantCatalog.isWitherType(typeId)) {
            return attackDamageFromRoll(typeId, random.nextInt(WITHER_DAMAGE_ROLL));
        }
        return attackDamageFromRoll(typeId, random.nextInt(NORMAL_DAMAGE_ROLL));
    }

    public static int attackDamageFromRoll(int typeId, int roll) {
        if (HellhoundVariantCatalog.isWitherType(typeId)) {
            return WITHER_DAMAGE_MIN + Math.floorMod(roll, WITHER_DAMAGE_ROLL);
        }
        return NORMAL_DAMAGE_MIN + Math.floorMod(roll, NORMAL_DAMAGE_ROLL);
    }

    public static boolean appliesWither(int typeId) {
        return HellhoundVariantCatalog.isWitherType(typeId);
    }

    public static int witherDurationTicks(int typeId) {
        return appliesWither(typeId) ? WITHER_DURATION_TICKS : 0;
    }

    public static int coalDropCount(int typeId, RandomSource random) {
        if (!HellhoundVariantCatalog.isWitherType(typeId)) {
            return 0;
        }
        return coalDropCountFromRoll(typeId, random.nextInt(COAL_DROP_ROLL));
    }

    public static int coalDropCountFromRoll(int typeId, int roll) {
        if (!HellhoundVariantCatalog.isWitherType(typeId)) {
            return 0;
        }
        return COAL_DROP_MIN + Math.floorMod(roll, COAL_DROP_ROLL);
    }
}
