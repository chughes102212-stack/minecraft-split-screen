package com.chughes102212.splitscreen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

/** A vanilla-styled controller mapping screen, opened with the normal Controls-style menu key. */
public final class ControllerMappingScreen extends Screen {
    private static final int ROW_HEIGHT = 24;
    private final Screen parent;
    private final SplitScreenConfig config;
    private final ControllerMapping mapping;
    private ControllerMapping.Action listeningFor;

    public ControllerMappingScreen(Screen parent, ControllerProfile profile) {
        super(Text.translatable("screen.split_screen.controller_mapping"));
        this.parent = parent;
        this.config = SplitScreenConfig.load();
        this.mapping = new ControllerMapping(profile);
    }

    @Override
    protected void init() {
        int left = this.width / 2 - 155;
        int top = 35;
        int row = 0;

        for (int player = 0; player < 2; player++) {
            int y = top + row++ * ROW_HEIGHT;
            int controllerId = player == 0 ? config.playerOneController() : config.playerTwoController();
            this.addDrawableChild(ButtonWidget.builder(
                    Text.literal("Player " + (player + 1) + ": Controller " + controllerId),
                    button -> cycleController(player)
            ).dimensions(left, y, 310, 20).build());
        }

        int joinY = top + row++ * ROW_HEIGHT;
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Player 2 join trigger: " + config.playerTwoJoinTrigger().name()),
                button -> cycleJoinTrigger()
        ).dimensions(left, joinY, 310, 20).build());

        for (ControllerMapping.Action action : ControllerMapping.Action.values()) {
            int y = top + row++ * ROW_HEIGHT;
            this.addDrawableChild(ButtonWidget.builder(label(action), button -> beginListening(action))
                    .dimensions(left, y, 310, 20).build());
        }
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.done"), button -> close())
                .dimensions(this.width / 2 - 100, top + row * ROW_HEIGHT + 10, 200, 20).build());
    }

    private Text label(ControllerMapping.Action action) {
        String suffix = listeningFor == action ? "  < Press a button >" : "  [" + mapping.button(action) + "]";
        return Text.literal(action.name().replace('_', ' ') + suffix);
    }

    private void cycleController(int player) {
        int current = (player == 0 ? config.playerOneController() : config.playerTwoController());
        int nextController = current + 1;
        if (nextController > 15) nextController = 0;
        config.assignController(player, nextController);
        if (SplitScreenClient.runtime() != null) {
            SplitScreenClient.runtime().controllers().assignPlayerToController(player, nextController);
            SplitScreenClient.runtime().player(player).controller(nextController);
        }
        clearAndInit();
    }

    private void cycleJoinTrigger() {
        SplitScreenConfig.JoinTrigger[] values = SplitScreenConfig.JoinTrigger.values();
        int currentIndex = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == config.playerTwoJoinTrigger()) {
                currentIndex = i;
                break;
            }
        }
        SplitScreenConfig.JoinTrigger next = values[(currentIndex + 1) % values.length];
        config.playerTwoJoinTrigger(next);
        if (SplitScreenClient.runtime() != null) {
            SplitScreenClient.runtime().player(1).joinTrigger(next);
        }
        clearAndInit();
    }

    private void beginListening(ControllerMapping.Action action) {
        listeningFor = action;
        clearAndInit();
    }

    /** Called by the controller backend when a button edge is detected. */
    public void acceptButton(String buttonName) {
        if (listeningFor == null) return;
        mapping.set(listeningFor, buttonName);
        listeningFor = null;
        clearAndInit();
    }

    private void close() {
        if (this.client != null) this.client.setScreen(parent);
    }

    @Override
    public void close() { close(); }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
