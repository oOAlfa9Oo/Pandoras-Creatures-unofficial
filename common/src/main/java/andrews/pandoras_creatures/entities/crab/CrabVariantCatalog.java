package andrews.pandoras_creatures.entities.crab;

public final class CrabVariantCatalog {
    public static final int MIN_TYPE = 1;
    public static final int MAX_TYPE = 2;
    public static final int DEFAULT_TYPE = 1;

    private CrabVariantCatalog() {
    }

    public static int normalizeType(int typeId) {
        if (typeId < MIN_TYPE || typeId > MAX_TYPE) {
            return DEFAULT_TYPE;
        }
        return typeId;
    }

    public static int randomTypeId(int roll) {
        return MIN_TYPE + Math.floorMod(roll, MAX_TYPE - MIN_TYPE + 1);
    }

    public static String tooltipKey(int typeId) {
        return switch (normalizeType(typeId)) {
            case 1 -> "chat.pandoras_creatures.crabBucketTooltip.sea";
            default -> "chat.pandoras_creatures.crabBucketTooltip.tropical";
        };
    }

    public static String texturePath(int typeId) {
        return "textures/entity/crab/crab_" + normalizeType(typeId) + ".png";
    }
}
