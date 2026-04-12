package andrews.pandoras_creatures.content.material;

import andrews.pandoras_creatures.util.Reference;
import net.minecraft.util.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.EnumMap;

public class PCArmorMaterials {
    private static final TagKey<Item> REPAIRS_PLANT_HAT = TagKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath(Reference.MODID, "repairs_plant_hat")
    );
    private static final ResourceKey<EquipmentAsset> PLANT_HAT_ASSET = EquipmentAssets.createId(Reference.MODID + ":plant_hat");

    // Plant Hat: durability=30, defense=1, enchantability=15
    public static final ArmorMaterial PLANT_HAT = new ArmorMaterial(
            30,
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
            PLANT_HAT_ASSET
    );

    public static void init() {
        // Called to force static initialization
    }
}
