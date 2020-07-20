package cc.ghast.packet.nms.version;

import cc.ghast.packet.reflections.Reflection;

/**
 * @author Ghast
 * @since 11-May-20
 */
public class ServerConnectionHandler {
    private Class<?> serverConnectionClass;
    private Object nmsServerConnection;

    private Class<?> craftServerClass;
    private Object craftServer;

    private void init() {
        craftServerClass = Reflection.getCraftBukkitClass("CraftServer");
        serverConnectionClass = Reflection.getMinecraftClass("ServerConnection");


    }
}
