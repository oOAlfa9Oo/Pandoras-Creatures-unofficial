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
| 26.1 | Java 25 para toolchains y ejecucion de loaders | Gradle 9.4.0 | Loader 0.19.1, API 0.145.1+26.1, Loom 1.16.1, build OK; server preflight carga el mod y queda detenido por EULA local | Forge 26.1-62.0.9, ForgeGradle `[7.0.17,8)`, Java 25, MDK oficial; build OK; datagen runtime OK; GameTest server smoke OK | NeoForge 26.1.0.19-beta experimental, ModDevGradle 2.0.141, NeoForm 26.1-1, build OK; GameTests `28/28`; datagen runtime OK; sin warning `@OnlyIn` | Official/unobfuscated 26.1 | Bring-up automatizado validado para Fabric + Forge + NeoForge; pendiente validacion visual/manual de cliente |

## Reglas de actualizacion

- Una fila solo puede pasar a `Activa` cuando su rama compila en todos los loaders objetivo.
- No se deben copiar valores tecnicos de otra familia sin verificarlos en la rama correspondiente.
- Si `NeoForge` para `26.1` depende de builds alpha, snapshot o inestables, debe seguir marcado como `Experimental`.
- Cada rama release debe actualizar su propia fila con versiones concretas de Java, Gradle, plugins, mappings y loaders.
