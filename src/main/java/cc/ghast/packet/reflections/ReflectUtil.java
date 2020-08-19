package cc.ghast.packet.reflections;

import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.EnumProtocolCurrent;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.packet.Packet;
import com.google.common.collect.Maps;
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
    public static FieldAccessor<?> MINECRAFT_SERVER_FIELD = Reflection.getField(CRAFT_SERVER_CLAZZ, MINECRAFT_SERVER_CLAZZ, 0);
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
    public static final FieldAccessor<?> CHANNEL_FIELD = Reflection.getField(NETWORK_MANAGER_CLAZZ, "channel", 0);
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

    public static final FieldAccessor<Integer> ENUM_DIRECTION_ORDINAL_FIELD = Reflection.getField(ENUM_DIRECTION_CLAZZ, "ordinal", int.class);

    public static Object getChannel(UUID uuid, String address){
        List futures = NETWORK_MANAGERS_FIELD.get(SERVER_CONNECTION);

        Object future = futures.parallelStream().filter(ch -> {
            SocketAddress address1 = (SocketAddress) ADDRESS_FIELD.get(ch);
            String parsed = parseAddress(address1);
            return address.equalsIgnoreCase(parsed);
        }).findFirst().orElse(null);

        if (future != null) return CHANNEL_FIELD.get(future);
        return null;
    }

    private static String parseAddress(SocketAddress address) {
        return address.toString().split("/")[1].split(":")[0];
    }

    public static Map<ProtocolDirection, Map<Integer, Class<? extends Packet<?>>>> getPacketMap(EnumProtocol id) {
        // Create the map
        Map<ProtocolDirection, Map<Integer, Class<? extends Packet<?>>>> map = new HashMap<>();

        // Get the map from the id to match the Spigot enum protocol
        Object enumProtocol = ENUM_PROTOCOLS[id.getOrdinal()];

        // For every direction, we'll seek to getting all the values from it's map
        for (int i = 0; i < ProtocolDirection.values().length; i++) {

            ProtocolDirection direction = ProtocolDirection.values()[i];

            // Create a new map where we'll store the values
            Map<Integer, Class<? extends Packet<?>>> packetMap = new HashMap<>();

            // Get the map from the packet map
            Map map1 = PACKET_MAP_FIELD.get(enumProtocol);

            Map interest = (Map) map1.get(DIRECTIONS[i]);

            // Map can be nullable. Just skip if it is
            if (interest == null) continue;

            // For every value iterated, get the integer and the clazz and match the name
            interest.forEach((packetId, clazz) -> {
                // Grab the packet ID
                int packet = (int) packetId;

                // Grab the class
                Class claz = (Class) clazz;

                // Convert name to string. This won't unfortunately work with obfuscated spigots. If
                // You do obfuscate your spigots and rename the packets, it isn't my problem anymore.
                // This API already supports for itself to be obfuscated. Don't be too needy >:(
                String packetName = claz.getSimpleName();

                // Add it to the map
                packetMap.put(packet, id.getPacketClass(direction, packetName));
            });

            // Put the packet map in itself
            map.put(direction, packetMap);
        }

        // Return the map
        return map;
    }
}
