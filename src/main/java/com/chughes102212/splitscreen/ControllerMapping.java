package com.chughes102212.splitscreen;

import java.util.EnumMap;
import java.util.Map;

/** Persistent action-to-button mapping for one local controller. */
public final class ControllerMapping {
    public enum Action { MOVE, LOOK, JUMP, ATTACK, USE, INVENTORY, PAUSE, SPRINT, CROUCH }

    private final Map<Action, String> buttons = new EnumMap<>(Action.class);

    public ControllerMapping(ControllerProfile profile) {
        buttons.put(Action.MOVE, "Left Stick");
        buttons.put(Action.LOOK, "Right Stick");
        buttons.put(Action.JUMP, profile.jump());
        buttons.put(Action.ATTACK, profile.attack());
        buttons.put(Action.USE, profile.use());
        buttons.put(Action.INVENTORY, profile.inventory());
        buttons.put(Action.PAUSE, profile.pause());
        buttons.put(Action.SPRINT, "Left Stick Click");
        buttons.put(Action.CROUCH, profile.leftBumper());
    }

    public String button(Action action) { return buttons.getOrDefault(action, "Unassigned"); }
    public void set(Action action, String button) { buttons.put(action, button); }
    public Map<Action, String> values() { return Map.copyOf(buttons); }
}
