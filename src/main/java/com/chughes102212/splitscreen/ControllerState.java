package com.chughes102212.splitscreen;

public final class ControllerState {
    public float leftX, leftY, rightX, rightY, triggerLeft, triggerRight;
    public boolean jump, attack, use, inventory, pause;
    private boolean previousJump;
    private boolean previousAttack;
    private boolean previousUse;
    private boolean previousPause;

    void copyFrom(ControllerState other) {
        previousJump = jump;
        previousAttack = attack;
        previousUse = use;
        previousPause = pause;

        leftX = other.leftX; leftY = other.leftY;
        rightX = other.rightX; rightY = other.rightY;
        triggerLeft = other.triggerLeft; triggerRight = other.triggerRight;
        jump = other.jump; attack = other.attack; use = other.use;
        inventory = other.inventory; pause = other.pause;
    }

    public boolean jumpPressed() { return jump && !previousJump; }
    public boolean attackPressed() { return attack && !previousAttack; }
    public boolean usePressed() { return use && !previousUse; }
    public boolean pausePressed() { return pause && !previousPause; }
}
