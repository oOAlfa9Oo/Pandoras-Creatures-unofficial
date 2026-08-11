package andrews.pandoras_creatures.content.material;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.EnumMap;

/**
 * 1.21.2+ movio ArmorMaterial de package (world.item -> world.item.equipment) y le cambio la
 * forma: ya no es un Holder registrado en BuiltInRegistries (ArmorMaterial dejo de ser un
 * registro, ver ArmorMaterials en la fuente vanilla -- son constantes de interfaz sin
 * registrar), ni tiene una lista de Layer con texturas por capa (eso ahora vive en un recurso
 * JSON de equipment, ver assets/pandoras_creatures/equipment/plant_hat.json) ni un
 * Supplier<Ingredient> de reparacion (ahora TagKey<Item>, ver
 * data/pandoras_creatures/tags/item/repairs_plant_hat.json). Verificado contra la fuente real
 * de 1.21.3.
 */
public final class PCArmorMaterials {
    private static final TagKey<Item> REPAIRS_PLANT_HAT = TagKey.create(
            Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Reference.MODID, "repairs_plant_hat"));

    // Plant Hat: durability base=3 (x11 para HELMET = 33, la aproximacion mas cercana al 30
    // original ya que ArmorType.getDurability aplica un multiplicador entero fijo por slot),
    // defense=1, enchantability=15
    public static final ArmorMaterial PLANT_HAT = new ArmorMaterial(
            3, // durability base
            Util.make(new EnumMap<>(ArmorType.class), map -> {
                map.put(ArmorType.BOOTS, 1);
                map.put(ArmorType.LEGGINGS, 1);
                map.put(ArmorType.CHESTPLATE, 1);
                map.put(ArmorType.HELMET, 1);
                map.put(ArmorType.BODY, 1);
            }),
            15, // enchantability
            SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, // toughness
            0.0F, // knockback resistance
            REPAIRS_PLANT_HAT,
            ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(Reference.MODID, "plant_hat"))
    );

    private PCArmorMaterials() {
    }

    public static void init() {
        // Called to force static initialization
    }
}
