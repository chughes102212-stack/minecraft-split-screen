package com.chughes102212.splitscreen;

import java.util.prefs.Preferences;

public final class SplitScreenConfig {
    public enum Layout { HORIZONTAL, VERTICAL }
    private static final Preferences PREFS = Preferences.userRoot().node("minecraft-split-screen");
    private Layout layout = Layout.HORIZONTAL;
    private int playerOneController = 0;
    private int playerTwoController = 1;
    private int viewportRenderDistance = 6;

    public static SplitScreenConfig load() {
        SplitScreenConfig c = new SplitScreenConfig();
        c.layout = Layout.valueOf(PREFS.get("layout", Layout.HORIZONTAL.name()));
        c.playerOneController = PREFS.getInt("player1.controller", 0);
        c.playerTwoController = PREFS.getInt("player2.controller", 1);
        c.viewportRenderDistance = Math.max(2, Math.min(17, PREFS.getInt("render_distance", 6)));
        return c;
    }

    public Layout layout() { return layout; }
    public void layout(Layout value) { layout = value; PREFS.put("layout", value.name()); }
    public int playerOneController() { return playerOneController; }
    public int playerTwoController() { return playerTwoController; }
    public int viewportRenderDistance() { return viewportRenderDistance; }
}
