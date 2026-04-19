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

## Tercer corte aplicado

Se aplico otro lote de migracion server/common y Fabric wiring:

- `ItemArachnonHammer` deja de depender de `PickaxeItem`, que ya no existe como clase publica en `26.1`.
  - Ahora extiende `Item`.
  - Usa `new Item.Properties().pickaxe(PCToolMaterials.ARACHNON_MATERIAL, 0.0F, -3.0F)`.
  - Mantiene su logica custom de minado 3x3.
- `PCToolMaterials.ARACHNON_MATERIAL` migra al `record ToolMaterial`.
  - Usa `ItemTags.DIAMOND_TOOL_MATERIALS` como material de reparacion.
- `ItemPlantHat` deja de depender de `ArmorItem`, que ya no existe como clase publica en `26.1`.
  - Ahora extiende `Item`.
  - Usa `new Item.Properties().humanoidArmor(PCArmorMaterials.PLANT_HAT, ArmorType.HELMET)`.
- `PCArmorMaterials.PLANT_HAT` migra al `record ArmorMaterial`.
  - Usa `ArmorType` en lugar de `ArmorItem.Type`.
  - Usa `EquipmentAssets.createId("pandoras_creatures:plant_hat")`.
  - Agrega el tag `data/pandoras_creatures/tags/item/repairs_plant_hat.json`.
- `BufflonEntity` deja de implementar `Saddleable`, porque esa interfaz ya no existe en el jar 26.1 inspeccionado.
  - Los metodos propios `isSaddled`, `isSaddleable` y `equipSaddle` se conservan como API interna del mod.
- `RecipeSerializer` deja de tratarse como interfaz.
  - Las recetas de End Troll Box ahora exponen factories `serializer()` que crean `new RecipeSerializer<>(CODEC, STREAM_CODEC)`.
  - Forge/NeoForge se ajustaron a esas factories para no depender de `new Serializer()`.
- Fabric networking migra:
  - `playS2C()` -> `clientboundPlay()`
  - `playC2S()` -> `serverboundPlay()`
- `FabricRegistryBridge` migra lookups de `Registry#get(id)` a `Registry#getValue(id)`.
- `FabricEntityBridge` limita `GameRules.MOB_GRIEFING` a `ServerLevel#getGameRules()`.

Despues de este corte el build sigue fallando, pero el frente de errores se concentro en:

- renderer/GUI 26.1:
  - `MobRenderer` y `EntityRenderer` requieren `RenderState`.
  - `BlockEntityRenderer` requiere `BlockEntityRenderState`.
  - `GuiGraphics` ya no existe como tipo de screen rendering.
  - `BuiltinItemRendererRegistry`, `ColorProviderRegistry`, `BlockRenderLayerMap` ya no estan disponibles igual.
  - `PlantHatModel` necesita migrar de `LivingEntity` a `HumanoidRenderState`.
- Block entities/NBT:
  - `BaseContainerBlockEntity` ahora carga/guarda con `ValueInput` y `ValueOutput`.
  - `ContainerHelper` tambien migro a `ValueInput`/`ValueOutput`.
  - `BlockEntityType.Builder` ya no esta disponible como antes.
- entidades/daño/XP:
  - `Entity#hurt` aparece como final en `26.1`.
  - `LivingEntity#hurt` ahora devuelve `void` en la jerarquia observada.
  - `lastHurtByPlayerTime`, `lastHurtByPlayer` y `getBaseExperienceReward()` cambiaron.

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

## Cuarto corte aplicado

Se aplico una migracion adicional para que el carril `fabricOnly` avance como spike servidor/common de 26.1:

- Las entidades common migran a las firmas de 26.1:
  - `hurtServer` / `hurtOrSimulate` en vez de sobrescribir `hurt`;
  - `getBaseExperienceReward(ServerLevel)`;
  - `ValueInput` / `ValueOutput` para persistencia;
  - `setPos` / `snapTo` en reemplazo de llamadas directas a `moveTo`.
