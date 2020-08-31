package cc.ghast.packet.utils;

import cc.ghast.packet.utils.hook.ProtocolLibHook;
import org.bukkit.Bukkit;

/**
 * @author Ghast
 * @since 15/08/2020
 * Artemis © 2020
 */
public class HookUtil {

    private static boolean plib;

    static {
        try {
            new ProtocolLibHook();
            plib = true;
        } catch (Exception e){
            plib = false;
        }
    }

    public static String getHookBehind() {
        if (plib) {
            return "protocol_lib_decoder";
        }

        return "decoder";
    }

    public static String getHookOutbound() {
        return "encoder";
    }

    public static String getHookForward() {
        if (plib) {
            return "protocol_lib_encoder";
        }

        return "encoder";
    }


}
