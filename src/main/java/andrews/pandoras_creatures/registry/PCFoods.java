package andrews.pandoras_creatures.registry;

import net.minecraft.world.food.FoodProperties;

public class PCFoods {
    // Crab Meat
    public static final FoodProperties CRAB_MEAT_RAW = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.1F)
            .build();

    public static final FoodProperties CRAB_MEAT_COOKED = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.6F)
            .build();

    // Seahorse
    public static final FoodProperties SEAHORSE_RAW = new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1F)
            .build();

    public static final FoodProperties SEAHORSE_COOKED = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.4F)
            .build();

    // Bufflon Beef
    public static final FoodProperties BUFFLON_BEEF_RAW = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.3F)
            .build();

    public static final FoodProperties BUFFLON_BEEF_COOKED = new FoodProperties.Builder()
            .nutrition(9)
            .saturationModifier(0.9F)
            .build();
}
