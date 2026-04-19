# Checklist de Port Multiversion

Usar este checklist al abrir una familia nueva en `release/<minecraft>`.

## Preparacion

- Crear o actualizar la rama `release/<minecraft>`.
- Ajustar `gradle.properties` para la familia objetivo.
- Confirmar Java, Gradle, mappings y plugins de loader para esa familia.
- Confirmar loaders objetivo segun `MULTIVERSION_SUPPORT.md`.
- Actualizar `MULTIVERSION_TECHNICAL_MATRIX.md` con los valores concretos de la rama.
- Ejecutar una compilacion minima antes de portar contenido.

## Bootstrap

- Conectar `common`.
- Conectar entrypoint del loader.
- Inicializar `PandorasCreaturesCommon`.
- Dejar descriptors del loader funcionando.
- Validar `compileJava`.

## Contenido base

- Registrar sounds.
- Registrar core items.
- Registrar blocks simples.
- Registrar creative tab desde catalogo compartido.
- Validar que el cliente cargue sin missing assets del mod.

## Entidades

- Registrar entidades base.
- Registrar atributos.
- Registrar spawn eggs y colores.
- Registrar spawn placements.
- Registrar renderers y model layers.
- Validar al menos `Crab`, `Seahorse`, `Hellhound`, `End Troll`, `Bufflon` y `Acidic Archvine`.

## Menus y networking

- Registrar menus.
- Registrar payloads del loader.
- Validar `BufflonMenu`.
- Validar botones `Sit`, `Follow`, `Move Freely`, `Combat` y `Peaceful`.
- Validar que los botones de GUI usen el tamano real de su atlas para evitar UV corridas entre versiones.
- Validar tecla de inventario montado.
- Validar sincronizacion de animaciones.

## Worldgen y structures

- Compartir recursos `data` necesarios.
- Registrar structure types y piece types.
- Validar `End Prison` con `locate` y `place structure`.
- Validar biome spawns automaticos.

## Cliente especial

- Validar `End Troll Box` item renderer.
- Validar que los item renderers especiales no dupliquen transforms ya aplicados por descriptors o special renderers modernos.
- Validar `Plant Hat`.
- Validar plantas en `cutout`.
- Validar orientacion de entidades colgantes, especialmente modelos legacy como `Acidic Archvine`.
- Validar que modelos de item no mezclen atlas de `block` e `item` cuando la version exige descriptors modernos.
- Revisar logs por missing textures, missing models y missing blockstates.

## Cierre

- Ejecutar `validateCurrentFamily` si todos los loaders objetivo pueden configurarse con el wrapper de la rama.
- Si un loader queda bloqueado por tooling externo, ejecutar los gates aislados de los loaders publicables y documentar el bloqueo con comando exacto.
- Ejecutar build de cada loader publicable si la rama no mantiene todos los loaders.
- Ejecutar tests comunes.
- Actualizar README de la rama.
- Documentar riesgos conocidos.
- Generar jars con nombre `pandoras_creatures-<loader>-<mc>-<mod>.jar`.

## Gate actual de 26.1

El gate automatizado de esta familia valida los tres loaders activos:

```powershell
.\gradlew.bat -PfabricOnly=true :fabric:build --console=plain
.\gradlew.bat -PfabricOnly=true :fabric:runServer --console=plain
.\gradlew.bat -PforgeOnly=true :forge:build --console=plain
.\gradlew.bat -PforgeOnly=true :forge:runData --console=plain
.\gradlew.bat -PforgeOnly=true :forge:runGameTestServer --console=plain
.\gradlew.bat -PneoforgeOnly=true :neoforge:build --console=plain
.\gradlew.bat -PneoforgeOnly=true :neoforge:runData --console=plain
.\gradlew.bat -PneoforgeOnly=true :neoforge:runGameTestServer --console=plain
.\gradlew.bat -PfabricOnly=true verifyMultiversionDocs --console=plain
```

Tambien existe un gate integrado para recolectar el carril publicable `Fabric + Forge + NeoForge`:

```powershell
.\gradlew.bat validatePublishable26_1 --console=plain
```

Ese gate usa `:fabric:jar` en vez de `:fabric:build` porque, en modo mixto Fabric + NeoForge, Loom `1.16.1` intenta configurar `:fabric:test` mediante `fabric-loom-companion`, que no esta disponible en este workspace. La validacion completa de Fabric sigue siendo el build aislado con `-PfabricOnly=true`.

El smoke `:fabric:runServer` requiere que el usuario acepte la EULA en `fabric/run/fabric-server/eula.txt`. Sin aceptar esa EULA, el preflight es valido si el log carga `Pandoras Creatures Fabric bootstrap initialized` y se detiene solo en el aviso de EULA.

Para preparar una carpeta limpia con solo los jars publicables:

```powershell
.\gradlew.bat collectPublishable26_1 --console=plain
```

Salida esperada:

- `build/publishable/26.1/pandoras_creatures-fabric-26.1-3.1.0-beta.jar`
- `build/publishable/26.1/pandoras_creatures-forge-26.1-3.1.0-beta.jar`
- `build/publishable/26.1/pandoras_creatures-neoforge-26.1-3.1.0-beta.jar`

Forge `26.1` se mantiene sobre el MDK oficial `26.1-62.0.9` y ForgeGradle 7:

```powershell
.\gradlew.bat -PforgeOnly=true :forge:compileJava --console=plain
```

El port usa `net.minecraftforge.gradle` `[7.0.17,8)`, dependency `minecraft.dependency("net.minecraftforge:forge:26.1-62.0.9")`, Java `25` y EventBus 7 con `BusGroup`.
