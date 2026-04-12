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
