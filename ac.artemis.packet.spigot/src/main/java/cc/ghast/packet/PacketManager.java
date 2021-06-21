package cc.ghast.packet;

import cc.ghast.packet.compat.HookManager;
import cc.ghast.packet.chain.ChainManager;
import cc.ghast.packet.listener.ChannelListener;
import lombok.Getter;
import org.bukkit.plugin.Plugin;

/**
 * @author Ghast
 * @since 24-Apr-20
 */

@Getter
public enum PacketManager {

    INSTANCE;

    private Plugin plugin;
    private ChainManager manager;
    private ChannelListener listener;
    private HookManager hookManager;

    public void init(Plugin plugin) {
        this.plugin = plugin;
        this.manager = new ChainManager();
        this.listener = new ChannelListener(this);
        this.hookManager = new HookManager();
    }


    public void info(String log) {
        plugin.getLogger().info(log);
    }
    public void fatal(String log) {
        plugin.getLogger().severe(log);
    }
}
