# Playbook de Ramas Multiversion

Este playbook define como abrir y mantener ramas `release/<minecraft>` sin mezclar decisiones de distintas familias.

## Reglas antes de crear una rama

- No crear ramas release con cambios sin commitear.
- Validar primero la rama base con `compileJava test`.
- Confirmar que `MULTIVERSION_SUPPORT.md` y `MULTIVERSION_TECHNICAL_MATRIX.md` describen la familia que se va a abrir.
- Crear la rama desde el ultimo commit estable de la familia origen, no desde un working tree sucio.

## Comandos base

```powershell
git status --short --branch
.\gradlew.bat validateCurrentFamily --console=plain
git branch release/1.21.1
git branch release/26.1
```

Usar `git switch release/<minecraft>` solo cuando se vaya a trabajar especificamente en esa familia.

## Orden recomendado

1. Congelar `release/1.21.1` desde la rama actual cuando este lista para publicar.
2. Abrir `release/26.1` desde `release/1.21.1` o desde `main`, segun donde quede la linea moderna.
3. Bajar luego a `release/1.20.6` y `release/1.20.1`.
4. Dejar `release/1.19.2`, `release/1.18.2` y `release/1.17.1` para la oleada legacy.

## Politica de backport

- Backportear primero fixes de bugs criticos.
- Backportear gameplay nuevo solo si no fuerza reescrituras grandes de APIs antiguas.
- No backportear cambios cosmeticos si ponen en riesgo la estabilidad de ramas viejas.
- Documentar en el README de cada rama que loaders estan soportados.

## Checklist antes de publicar una rama

- `.\gradlew.bat validateCurrentFamily --console=plain` pasa en la familia actual.
- los jars usan `pandoras_creatures-<loader>-<mc>-<mod>.jar`.
- cliente y servidor arrancan en los loaders objetivo.
- `Bufflon`, `End Troll Box`, `End Prison` y spawns naturales tienen validacion manual minima.
- si un loader queda bloqueado por tooling externo, el release debe declarar soporte parcial y no publicar jar de ese loader.

## Comandos de validacion por familia actual

```powershell
.\gradlew.bat compileJava test verifyArtifactNaming --console=plain
.\gradlew.bat buildLoaderJars --console=plain
.\gradlew.bat validateCurrentFamily --console=plain
```

`validateCurrentFamily` es el gate minimo antes de crear una rama `release/<minecraft>` desde el estado actual.

Para la familia actual, revisar tambien `RELEASE_1.21.1_FREEZE.md`.

## Validacion cuando un loader esta bloqueado

No forzar una rama a fallar indefinidamente por un loader cuyo plugin no configura. En ese caso:

- aislar los loaders publicables con flags como `-PfabricOnly=true` o `-PneoforgeOnly=true`
- ejecutar sus builds y smokes runtime
- documentar el comando que reproduce el bloqueo del loader afectado
- mantener el loader bloqueado fuera de los artefactos publicables hasta que el tooling upstream cambie

Para `26.1`, revisar `RELEASE_26.1_BRINGUP.md`.
