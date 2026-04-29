# Bootstrap `release/1.20.1`

## Estado actual

La rama `release/1.20.1` fue abierta desde el ultimo estado validado de `release/1.21.1` y ya tiene:

- `gradle.properties` apuntando a `Minecraft 1.20.1`
- `Java 17`
- `Fabric` como carril principal con `Loom 1.2.7`, `Loader 0.15.11` y `Fabric API 0.92.5+1.20.1`
- `Forge 47.4.18` como carril oficial
- `NeoForge 1.20.1` como carril experimental sobre `net.neoforged:forge:1.20.1-47.1.106`
- tareas raiz separadas en:
  - `validateOfficialLoaders`
  - `validateExperimentalLoaders`
  - `validateCurrentFamily`

## Industrializacion aplicada

- `common` dejo de depender de NeoForm moderno y ahora compila sobre un baseline neutro para `1.20.1`
- `Forge` dejo de absorber `common/src/main/java` y `common/resources` por mezcla desigual
- `Fabric`, `Forge` y `NeoForge` quedaron orientados a consumir `:common` como salida compilada
- la politica de familia `1.20.1` ya esta documentada como:
  - oficiales: `Fabric + Forge`
  - experimental: `NeoForge`

## Compatibilidad ya bajada a `1.20.1`

- `CustomPacketPayload` y `StreamCodec` salieron del dominio compartido; los payloads comunes volvieron a ser DTOs simples
- `ResourceLocation.fromNamespaceAndPath(...)` fue bajado al constructor clasico
- los buckets y spawn eggs dejaron de usar `DataComponents` del branch `1.21.1`
- `SynchedEntityData.Builder` fue reemplazado por la firma previa de `defineSynchedData()`
- se inicio el retroceso de recetas custom hacia serializacion `FriendlyByteBuf`

## Bloqueadores tecnicos abiertos

La rama **todavia no compila**. Los bloques de trabajo ya detectados son:

1. `client/`
   - modelos usan firmas de render de `1.21.1`
   - render layers y screens usan helpers nuevos que no existen igual en `1.20.1`

2. `entities/`
   - varios mobs usan firmas modernas de `finalizeSpawn`
   - hay comportamiento que depende de APIs nuevas de experiencia, teams y registry access
   - buckets aun tienen restos de `DataComponents` en entidades acuaticas

3. `block_entities/`
   - `EndTrollBoxBlockEntity` sigue usando firma moderna de guardado/carga con registries

4. `world/structures`
   - `EndPrison` ya bajo parte de su API, pero aun necesita cierre fino contra `1.20.1`

## Siguiente orden recomendado

1. cerrar `block_entities` y serializacion NBT clasica
2. cerrar `entities` y buckets
3. cerrar `client/models`
4. cerrar `client/screens`
5. compilar `common`
6. compilar `fabric`
7. compilar `forge`
8. reactivar `neoforge` experimental
