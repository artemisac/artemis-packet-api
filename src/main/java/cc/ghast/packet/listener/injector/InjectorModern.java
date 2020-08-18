package cc.ghast.packet.listener.injector;

import cc.ghast.packet.codec.ArtemisDecoder;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.reflections.ReflectUtil;
import cc.ghast.packet.utils.HookUtil;
import io.netty.channel.Channel;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.InetAddress;
import java.util.UUID;

/**
 * @author Ghast
 * @since 18/08/2020
 * Artemis © 2020
 */
public class InjectorModern implements Injector {
    @Override
    public void inject(AsyncPlayerPreLoginEvent event) {
        Channel channel = (Channel) ReflectUtil.getChannel(event.getUniqueId(), event.getAddress().toString());
        inject(channel, event.getUniqueId(), event.getAddress());
    }

    @Override
    public void uninject(PlayerQuitEvent event) {

    }

    @Override
    public void inject(Object channel, UUID uuid, InetAddress inetAddress) {
        Profile profile = new Profile(uuid, inetAddress, ProtocolVersion.getGameVersion(), channel);
        ((Channel)channel).pipeline().addBefore(HookUtil.getHookBehind(), "artemis_client", new ArtemisDecoder(profile));
        profiles.put(uuid, profile);
    }
}
