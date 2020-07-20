package cc.ghast.packet.listener.injector;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.codec.ArtemisDecoder;
import cc.ghast.packet.protocol.EnumProtocolDirection;
import io.netty.channel.Channel;
import lombok.SneakyThrows;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.UUID;

public interface Injector {

    @SneakyThrows
    void inject(AsyncPlayerPreLoginEvent event);

    @SneakyThrows
    void uninject(PlayerQuitEvent event);

    default void inject(Channel channel, UUID uuid, InetAddress inetAddress) {
        channel.pipeline().addBefore("protocol_lib_finish", "artemis_test",
                new ArtemisDecoder(EnumProtocolDirection.IN, inetAddress, uuid, ProtocolVersion.getGameVersion()));
    }

    default String parseAddress(SocketAddress address) {
        return address.toString().split("/")[1].split(":")[0];
    }
}
