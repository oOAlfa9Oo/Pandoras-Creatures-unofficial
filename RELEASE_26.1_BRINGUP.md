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

## Cambios iniciales aplicados al spike Fabric

- `minecraft_version=26.1`
- `fabric_loader_version=0.19.1`
- `fabric_api_version=0.145.1+26.1`
- `fabric_loom_version=1.16.1`
- `fabric/build.gradle` usa el plugin `net.fabricmc.fabric-loom`
- `fabric/build.gradle` elimina la dependency `mappings`
- `fabric/build.gradle` reemplaza `modImplementation` por `implementation`
- `fabric.mod.json` exige Java `>=25`

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
