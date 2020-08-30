package cc.ghast.packet.listener.injector;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

public interface Injector {

    String clientBound = "artemis_client";
    String serverBound = "artemis_server";
    String encoder = "artemis_encoder";

    static Injector build() {
        return ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_8)
                ? new InjectorModern() : new InjectorLegacy();
    }

    @SneakyThrows
    void inject(AsyncPlayerPreLoginEvent event);

    @SneakyThrows
    void uninject(PlayerQuitEvent event);

    void writePacket(Player player, Packet<?> packet);

    Map<UUID, Profile> profiles = new WeakHashMap<>();

    void inject(Object channel, UUID uuid, InetAddress inetAddress);

    default Profile getProfile(UUID uuid) {
        return profiles.get(uuid);
    }

    default String parseAddress(SocketAddress address) {
        return address.toString().split("/")[1].split(":")[0];
    }
}
