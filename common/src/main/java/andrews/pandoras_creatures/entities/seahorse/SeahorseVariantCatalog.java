package andrews.pandoras_creatures.entities.seahorse;

import andrews.pandoras_creatures.lang.PCLanguageKeys;

public final class SeahorseVariantCatalog {
    public static final int MIN_TYPE = 1;
    public static final int MAX_TYPE = 10;
    public static final int DEFAULT_TYPE = 1;
    public static final int MIN_SIZE = 1;
    public static final int MAX_SIZE = 5;
    public static final int DEFAULT_SIZE = 3;

    private SeahorseVariantCatalog() {
    }

    public static int normalizeType(int typeId) {
        return normalize(typeId, MIN_TYPE, MAX_TYPE, DEFAULT_TYPE);
    }

    public static int normalizeSize(int sizeId) {
        return normalize(sizeId, MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
    }

    public static int randomTypeId(int roll) {
        return MIN_TYPE + Math.floorMod(roll, MAX_TYPE - MIN_TYPE + 1);
    }

    public static int randomSizeId(int roll) {
        return MIN_SIZE + Math.floorMod(roll, MAX_SIZE - MIN_SIZE + 1);
    }

    public static String variantTooltipKey(int typeId) {
        return switch (normalizeType(typeId)) {
            case 1 -> PCLanguageKeys.chat("seahorseBucketTooltip.orange");
            case 2 -> PCLanguageKeys.chat("seahorseBucketTooltip.green");
            case 3 -> PCLanguageKeys.chat("seahorseBucketTooltip.red");
            case 4 -> PCLanguageKeys.chat("seahorseBucketTooltip.yellow");
            case 5 -> PCLanguageKeys.chat("seahorseBucketTooltip.chromatic");
            case 6 -> PCLanguageKeys.chat("seahorseBucketTooltip.cyan");
            case 7 -> PCLanguageKeys.chat("seahorseBucketTooltip.purple");
            case 8 -> PCLanguageKeys.chat("seahorseBucketTooltip.pink");
            case 9 -> PCLanguageKeys.chat("seahorseBucketTooltip.lime");
            default -> PCLanguageKeys.chat("seahorseBucketTooltip.ghost");
        };
    }

    public static String sizeTooltipKey(int sizeId) {
        return switch (normalizeSize(sizeId)) {
            case 1 -> PCLanguageKeys.chat("seahorseBucketTooltip.verySmall");
            case 2 -> PCLanguageKeys.chat("seahorseBucketTooltip.small");
            case 3 -> PCLanguageKeys.chat("seahorseBucketTooltip.normal");
            case 4 -> PCLanguageKeys.chat("seahorseBucketTooltip.big");
            default -> PCLanguageKeys.chat("seahorseBucketTooltip.veryBig");
        };
    }

    private static int normalize(int value, int min, int max, int defaultValue) {
        if (value < min || value > max) {
            return defaultValue;
        }
        return value;
    }
}
