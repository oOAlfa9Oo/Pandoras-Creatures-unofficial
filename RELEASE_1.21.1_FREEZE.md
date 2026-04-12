# Freeze de Familia 1.21.1

## Estado

La familia `1.21.1` queda lista para congelarse como rama `release/1.21.1` cuando el working tree este limpio y se decida publicar el corte.

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

## Pendiente antes de crear `release/1.21.1`

- Dejar el working tree limpio con un commit del corte de Fase 7.
- Crear la rama desde ese commit:

```powershell
git branch release/1.21.1
```

- Si se va a publicar remotamente:

```powershell
git push origin release/1.21.1
```

## Riesgos conocidos

- Las ramas viejas no deben asumir que `validateCurrentFamily` aplica sin cambios, porque algunas familias no tendran `neoforge`.
- `26.1` debe abrirse como carril separado; no se debe tratar como una supuesta version `1.26.0`.
- La carpeta `docs/` y la bitacora principal estan ignoradas localmente por `.gitignore`, por eso los documentos de Fase 7 que deben versionarse viven tambien en raiz.
