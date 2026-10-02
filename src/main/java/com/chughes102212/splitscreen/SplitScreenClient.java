package com.chughes102212.splitscreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SplitScreenClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("split_screen");
    public static final String MOD_ID = "split_screen";
    private static SplitScreenRuntime runtime;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Split-screen client starting with MidnightControls dependency");
        runtime = new SplitScreenRuntime();

        SplitScreenKeybinds.OPEN_CONTROLLER_MAPPING.setBoundKey(net.minecraft.client.util.InputUtil.UNKNOWN_KEY);
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
