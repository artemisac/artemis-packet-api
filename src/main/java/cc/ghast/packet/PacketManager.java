package cc.ghast.packet;

import cc.ghast.packet.chain.ChainManager;
import cc.ghast.packet.listener.ChannelListener;
import cc.ghast.packet.codec.ArtemisDecoder;
import cc.ghast.packet.codec.ArtemisEncoder;
import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

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

    public void init() {
        //this.inbound = new ArtemisDecoder(ProtocolDirection.IN);
        //new ServerConnectionInjector().inject();
        listener = new ChannelListener(this);
        System.out.println("[Artemis Test] Enabled Test Decoder");
;
    }

    public void onDisable() {

    }


}
