package com.chughes102212.splitscreen;

public final class ControllerState {
    public float leftX, leftY, rightX, rightY, triggerLeft, triggerRight;
    public boolean jump, attack, use, inventory;

    void copyFrom(ControllerState other) {
        leftX = other.leftX; leftY = other.leftY;
        rightX = other.rightX; rightY = other.rightY;
        triggerLeft = other.triggerLeft; triggerRight = other.triggerRight;
        jump = other.jump; attack = other.attack; use = other.use; inventory = other.inventory;
    }
}
