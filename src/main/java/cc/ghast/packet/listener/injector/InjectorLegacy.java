package cc.ghast.packet.listener.injector;

import cc.ghast.packet.codec.ArtemisDecoder;
import cc.ghast.packet.codec.ArtemisDecoderLegacy;
import cc.ghast.packet.codec.ArtemisEncoder;
import cc.ghast.packet.codec.ArtemisEncoderLegacy;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.reflections.ReflectUtil;
import cc.ghast.packet.utils.HookUtil;
import cc.ghast.packet.wrapper.packet.Packet;
import net.minecraft.util.io.netty.channel.Channel;
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
public class InjectorLegacy implements Injector {
    @Override
    public void inject(AsyncPlayerPreLoginEvent event) {
        Channel channel = (Channel) ReflectUtil.getChannel(event.getUniqueId(), event.getAddress().getHostAddress());
        inject(channel, event.getUniqueId(), event.getAddress());
    }

    @Override
    public void uninject(PlayerQuitEvent event) {
        Channel channel = (Channel) profiles.get(event.getPlayer().getUniqueId()).getChannel();
        if (channel.pipeline().get("artemis_client") != null) {
            channel.pipeline().remove("artemis_client");
        }
    }

    @Override
    public void inject(Object channel, UUID uuid, InetAddress inetAddress) {
        Profile profile = new Profile(uuid, inetAddress, ProtocolVersion.getGameVersion(), channel);
        ((Channel)channel).pipeline().addBefore(HookUtil.getHookBehind(), clientBound, new ArtemisDecoderLegacy(profile, ProtocolDirection.IN));
        ((Channel)channel).pipeline().addAfter(HookUtil.getHookForward(), serverBound, new ArtemisDecoderLegacy(profile, ProtocolDirection.OUT));
        ((Channel)channel).pipeline().addAfter(HookUtil.getHookForward(), encoder, new ArtemisEncoderLegacy(profile));
        profiles.put(uuid, profile);
    }

    @Override
    public void writePacket(Player player, Packet<?> packet) {
        Channel channel = (Channel) profiles.get(player.getUniqueId()).getChannel();
        channel.pipeline().writeAndFlush(packet);
    }
    
}
