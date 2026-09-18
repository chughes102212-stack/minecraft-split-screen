package com.chughes102212.splitscreen;

import net.minecraft.client.MinecraftClient;

public final class LocalPlayerSlot {
    private final int index;
    private int controller;
    private final ControllerState input = new ControllerState();

    public LocalPlayerSlot(int index, int controller) {
        this.index = index;
        this.controller = controller;
    }

    public void tick(MinecraftClient client, ControllerState state) {
        input.copyFrom(state);
    }

    public int index() { return index; }
    public int controller() { return controller; }
    public void controller(int value) { controller = Math.max(-1, value); }
    public ControllerState input() { return input; }
}
