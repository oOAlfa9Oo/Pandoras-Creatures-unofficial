# Bring-up de Familia 26.1

## Estado

La rama `release/26.1` queda abierta como carril moderno despues del freeze de `1.21.1`.

Este documento no marca soporte jugable todavia. Su objetivo es fijar el primer punto de partida tecnico para la migracion.

## Loaders objetivo iniciales

- `fabric`: objetivo oficial del carril.
- `forge`: objetivo oficial del carril.
- `neoforge`: experimental mientras la linea `26.1` siga publicandose como beta.

## Versiones detectadas al abrir el carril

Consulta realizada contra los Maven oficiales el `2026-04-11`:

- Forge: `26.1-62.0.9`
- Fabric Loader: `0.19.1`
- Fabric API: `0.145.1+26.1`
- Fabric Loom: `1.16.1`
- NeoForge: `26.1.0.19-beta`

## Fuentes oficiales consultadas

- Fabric 26.1 porting: https://docs.fabricmc.net/26.1/develop/porting/
- Fabric Loom: https://docs.fabricmc.net/develop/loom/
- NeoForged 1.21.11 -> 26.1 primer: https://docs.neoforged.net/primer/docs/26.1/
- NeoForged versioning 26.1+: https://docs.neoforged.net/docs/gettingstarted/versioning/
- Forge 26.1 files: https://files.minecraftforge.net/net/minecraftforge/forge/index_26.1.html
- Forge 26.1-62.0.9 MDK: https://maven.minecraftforge.net/net/minecraftforge/forge/26.1-62.0.9/forge-26.1-62.0.9-mdk.zip

## Cambios iniciales aplicados al spike Fabric

- `minecraft_version=26.1`
- `fabric_loader_version=0.19.1`
- `fabric_api_version=0.145.1+26.1`
- `fabric_loom_version=1.16.1`
- `fabric/build.gradle` usa el plugin `net.fabricmc.fabric-loom`
- `fabric/build.gradle` elimina la dependency `mappings`
- `fabric/build.gradle` reemplaza `modImplementation` por `implementation`
- `fabric.mod.json` exige Java `>=25`

## Flags de aislamiento por loader

Durante el bring-up de `26.1` se agregaron flags de Gradle para validar un loader sin cargar plugins de los otros loaders:

- `-PfabricOnly=true`: incluye `common + fabric`.
- `-PforgeOnly=true`: incluye `common + forge`.
- `-PneoforgeOnly=true`: incluye `common + neoforge`.
- `-PskipForge=true`: evita cargar `forge` en validaciones mixtas.
- `-PskipNeoForge=true`: evita cargar `neoforge` en validaciones mixtas.

Estos flags permiten validar cada loader contra su propio plugin sin que un fallo de configuracion en otro carril bloquee el diagnostico.

## Estrategia de migracion

No cambiar todos los loaders de golpe.

Orden recomendado:

1. Actualizar solo metadatos y matriz tecnica de `26.1`.
2. Abrir un spike de build con `fabric` porque es el carril mas continuo.
3. Portar `forge` cuando `fabric` compile o cuando el bloqueo principal sea comun a ambos.
4. Mantener `neoforge` como experimental hasta que el build no dependa de betas inestables.
5. Rehabilitar `validateCurrentFamily` para esta rama solo cuando los loaders objetivo esten definidos y compilen.

## Primeros riesgos esperados

- Minecraft `26.1` puede requerir ajustes de Java, Gradle y toolchain frente a `1.21.1`.
- Fabric documenta que Minecraft `26.1` requiere Java 25 como minimo para la Gradle JVM.
- APIs de data components, networking, registry y worldgen pueden haber cambiado.
- `NeoForge 26.1` esta en beta al momento de abrir la rama, por lo que no debe bloquear el primer cierre de `fabric + forge`.
- La tarea `validateCurrentFamily` actual asume los tres loaders activos; en esta rama puede requerir una variante temporal si `neoforge` queda experimental.

## Criterio para pasar de bring-up a soporte

- `fabric` compila.
- `forge` compila.
- cliente y servidor arrancan al menos en `fabric` y `forge`.
- se valida creative tab, entidades principales, `Bufflon`, `End Troll Box`, `End Prison` y spawns naturales.
- la matriz tecnica se actualiza con Java, Gradle y mappings finales de la rama.

