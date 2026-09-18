package com.chughes102212.splitscreen;

/** Console-family controller profiles with familiar default Minecraft bindings. */
public enum ControllerProfile {
    XBOX("Xbox", "A", "B", "X", "Y", "LB", "RB", "Menu", "View"),
    PLAYSTATION("PlayStation", "Cross", "Circle", "Square", "Triangle", "L1", "R1", "Options", "Create"),
    NINTENDO("Nintendo", "B", "A", "Y", "X", "L", "R", "+", "-"),
    GENERIC("Generic", "South", "East", "West", "North", "Left Bumper", "Right Bumper", "Start", "Back");

    private final String displayName;
    private final String jump, cancel, attack, use, leftBumper, rightBumper, pause, inventory;

    ControllerProfile(String displayName, String jump, String cancel, String attack, String use,
                      String leftBumper, String rightBumper, String pause, String inventory) {
        this.displayName = displayName;
        this.jump = jump;
        this.cancel = cancel;
        this.attack = attack;
        this.use = use;
        this.leftBumper = leftBumper;
        this.rightBumper = rightBumper;
        this.pause = pause;
        this.inventory = inventory;
    }

    public static ControllerProfile fromName(String name) {
        if (name == null) return GENERIC;
        String value = name.toLowerCase();
        if (value.contains("xbox") || value.contains("x-input") || value.contains("xinput")) return XBOX;
        if (value.contains("playstation") || value.contains("dualshock") || value.contains("dualsense") || value.contains("ps4") || value.contains("ps5")) return PLAYSTATION;
        if (value.contains("nintendo") || value.contains("switch") || value.contains("joy-con") || value.contains("joy con") || value.contains("pro controller")) return NINTENDO;
        return GENERIC;
    }

    public String displayName() { return displayName; }
    public String jump() { return jump; }
    public String cancel() { return cancel; }
    public String attack() { return attack; }
    public String use() { return use; }
    public String leftBumper() { return leftBumper; }
    public String rightBumper() { return rightBumper; }
    public String pause() { return pause; }
    public String inventory() { return inventory; }
}
