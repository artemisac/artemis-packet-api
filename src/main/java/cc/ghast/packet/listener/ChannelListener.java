package cc.ghast.packet.listener;

import cc.ghast.packet.PacketManager;
import cc.ghast.packet.listener.injector.Injector;
import cc.ghast.packet.utils.Chat;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        this.injector = Injector.build();
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent e) {
        injector.inject(e);
        Bukkit.getConsoleSender().sendMessage(Chat.translate("&r[&bPacket&r] &aSuccessfully &binjected&a into player &r" + e.getName()));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        injector.uninject(e);
        Bukkit.getConsoleSender().sendMessage(Chat.translate("&r[&bPacket&r] &aSuccessfully &cdisinjected&r from player &r" + e.getPlayer().getName()));
    }

}