- `BufflonEntity` deja de depender de listeners removidos de `SimpleContainer`.
  - Usa un `SimpleContainer` local observable que recalcula montura y accesorio cuando cambia el inventario.
  - El owner sincronizado se guarda como string UUID, porque `EntityDataSerializers.OPTIONAL_UUID` ya no existe en 26.1.
- `EndTrollBoxBlockEntity` migra a `ValueInput` / `ValueOutput` y a `ContainerUser` para apertura/cierre.
- Las particulas `DustParticleOptions` migran al formato de color entero requerido en 26.1.
- `fabricOnly` excluye temporalmente `andrews/pandoras_creatures/client/**` y `Animator.java`.
  - Motivo: el cliente 26.1 requiere una migracion separada al sistema moderno de `EntityRenderState`, `LivingEntityRenderState` y `BlockEntityRenderState`.
  - Este corte valida primero el servidor/common para no mezclar ese frente con la reescritura completa de renderers y GUI.

## Quinto corte aplicado

El carril `Fabric 26.1` en modo `fabricOnly` ya compila y empaqueta el frente servidor/common:

- Bloques e items common migran las firmas restantes de 26.1:
  - `updateShape` usa `ScheduledTickAccess`;
  - los buckets actualizan `checkExtraContent`, `playEmptySound` y tooltips;
  - los spawn eggs usan el componente diferido `ENTITY_DATA` de `SpawnEggItem`.
- Las recetas de `End Troll Box` migran a los constructores modernos:
  - `Recipe.CommonInfo`;
  - `CraftingRecipe.CraftingBookInfo`;
  - `ItemStackTemplate`;
  - serializers genericos compatibles con `ShapedRecipe` y `ShapelessRecipe`.
- Goals y combate migran a firmas servidor:
  - `doHurtTarget(ServerLevel, Entity)`;
  - `TargetingConditions#test(ServerLevel, LivingEntity, LivingEntity)`;
  - `isWithinHome` en lugar de la restriccion removida;
  - proteccion de `Plant Hat` por `EquipmentSlot.HEAD`.
- `End Prison` migra spawn de shulkers a `EntitySpawnReason.STRUCTURE` y `JigsawStructure.MaxDistance`.

