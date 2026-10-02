package com.chughes102212.splitscreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public final class SplitScreenClient implements ClientModInitializer {
    public static final String MOD_ID = "split_screen";
    private static SplitScreenRuntime runtime;

    @Override
    public void onInitializeClient() {
        if (!MidnightControlsBridge.isAvailable()) {
            throw new IllegalStateException("MidnightControls is required for this split-screen mod");
        }
        runtime = new SplitScreenRuntime();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            runtime.tick(client);
            while (SplitScreenKeybinds.OPEN_CONTROLLER_MAPPING.wasPressed()) {
                client.setScreen(new ControllerMappingScreen(client.currentScreen, ControllerProfile.GENERIC));
            }
        });
    }

    public static SplitScreenRuntime runtime() {
        return runtime;
    }
}
