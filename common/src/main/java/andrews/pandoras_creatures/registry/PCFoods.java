package andrews.pandoras_creatures.registry;

import net.minecraft.world.food.FoodProperties;

public final class PCFoods {
    // Crab Meat
    public static final FoodProperties CRAB_MEAT_RAW = food(2, 0.1F);

    public static final FoodProperties CRAB_MEAT_COOKED = food(5, 0.6F);

    // Seahorse
    public static final FoodProperties SEAHORSE_RAW = food(1, 0.1F);

    public static final FoodProperties SEAHORSE_COOKED = food(3, 0.4F);

    // Bufflon Beef
    public static final FoodProperties BUFFLON_BEEF_RAW = food(4, 0.3F);

    public static final FoodProperties BUFFLON_BEEF_COOKED = food(9, 0.9F);

    private PCFoods() {
    }

    private static FoodProperties food(int nutrition, float saturationModifier) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturationModifier)
                .build();
    }
}
