# Pandoras Creatures Unofficial (NeoForge Port)

This repository contains an **unofficial multiloader port** of *Pandoras Creatures* for Minecraft 1.21.1, with a documented path toward multiversion support.

---

## Disclaimer

This project is not affiliated with, endorsed by, or maintained by the original author.

The original mod was created by **andrew0030**.

---

## Original Project

* Repository: https://github.com/andrew0030/Pandoras-Creatures
* Issue Tracker: Refer to the original repository

If you encounter issues related to original functionality, please verify them against the upstream project before reporting them here.

---

## Status

* Type: Unofficial port
* Target Minecraft version: 1.21.1
* Target loaders: NeoForge, Fabric and Forge
* Port line: 3.1 beta
* Goal: Preserve original mod behavior while moving the project toward maintainable multiloader and multiversion support

### Multiversion Roadmap

The project uses version anchors instead of promising every minor release. The current roadmap is tracked in:

* [MULTIVERSION_SUPPORT.md](./MULTIVERSION_SUPPORT.md)
* [MULTIVERSION_PORTING_CHECKLIST.md](./MULTIVERSION_PORTING_CHECKLIST.md)
* [MULTIVERSION_TECHNICAL_MATRIX.md](./MULTIVERSION_TECHNICAL_MATRIX.md)
* [MULTIVERSION_BRANCH_PLAYBOOK.md](./MULTIVERSION_BRANCH_PLAYBOOK.md)
* [RELEASE_1.21.1_FREEZE.md](./RELEASE_1.21.1_FREEZE.md)

---

## About The Mod

*Pandoras Creatures* is a Minecraft mod that introduces new hostile and neutral creatures designed to increase gameplay challenge.

### Main features included in this port:

* Seven custom creatures with unique behaviors
* Custom items and blocks
* Tameable Bufflon mount
* End-dimension-related content

---

## Modifications

This repository represents a port of the original mod to newer Minecraft versions.

Changes include:

* Migration to Minecraft 1.21.1
* Multiloader split into `common`, `neoforge`, `fabric` and `forge`
* Adaptation to updated modding APIs
* Compatibility fixes for modern Minecraft versions
* Internal refactoring where required

---

## Technical Notes

* This port retains the original mod ID for compatibility purposes
* Distribution and branding are clearly marked as unofficial

---

## Development

Project restructuring and migration planning are being tracked in:

* [PLAN_REESTRUCTURACION_MULTILOADER.md](./PLAN_REESTRUCTURACION_MULTILOADER.md)
* [CONTRIBUTING.md](./CONTRIBUTING.md)
* [docs/README.md](./docs/README.md)

---

## License

This project is licensed under the GNU Lesser General Public License v3.0 (LGPL-3.0), in accordance with the original work by **andrew0030**.

You may use, modify, and redistribute this project under the terms of this license.

Requirements include:

* Preservation of original copyright notices
* Distribution of source code when providing binaries
* Licensing of modifications under LGPL-3.0

A copy of the license is included in the `LICENSE` file.

---

## Credits

* Original author: **andrew0030**
* Port and updates: *oOAlfa9Oo*

---

## Final Notes

This repository exists to maintain compatibility of the mod with modern Minecraft versions.

If the original author resumes development, the official version should take precedence over this port.
