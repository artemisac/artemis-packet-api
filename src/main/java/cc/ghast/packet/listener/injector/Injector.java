package cc.ghast.packet.listener.injector;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.SocketAddress;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

public interface Injector {

    String clientBound = "artemis_client";
    String serverBound = "artemis_server";
    String encoder = "artemis_encoder";

    void injectReader();
    void uninjectReader();
    void injectFuturePlayer(Profile profile);
    void uninjectFuturePlayer(Object channel);
    void injectPlayer(UUID uuid);
    void uninjectPlayer(UUID uuid);
    Profile getProfile(UUID uuid);

    void writePacket(UUID target, Packet<?> packet);
}