## Resultado del primer spike Fabric

Comando:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:compileJava --console=plain
```

Resultado:

- el build llega a `:fabric:compileJava`
- la compilacion falla por cambios reales de API de Minecraft/Fabric 26.1
- el detalle queda documentado en `FABRIC_26.1_COMPILE_AUDIT.md`

## Resultado del corte Fabric/Forge/NeoForge

Validacion local:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:build --console=plain
.\gradlew.bat -PforgeOnly=true :forge:build --console=plain
.\gradlew.bat -PforgeOnly=true :forge:runData --console=plain
.\gradlew.bat -PforgeOnly=true :forge:runGameTestServer --console=plain
.\gradlew.bat -PneoforgeOnly=true :neoforge:build --console=plain
```

Resultado:

- `fabric` compila y empaqueta en `26.1`.
- `fabric` ejecuta preflight de servidor hasta cargar `Pandoras Creatures Fabric bootstrap initialized`; el arranque completo queda detenido por `eula.txt`, que debe aceptar el usuario localmente antes de validar servidor dedicado completo.
- `forge` compila, empaqueta, ejecuta datagen y arranca el servidor GameTest headless en `26.1`.
- `neoforge` compila y empaqueta en `26.1` como carril experimental.

Cambios tecnicos relevantes:

- `common` y `neoforge` usan Java toolchain `25`.
- `common` y `neoforge` usan ModDevGradle `2.0.141`.
- `neo_form_version` se corrigio a `26.1-1`, que es el NeoForm declarado por `neoforge 26.1.0.19-beta`.
- `forge` usa el MDK oficial `26.1-62.0.9`, ForgeGradle `[7.0.17,8)`, Gradle `9.4.0` y Java toolchain `25`.
- `forge` se migro a EventBus 7 con `BusGroup`, `RegisterEvent.getBus(modEventBus)` y eventos con `.BUS`.
- `forge` usa `minecraft.dependency("net.minecraftforge:forge:26.1-62.0.9")`, `minecraft.mavenizer(it)`, `fg.forgeMaven` y `fg.minecraftLibsMaven`, siguiendo el layout del MDK oficial.
- `PCCreativeTabEntries` ya no expone `CreativeModeTab.Output` desde `common`; ahora recibe `Consumer<ItemLike>` y cada loader adapta su salida.
- `neoforge` migra `End Troll Box` item renderer al evento moderno `RegisterSpecialModelRendererEvent`.
- `neoforge` actualiza adapters de registry, networking cliente->servidor, sided hooks, block entities, entity type keys y NBT de `PandoricShardBlockEntity`.
- Los spawn eggs dejan de depender del viejo `minecraft:item/template_spawn_egg`; ahora tienen modelos `minecraft:item/generated` con textura propia y JSON moderno en `assets/pandoras_creatures/items`.
- Los items y block-items comunes de `26.1` deben tener descriptor en `assets/pandoras_creatures/items/*.json`, ademas del modelo en `models/item/*.json`; si falta ese descriptor, el creativo muestra el placeholder morado/negro aunque el PNG exista.
- `arachnon_crystal` usa una textura duplicada en el atlas de bloques (`textures/block/crystal.png`) para evitar que el loader rechace el modelo por mezclar atlas de item y block.
- La mayor parte de `neoforge/datagen` vuelve a compilar con `Identifier` y `GatherDataEvent#addProvider`.
- `neoforge` vuelve a compilar los GameTests. La API antigua por anotaciones se reemplazo por registro explicito via `RegisterGameTestsEvent`, manteniendo los cuerpos de prueba por entidad.
- `neoforge` mantiene los GameTests aislados al run `gameTestServer`; los runs jugables `client` y `server` no activan `neoforge.enabledGameTestNamespaces`, y el bootstrap solo registra tests si ese namespace incluye `pandoras_creatures`.
- Los helpers de GameTest usan serializacion moderna `ValueInput`/`ValueOutput`, nombres de entidades `26.1` y firmas server-side (`ServerLevel`) donde Minecraft las exige.
- `neoforge` separa datagen en eventos concretos `GatherDataEvent.Server` y `GatherDataEvent.Client`; el run `data` usa `serverData` para evitar registrar listeners sobre el evento abstracto.
- Los bootstraps comunes de bloques e items asignan `ResourceKey` en las propiedades para cumplir el requisito de ids explicitos de `26.1`.
- Las recetas custom de `End Troll Box` conservan `ItemStackTemplate` durante decode/streaming y solo materializan `ItemStack` en tiempo de ensamblado, evitando fallos por componentes aun no enlazados.
- Las recetas generadas usan el formato moderno de ingredientes como strings (`"mod:item"` y `"#mod:tag"`).
- Las configured features de plantas se generan como `minecraft:simple_block`, ya que el viejo wrapper `minecraft:flower` no carga correctamente en este carril.
- `forge` reutiliza los helpers comunes `PCBlockBootstrap.properties(...)` y `PCItemBootstrap.properties(...)` para asignar `ResourceKey` a bloques/items antes de construirlos, requisito runtime de `26.1`.

