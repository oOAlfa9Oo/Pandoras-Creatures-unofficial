# Freeze de Familia 1.21.1

## Estado

La familia `1.21.1` vive en la rama `release/1.21.1` y queda como base estable para validacion final antes de publicar el corte.

Loaders objetivo:

- `fabric`
- `forge`
- `neoforge`

Version del mod:

- `3.1.0-beta`

## Validacion automatizada

Gate minimo:

```powershell
.\gradlew.bat validateCurrentFamily --console=plain
```

El gate incluye:

- `compileJava`
- `test`
- `verifyArchitecture`
- `verifyArtifactNaming`
- `buildLoaderJars`

## Artefactos esperados

Despues de ejecutar `validateCurrentFamily`, los jars distribuibles esperados son:

- `fabric/build/libs/pandoras_creatures-fabric-1.21.1-3.1.0-beta.jar`
- `forge/build/libs/pandoras_creatures-forge-1.21.1-3.1.0-beta.jar`
- `neoforge/build/libs/pandoras_creatures-neoforge-1.21.1-3.1.0-beta.jar`

## Validacion manual minima realizada en la fase actual

- `NeoForge` fue validado durante la Fase 5/6 como host estable de referencia.
- `Fabric` fue validado durante la Fase 5 con contenido, cliente, estructuras, Bufflon y visuales principales.
- `Forge` fue validado durante la Fase 6 con arranque, creative tab, entidades, spawns, Bufflon, End Troll Box, End Prison y Acidic Archvine.

## Pendiente antes de publicar `release/1.21.1`

- Correr el gate automatizado final:

```powershell
.\gradlew.bat validateCurrentFamily --console=plain
```

- Hacer smoke visual final de cliente en `fabric`, `forge` y `neoforge`.
- Publicar los jars finales despues de validar que no queden regresiones visuales o de worldgen.

## Correcciones Fabric posteriores al smoke

- La End Troll Box no dropeaba el item de la caja en Fabric porque los loot tables neutrales estaban generados solo bajo `neoforge/src/generated/resources`.
- La End Troll Box conserva comportamiento tipo shulker: al romperse debe caer la caja con su contenido guardado en componentes, no soltar contenido suelto por `onRemove`.
- Los datos neutrales compartidos (`recipes`, `loot_table`, `tags/item` y tags vanilla de entidades) se movieron al output comun `common/src/generated/resources` mediante `pandoras_creatures.sharedGeneratedDataOutput`.
- Los datos especificos de NeoForge (`neoforge/biome_modifier` y `loot_modifiers`) permanecen en `neoforge/src/generated/resources`.
- Fabric ahora inyecta la generacion natural de plantas desde el catalogo comun `PCBiomeFeatureCatalog`, igualando las reglas usadas para generar los biome modifiers de Forge/NeoForge.
- Fabric registra explicitamente los serializers de receta `end_troll_box` y `end_troll_box_coloring`, necesarios al compartir las recetas comunes desde `common/src/generated/resources`.
- Las plantas worldgen usan el patron floral del mod original (`Feature.FLOWER` con 64 intentos y repeticion 3), pero expresado en JSON 1.21.1 con `block_predicate_filter` sobre `minecraft:air` para evitar reemplazar bloques del terreno.

## Comparacion worldgen plantas contra 1.16.5

Fuente original revisada:

- `andrews/pandoras_creatures/registry/PCFeatures.java` (fuente 1.16.5, revisada localmente)
- `andrews/pandoras_creatures/registry/util/PCBiomeAdditions.java` (fuente 1.16.5, revisada localmente)
- `andrews/pandoras_creatures/objects/blocks/PCBlockBush.java` (fuente 1.16.5, revisada localmente)

Hallazgo:

- En 1.16.5, `horsetail`, `dhania` y `hill_bloom` se generaban con `Feature.FLOWER` + `BlockClusterFeatureConfig` usando `tries(64)`.
- La feature se agregaba en `GenerationStage.Decoration.VEGETAL_DECORATION`.
- La repeticion era `func_242731_b(3)`, equivalente al `count: 3` usado en 1.21.1.
- El bloque de planta solo era valido sobre `grass_block`, `dirt`, `coarse_dirt`, `podzol` o `farmland`.
- En 1.21.1, el JSON moderno necesita declarar explicitamente un `block_predicate_filter` de `minecraft:air` en el sub-feature `simple_block`; sin ese filtro, la feature puede reemplazar terreno durante los intentos verticales.

Regla final 1.21.1:

