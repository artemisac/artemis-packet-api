package cc.ghast.packet;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.chain.PacketListener;
import cc.ghast.packet.listener.callback.LoginCallback;
import cc.ghast.packet.utils.PacketCallback;
import cc.ghast.packet.wrapper.packet.Packet;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * @author Ghast
 * @since 15/08/2020
 * Artemis © 2020
 */
public class PacketAPI {
    public static void addListener(PacketListener listener){
        PacketManager.INSTANCE.getManager().injectListener(listener);
    }

    public static void addAsyncListener(PacketListener listener){
        listener.setAsync(true);
        PacketManager.INSTANCE.getManager().injectListener(listener);
    }

    public static Profile getProfile(UUID uuid) {
        return PacketManager.INSTANCE.getListener().getInjector().getProfile(uuid);
    }

    public static boolean isInjected(UUID uuid) {
        return PacketManager.INSTANCE.getListener().getInjector().getProfile(uuid) != null;
    }


    public static void disinject(Player player){
        if (isInjected(player.getUniqueId()))
            PacketManager.INSTANCE.getListener().getInjector()
                .uninjectPlayer(player.getUniqueId());
    }

    public static ProtocolVersion getVersion(UUID uuid) {
        return PacketManager.INSTANCE.getListener().getInjector().getProfile(uuid).getVersion();
    }

    public static void sendPacket(Player player, Packet<?> packet){
        sendPacket(player, packet, null);
    }

    public static void sendPacket(Player player, Packet<?> packet, Consumer<PacketCallback> callback){
        PacketManager.INSTANCE.getListener().getInjector().writePacket(player.getUniqueId(), packet, callback);
    }

    public static void addLoginCallback(LoginCallback loginCallback) {
        PacketManager.INSTANCE.getListener().getInjector().addLoginCallback(loginCallback);
    }

    public static void removeLoginCallback(LoginCallback loginCallback) {
        PacketManager.INSTANCE.getListener().getInjector().removeLoginCallback(loginCallback);
    }
}
