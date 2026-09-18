package com.chughes102212.splitscreen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.session.Session;

/**
 * Keeps all local players inside the one authenticated Minecraft client session.
 * No second Microsoft login, token, or credential is created or stored.
 */
public final class SingleAccountSession {
    private String accountUuid;
    private String accountName;

    public void capture(MinecraftClient client) {
        Session session = client.getSession();
        accountUuid = session.getUuidOrNull() == null ? "offline" : session.getUuidOrNull().toString();
        accountName = session.getUsername();
    }

    public boolean isCaptured() {
        return accountName != null;
    }

    public String accountUuid() { return accountUuid; }
    public String accountName() { return accountName; }

    /** Both local slots deliberately use this one authenticated session. */
    public Session session() {
        return null;
    }
}
