package cc.ghast.packet.listener.injector;

import cc.ghast.packet.codec.ArtemisDecoder;
import cc.ghast.packet.codec.ArtemisDecoderLegacy;
import cc.ghast.packet.codec.ArtemisEncoder;
import cc.ghast.packet.codec.ArtemisEncoderLegacy;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.EnumProtocolCurrent;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.reflections.ReflectUtil;
import cc.ghast.packet.utils.HookUtil;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.SneakyThrows;
import net.minecraft.util.io.netty.channel.Channel;
import net.minecraft.util.io.netty.channel.ChannelFuture;
import net.minecraft.util.io.netty.channel.ChannelFutureListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.UUID;

/**
 * @author Ghast
 * @since 18/08/2020
 * Artemis © 2020
 */
public class InjectorLegacy implements Injector {

    public InjectorLegacy() {
        System.out.println("[Artemis] Using Legacy Encoder");
    }

    @Override
    public void inject(AsyncPlayerPreLoginEvent event) {
        Channel channel = (Channel) ReflectUtil.getChannel(event.getUniqueId(), event.getAddress().getHostAddress());
        inject(channel, event.getUniqueId(), event.getAddress().getHostAddress());
    }

    @Override
    public void uninject(PlayerQuitEvent event) {
        Channel channel = (Channel) profiles.get(event.getPlayer().getUniqueId()).getChannel();
        if (channel.pipeline().get("artemis_client") != null) {
            channel.pipeline().remove("artemis_client");
        }
    }

    @Override
    public void inject(Object channel, UUID uuid, String inetAddress) {
        Profile profile = new Profile(uuid, inetAddress, ProtocolVersion.getGameVersion(), channel);
        profile.setProtocol(EnumProtocolCurrent.PLAY);
        ((Channel)channel).pipeline().addBefore(HookUtil.getHookBehind(), clientBound,
                new ArtemisDecoderLegacy(profile, ProtocolDirection.IN));
        ((Channel)channel).pipeline().addAfter(HookUtil.getHookOutbound(), serverBound,
                new ArtemisDecoderLegacy(profile, ProtocolDirection.OUT));
        ((Channel)channel).pipeline().addLast(encoder, new ArtemisEncoderLegacy(profile));
        profiles.put(uuid, profile);
    }

    @Override
    public Profile getProfile(UUID uuid) {
        return profiles.computeIfAbsent(uuid, e -> {
            String addy = parseAddress(Bukkit.getPlayer(e).getAddress());
            Channel pipeline = (Channel) ReflectUtil.getChannel(e, addy);
            inject(pipeline, e, addy);
            return profiles.get(e);
        });
    }

    @Override
    @SneakyThrows
    public void writePacket(Player player, Packet<?> packet) {
        Channel channel = (Channel) this.getProfile(player.getUniqueId()).getChannel();

        ChannelFuture channelfuture = channel.writeAndFlush(packet);
        channelfuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }
    
}
