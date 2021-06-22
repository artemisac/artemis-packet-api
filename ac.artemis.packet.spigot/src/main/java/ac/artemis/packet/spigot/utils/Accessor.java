package ac.artemis.packet.spigot.utils;

import ac.artemis.packet.spigot.ArtemisSpigotPlugin;

public abstract class Accessor {
    protected final ArtemisSpigotPlugin plugin;

    public Accessor(ArtemisSpigotPlugin plugin) {
        this.plugin = plugin;
    }

    public abstract void create();
    public abstract void dispose();
}
