# Matriz Tecnica Multiversion

Esta matriz registra las decisiones tecnicas por familia de Minecraft. Las filas pendientes no deben tratarse como soporte publicado hasta que exista una rama `release/<minecraft>` compilando.

| Familia | Java | Gradle | Fabric | Forge | NeoForge | Mappings | Estado |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1.17.1 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | No aplica | Por definir | Pendiente |
| 1.18.2 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | No aplica | Por definir | Pendiente |
| 1.19.2 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | No aplica | Por definir | Pendiente |
| 1.20.1 | Java 17 | Gradle 8.10 wrapper, baseline 8.1 | Loader 0.15.11, API 0.92.5+1.20.1, Loom 1.2.7 | Forge 47.4.18, ForgeGradle 6.x | Experimental sobre `net.neoforged:forge` 1.20.1-47.1.106 | Official 1.20.1 | Bootstrap en progreso |
| 1.20.2 | Java 17 | Gradle 8.10 (heredado de 1.20.1) | Loader 0.19.3, API 0.91.6+1.20.2, Loom 1.2.7 (sin cambio, no re-verificado) | Forge 48.1.0, ForgeGradle 6.x | Experimental, no actualizado (sigue apuntando a coordenadas de 1.20.1, sin verificar) | Official 1.20.2 | common+fabric+forge compilan y validan (59/60 gametests fabric; ver notas) |
| 1.20.6 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | Objetivo | Por definir | Pendiente |
| 1.21.1 | Java 21 | Gradle 8.10 | Loader 0.16.14, API 0.115.6+1.21.1, Loom 1.8.13 | Forge 52.1.14, ForgeGradle 6.x | NeoForge 21.1.77, ModDevGradle 2.0.42-beta | Official 1.21.1 | Lista para freeze |
| 26.1 | Por definir en rama | Por definir en rama | Objetivo | Objetivo | Experimental | Por definir | Pendiente |

## Reglas de actualizacion

- Una fila solo puede pasar a `Activa` cuando su rama compila en todos los loaders objetivo.
- No se deben copiar valores tecnicos de otra familia sin verificarlos en la rama correspondiente.
- Si `NeoForge` para `26.1` depende de builds alpha, snapshot o inestables, debe seguir marcado como `Experimental`.
- Cada rama release debe actualizar su propia fila con versiones concretas de Java, Gradle, plugins, mappings y loaders.
