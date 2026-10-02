package com.chughes102212.splitscreen;

import net.fabricmc.loader.api.FabricLoader;

public final class MidnightControlsBridge {
    private static final boolean MIDNIGHT_CONTROLS_LOADED = FabricLoader.getInstance().isModLoaded("midnightcontrols");

    public static boolean isAvailable() {
        return MIDNIGHT_CONTROLS_LOADED;
    }

    public static String dependencyName() {
        return "midnightcontrols";
    }
}
