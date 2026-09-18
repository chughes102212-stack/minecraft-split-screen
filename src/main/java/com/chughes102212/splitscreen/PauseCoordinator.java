package com.chughes102212.splitscreen;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

/** Coordinates pause requests from both local players. */
public final class PauseCoordinator {
    private final boolean essentialPresent = FabricLoader.getInstance().isModLoaded("essential");
    private boolean playerOnePaused;
    private boolean playerTwoPaused;

    public void setPaused(int player, boolean paused) {
        if (player == 0) playerOnePaused = paused;
        if (player == 1) playerTwoPaused = paused;
    }

    public boolean isPaused(int player) {
        return player == 0 ? playerOnePaused : playerTwoPaused;
    }

    /** Pause the shared local world only when both local players request it. */
    public boolean shouldPauseWorld() {
        return playerOnePaused && playerTwoPaused;
    }

    public void apply(MinecraftClient client) {
        if (client.world == null) return;
        // The local split-screen client remains active while only one player pauses.
        // The actual screen/mixin hook should use this shared decision before opening
        // the vanilla pause screen, preventing one player's pause from stopping both.
        if (shouldPauseWorld() && client.currentScreen == null) {
            client.openPauseMenu(false);
        }
    }

    public boolean essentialPresent() {
        return essentialPresent;
    }
}