Validacion local:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:compileJava --console=plain
.\gradlew.bat -PfabricOnly=true verifyMultiversionDocs --console=plain
.\gradlew.bat -PfabricOnly=true :fabric:build --console=plain
```

Resultado: los tres comandos pasaron. El jar generado queda en `fabric/build/libs/pandoras_creatures-fabric-26.1-3.1.0-beta.jar`.

Pendiente deliberado: el modo `fabricOnly` sigue excluyendo `client/**` y `Animator.java`. La siguiente unidad de trabajo es migrar renderers, GUI, item renderers y model layers al sistema de render state de 26.1.

## Sexto corte aplicado

Se reactivo una parte del cliente `Fabric 26.1` y se reemplazo la exclusion total de `client/**` por exclusiones mas finas.

- Se agrego `PCEntityRenderState` como puente comun para pasar datos legacy de entidad/animacion al pipeline `RenderState` de 26.1.
- Se agrego `PCMobRenderer` para centralizar:
  - `createRenderState`;
  - `extractRenderState`;
  - resolucion de textura desde la entidad legacy.
- Migraron a `PCMobRenderer`:
  - `AcidicArchvineRenderer`;
  - `ArachnonRenderer`;
  - `BufflonRenderer`;
  - `CrabRenderer`;
  - `EndTrollRenderer`;
  - `HellhoundRenderer`;
  - `SeahorseRenderer`.
- Las capas emissive de Arachnon, End Troll y Hellhound migran de `render(...)` con `MultiBufferSource` a `submit(...)` con `SubmitNodeCollector`.
- `PlantHatModel` y `FabricPlantHatArmorRenderer` migran a `HumanoidRenderState` y `ArmorRenderer.submitTransformCopyingModel`.
- `Animator` usa `Minecraft#getDeltaTracker()` en vez de la API removida `getTimer()`.
- `RenderTypes` reemplaza accesos estaticos removidos desde `RenderType` donde aplica.

Validacion local:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:compileJava --console=plain
.\gradlew.bat -PfabricOnly=true :fabric:build --console=plain
```

Resultado: ambos comandos pasaron para el cliente parcial de 26.1.

Pendiente deliberado actualizado: `fabricOnly` todavia excluye temporalmente:

- `andrews/pandoras_creatures/client/renderer/tile/**`;
- `andrews/pandoras_creatures/client/screen/**`;
- `andrews/pandoras_creatures/client/widget/**`;
- `andrews/pandoras_creatures/client/bootstrap/PCFabricClientScreenRegistry.java`.

Tambien quedaron simplificados temporalmente los renderers de bullets y el render especial de lengua de Acidic Archvine. El siguiente corte debe restaurar esos efectos sobre el nuevo pipeline `submit` / `SubmitNodeCollector` y luego abordar GUI.

## Septimo corte aplicado

Se reactivo el frente GUI de `Fabric 26.1`:

- `BufflonScreen` migra de `GuiGraphics` a `GuiGraphicsExtractor`.
  - Usa el constructor moderno de `AbstractContainerScreen` con `imageWidth` / `imageHeight`.
  - `renderLabels` migra a `extractLabels`.
  - `renderBg` / `render` migran a `extractContents`.
  - Los items fake usan `GuiGraphicsExtractor#fakeItem`.
  - El modelo 3D del Bufflon usa `InventoryScreen.extractEntityInInventoryFollowsMouse`.
- `EndTrollBoxScreen` migra al mismo patron `extractLabels` / `extractContents`.
- `BufflonStateButton` migra de `renderWidget` a `extractContents`.
- `PCFabricClientScreenRegistry` vuelve a estar activo en `PandorasCreaturesFabricClient`.
- `fabricOnly` ya no excluye `screen/**`, `widget/**` ni `PCFabricClientScreenRegistry.java`.

Validacion local:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:compileJava --console=plain
.\gradlew.bat -PfabricOnly=true :fabric:build --console=plain
```

Resultado: ambos comandos pasaron.

Pendiente deliberado actualizado: `fabricOnly` solo mantiene fuera `andrews/pandoras_creatures/client/renderer/tile/**`, porque el renderer de block entity/item necesita migracion propia al pipeline de `BlockEntityRenderState` y al reemplazo moderno de `BlockEntityWithoutLevelRenderer`.

## Octavo corte aplicado

Se reactivo el renderer de block entity de `End Troll Box` para `Fabric 26.1`:

- `EndTrollBoxBlockEntityRenderer` migra a `BlockEntityRenderer<EndTrollBoxBlockEntity, State>`.
- Agrega un `State` propio que extiende `BlockEntityRenderState` y conserva:
  - direccion;
  - textura/color;
  - progreso de apertura.
- `extractRenderState` captura los datos del bloque y llama `BlockEntityRenderState.extractBase`.
- `submit` reemplaza el viejo `render(...)` y emite cada `ModelPart` con `SubmitNodeCollector#submitModelPart`.
- `PCFabricClientBlockRegistry` vuelve a registrar `EndTrollBoxBlockEntityRenderer` usando `BlockEntityRendererRegistry`.
- `fabricOnly` ya no excluye todo `client/renderer/tile/**`; ahora solo mantiene fuera `PCItemRenderer.java`.

Validacion local:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:compileJava --console=plain
.\gradlew.bat -PfabricOnly=true :fabric:build --console=plain
```

Resultado: ambos comandos pasaron.

Pendiente deliberado actualizado: el unico archivo de cliente excluido por `fabricOnly` es `andrews/pandoras_creatures/client/renderer/tile/PCItemRenderer.java`. Ese renderer dependia de `BlockEntityWithoutLevelRenderer`, que ya no existe igual en 26.1; debe migrarse al sistema moderno de `SpecialModelRenderer` / modelos de item. Tambien siguen pendientes los efectos especiales simplificados de bullets y lengua de `Acidic Archvine`.

## Noveno corte aplicado

Se reactivo el renderer especial de item para `End Troll Box` en `Fabric 26.1`:

- `PCItemRenderer` deja de extender `BlockEntityWithoutLevelRenderer`, API que ya no encaja con 26.1.
- `PCItemRenderer` ahora implementa `NoDataSpecialModelRenderer` y emite el modelo cerrado de `EndTrollBoxModel` con `SubmitNodeCollector#submitModelPart`.
- Se agrego `PCItemRenderer.Unbaked` con `MapCodec` para que el item model JSON pueda declarar el renderer especial `pandoras_creatures:end_troll_box`.
- Se agrego `PCFabricSpecialModelRegistry` y un mixin accessor `SpecialModelRenderersAccessor` para registrar el tipo especial en el mapper interno de `SpecialModelRenderers`.
- `fabric.mod.json` declara `pandoras_creatures.client.mixins.json`.
- Se agregaron 17 JSONs bajo `assets/pandoras_creatures/items/` para la caja base y todas las variantes de color, siguiendo el formato moderno `minecraft:special`.
- `fabricOnly` ya no excluye `PCItemRenderer.java`; el frente cliente vuelve a compilar con ese archivo incluido.

Validacion local:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:compileJava --console=plain
.\gradlew.bat -PfabricOnly=true :fabric:build --console=plain
.\gradlew.bat -PfabricOnly=true verifyMultiversionDocs --console=plain
```

Resultado: los tres comandos pasaron. Tambien se verifico que el jar generado incluye `PCItemRenderer`, `SpecialModelRenderersAccessor`, `pandoras_creatures.client.mixins.json` y los 17 JSONs `assets/pandoras_creatures/items/*_end_troll_box.json`.

Pendiente deliberado actualizado: ya no quedan exclusiones de codigo cliente en `fabricOnly`. Siguen como riesgo visual, no como bloqueo de compilacion, los renderers simplificados de bullets del End Troll y la lengua especial de `Acidic Archvine`; ambos necesitan una segunda pasada visual sobre el pipeline `submit` / `SubmitNodeCollector`.

## Decimo corte aplicado

Se recuperaron dos efectos visuales que habian quedado simplificados durante la migracion inicial del cliente `Fabric 26.1`:

- Se agrego `EndTrollBulletRenderer` como renderer comun para los tres proyectiles del End Troll.
- `EndTrollBulletDamageRenderer`, `EndTrollBulletPoisonRenderer` y `EndTrollBulletWitherRenderer` ahora solo declaran su textura y reutilizan la animacion/render comun.
- El render de bullets vuelve a emitir:
  - la pasada principal con la textura propia de cada variante;
  - la pasada translucida ampliada con alpha bajo, equivalente al brillo/halo del renderer original.
- `AcidicArchvineRenderer` recupera la lengua hacia la entidad objetivo usando `SubmitNodeCollector#submitCustomGeometry`.
- La lengua conserva la logica base del original:
  - interpolacion entre posicion del Archvine y del objetivo;
  - orientacion por yaw/pitch hacia el objetivo;
  - desplazamiento de UV animado;
  - textura de lengua segun tipo disponible.
- Para el tipo 3 de Archvine se usa fallback a `acidic_archvine_tongue_2.png`, porque en los assets actuales solo existen `acidic_archvine_tongue_1.png` y `acidic_archvine_tongue_2.png`.

Validacion local:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:compileJava --console=plain
.\gradlew.bat -PfabricOnly=true :fabric:build --console=plain
.\gradlew.bat -PfabricOnly=true verifyMultiversionDocs --console=plain
```

Resultado: los tres comandos pasaron.

Pendiente deliberado actualizado: no quedan efectos cliente conocidos excluidos por compilacion. Falta validacion visual/runtime en entorno dev para confirmar que el halo del bullet, la lengua del `Acidic Archvine`, el renderer especial de `End Troll Box` como item y las texturas especiales se ven igual de bien dentro del juego.
