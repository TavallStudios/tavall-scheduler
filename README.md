# tavall-scheduler

A Java scheduling library with delayed, repeating, cancellation, and shutdown APIs.

The repository contains one Java library module. Its current CustomScheduler source still labels the implementation as unfinished, so the API should be treated as experimental.

## Why tavall-scheduler

- Defines async and sync-like delayed/repeating task methods.
- Includes task cancellation and scheduler shutdown methods.
- The implementation is not documented as production-ready; verify current source behavior before adopting it.

## Features

- Delayed Runnable and Callable methods
- Repeating task methods
- Cancel one task or all tasks
- Shutdown and graceful-shutdown APIs

## Quick Start

Add the published artifact to a Gradle project:

```kotlin
dependencies {
    implementation("org.tavall:tavall-scheduler:<version>")
}
```

Use the exact published version and repository access configured for your project. See the links below for API and contribution details.

## Project Structure

This repository is a single Java library module (Module Type: LIBRARY; Runtime: None).

## Documentation

| Document | Purpose |
| --- | --- |
| [Contributing](CONTRIBUTING.md) | Contribution and development notes. |
| [Repository Git Workflow](docs/quality/GIT_WORKFLOW.md) | Applicable repository guidance. |

## Requirements / Compatibility

Java 25. Current source comments mark CustomScheduler functionality TODO.

## Building From Source

```bash
./gradlew check
```

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

## License

Source headers refer to the TJVD License and LICENSE.TXT, but no tracked LICENSE.TXT appears in the current repository tree. Confirm the applicable license with the repository owner before reuse.

## Documentation Update State

<details>
<summary>Documentation Update State</summary>

### Current Locations

| Surface | Sync State | Location | Last Updated | Evidence |
| --- | --- | --- | --- | --- |
| GitHub | PRIMARY | TavallStudios/tavall-scheduler/README.md | 2026-09-27 12:29 PM PDT | https://github.com/TavallStudios/tavall-scheduler/pull/10. |
| Notion | NOT_APPLICABLE | — | 2026-09-27 12:29 PM PDT | README files are not synchronized as Notion twins. |

### Update History

| Timestamp | Surface | Event | Location | Previous Location | Evidence | Notes |
| --- | --- | --- | --- | --- | --- | --- |
| 2026-09-27 12:29 PM PDT | GitHub | UPDATED | TavallStudios/tavall-scheduler/README.md | Same path | https://github.com/TavallStudios/tavall-scheduler/pull/10. | Reworked the public README to describe the current project, module boundary, usage, and documentation. |

</details>
