package com.chughes102212.splitscreen;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class SplitScreenKeybinds {
    private SplitScreenKeybinds() {}

    public static final KeyBinding OPEN_CONTROLLER_MAPPING = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("key.split_screen.controller_mapping", InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_F8, "category.split_screen"));
}
