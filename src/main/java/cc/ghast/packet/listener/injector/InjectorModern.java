package cc.ghast.packet.listener.injector;

import cc.ghast.packet.codec.ArtemisDecoder;
import cc.ghast.packet.codec.ArtemisEncoder;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.EnumProtocolCurrent;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.reflections.ReflectUtil;
import cc.ghast.packet.utils.HookUtil;
import cc.ghast.packet.wrapper.packet.Packet;
import io.netty.channel.Channel;
import org.bukkit.entity.Player;
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
        Channel channel = (Channel) ReflectUtil.getChannel(event.getUniqueId(), event.getAddress().getHostAddress());
        inject(channel, event.getUniqueId(), event.getAddress());
    }

    @Override
    public void uninject(PlayerQuitEvent event) {
        Channel channel = (Channel) profiles.get(event.getPlayer().getUniqueId()).getChannel();
        if (channel.pipeline().get(clientBound) != null) {
            channel.pipeline().remove(clientBound);
        }

        if (channel.pipeline().get(serverBound) != null) {
            channel.pipeline().remove(serverBound);
        }
    }

    @Override
    public void inject(Object channel, UUID uuid, InetAddress inetAddress) {
        Profile profile = new Profile(uuid, inetAddress, ProtocolVersion.getGameVersion(), channel);
        profile.setProtocol(EnumProtocolCurrent.PLAY);
        ((Channel)channel).pipeline().addBefore(HookUtil.getHookBehind(), clientBound, new ArtemisDecoder(profile, ProtocolDirection.IN));
        ((Channel)channel).pipeline().addAfter(HookUtil.getHookForward(), serverBound, new ArtemisDecoder(profile, ProtocolDirection.OUT));
        ((Channel)channel).pipeline().addAfter(HookUtil.getHookForward(), encoder, new ArtemisEncoder(profile));
        profiles.put(uuid, profile);
    }

    @Override
    public void writePacket(Player player, Packet<?> packet) {
        Channel channel = (Channel) profiles.get(player.getUniqueId()).getChannel();
        channel.pipeline().writeAndFlush(packet);
    }
}
