package cc.ghast.packet;

import cc.ghast.packet.chain.PacketListener;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.wrapper.packet.Packet;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

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

    public static void inject(Player player){
        if (!isInjected(player.getUniqueId())){
            AsyncPlayerPreLoginEvent event = new AsyncPlayerPreLoginEvent(player.getName(), player.getAddress().getAddress(), player.getUniqueId());
            PacketManager.INSTANCE.getListener()
                    .getInjector()
                    .inject(event);
        }
    }

    public static void disinject(Player player){
        if (isInjected(player.getUniqueId())) PacketManager.INSTANCE.getListener().getInjector().uninject(new PlayerQuitEvent(player, ""));
    }

    public static ProtocolVersion getVersion(UUID uuid) {
        return PacketManager.INSTANCE.getListener().getInjector().getProfile(uuid).getVersion();
    }

    public static void sendPacket(Player player, Packet<?> packet){
        PacketManager.INSTANCE.getListener().getInjector().writePacket(player, packet);
        PacketManager.INSTANCE.getManager().callPacket(getProfile(player.getUniqueId()), packet);
    }
}