## Correcciones visuales compartidas 26.1

Estas correcciones quedaron en `common`, por lo que aplican al carril `26.1` de `fabric`, `forge` y `neoforge`. No son backports automaticos para ramas antiguas; si se abre `release/1.20.1`, `release/1.19.2` u otra familia, se debe portar el mismo patron y validar esa rama.

- `Arachnon Hammer`: el modelo de item usa solo texturas del atlas de item (`textures/item`) para evitar placeholders morado/negro en el creativo.
- `End Troll Box`: el item renderer comun no re-aplica transforms legacy de `BlockEntityWithoutLevelRenderer`; el descriptor/special renderer moderno ya centra el modelo en `26.1`.
- `End Troll Box`: el block entity renderer comun usa `OverlayTexture.NO_OVERLAY` al renderizar el bloque colocado; no debe hardcodear overlay `0`, porque puede verse como filtro rojizo en runtime.
- `End Troll Box`: los nombres en castellano se normalizan con recursos comunes `es_ar`, `es_es` y `es_mx`.
- `Acidic Archvine`: el modelo comun oculta `acid_blob_holder`, igual que el render legacy original, y el renderer comun rota la entidad para que cuelgue del techo en vez de mirar hacia arriba.
- `BufflonMenu`: los botones de estado usan el tamano real del atlas `bufflon_menu_buttons.png` (`256x256`) para que las UV no apunten a zonas incorrectas.

## Smoke runtime NeoForge 26.1

Validacion local:

```powershell
.\gradlew.bat -PneoforgeOnly=true :neoforge:runData --console=plain
.\gradlew.bat -PneoforgeOnly=true :neoforge:runGameTestServer --console=plain
```

Resultado:

- `runData` finaliza correctamente y regenera recetas, biome modifiers, configured features, placed features y structures.
- `runGameTestServer` arranca servidor dedicado headless, carga datapacks, recetas y advancements.
- `runGameTestServer` ejecuta `28` tests en total: `1` vanilla default y `27` de `pandoras_creatures`.
- Todos los `28/28` GameTests requeridos pasan.
- El warning previo de NeoForge por `@OnlyIn` se elimino retirando esa anotacion del handler cliente `AnimationPayloadClientHandler`.

## Smoke runtime Fabric 26.1

