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
- Validar tecla de inventario montado.
- Validar sincronizacion de animaciones.

## Worldgen y structures

- Compartir recursos `data` necesarios.
- Registrar structure types y piece types.
- Validar `End Prison` con `locate` y `place structure`.
- Validar biome spawns automaticos.

## Cliente especial

- Validar `End Troll Box` item renderer.
- Validar `Plant Hat`.
- Validar plantas en `cutout`.
- Revisar logs por missing textures, missing models y missing blockstates.

## Cierre

- Ejecutar `validateCurrentFamily` si la familia mantiene los tres loaders del workspace actual.
- Ejecutar build de cada loader objetivo si la rama no mantiene todos los loaders.
- Ejecutar tests comunes.
- Actualizar README de la rama.
- Documentar riesgos conocidos.
- Generar jars con nombre `pandoras_creatures-<loader>-<mc>-<mod>.jar`.
