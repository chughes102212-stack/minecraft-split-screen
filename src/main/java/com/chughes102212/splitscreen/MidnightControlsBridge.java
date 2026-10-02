package com.chughes102212.splitscreen;

import net.fabricmc.loader.api.FabricLoader;

public final class MidnightControlsBridge {
    private MidnightControlsBridge() {}

    public static boolean isAvailable() {
        return FabricLoader.getInstance().isModLoaded("midnightcontrols");
    }

    public static String requiredModId() {
        return "midnightcontrols";
    }

    /**
     * MidnightControls is the controller backend used by this mod. The split-screen
     * layer assigns each controller to a player slot by controller id so Player 1 and
     * Player 2 can use separate local inputs while sharing the same Microsoft account.
     */
    public static void bindDefaultPlayerControllers(ControllerManager manager, int playerOneController, int playerTwoController) {
        if (manager == null) return;
        manager.assignPlayerToController(0, playerOneController);
        manager.assignPlayerToController(1, playerTwoController);
    }
}
