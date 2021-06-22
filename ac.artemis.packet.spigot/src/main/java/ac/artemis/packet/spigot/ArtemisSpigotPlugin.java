package ac.artemis.packet.spigot;

import ac.artemis.packet.spigot.protocol.ProtocolRepository;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArtemisSpigotPlugin extends JavaPlugin {
    private ProtocolRepository repository;
    private ArtemisSpigotApi api;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.repository = new ProtocolRepository(this);
        this.api = new ArtemisSpigotApi(this);

        repository.create();
        api.create();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        repository.dispose();
        api.dispose();

        this.repository = null;
        this.api = null;
    }
}
