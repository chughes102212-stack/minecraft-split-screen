package com.chughes102212.splitscreen;

import java.util.HashMap;
import java.util.Map;

public final class ControllerManager {
    private final Map<Integer, ControllerState> states = new HashMap<>();

    public void poll() {
        for (int i = 0; i < 16; i++) states.computeIfAbsent(i, ignored -> new ControllerState());
    }

    public ControllerState state(int controller) {
        return states.computeIfAbsent(Math.max(0, controller), ignored -> new ControllerState());
    }
}
