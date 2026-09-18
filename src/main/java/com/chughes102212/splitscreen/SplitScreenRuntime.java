package com.chughes102212.splitscreen;

import net.minecraft.client.MinecraftClient;

public final class SplitScreenRuntime {
    private final SplitScreenConfig config = SplitScreenConfig.load();
    private final ControllerManager controllers = new ControllerManager();
    private final PauseCoordinator pauseCoordinator = new PauseCoordinator();
    private final LocalPlayerSlot[] players = {
            new LocalPlayerSlot(0, config.playerOneController()),
            new LocalPlayerSlot(1, config.playerTwoController())
    };

    public void tick(MinecraftClient client) {
        if (client.world == null) return;
        controllers.poll();
        for (LocalPlayerSlot player : players) {
            ControllerState state = controllers.state(player.controller());
            player.tick(client, state);
            pauseCoordinator.setPaused(player.index(), state.pause);
        }
        pauseCoordinator.apply(client);
    }

    public SplitScreenConfig config() { return config; }
    public LocalPlayerSlot player(int index) { return players[index]; }
    public ControllerManager controllers() { return controllers; }
    public PauseCoordinator pauseCoordinator() { return pauseCoordinator; }
}
