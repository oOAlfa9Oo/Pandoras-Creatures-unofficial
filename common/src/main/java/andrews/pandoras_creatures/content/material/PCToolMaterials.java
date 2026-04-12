package andrews.pandoras_creatures.content.material;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ToolMaterial;

public final class PCToolMaterials {
    public static final ToolMaterial ARACHNON_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2000,
            8.0F,
            5.0F,
            10,
            ItemTags.DIAMOND_TOOL_MATERIALS
    );

    private PCToolMaterials() {
    }
}
