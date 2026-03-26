package andrews.pandoras_creatures.content.material;

import andrews.pandoras_creatures.PandorasCreaturesCommon;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class PCArmorMaterials {
    private static final String ACIDIC_ARCHVINE_TONGUE_ITEM_ID = "acidic_archvine_tongue";

    // Plant Hat: durability=30, defense=1, enchantability=15
    public static final Holder<ArmorMaterial> PLANT_HAT = register("plant_hat",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 1);
                map.put(ArmorItem.Type.CHESTPLATE, 1);
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.BODY, 1);
            }),
            15, // enchantability
            SoundEvents.ARMOR_EQUIP_LEATHER,
            0.0F, // toughness
            0.0F, // knockback resistance
            () -> Ingredient.of(PandorasCreaturesCommon.platform().registry().item(ACIDIC_ARCHVINE_TONGUE_ITEM_ID))
    );

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> defense,
                                                   int enchantability, Holder<SoundEvent> equipSound,
                                                   float toughness, float knockbackResistance,
                                                   Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Reference.MODID, name)));

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.fromNamespaceAndPath(Reference.MODID, name),
                new ArmorMaterial(defense, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance));
    }

    public static void init() {
        // Called to force static initialization
    }
}
