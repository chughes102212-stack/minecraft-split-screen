package com.chughes102212.splitscreen;

/** A controller detected by the startup assignment flow. */
public record ControllerDevice(int id, String name, ControllerProfile profile) {
    public ControllerDevice(int id, String name) {
        this(id, name, ControllerProfile.fromName(name));
    }
}
