# Minecraft Split Screen

Fabric client project targeting Minecraft Java 1.21.11 for two-player local split-screen.

This initial foundation includes the client-only Gradle project, two local player slots, controller-state boundaries, configurable horizontal/vertical layout, controller assignment defaults, and low-end render-distance defaults.

The full playable feature set still requires client mixins for isolated cameras, viewports, HUDs, input routing, player state, menus, death, and respawn. Minecraft's vanilla client is built around one client player, so these systems must be integrated carefully rather than duplicating the player object.

## Build

Use Java 21 and run `./gradlew build`.
