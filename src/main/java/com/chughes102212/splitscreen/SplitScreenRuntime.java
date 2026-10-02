package com.chughes102212.splitscreen;

import net.minecraft.client.MinecraftClient;

public final class SplitScreenRuntime {
    private final SplitScreenConfig config = SplitScreenConfig.load();
    private final ControllerManager controllers = new ControllerManager();
    private final PauseCoordinator pauseCoordinator = new PauseCoordinator();
    private final SingleAccountSession account = new SingleAccountSession();
    private final LocalPlayerSlot[] players = {
            new LocalPlayerSlot(0, config.playerOneController()),
            new LocalPlayerSlot(1, config.playerTwoController())
    };

    public SplitScreenRuntime() {
        MidnightControlsBridge.bindDefaultPlayerControllers(controllers,
                config.playerOneController(),
                config.playerTwoController());
        players[0].controller(config.playerOneController());
        players[1].controller(config.playerTwoController());
        players[1].joinTrigger(config.playerTwoJoinTrigger());
    }

    public void tick(MinecraftClient client) {
        if (client.world == null) return;
        if (!account.isCaptured()) account.capture(client);
        controllers.poll();

        for (LocalPlayerSlot player : players) {
            int controllerId = controllers.controllerForPlayer(player.index());
            if (player.controller() != controllerId) {
                player.controller(controllerId);
            }
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
    public SingleAccountSession account() { return account; }
}
