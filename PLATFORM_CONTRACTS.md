# Contratos de Plataforma

## Objetivo

Este documento fija la frontera entre el nucleo compartido en `common/` y los adaptadores por loader como `neoforge/`.

La regla general es:

- `common/` contiene logica, contenido y contratos reutilizables
- `neoforge/` contiene bootstrap, registro activo, eventos, payloads y adaptadores del loader

## Reglas base

- Ninguna clase en `common/src/main/java` o `common/src/test/java` debe importar `net.neoforged`, `net.minecraftforge`, `net.fabricmc` o `cpw.mods`
- Los recursos especificos de loader deben quedarse fuera de `common`
- Todo recurso `data/.../neoforge` debe vivir en `neoforge/src/main/resources`
- Los descriptores del loader como `META-INF/neoforge.mods.toml` deben vivir solo en el host correspondiente
- El wiring activo del host debe quedar agrupado en paquetes de bootstrap del loader, no disperso por clases de dominio o entrypoints

## Contratos actuales

### `PlatformServices`

Punto de entrada del modulo comun para acceder a servicios del host.

Expone:

- `registry()`
- `network()`
- `menus()`
- `entities()`
- `sidedHooks()`

### `RegistryBridge`

Responsable de resolver ids y entradas de registro sin acoplar `common` a `DeferredRegister` ni a tipos del loader.

Uso esperado:

- obtener `Item` por path
- obtener `EntityType` por path
- obtener `SoundEvent` por path
- construir `ResourceLocation` del mod

### `NetworkBridge`

Encapsula requests cliente -> servidor que nacen desde codigo compartido o GUI compartida.

Uso esperado:

- acciones del Bufflon
- futuros requests equivalentes que necesiten mantenerse neutrales al loader

Nota:

- el serializer y el registro de payloads siguen siendo responsabilidad del host
- los ids estables de payload pueden vivir en `common` si no dependen del loader

### `MenuBridge`

Encapsula apertura de menus desde codigo compartido.

Uso esperado:

- apertura del menu del Bufflon
- futuras aperturas equivalentes si pasan a `common`

### `PCMenuIds` y `PCPayloadIds`

Catalogos pequenos de ids compartidos que fijan nombres estables entre `common` y los hosts.

Uso esperado:

- evitar strings duplicados en registro de menus y payloads
- sostener nombres estables al sumar `fabric/` y `forge/`
- dejar al host como adaptador y registrador, no como duenio del contrato semantico

### `Catalogos de ids compartidos`

Catalogos como `PCEntityIds`, `PCItemIds`, `PCBlockIds`, `PCBlockEntityIds`, `PCCreativeTabIds`, `PCStructureIds`, `PCRecipeIds` y `PCLanguageKeys` deben vivir en `common` cuando expresan nombres estables del contenido del mod.

Uso esperado:

- evitar ids hardcodeados en entidades, items y registries del host
- fijar los nombres canonicos antes de sumar otros loaders
- permitir que `common` y `neoforge` hablen el mismo contrato de contenido sin depender de strings duplicados
- sostener tambien ids estables de pestanas creativas, estructuras, pools y recipe types cuando esos nombres ya forman parte del contrato del contenido
- sostener tambien translation keys compartidas entre runtime y datagen para que textos de UI y tooltips no queden reescritos solo en el host
- incluir tambien keys pequenas de `gui.button` y `chat` cuando ya formen parte del contrato reutilizado por GUI compartida, variantes o mensajes del mod

### `EntityBridge`

Encapsula hooks de runtime de entidades que siguen siendo responsabilidad del host.

Uso esperado:

- sincronizacion de animaciones
- calculo de experiencia via eventos del loader

Nota:

- el transporte real de sincronizacion debe vivir en el adapter del host, no en utilitarios generales del modulo `neoforge`
- el codigo compartido y el host deben preferir `PandorasCreaturesCommon.platform().entities().syncAnimation(...)` como punto unico de entrada

### `SidedHooks`

Expone consultas minimas sobre el entorno actual.

Uso esperado:

- validaciones de lado
- decisiones ligeras de cliente/servidor cuando no convenga cablear el loader en `common`

## Que no debe volver a pasar a `common`

- `DeferredRegister`, `DeferredHolder`, `PacketDistributor`, `EventHooks`
- entrypoints del mod
- eventos de NeoForge
- payload registration
- serializers o handlers de payload atados al loader
- renderers y client extensions que dependan del host
- recursos `data/.../neoforge`

## Ownership esperado de registros del host

Dentro del host, el registro declarativo debe quedar separado por tipo de contenido.

Uso esperado:

- `PCBlocks` registra bloques y mantiene catalogos derivados de bloques
- `PCItems` registra items, incluyendo `BlockItem` y wrappers especificos del host para esos bloques
- el wiring que une ambos lados debe vivir del lado del registro de items, no duplicado dentro del registro de bloques

## Criterio de evolucion

Si una clase candidata a `common` necesita algo del host, primero debe preguntarse:

1. Se puede resolver con un contrato ya existente
2. Si no, conviene ampliar un bridge actual
3. Solo si eso no alcanza, se crea un contrato nuevo

La prioridad es mantener `common` limpio y pequeno en dependencias, aunque eso implique dejar adapters delgados en el host.

En paralelo, dentro del host, la prioridad es que el wiring vivo quede concentrado en:

- `bootstrap/` para entrypoints y listeners
- `registry/bootstrap/` para agrupacion de `DeferredRegister`
- adapters finos bajo `platform/neoforge/`
