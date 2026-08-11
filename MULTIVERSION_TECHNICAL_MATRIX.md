# Matriz Tecnica Multiversion

Esta matriz registra las decisiones tecnicas por familia de Minecraft. Las filas pendientes no deben tratarse como soporte publicado hasta que exista una rama `release/<minecraft>` compilando.

| Familia | Java | Gradle | Fabric | Forge | NeoForge | Mappings | Estado |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1.17.1 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | No aplica | Por definir | Pendiente |
| 1.18.2 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | No aplica | Por definir | Pendiente |
| 1.19.2 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | No aplica | Por definir | Pendiente |
| 1.20.1 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | No aplica | Por definir | Pendiente |
| 1.20.6 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | Objetivo | Por definir | Pendiente |
| 1.21.1 | Java 21 | Gradle 8.10 | Loader 0.16.14, API 0.115.6+1.21.1, Loom 1.8.13 | Forge 52.1.14, ForgeGradle 6.x | NeoForge 21.1.77, ModDevGradle 2.0.42-beta | Official 1.21.1 | Lista para freeze |
| 1.21.3 | Java 21 | Gradle 8.10 | Loader 0.19.3, API 0.106.1+1.21.3, Loom 1.8.13 | Forge 53.1.0 (Recommended), ForgeGradle 6.x | NeoForge 21.3.97, neo_form_version 1.21.3-20241023.131943 | Official 1.21.3 | Lista para freeze (58/58 GameTests OK en fabric/forge/neoforge) |
| 1.21.4 | Java 21 | Gradle 8.10 | Loader 0.19.3, API 0.110.5+1.21.4, Loom 1.8.13 | Forge 54.1.14, ForgeGradle 6.x | NeoForge 21.4.157, neo_form_version 1.21.4-20241203.161809, ModDevGradle 2.0.141 | Official 1.21.4 | Lista para freeze (58/58 GameTests OK en fabric/forge/neoforge) |
| 1.21.5 | Java 21 | Gradle 8.14 | Loader 0.19.3, API 0.119.5+1.21.5, Loom 1.10.5, fabric-gametest-api-v1 (resuelto via fabricApi.configureTests) | Forge 55.1.0 (Recommended), ForgeGradle 6.x | NeoForge 21.5.98, neo_form_version 1.21.5-20250325.162830, ModDevGradle 2.0.141 | Official 1.21.5 | Lista para freeze (59/59 GameTests OK en fabric/neoforge; Forge compila y buildea pero el runGameTestServer esta roto upstream -- ver nota) |
| 26.1 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | Experimental | Por definir | Pendiente |

## Nota: bug upstream de Forge 55.1.0 en GameTest

Forge 55.1.0 (Minecraft 1.21.5) rompio la ejecucion de `runGameTestServer`: su propio parche a
`net/minecraft/server/Main.java` (dentro del jar de fuentes de Forge) tiene el bloque que crea el
`GameTestServer` comentado, con el literal `// TODO: [Forge][GameTest] URGENT !!! FIX GAME TESTS !!!`.
El run cae a un `DedicatedServer` normal en vez de ejecutar los tests -- no hay forma de arreglar
esto desde el mod. `:forge:compileJava`, `:forge:build` y el jar final funcionan sin problemas;
solo la validacion de GameTests en Forge queda bloqueada hasta que Forge lo arregle upstream.

## Reglas de actualizacion

- Una fila solo puede pasar a `Activa` cuando su rama compila en todos los loaders objetivo.
- No se deben copiar valores tecnicos de otra familia sin verificarlos en la rama correspondiente.
- Si `NeoForge` para `26.1` depende de builds alpha, snapshot o inestables, debe seguir marcado como `Experimental`.
- Cada rama release debe actualizar su propia fila con versiones concretas de Java, Gradle, plugins, mappings y loaders.
