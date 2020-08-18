package cc.ghast.packet.reflections;

import cc.ghast.packet.protocol.EnumProtocolDirection;
import cc.ghast.packet.wrapper.packet.Packet;
import org.bukkit.Bukkit;

import java.net.SocketAddress;
import java.util.*;

/**
 * @author Ghast
 * @since 17/08/2020
 * Artemis © 2020
 */
public class ReflectUtil {

    /*
        Minecraft Server field
     */
    public static final Class<?> MINECRAFT_SERVER_CLAZZ = Reflection.getMinecraftClass("MinecraftServer");
    public static final Class<?> CRAFT_SERVER_CLAZZ = Reflection.getCraftBukkitClass("CraftServer");
    public static FieldAccessor<?> MINECRAFT_SERVER_FIELD = Reflection.getField(MINECRAFT_SERVER_CLAZZ, CRAFT_SERVER_CLAZZ, 0);
    public static Object MINECRAFT_SERVER = MINECRAFT_SERVER_FIELD.get(Bukkit.getServer());

    /*
        Minecraft Connection Field
     */

    public static final Class<?> SERVER_CONNECTION_CLAZZ = Reflection.getMinecraftClass("ServerConnection");
    public static final FieldAccessor<?> SERVER_CONNECTION_FIELD = Reflection.getField(MINECRAFT_SERVER_CLAZZ, SERVER_CONNECTION_CLAZZ, 0);
    public static Object SERVER_CONNECTION = SERVER_CONNECTION_FIELD.get(MINECRAFT_SERVER);

    /*
        Minecraft Manager Field
     */
    public static final Class<?> NETWORK_MANAGER_CLAZZ = Reflection.getMinecraftClass("NetworkManager");
    public static final FieldAccessor<List> NETWORK_MANAGERS_FIELD = Reflection.getField(SERVER_CONNECTION_CLAZZ, List.class, 1);

    /*
        Socket Field
     */
    public static final FieldAccessor<SocketAddress> ADDRESS_FIELD = Reflection.getField(NETWORK_MANAGER_CLAZZ, SocketAddress.class, 0);


    /*
        Enum Protocol Class
     */
    public static final Class<?> ENUM_PROTOCOL_CLAZZ = Reflection.getMinecraftClass("EnumProtocol");
    public static final Object[] ENUM_PROTOCOLS = ENUM_PROTOCOL_CLAZZ.getEnumConstants();
    public static final FieldAccessor<Map> PACKET_MAP_FIELD = Reflection.getField(ENUM_PROTOCOL_CLAZZ, Map.class, 1);

    /*
        Enum Direction Class
     */
    public static final Class<?> ENUM_DIRECTION_CLAZZ = Reflection.getMinecraftClass("EnumProtocolDirection");

    // ServerBound = [0] -> To server
    // ClientBound = [1] -> To client
    public static final Object[] DIRECTIONS = ENUM_DIRECTION_CLAZZ.getEnumConstants();

    public static Object getChannel(UUID uuid, String address){
        List futures = NETWORK_MANAGERS_FIELD.get(SERVER_CONNECTION);

        Object future = futures.parallelStream().filter(ch -> {
            SocketAddress address1 = (SocketAddress) ADDRESS_FIELD.get(ch);
            return address.equalsIgnoreCase(parseAddress(address1));
        }).findFirst().orElse(null);

        return future;
    }

    private static String parseAddress(SocketAddress address) {
        return address.toString().split("/")[1].split(":")[0];
    }

    public static Map<EnumProtocolDirection, Map<Integer, Class<? extends Packet<?>>>> getPacketMap(int id) {
        Map<EnumProtocolDirection, Map<Integer, Class<? extends Packet<?>>>> map = new HashMap<>();

        Object enumProtocol = ENUM_PROTOCOLS[id];
        for (int i = 0; i < EnumProtocolDirection.values().length; i++) {
            Map<Integer, Class<? extends Packet<?>>> packetMap = new HashMap<>();

            Map map1 = PACKET_MAP_FIELD.get(enumProtocol);

            map1.forEach((enumDirection, biMap) -> {
                Object enumVar = enumDirection;

                //enumVar.
            });

        }
    }
}
