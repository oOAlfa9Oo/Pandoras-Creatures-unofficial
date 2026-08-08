# Contributing Guide

## Scope

This repository currently maintains an unofficial NeoForge 1.21.1 port of Pandoras Creatures and is being prepared for a future multiloader architecture.

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

If the change affects gameplay or content loading, also run the appropriate local game validation for the affected area.

## Testing Expectations

- add or update unit tests when extracting pure logic
- prefer small tests around deterministic logic first
- use integration or GameTests for behavior that depends on the Minecraft runtime
- document manual smoke tests when automated coverage is not yet available

## Documentation Rule

All architectural work, migration decisions, and restructuring progress should be recorded in:

- `PLAN_REESTRUCTURACION_MULTILOADER.md` (local only, gitignored)

New technical reference documents should be placed under:

- [docs/](docs/README.md)

## Repository Conventions

- Java version: `21`
- Loader line today: `NeoForge 1.21.1`
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
