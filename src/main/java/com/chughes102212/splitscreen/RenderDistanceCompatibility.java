package com.chughes102212.splitscreen;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

/**
 * Keeps the split-screen distance on Minecraft's shared view-distance option.
 * Sodium and other renderers normally consume this same option, so no renderer
 * internals or compile-time Sodium dependency are required.
 */
public final class RenderDistanceCompatibility {
    public static final int MIN_DISTANCE = 2;
    public static final int MAX_DISTANCE = 17;
    private static final boolean SODIUM_PRESENT = FabricLoader.getInstance().isModLoaded("sodium");
    private int appliedDistance = -1;

    public void apply(MinecraftClient client, SplitScreenConfig config) {
        if (client.options == null) return;

        int distance = Math.max(MIN_DISTANCE,
                Math.min(MAX_DISTANCE, config.viewportRenderDistance()));
        if (distance == appliedDistance) return;

        warnIfPotentiallyUnsafe(distance);

        // Minecraft's view-distance option is also the compatibility boundary
        // used by Sodium, Indium-compatible renderers, and vanilla chunk setup.
        client.options.getViewDistance().setValue(distance);
        appliedDistance = distance;
    }

    public void warnIfPotentiallyUnsafe(int distance) {
        if (distance >= 14 || (SODIUM_PRESENT && distance >= 10)) {
            System.err.println("[Minecraft Split Screen] WARNING: render distance " + distance +
                    " may be unsafe on low-end GPUs. If the game crashes or stutters, reduce it to 8-12 chunks.");
        }
    }

    public boolean sodiumPresent() {
        return SODIUM_PRESENT;
    }

    public int appliedDistance() {
        return appliedDistance;
    }
}
