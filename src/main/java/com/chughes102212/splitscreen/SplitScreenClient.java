package com.chughes102212.splitscreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public final class SplitScreenClient implements ClientModInitializer {
    public static final String MOD_ID = "split_screen";
    private static SplitScreenRuntime runtime;

    @Override
    public void onInitializeClient() {
        SplitScreenKeybinds.OPEN_CONTROLLER_MAPPING.setBoundKey(InputUtil.UNKNOWN_KEY);
        runtime = new SplitScreenRuntime();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            runtime.tick(client);
            while (SplitScreenKeybinds.OPEN_CONTROLLER_MAPPING.wasPressed()) {
                client.setScreen(new ControllerMappingScreen(client.currentScreen, ControllerProfile.GENERIC));
            }
        });
    }

    public static SplitScreenRuntime runtime() { return runtime; }
}
