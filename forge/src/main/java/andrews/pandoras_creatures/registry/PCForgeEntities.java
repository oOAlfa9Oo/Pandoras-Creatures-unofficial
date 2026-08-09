package andrews.pandoras_creatures.forge.registry;

import andrews.pandoras_creatures.entities.AcidicArchvineEntity;
import andrews.pandoras_creatures.entities.ArachnonEntity;
import andrews.pandoras_creatures.entities.BufflonEntity;
import andrews.pandoras_creatures.entities.CrabEntity;
import andrews.pandoras_creatures.entities.EndTrollEntity;
import andrews.pandoras_creatures.entities.HellhoundEntity;
import andrews.pandoras_creatures.entities.SeahorseEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletDamageEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletPoisonEntity;
import andrews.pandoras_creatures.entities.projectiles.EndTrollBulletWitherEntity;
import andrews.pandoras_creatures.registry.entity.PCEntityBootstrap;
import andrews.pandoras_creatures.registry.entity.PCEntityIds;
import andrews.pandoras_creatures.util.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public final class PCForgeEntities {
    private static boolean registered;

    private PCForgeEntities() {
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(PCForgeEntities::registerEntities);
        modEventBus.addListener(PCForgeEntities::registerAttributes);
    }

    public static EntityType<AcidicArchvineEntity> acidicArchvine() {
        return entityType(PCEntityIds.ACIDIC_ARCHVINE);
    }

    public static EntityType<ArachnonEntity> arachnon() {
        return entityType(PCEntityIds.ARACHNON);
    }

    public static EntityType<CrabEntity> crab() {
        return entityType(PCEntityIds.CRAB);
    }

    public static EntityType<BufflonEntity> bufflon() {
        return entityType(PCEntityIds.BUFFLON);
    }

    public static EntityType<SeahorseEntity> seahorse() {
        return entityType(PCEntityIds.SEAHORSE);
    }

    public static EntityType<HellhoundEntity> hellhound() {
        return entityType(PCEntityIds.HELLHOUND);
    }

    public static EntityType<EndTrollEntity> endTroll() {
        return entityType(PCEntityIds.END_TROLL);
    }

    public static EntityType<EndTrollBulletDamageEntity> endTrollBulletDamage() {
        return entityType(PCEntityIds.END_TROLL_BULLET_DAMAGE);
    }

    public static EntityType<EndTrollBulletPoisonEntity> endTrollBulletPoison() {
        return entityType(PCEntityIds.END_TROLL_BULLET_POISON);
    }

    public static EntityType<EndTrollBulletWitherEntity> endTrollBulletWither() {
        return entityType(PCEntityIds.END_TROLL_BULLET_WITHER);
    }

    @SuppressWarnings("unchecked")
    private static <T extends net.minecraft.world.entity.Entity> EntityType<T> entityType(String id) {
        ResourceLocation entityId = ResourceLocation.fromNamespaceAndPath(Reference.MODID, id);
        EntityType<?> value = BuiltInRegistries.ENTITY_TYPE.getValue(entityId);
        if (value == null) {
            throw new IllegalArgumentException("Unknown forge entity type id: " + entityId);
        }
        return (EntityType<T>) value;
    }

    private static void registerEntities(RegisterEvent event) {
        if (registered || !event.getRegistryKey().equals(ForgeRegistries.Keys.ENTITY_TYPES)) {
            return;
        }

        registered = true;
        event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
            helper.register(
                    ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.ACIDIC_ARCHVINE),
                    PCEntityBootstrap.acidicArchvineType()
                            .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.ACIDIC_ARCHVINE)))
            );
            helper.register(
                    ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.ARACHNON),
                    PCEntityBootstrap.arachnonType()
                            .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.ARACHNON)))
            );
            helper.register(
                    ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.CRAB),
                    PCEntityBootstrap.crabType()
                            .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.CRAB)))
            );
            helper.register(
                    ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.BUFFLON),
                    PCEntityBootstrap.bufflonType()
                            .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.BUFFLON)))
            );
            helper.register(
                    ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.SEAHORSE),
                    PCEntityBootstrap.seahorseType()
                            .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.SEAHORSE)))
            );
            helper.register(
                    ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.HELLHOUND),
                    PCEntityBootstrap.hellhoundType()
                            .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.HELLHOUND)))
            );
            helper.register(
                    ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.END_TROLL),
                    PCEntityBootstrap.endTrollType()
                            .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.END_TROLL)))
            );
            helper.register(
                    ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.END_TROLL_BULLET_DAMAGE),
                    PCEntityBootstrap.endTrollBulletDamageType()
                            .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.END_TROLL_BULLET_DAMAGE)))
            );
            helper.register(
                    ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.END_TROLL_BULLET_POISON),
                    PCEntityBootstrap.endTrollBulletPoisonType()
                            .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.END_TROLL_BULLET_POISON)))
            );
            helper.register(
                    ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.END_TROLL_BULLET_WITHER),
                    PCEntityBootstrap.endTrollBulletWitherType()
                            .build(net.minecraft.resources.ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Reference.MODID, PCEntityIds.END_TROLL_BULLET_WITHER)))
            );
        });
    }

    private static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(acidicArchvine(), PCEntityBootstrap.acidicArchvineAttributes().build());
        event.put(arachnon(), PCEntityBootstrap.arachnonAttributes().build());
        event.put(crab(), PCEntityBootstrap.crabAttributes().build());
        event.put(bufflon(), PCEntityBootstrap.bufflonAttributes().build());
        event.put(seahorse(), PCEntityBootstrap.seahorseAttributes().build());
        event.put(hellhound(), PCEntityBootstrap.hellhoundAttributes().build());
        event.put(endTroll(), PCEntityBootstrap.endTrollAttributes().build());
    }
}
