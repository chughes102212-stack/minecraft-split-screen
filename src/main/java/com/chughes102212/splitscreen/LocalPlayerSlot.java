package com.chughes102212.splitscreen;

import net.minecraft.client.MinecraftClient;

public final class LocalPlayerSlot {
    private final int index;
    private int controller;
    private final ControllerState input = new ControllerState();
    private boolean active = (index == 0);
    private SplitScreenConfig.JoinTrigger joinTrigger = SplitScreenConfig.JoinTrigger.JUMP;

    public LocalPlayerSlot(int index, int controller) {
        this.index = index;
        this.controller = controller;
    }

    public void tick(MinecraftClient client, ControllerState state) {
        input.copyFrom(state);
        if (!active && shouldJoin(state)) {
            activate();
        }
    }

    public boolean shouldJoin(ControllerState state) {
        if (state == null) return false;
        return switch (joinTrigger) {
            case JUMP -> state.jumpPressed();
            case ATTACK -> state.attackPressed();
            case USE -> state.usePressed();
            case PAUSE -> state.pausePressed();
        };
    }

    public void joinTrigger(SplitScreenConfig.JoinTrigger value) {
        joinTrigger = value == null ? SplitScreenConfig.JoinTrigger.JUMP : value;
    }

    public void activate() { active = true; }
    public void deactivate() { active = false; }
    public boolean isActive() { return active; }

    public int index() { return index; }
    public int controller() { return controller; }
    public void controller(int value) { controller = Math.max(-1, value); }
    public ControllerState input() { return input; }
}
