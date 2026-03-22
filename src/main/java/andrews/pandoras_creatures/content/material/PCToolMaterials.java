package andrews.pandoras_creatures.content.material;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class PCToolMaterials {
    // Arachnon material: attackDamage=5.0F, efficiency=8.0F, durability=2000, harvestLevel=3, enchantability=10
    public static final Tier ARACHNON_MATERIAL = new SimpleTier(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, // Incorrect blocks tag (equivalent to harvest level 3+)
            2000,  // durability
            8.0F,  // speed/efficiency
            5.0F,  // attack damage bonus
            10,    // enchantability
            () -> Ingredient.of(Items.DIAMOND) // repair material
    );
}
