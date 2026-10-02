# Minecraft Split Screen

Two-player local split-screen for Minecraft Java Edition running on a single account with controller support.

## Features

- **Shared Account Split-Screen:** Both players use the same Microsoft/Mojang account.
- **Independent Controllers:** Assign separate controllers to Player 1 and Player 2.
- **Bedrock-style Join:** Player 2 presses the A button (jump) to join the game, just like in Bedrock Edition.
- **Pause Independence:** Each player can pause independently; the world pauses only when both request it.
- **MidnightControls Integration:** Full support for Xbox, PlayStation, Nintendo, and generic HID controllers via MidnightControls.
- **Configurable Layout:** Switch between horizontal and vertical split-screen.

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/installer/) for Minecraft 1.21.11.
2. Install [Fabric API](https://modrinth.com/mod/fabric-api) mod.
3. Install [MidnightControls](https://modrinth.com/mod/midnightcontrols) mod.
4. Download the latest `split-screen-1.0.0.jar` from [Releases](../../releases) or CurseForge.
5. Place the mod JAR in your `mods` folder.
6. Launch Minecraft with Fabric.

## Usage

### Joining the Game

1. **Player 1** loads into a world normally.
2. **Player 2** connects their controller and presses the **A button** (or equivalent on their controller type) to join.
3. The screen splits and both players are in the world.

### Controller Configuration

- Press **F8** in-game to open the controller mapping and controller assignment screen.
- **Player 1 / Player 2:** Click to cycle which controller is assigned to each player.
- Button mappings are stored per-player and persist across sessions.

### Pause Behavior

- Each player can pause independently (start/menu button).
- The world only pauses when **both players** request pause.
- Resume by having both players un-pause.

## Requirements

- Minecraft Java 1.21.11
- Fabric Loader 0.18.1+
- Fabric API 0.141.0+
- MidnightControls (any compatible version)
- Java 21 or later

## Building from Source

```bash
git clone https://github.com/chughes102212-stack/minecraft-split-screen.git
cd minecraft-split-screen
./gradlew build
```

The built mod JAR will be in `build/libs/split-screen-1.0.0.jar`.

## Configuration

Settings are stored in the Java Preferences store at:
- `Preferences.userRoot().node("minecraft-split-screen")`

Key settings:
- `layout`: HORIZONTAL or VERTICAL
- `player1.controller`: Controller ID (0-15)
- `player2.controller`: Controller ID (0-15)
- `render_distance`: Viewport render distance (2-17)

## Contributing

Issues and pull requests are welcome. Please ensure:

1. Code follows the existing style.
2. Changes are tested with Minecraft 1.21.11.
3. The MidnightControls API is used correctly for controller input.

## License

MIT License. See [LICENSE](LICENSE) for details.

## Changelog

### 1.0.0 (Initial Release)

- Split-screen support for two players on one account.
- Per-player controller assignment.
- Bedrock-style join behavior (Player 2 presses A to join).
- Configurable split-screen layout (horizontal/vertical).
- MidnightControls integration for multi-platform controller support.
- Independent pause per player.

## Support

For issues, feature requests, or questions, please open an issue on [GitHub](../../issues).
