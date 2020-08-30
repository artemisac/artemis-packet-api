package cc.ghast.packet.utils;

import org.bukkit.Bukkit;

/**
 * @author Ghast
 * @since 15/08/2020
 * Artemis © 2020
 */
public class HookUtil {
    public static String getHookBehind() {
        final boolean hasProtocolLib = clazzExist("com.comphenix.protocol.ProtocolLib");
        final boolean hasViaVersion = clazzExist("us.myles.ViaVersion.ViaVersionPlugin");

        if (hasProtocolLib) {
            return "protocol_lib_finish";
        }

        return "decoder";
    }

    public static String getHookForward() {
        return "encoder";
    }

    private static boolean clazzExist(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (ClassNotFoundException ignored){
            return false;
        }
    }
}
