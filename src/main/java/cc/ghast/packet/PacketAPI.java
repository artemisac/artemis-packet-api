package cc.ghast.packet;

import cc.ghast.packet.chain.PacketListener;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;

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

    public static ProtocolVersion getVersion(UUID uuid) {
        return PacketManager.INSTANCE.getListener().getInjector().getProfile(uuid).getVersion();
    }
}
