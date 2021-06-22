package ac.artemis.packet.spigot.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * @author Ghast
 * @since 31/08/2020
 * Artemis © 2020
 */

@UtilityClass
public class ServerUtil {
    public String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public void sendConsoleMessage(String s) {
        Bukkit.getConsoleSender().sendMessage(color("&r[&b&lPacket&r] &r" + s));
    }
}