Validacion local:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:runServer --console=plain
```

Resultado:

- El primer intento detecto un fallo real de runtime en `PCFabricEntities`: `EntityType.Builder.build(null)` ya no es valido en `26.1`.
- Se corrigio Fabric para construir entidades con `ResourceKey` usando el helper compartido `PCEntityTypeFactory.entityKey(...)`.
- El segundo intento carga Fabric Loader, Fabric API y el mod hasta `Pandoras Creatures Fabric bootstrap initialized`.
- El proceso se detiene en el aviso de `eula.txt`; no se acepta la EULA automaticamente porque es una decision del usuario.
- Para completar el smoke de servidor dedicado Fabric, aceptar la EULA local en `fabric/run/fabric-server/eula.txt` y repetir el comando.

## Smoke runtime Forge 26.1

Validacion local:

```powershell
.\gradlew.bat -PforgeOnly=true :forge:runData --console=plain
.\gradlew.bat -PforgeOnly=true :forge:runGameTestServer --console=plain
```

Resultado:

- `runData` inicializa Forge `62.0.9`, carga el mod y termina sin errores de registro.
- `runGameTestServer` arranca servidor dedicado headless, carga datapacks, recetas y advancements.
- El smoke GameTest ejecuta `1` test requerido y finaliza con `All 1 required tests passed`.
- Los errores previos `Block id not set` y `Item id not set` quedaron resueltos al usar los helpers comunes de IDs.

## Gate publicable 26.1

El gate integrado de publicacion para `Fabric + Forge + NeoForge` es:

```powershell
.\gradlew.bat validatePublishable26_1 --console=plain
```

Resultado validado localmente:

- verifica arquitectura common/loader.
- verifica nombres de artefactos.
- verifica documentos multiversion.
- empaqueta `fabric` via `:fabric:jar`.
- compila y empaqueta `forge` via `:forge:build`.
- ejecuta `forge` datagen.
- ejecuta `forge` GameTest server smoke.
- compila y empaqueta `neoforge` via `:neoforge:build`.
- ejecuta `neoforge` datagen.
- ejecuta `neoforge` GameTest server con `28/28` tests pasando.

Nota: el gate integrado usa `:fabric:jar` porque `:fabric:build` en modo mixto Fabric + NeoForge intenta configurar `:fabric:test` mediante `fabric-loom-companion`, plugin que no esta disponible en este workspace. La validacion completa de Fabric sigue siendo el build aislado con `-PfabricOnly=true`.

Para recolectar solo los jars publicables de `26.1` en una carpeta limpia:

```powershell
.\gradlew.bat collectPublishable26_1 --console=plain
```

Salida validada:

- `build/publishable/26.1/pandoras_creatures-fabric-26.1-3.1.0-beta.jar`
- `build/publishable/26.1/pandoras_creatures-forge-26.1-3.1.0-beta.jar`
- `build/publishable/26.1/pandoras_creatures-neoforge-26.1-3.1.0-beta.jar`

## Validacion manual cliente 26.1

- `fabric`: validado manualmente en cliente dev el `2026-04-19`.
- Validacion Fabric cubierta: creative tab, assets especiales, `Arachnon Hammer`, `End Troll Box`, botones de `BufflonMenu` y orientacion visual de `Acidic Archvine`.
- `fabric`: regresion validada manualmente despues de los ajustes compartidos de `End Troll Box`; inventario, mano y bloque colocado quedan correctos.
- `neoforge`: validado manualmente en cliente dev el `2026-04-19`.
- Validacion NeoForge cubierta: creative tab, assets especiales y `End Troll Box` colocadas sin filtro rojizo despues de corregir el overlay comun.
- `neoforge`: regresion final validada manualmente despues de los ajustes compartidos de `End Troll Box`; inventario, mano y bloque colocado quedan correctos.
- `neoforge`: validacion automatizada posterior al aislamiento de GameTests pasa con `:neoforge:compileJava` y `:neoforge:runGameTestServer` (`28/28`).
- `forge`: validado manualmente en cliente dev el `2026-04-19`.
- Validacion Forge cubierta: creative tab, assets especiales, `End Troll Box` en inventario/mano mediante special renderer propio de Forge, y `End Troll Box` colocadas con el overlay comun corregido.
- `fabric`, `forge` y `neoforge` quedan validados manualmente para el smoke visual de cliente `26.1`.

Pendientes deliberados de NeoForge `26.1`:

- Los model generators NeoForge especificos de bloque/item se retiraron del carril `26.1`; los JSON de modelos quedan como recursos comunes generados en `common/src/generated/resources`.
- `PlantHatClientItemExtensions` compila con la firma nueva, pero necesita validacion visual runtime porque la API ya no entrega la entidad directamente en ese hook.
- `Forge 26.1` ya no se considera bloqueado por tooling; el bloqueo correspondia a intentar usar ForgeGradle 6 en una familia que el MDK oficial resuelve con ForgeGradle 7.
- Falta validacion visual/manual de cliente para assets especiales en `neoforge` y `forge` antes de declarar soporte jugable final.
