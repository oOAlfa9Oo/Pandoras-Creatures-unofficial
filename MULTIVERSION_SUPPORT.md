# Estrategia Multiversion

## Objetivo

Este documento define la politica de soporte para convertir el proyecto desde una base multiloader en una sola version hacia una base multiloader por familias de Minecraft.

La regla principal es trabajar por **versiones ancla**, no por cada release menor. Cada familia debe tener su propia rama, toolchain y matriz de loaders.

## Matriz de soporte objetivo

| Familia | Rama | Fabric | Forge | NeoForge | Estado |
| --- | --- | --- | --- | --- | --- |
| 1.17.1 | `release/1.17.1` | Objetivo | Objetivo | No aplica | Pendiente |
| 1.18.2 | `release/1.18.2` | Objetivo | Objetivo | No aplica | Pendiente |
| 1.19.2 | `release/1.19.2` | Objetivo | Objetivo | No aplica | Pendiente |
| 1.20.1 | `release/1.20.1` | Objetivo | Objetivo | No aplica | Pendiente |
| 1.20.6 | `release/1.20.6` | Objetivo | Objetivo | Objetivo | Pendiente |
| 1.21.1 | `release/1.21.1` | Objetivo | Objetivo | Objetivo | En progreso |
| 26.1 | `release/26.1` | Objetivo | Objetivo | Experimental | Pendiente |

La matriz tecnica detallada vive en `MULTIVERSION_TECHNICAL_MATRIX.md`.
El procedimiento para abrir ramas vive en `MULTIVERSION_BRANCH_PLAYBOOK.md`.
El freeze documentado de la familia actual vive en `RELEASE_1.21.1_FREEZE.md`.
El bring-up de la familia `26.1` vive en `RELEASE_26.1_BRINGUP.md`.
La auditoria inicial de compilacion Fabric 26.1 vive en `FABRIC_26.1_COMPILE_AUDIT.md`.

## Politica de ramas

- `main` queda como linea moderna de desarrollo.
- Cada familia objetivo usa una rama permanente `release/<minecraft>`.
- Las features nuevas nacen primero en la linea moderna y solo se backportean si el costo es razonable.
- Los backports deben preservar el patron `common + loader adapters` dentro de cada rama.
- No se promete soporte para todas las subversiones intermedias.

## Regla de arquitectura

Cada rama puede tener su propio `common`. No se intenta construir un `common` universal para todas las eras de Minecraft.

Dentro de cada rama:

- `common` contiene dominio, gameplay, reglas puras, ids y catalogos estables.
- `fabric`, `forge` y `neoforge` contienen bootstrap, registro activo, eventos, networking, wiring cliente y adaptadores del loader.
- Si una API cambia entre versiones, el orden de resolucion es:
  1. adapter por loader-version
  2. bridge compartido ampliado
  3. duplicacion controlada por rama si la API cambio demasiado

## Orden de trabajo por oleadas

1. Oleada A: consolidar la base moderna.
   - Cerrar y documentar `1.21.1`.
   - Abrir `26.1` como carril moderno.
2. Oleada B: bajar a la transicion moderna.
   - `1.20.6`.
   - `1.20.1`.
3. Oleada C: cubrir legado reciente.
   - `1.19.2`.
   - `1.18.2`.
   - `1.17.1`.

En cada familia se sigue el mismo orden:

1. bootstrap del loader
2. registro simple
3. entidades base
4. menus y networking
5. worldgen y structures
6. cliente especial
7. validacion manual y automatizada

## Criterios de aceptacion por familia

Una rama de familia queda lista cuando:

- compila en todos sus loaders objetivo
- arranca cliente y servidor
- la pestana creativa carga
- las entidades clave funcionan
- `Bufflon`, `End Troll Box` y `End Prison` pasan validacion manual minima
- los spawns naturales del mod ocurren en biomas esperados
- los assets especiales no muestran texturas faltantes
- la rama declara sus loaders soportados y riesgos conocidos

## Artefactos

Los jars distribuibles deben usar:

- `pandoras_creatures-fabric-<mc>-<mod>.jar`
- `pandoras_creatures-forge-<mc>-<mod>.jar`
- `pandoras_creatures-neoforge-<mc>-<mod>.jar`

Ejemplo para la familia actual:

- `pandoras_creatures-fabric-1.21.1-3.1.0-beta.jar`
- `pandoras_creatures-forge-1.21.1-3.1.0-beta.jar`
- `pandoras_creatures-neoforge-1.21.1-3.1.0-beta.jar`
