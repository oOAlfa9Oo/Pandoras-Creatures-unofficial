# Auditoria de Compilacion Fabric 26.1

## Comando ejecutado

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:compileJava --console=plain
```

## Estado

El build ya supera los bloqueos de configuracion iniciales:

- Gradle wrapper actualizado a `9.4.0`.
- Fabric Loom actualizado a `1.16.1`.
- Plugin Fabric actualizado a `net.fabricmc.fabric-loom`.
- `fabricOnly=true` permite configurar solo `common + fabric`, sin bloquear por ForgeGradle ni NeoForge.
- El build ya llega a `:fabric:compileJava`.

La compilacion todavia falla por cambios reales de API entre `1.21.1` y `26.1`.

## Bloqueos resueltos

1. `fabric-loom 1.16.1` requiere Gradle plugin API `9.4.0`.
   - Solucion aplicada: wrapper actualizado manualmente a Gradle `9.4.0`.

2. `foojay-resolver-convention 0.8.0` no es compatible con Gradle 9 por el cambio de `JvmVendorSpec`.
   - Solucion aplicada: `foojay-resolver-convention 1.0.0`.

3. `:fabric:compileJava` configuraba `forge` y fallaba porque ForgeGradle 6 no soporta Gradle 9.
   - Solucion aplicada: propiedad `-PfabricOnly=true` para incluir solo `common + fabric`.

4. `fabric` dependia del output de `common`, lo que ejecutaba el NeoForm del proyecto `common`.
   - Solucion aplicada: en modo `fabricOnly`, Fabric compila las fuentes de `common` como parte de su propio source set.

## Bloqueos actuales de codigo

La primera compilacion real produce errores de port 26.1. Los grupos principales son:

- Minecraft paso a nombres unobfuscated/official distintos en varios puntos:
  - `ResourceLocation` ya no esta en `net.minecraft.resources`.
  - `MobSpawnType` ya no esta en `net.minecraft.world.entity`.
  - `GameRules` ya no esta en `net.minecraft.world.level`.
  - `RenderType` ya no esta en `net.minecraft.client.renderer`.
  - varios renderers cambiaron la aridad generica de `MobRenderer` y `EntityRenderer`.

- Fabric API 26.1 renombro o movio APIs:
  - `BuiltinItemRendererRegistry`
  - `EntityModelLayerRegistry`
  - `ColorProviderRegistry`
  - `BlockRenderLayerMap`
  - `ExtendedScreenHandlerFactory`
  - `ExtendedScreenHandlerType`

- Faltan dependencias o reemplazos para anotaciones:
  - `javax.annotation.Nullable`

## Segundo corte aplicado

Se aplico un primer lote de migracion mecanica confirmado contra el jar local de Minecraft `26.1` generado por Loom:

- `ResourceLocation` -> `Identifier`
- `MobSpawnType` -> `EntitySpawnReason`
- `GameRules` -> `net.minecraft.world.level.gamerules.GameRules`
- constantes de gamerule:
  - `RULE_DOMOBLOOT` -> `MOB_DROPS`
  - `RULE_SHOWDEATHMESSAGES` -> `SHOW_DEATH_MESSAGES`
  - `RULE_MOBGRIEFING` -> `MOB_GRIEFING`
- `RenderType` -> `net.minecraft.client.renderer.rendertype.RenderType`
- renombres simples de paquetes:
  - `IronGolem` -> `animal.golem`
  - `AbstractArrow` -> `projectile.arrow`
  - `ContainerListener` -> `world.inventory`
  - `Cat` -> `animal.feline`
  - `Wolf` -> `animal.wolf`
  - `AbstractHorse` -> `animal.equine`
  - `WaterAnimal` -> `animal.fish`
  - `Util` -> `net.minecraft.util.Util`
  - `ArmorMaterial` -> `world.item.equipment.ArmorMaterial`
  - `Tier` -> `ToolMaterial`
- Fabric menu API:
  - `ExtendedScreenHandlerFactory` -> `ExtendedMenuProvider`
  - `ExtendedScreenHandlerType` -> `ExtendedMenuType`
- Model layer registry:
  - `EntityModelLayerRegistry` -> `ModelLayerRegistry`
- `fabric` agrega `compileOnly 'com.google.code.findbugs:jsr305:3.0.2'` para mantener `javax.annotation.Nullable` sin runtime dependency.

Despues de este corte el build sigue fallando, pero los errores avanzaron hacia cambios arquitectonicos de `26.1`:

- renderers de entidad y block entity ahora requieren `RenderState`.
- `GuiGraphics` ya no existe como en `1.21.1`; la GUI migro a un pipeline nuevo de `GuiGraphicsExtractor`/`GuiRenderer`.
- `BlockEntityWithoutLevelRenderer`, `RenderStateShard`, `PickaxeItem`, `ArmorItem`, `Saddleable` y parte de item/equipment requieren rediseño o adapters por familia.
- Fabric 26.1 ya no expone algunas APIs cliente antiguas como `BuiltinItemRendererRegistry`, `ColorProviderRegistry` y `BlockRenderLayerMap` en el mismo paquete/forma.

Conclusion del segundo corte: el port `26.1` no debe tratarse como una migracion mecanica completa. Debe dividirse en sub-spikes:

1. gameplay/server common sin cliente.
2. items/equipment/materials.
3. menus/networking Fabric.
4. renderers con `RenderState`.
5. GUI/screens.

## Orden recomendado para el siguiente corte

1. Crear una tabla de renombres 26.1 para imports de Minecraft usados por `common`.
2. Migrar primero tipos transversales:
   - identifiers/resources
   - spawn reasons
   - game rules
   - annotations
3. Migrar cliente/renderers en un segundo corte:
   - `RenderType`
   - `MobRenderer`
   - `EntityRenderer`
   - model layer registry
4. Migrar Fabric API host wiring en un tercer corte:
   - block render layers
   - item colors
   - screen handlers
   - builtin item renderer
5. Repetir:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:compileJava --console=plain
```

hasta que el error avance desde imports/tipos hacia cambios de comportamiento.
