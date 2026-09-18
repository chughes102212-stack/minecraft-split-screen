# Controller mapping menu

Press **F8** to open the vanilla-styled controller mapping menu. Each row shows a Minecraft action and its current controller binding. Select a row to put it into **Press a button** mode; the controller backend can call `ControllerMappingScreen.acceptButton(...)` when it receives the next button edge.

The menu is intentionally separate from Minecraft's keyboard bindings and supports the Xbox, PlayStation, Nintendo, and generic profiles. It uses normal Minecraft `Screen`, `ButtonWidget`, text, and done-button conventions so it can coexist with standard Controls and optional client UI mods.
