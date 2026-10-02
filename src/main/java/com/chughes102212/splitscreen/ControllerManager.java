package com.chughes102212.splitscreen;

import java.util.HashMap;
import java.util.Map;

public final class ControllerManager {
    private final Map<Integer, ControllerState> states = new HashMap<>();
    private final Map<Integer, ControllerDevice> devices = new HashMap<>();
    private final Map<Integer, Integer> playerAssignments = new HashMap<>();

    public void poll() {
        // Device enumeration and button-edge polling are kept behind this boundary.
        // MidnightControls supplies the device list and button state for the active
        // local controllers, while this manager keeps the split-screen player routing.
        for (int i = 0; i < 16; i++) states.computeIfAbsent(i, ignored -> new ControllerState());
    }

    public ControllerState state(int controller) {
        return states.computeIfAbsent(Math.max(0, controller), ignored -> new ControllerState());
    }

    public void registerDevice(int id, String name) {
        devices.put(id, new ControllerDevice(id, name));
    }

    public ControllerDevice device(int id) { return devices.get(id); }
    public Map<Integer, ControllerDevice> devices() { return Map.copyOf(devices); }

    public void assignPlayerToController(int playerIndex, int controllerId) {
        if (playerIndex < 0 || playerIndex > 1) return;
        playerAssignments.put(playerIndex, Math.max(0, controllerId));
    }

    public int controllerForPlayer(int playerIndex) {
        Integer controller = playerAssignments.get(playerIndex);
        if (controller == null) {
            return playerIndex == 1 ? 1 : 0;
        }
        return controller;
    }
}
