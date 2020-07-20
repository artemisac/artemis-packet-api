package cc.ghast.packet.listener.injector.v1_8_R3;

import cc.ghast.packet.listener.injector.Injector;
import lombok.SneakyThrows;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.ServerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.Field;
import java.util.List;

public class Injector_v1_8_R3 implements Injector {

    @Override
    @SneakyThrows
    public void inject(AsyncPlayerPreLoginEvent event) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        ServerConnection serverConnection = server.aq();

        Field field = ServerConnection.class.getDeclaredField("h");
        field.setAccessible(true);
        List<NetworkManager> futures = (List<NetworkManager>) field.get(serverConnection);

        injector: {
            NetworkManager future = futures.parallelStream().filter(ch ->{
                return event.getAddress().getHostAddress().equals(parseAddress(ch.getRawAddress())) ;
            }).findFirst().orElse(null);

            if (future == null) break injector;

            inject(future.channel, event.getUniqueId(), event.getAddress());
        }
    }

    @Override
    public void uninject(PlayerQuitEvent event) {
        CraftPlayer player = (CraftPlayer) event.getPlayer();
        EntityPlayer entity = player.getHandle();
        entity.playerConnection.networkManager.channel.pipeline().remove("artemis_test");
    }
}
