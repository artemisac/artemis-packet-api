package cc.ghast.packet.listener.initializator;

import cc.ghast.packet.PacketManager;
import cc.ghast.packet.codec.ArtemisDecoder;
import cc.ghast.packet.codec.ArtemisEncoder;
import cc.ghast.packet.listener.injector.Injector;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.ProtocolDirection;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Big credits to Myles; Heavily inspired from the BukkiChannelInitializer from ViaVersion
 * Can be found here: https://github.com/ViaVersion/ViaVersion/
 * @author Ghast
 * @since 18/08/2020
 * Artemis © 2020
 */
public class BukkitServerBootstrapper extends ChannelInitializer<SocketChannel> {

    private final ChannelInitializer<SocketChannel> original;
    private static Method initChannelMethod;

    public BukkitServerBootstrapper(ChannelInitializer<SocketChannel> original) {
        this.original = original;
    }

    public ChannelInitializer<SocketChannel> getOriginal() {
        return original;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        Profile info = new Profile(null, socketChannel.remoteAddress().getAddress().getHostAddress(), socketChannel);

        // Inject the profile
        PacketManager.INSTANCE.getListener().getInjector().injectFuturePlayer(info);

        // Add originals
        initChannelMethod.invoke(this.original, socketChannel);

        socketChannel.pipeline().addBefore("decoder", Injector.clientBound,
                new ArtemisDecoder(info, ProtocolDirection.IN));
        socketChannel.pipeline().addBefore("encoder", Injector.serverBound,
                new ArtemisDecoder(info, ProtocolDirection.OUT));
        socketChannel.pipeline().addLast(Injector.encoder,
                new ArtemisEncoder(info));
    }

    static {
        try {
            initChannelMethod = ChannelInitializer.class.getDeclaredMethod("initChannel", Channel.class);
            initChannelMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            PacketManager.INSTANCE.fatal("Failed to initialize Channel injection [Init Channel reflections]");
            e.printStackTrace();
        }
    }
}