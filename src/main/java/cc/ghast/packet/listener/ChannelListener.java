package cc.ghast.packet.listener;

import cc.ghast.packet.PacketManager;
import cc.ghast.packet.listener.injector.Injector;
import cc.ghast.packet.listener.injector.v1_8_R3.Injector_v1_8_R3;
import cc.ghast.packet.nms.ProtocolVersion;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Ghast
 * @since 11-May-20
 */

@Getter
public class ChannelListener implements Listener {

    private final PacketManager packetManager;
    private final Injector injector;

    public ChannelListener(PacketManager packetManager) {
        this.packetManager = packetManager;
        Bukkit.getPluginManager().registerEvents(this, packetManager.getPlugin());
        this.injector = findInjector();
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent e) {
        injector.inject(e);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        injector.uninject(e);
    }

    private Injector findInjector() {
        switch (ProtocolVersion.getGameVersion()) {
            case V1_8_9: return new Injector_v1_8_R3();
            default: return null;
        }
    }


}