- Mantener `minecraft:flower`, `tries: 64`, `count: 3`, `xz_spread: 7`, `y_spread: 3`.
- Usar heightmap `MOTION_BLOCKING`.
- Colocar la planta solo cuando el destino es aire, dejando que `PCPlantBlock#mayPlaceOn` valide el bloque soporte.

## Comparacion cangrejo anfibio contra 1.16.5

Fuente original revisada:

- `andrews/pandoras_creatures/entities/CrabEntity.java` (fuente 1.16.5, revisada localmente)
- `andrews/pandoras_creatures/entities/bases/AnimatedWaterMobEntity.java` (fuente 1.16.5, revisada localmente)
- `andrews/pandoras_creatures/registry/util/SpawnConditions.java` (fuente 1.16.5, revisada localmente)

Hallazgo:

- El cangrejo original es anfibio: puede aparecer en `beach` y `warm_ocean`, con arena o pasto debajo, usando placement `NO_RESTRICTIONS`.
- En 1.16.5 `AnimatedWaterMobEntity` podia sobreescribir `canBreatheUnderwater()`.
- En 1.21.1 `canBreatheUnderwater()` depende del tag vanilla `minecraft:can_breathe_under_water`, asi que `crab` y `seahorse` deben declararse alli por datos compartidos.
- La regla comun de spawn del cangrejo ahora mantiene el comportamiento anfibio, pero solo permite que el bloque de aparicion sea aire o agua para evitar colocaciones dentro de bloques no validos.

## Ajuste de validacion Arachnon

- Para facilitar la validacion manual en `1.21.1`, `Arachnon` ahora puede generarse en todos los biomas del Overworld mediante `#minecraft:is_overworld`.
- El cambio vive en `PCBiomeSpawnCatalog`, por lo que se reutiliza en Fabric, Forge y NeoForge sin duplicar reglas por loader.
- La regla de spawn sigue alineada con `1.16.5` en dificultad/luz: dificultad distinta de peaceful y brillo `<= 7`.
- Para hacerlo mas visible en runtime sin sacarlo del comportamiento hostil vanilla, su tuning comun subio a peso `60` y grupo `1-2`.
- Se agregaron pruebas para fijar ese contrato:
  - test comun del catalogo para `#minecraft:is_overworld` y peso/grupo de `Arachnon`
  - test comun de tuning para peso y tamano de pack de `Arachnon`
  - GameTest NeoForge para verificar que el mob queda inyectado en la lista runtime de monstruos del Overworld
  - GameTest NeoForge para verificar que `SpawnPlacements` y `PCEntitySpawnRules` toman la misma decision para `Arachnon` en runtime
- Con esta verificacion, no se detecto un parametro fuera de rango ni una integracion rota del loader; si en juego sigue viendose poco, el siguiente ajuste ya seria de tuning de aparicion, no de correccion tecnica.

## Rutas de validacion natural Fabric

Entidades:

- `Acidic Archvine`: junglas (`#minecraft:is_jungle`) y Nether (`#minecraft:is_nether`); no aparece en peaceful. En jungla requiere `Y >= 62`, techo de `jungle_leaves`, bloque actual y superior en aire, y 5 bloques de aire hacia abajo. En Nether requiere `Y >= 38`, techo de `netherrack`, bloque actual y superior en aire, y 5 bloques de aire hacia abajo.
- `Arachnon`: cualquier bioma del Overworld (`#minecraft:is_overworld`); no aparece en peaceful y requiere luz baja (`brightness <= 7`).
- `Bufflon`: `snowy_plains`, `frozen_river`, `snowy_slopes`; requiere luz alta (`brightness >= 9`) y `grass_block` debajo.
- `Crab`: `beach` y `warm_ocean`; en playa aparece entre `Y 56..70`, en warm ocean entre `Y 30..60`, con `sand` o `grass_block` debajo.
- `Seahorse`: `ocean`, `lukewarm_ocean`, `deep_ocean`, `deep_lukewarm_ocean` y `warm_ocean`; requiere agua.

Plantas:

- `Horsetail`: `plains` y `sunflower_plains`.
- `Dhania`: `swamp`.
- `Hill Bloom`: `windswept_hills`, `windswept_gravelly_hills`, `windswept_forest`.

## Riesgos conocidos

- Las ramas viejas no deben asumir que `validateCurrentFamily` aplica sin cambios, porque algunas familias no tendran `neoforge`.
- `26.1` debe abrirse como carril separado; no se debe tratar como una supuesta version `1.26.0`.
- La carpeta `docs/` y la bitacora principal estan ignoradas localmente por `.gitignore`, por eso los documentos de Fase 7 que deben versionarse viven tambien en raiz.
