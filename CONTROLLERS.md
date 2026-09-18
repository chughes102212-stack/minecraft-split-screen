# Controller support

The startup assignment flow is designed around console-style joining: press the confirm button on an unassigned controller to join Player 1 or Player 2. Controllers are exclusive to one player, and assignments can be saved or reassigned.

Recognized profiles and default labels:

- **Xbox / XInput:** A jump, B cancel, X attack, Y use, LB/RB shoulders, Menu pause, View inventory.
- **PlayStation / DualShock / DualSense:** Cross jump, Circle cancel, Square attack, Triangle use, L1/R1 shoulders, Options pause, Create inventory.
- **Nintendo Switch / Pro Controller / Joy-Con:** B jump, A cancel, Y attack, X use, L/R shoulders, + pause, - inventory.
- **Generic HID:** Uses the standard south/east/west/north face-button order.

The profile layer uses controller names and GLFW's standard gamepad mapping, so it does not require Xbox, PlayStation, or Nintendo SDKs. Exact button numbering is handled by the eventual input screen/backend, while Minecraft keybinds remain configurable.
