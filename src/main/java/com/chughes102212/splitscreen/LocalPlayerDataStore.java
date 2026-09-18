package com.chughes102212.splitscreen;

import net.minecraft.client.MinecraftClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** Stores local split-screen player data separately from the Microsoft account. */
public final class LocalPlayerDataStore {
    private final Path root;

    public LocalPlayerDataStore(MinecraftClient client) {
        root = client.runDirectory.toPath().resolve("split-screen");
    }

    public PlayerData load(int playerIndex) {
        Path file = file(playerIndex);
        Properties properties = new Properties();
        try {
            if (Files.exists(file)) {
                try (var reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
                    properties.load(reader);
                }
            }
        } catch (IOException ignored) {
            // A missing or unreadable profile falls back to safe defaults.
        }
        return new PlayerData(
                properties.getProperty("name", "Player " + (playerIndex + 1)),
                properties.getProperty("selected", Boolean.toString(playerIndex == 0)));
    }

    public void save(int playerIndex, PlayerData data) {
        try {
            Files.createDirectories(root);
            Properties properties = new Properties();
            properties.setProperty("name", data.name());
            properties.setProperty("selected", Boolean.toString(data.selected()));
            try (var writer = Files.newBufferedWriter(file(playerIndex), StandardCharsets.UTF_8)) {
                properties.store(writer, "Minecraft Split Screen local player data");
            }
        } catch (IOException ignored) {
            // Gameplay must continue if the profile directory cannot be written.
        }
    }

    public Path file(int playerIndex) {
        return root.resolve("player" + (playerIndex + 1) + ".properties");
    }

    public record PlayerData(String name, boolean selected) {
        public PlayerData(String name, String selected) {
            this(name, Boolean.parseBoolean(selected));
        }
    }
}
