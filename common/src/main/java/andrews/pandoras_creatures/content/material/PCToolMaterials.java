package andrews.pandoras_creatures.content.material;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

/**
 * 1.21.2+ reemplazo la interfaz Tier (implementable con clase anonima) por el record
 * ToolMaterial (campos directos, sin Ingredient de reparacion -- ahora TagKey<Item>),
 * verificado contra la fuente real de 1.21.3.
 */
public final class PCToolMaterials {
    public static final ToolMaterial ARACHNON_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2000, 8.0F, 5.0F, 10, ItemTags.DIAMOND_TOOL_MATERIALS
    );

    private PCToolMaterials() {
    }
}
