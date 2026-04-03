# Contratos de Plataforma

## Objetivo

Este documento fija la frontera entre el nucleo compartido en `common/` y los adaptadores por loader como `neoforge/` y `fabric/`.

La regla general es:

- `common/` contiene logica, contenido y contratos reutilizables
- `neoforge/` contiene bootstrap, registro activo, eventos, payloads y adaptadores del loader
- `fabric/` contiene bootstrap, descriptor del mod, runs de Loom y adaptadores del loader

## Reglas base

- Ninguna clase en `common/src/main/java` o `common/src/test/java` debe importar `net.neoforged`, `net.minecraftforge`, `net.fabricmc` o `cpw.mods`
- Los recursos especificos de loader deben quedarse fuera de `common`
- Los recursos compartidos del mod como `assets/pandoras_creatures/**` deben vivir en `common` para que `neoforge/` y `fabric/` consuman la misma fuente
- Los recursos generados compartidos de cliente como `blockstates`, `models` y `lang` deben vivir en `common/src/generated/resources` para que ambos loaders consuman la misma salida de datagen
- Todo recurso `data/.../neoforge` debe vivir en `neoforge/src/main/resources`
- Los descriptores del loader como `META-INF/neoforge.mods.toml` deben vivir solo en el host correspondiente
- El wiring activo del host debe quedar agrupado en paquetes de bootstrap del loader, no disperso por clases de dominio o entrypoints

## Estado actual por host

- `neoforge/` es el host de referencia y hoy conserva el wiring funcional completo del mod
- `fabric/` esta abierto como segundo loader y ya compila/arranca con bootstrap minimo, pero todavia no registra contenido ni networking del mod

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

### `Bootstraps compartidos de registro`

Cuando un tipo de contenido tenga suficiente forma estable, el catalogo y su bootstrap declarativo deben vivir en `common`, y los loaders solo deben aportar el adaptador concreto de registro.

Uso esperado:

- sonidos, ids y futuros registros simples
- reutilizar el mismo orden y los mismos nombres en `neoforge` y `fabric`
- convertir al host en consumidor del bootstrap compartido, no en duenio del listado de contenido
- aplicar el mismo patron tambien a `BlockItem` simples cuando sus bloques ya esten compartidos entre loaders
- aplicar el mismo patron tambien a primeros batches de entidades cuando su logica ya viva en `common`
- extender ese mismo patron desde entidades pasivas o ambient hasta mobs hostiles cuando sus atributos, spawn egg y visuales reutilizables ya tengan frontera estable
- cuando un `spawn egg` deje de ser especifico de un host, debe entrar al bootstrap portable compartido aunque la entidad no sea pasiva ni acuatica
- cuando un renderer reutilizable requiera un helper cliente pequeno, ese helper debe migrar tambien a `common` o simplificarse a una ruta vanilla-compatible antes de abrir el siguiente loader
- cuando un batch de entidades pase a `common`, sus `model layers`, modelos y renderers reutilizables deben migrar tambien fuera del host, dejando en cada loader solo el registro cliente
- los predicates y helpers neutrales al loader de `spawn placement` tambien deben migrar a `common` cuando un batch nuevo necesite equivalencia real entre `neoforge` y `fabric`

### `Salida compartida de datagen cliente`

Los providers cliente reutilizables entre loaders deben generar en `common/src/generated/resources`.

Uso esperado:

- `lang`
- `blockstates`
- `models`
- cualquier asset generado que deba entrar igual en `neoforge` y `fabric`

Nota:

- la data loader-specific como `data/.../neoforge` sigue saliendo del lado del host
- `runData` del host debe exponer `common/src/main/resources` y `common/src/generated/resources` como rutas `existing` cuando un provider cliente dependa de modelos o assets ya compartidos

### `EntityBridge`

Encapsula hooks de runtime de entidades que siguen siendo responsabilidad del host.

Uso esperado:

- sincronizacion de animaciones
- calculo de experiencia via eventos del loader
- permisos de `mob griefing` cuando una entidad compartida necesite romper bloques o modificar el mundo

Nota:

- el transporte real de sincronizacion debe vivir en el adapter del host, no en utilitarios generales del modulo `neoforge`
- el codigo compartido y el host deben preferir `PandorasCreaturesCommon.platform().entities().syncAnimation(...)` como punto unico de entrada
- cuando una entidad compartida necesite consultar `mob griefing`, debe hacerlo por `PandorasCreaturesCommon.platform().entities().canEntityGrief(...)`

### `AnimationSync` y `AnimationPayload`

La semantica de animaciones compartidas ya vive en `common`.

Uso esperado:

- resolver el indice de una `Animation` dentro de un `IAnimatedEntity`
- aplicar una animacion por `entityId` en cliente o directamente sobre una entidad animada
- mantener un payload estable reutilizable por loader para sincronizacion `server -> client`

Distribucion de responsabilidades:

- `common` define `AnimationSync` y `AnimationPayload`
- cada loader solo registra el payload y decide como enviarlo a los jugadores que trackean la entidad
- `EntityBridge.syncAnimation(...)` sigue siendo el punto unico de entrada desde gameplay compartido

Resultado esperado:

- `neoforge`, `fabric` y un futuro `forge` pueden compartir exactamente la misma semantica de animacion
- los bugs de animacion no vuelven a depender de una implementacion escondida en un loader concreto

### `SidedHooks`

Expone consultas minimas sobre el entorno actual.

Uso esperado:

- validaciones de lado
- decisiones ligeras de cliente/servidor cuando no convenga cablear el loader en `common`

### `RegistryBridge` ampliado para `End Troll Box`

El bridge de registro compartido ya no cubre solo `Item`, `EntityType` y `SoundEvent`.

Uso esperado:

- resolver `Block` por id desde `common`
- resolver `BlockEntityType` por id cuando un bloque compartido cree su propia entidad
- resolver `MenuType` por id cuando un menu compartido deba vivir fuera del host

Resultado esperado:

- bloques compartidos como `End Troll Box` pueden vivir en `common`
- su `BlockEntity`, `Menu`, `Screen` y `BuiltinItemRenderer` reutilizable pueden compilarse una sola vez
- cada loader mantiene solo el registro/adaptacion concreta del runtime

Nota:

- el primer slice completo que usa esta ampliacion es `End Troll Box`
- `neoforge` y `fabric` deben registrar la misma familia de ids y dejar que `common` consulte siempre por `PandorasCreaturesCommon.platform().registry()`

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
