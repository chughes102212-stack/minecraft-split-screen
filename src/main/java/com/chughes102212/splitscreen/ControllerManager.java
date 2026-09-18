package com.chughes102212.splitscreen;

import java.util.HashMap;
import java.util.Map;

public final class ControllerManager {
    private final Map<Integer, ControllerState> states = new HashMap<>();
    private final Map<Integer, ControllerDevice> devices = new HashMap<>();

    public void poll() {
        // Device enumeration and button-edge polling are kept behind this boundary.
        // GLFW mappings supplied by Minecraft identify Xbox, PlayStation, Nintendo,
        // and generic HID controllers without a hard dependency on a controller mod.
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
}
