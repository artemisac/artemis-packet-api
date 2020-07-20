package cc.ghast.packet;

import cc.ghast.packet.listener.ChannelListener;
import cc.ghast.packet.codec.ArtemisDecoder;
import cc.ghast.packet.codec.ArtemisEncoder;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * @author Ghast
 * @since 24-Apr-20
 */

@Getter
public class PacketManager extends JavaPlugin {

    private ArtemisDecoder inbound;
    private ArtemisEncoder outbound;

    @Override
    public void onEnable() {
        //this.inbound = new ArtemisDecoder(EnumProtocolDirection.IN);
        //new ServerConnectionInjector().inject();
        new ChannelListener(this);
        System.out.println("[Artemis Test] Enabled Test Decoder");
        System.out.println("[Artemis Test] Version: " + this.getDescription().getVersion());
        System.out.println("[Artemis Test] Authors: " + Arrays.toString(this.getDescription().getAuthors().toArray()));
    }

    @Override
    public void onDisable() {

    }


}
