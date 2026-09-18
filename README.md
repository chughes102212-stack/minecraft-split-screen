# Minecraft Split Screen

Fabric client project targeting Minecraft Java 1.21.11 for two-player local split-screen.

## Pause behavior

Each player has an independent pause request. The shared local world pauses only when **both players request pause**. Pausing one controller does not stop the other player's movement. The implementation detects the optional Essential mod (`essential`) without depending on its private APIs, avoiding a hard compatibility requirement. Essential remains responsible for its own menus and network features; this mod keeps the local pause decision independent of those screens.

## Build

Use Java 21 and run `./gradlew build`.

## Compatibility

The project is client-only and does not require Essential, Sodium, or other optional client mods. Renderer and menu integrations must be tested against the exact installed versions.
