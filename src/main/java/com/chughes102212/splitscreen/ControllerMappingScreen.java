package com.chughes102212.splitscreen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

/** A vanilla-styled controller mapping screen, opened with the normal Controls-style menu key. */
public final class ControllerMappingScreen extends Screen {
    private static final int ROW_HEIGHT = 24;
    private final Screen parent;
    private final ControllerMapping mapping;
    private ControllerMapping.Action listeningFor;

    public ControllerMappingScreen(Screen parent, ControllerProfile profile) {
        super(Text.translatable("screen.split_screen.controller_mapping"));
        this.parent = parent;
        this.mapping = new ControllerMapping(profile);
    }

    @Override
    protected void init() {
        int left = this.width / 2 - 155;
        int top = 45;
        int row = 0;
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
