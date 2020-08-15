package cc.ghast.packet.listener.injector;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.codec.ArtemisDecoder;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.EnumProtocolDirection;
import cc.ghast.packet.utils.HookUtil;
import io.netty.channel.Channel;
import lombok.SneakyThrows;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public interface Injector {

    @SneakyThrows
    void inject(AsyncPlayerPreLoginEvent event);

    @SneakyThrows
    void uninject(PlayerQuitEvent event);

    Map<UUID, Profile> profiles = new WeakHashMap<>();

    default void inject(Channel channel, UUID uuid, InetAddress inetAddress) {
        Profile profile = new Profile(uuid, inetAddress, ProtocolVersion.getGameVersion(), channel);
        channel.pipeline().addBefore(HookUtil.getHookBehind(), "artemis_client", new ArtemisDecoder(profile));
        profiles.put(uuid, profile);
    }

    default Profile getProfile(UUID uuid) {
        return profiles.get(uuid);
    }

    default String parseAddress(SocketAddress address) {
        return address.toString().split("/")[1].split(":")[0];
    }
}
