# Contributing Guide

## Scope

This repository maintains an unofficial **multiloader** port of Pandoras Creatures (`common` + `forge` + `fabric` + `neoforge`), targeting Minecraft 1.21.1 with a documented path toward multiversion support.

The immediate priority is stability, maintainability, and traceable refactoring.

## Development Principles

- keep behavior stable unless a change is intentional and documented
- prefer small, reviewable refactors over large rewrites
- avoid mixing structural refactors with unrelated gameplay changes
- document technical decisions and migration work as they happen
- do not assume loader-specific code belongs in future shared code

## Local Validation

Before considering a change ready, run:

```powershell
.\gradlew.bat compileJava
.\gradlew.bat test
```

Changes should also pass the architecture check (`verifyArchitecture`). The exact integrity gate depends on the version family, because not every loader compiles on every line; see [docs/adr/STATUS.md](docs/adr/STATUS.md) for the current command (e.g. on the 1.20.1 family: `:common:test :fabric:compileJava :forge:compileJava verifyArchitecture`).

If the change affects gameplay or content loading, also run the appropriate local game validation for the affected area.

## Testing Expectations

- add or update unit tests when extracting pure logic
- prefer small tests around deterministic logic first
- use integration or GameTests for behavior that depends on the Minecraft runtime
- document manual smoke tests when automated coverage is not yet available

## Documentation Rule

All architectural decisions are recorded as ADRs (versioned, clonable source of truth):

- [docs/adr/](docs/adr/README.md) — the decisions themselves
- [docs/adr/STATUS.md](docs/adr/STATUS.md) — live board of resolved/pending items and context to resume cold

Detailed chronological progress is kept in `PLAN_REESTRUCTURACION_MULTILOADER.md` (local only, gitignored).

New technical reference documents should be placed under [docs/](docs/README.md).

## Repository Conventions

- Java version: `21`
- Loaders: multiloader `common` + `forge` + `fabric` + `neoforge` (target line `1.21.1`)
- Build tool: `Gradle`
- Test framework: `JUnit 5`

## Code Organization Direction

Until the multiloader split begins, new code should already move toward these conventions where practical:

- prefer domain-oriented package names over generic buckets
- keep loader bootstrap and event wiring separate from gameplay logic
- keep pure logic extractable and testable
- avoid adding new utility classes that mix unrelated concerns
- prefer shared constants over duplicated magic numbers when a concept has a stable meaning

## Git Safety

- do not rewrite history unless explicitly requested
- do not use destructive git commands on a dirty workspace
- do not push or create commits unless explicitly requested
- if local user changes are detected, preserve them unless they directly block the requested task
