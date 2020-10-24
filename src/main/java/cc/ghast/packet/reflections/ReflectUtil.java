package cc.ghast.packet.reflections;

import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.EnumProtocolCurrent;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.nbt.WrappedItem;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import cc.ghast.packet.wrapper.netty.MutableByteBufInputStream;
import cc.ghast.packet.wrapper.netty.MutableByteBufOutputStream;
import cc.ghast.packet.wrapper.netty.input.NettyUtil;
import cc.ghast.packet.wrapper.packet.Packet;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketAddress;
import java.util.*;

/**
 * @author Ghast
 * @since 17/08/2020
 * Artemis © 2020
 */
public class ReflectUtil {

    /**
     * Util designated to help with some random object stuff. Jeez 1.7 is a pain :/
     */
    private static final NettyUtil NETTY_UTIL = NettyUtil.getInstance();

    /*
        Minecraft Server field
     */
    private static final Class<?> MINECRAFT_SERVER_CLAZZ = Reflection.getMinecraftClass("MinecraftServer");
    private static final Class<?> CRAFT_SERVER_CLAZZ = Reflection.getCraftBukkitClass("CraftServer");
    private static final FieldAccessor<?> MINECRAFT_SERVER_FIELD = Reflection.getField(CRAFT_SERVER_CLAZZ, MINECRAFT_SERVER_CLAZZ, 0);
    private static final Object MINECRAFT_SERVER = MINECRAFT_SERVER_FIELD.get(Bukkit.getServer());

    /*
        Minecraft Connection Field
     */

    private static final Class<?> SERVER_CONNECTION_CLAZZ = Reflection.getMinecraftClass("ServerConnection");
    private static final FieldAccessor<?> SERVER_CONNECTION_FIELD = Reflection.getField(MINECRAFT_SERVER_CLAZZ, SERVER_CONNECTION_CLAZZ, 0);
    private static final Object SERVER_CONNECTION = SERVER_CONNECTION_FIELD.get(MINECRAFT_SERVER);

    /*
        Minecraft Manager Field
     */
    private static final Class<?> NETWORK_MANAGER_CLAZZ = Reflection.getMinecraftClass("NetworkManager");
    private static final FieldAccessor<List> NETWORK_MANAGERS_FIELD = Reflection.getField(SERVER_CONNECTION_CLAZZ, List.class, 1);
    private static final FieldAccessor<?> CHANNEL_FIELD = Reflection.getField(NETWORK_MANAGER_CLAZZ, "channel", 0);
    /*
        Socket Field
     */
    private static final FieldAccessor<SocketAddress> ADDRESS_FIELD = Reflection.getField(NETWORK_MANAGER_CLAZZ, SocketAddress.class, 0);


    /*
        Enum Protocol Class
     */
    private static final Class<?> ENUM_PROTOCOL_CLAZZ = Reflection.getMinecraftClass("EnumProtocol");
    private static final Object[] ENUM_PROTOCOLS = ENUM_PROTOCOL_CLAZZ.getEnumConstants();
    private static final FieldAccessor<Map> PACKET_MAP_FIELD = Reflection.getField(ENUM_PROTOCOL_CLAZZ, Map.class, 1);

    /*
        Enum Direction Class
     */
    private static final Class<?> ENUM_DIRECTION_CLAZZ = Reflection.getMinecraftClass("EnumProtocolDirection");

    // ServerBound = [0] -> To server
    // ClientBound = [1] -> To client
    private static final Object[] DIRECTIONS = ENUM_DIRECTION_CLAZZ.getEnumConstants();

    private static final FieldAccessor<Integer> ENUM_DIRECTION_ORDINAL_FIELD = Reflection.getField(ENUM_DIRECTION_CLAZZ, "ordinal", int.class);

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
            if (interest == null){
                map.put(direction, packetMap);
                continue;
            }

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

    /*
        NBT READING/WRITING
     */

    private static final Class<?> NBT_READ_LIMITER_CLAZZ = Reflection.getMinecraftClass("NBTReadLimiter");
    private static final ConstructorInvoker NBT_READ_LIMITER_CONSTRUCTOR = Reflection.getConstructor(NBT_READ_LIMITER_CLAZZ, long.class);

    private static final Class<?> NBT_TOOLS_CLAZZ = Reflection.getMinecraftClass("NBTCompressedStreamTools");

    private static final Class<?> NBT_COMPOUND_CLAZZ = Reflection.getMinecraftClass("NBTTagCompound");
    private static final MethodInvoker NBT_COMPOUND_READ_FROM_BYTEBUF = Reflection.getMethod(NBT_TOOLS_CLAZZ, NBT_COMPOUND_CLAZZ,
            0, DataInput.class, NBT_READ_LIMITER_CLAZZ);

    /**
     * Allows users to read compound tags directly from the buffer
     * @param stream ByteBufStream
     * @return NMS object of the compound tag
     */
    public static Object getCompoundTag(Object stream) {
        // Instantiate a new limiter
        Object threadLimiter = NBT_READ_LIMITER_CONSTRUCTOR.invoke(2097152L);
        // Invoke the object
        Object tag = null;

        try {
            tag = NBT_COMPOUND_READ_FROM_BYTEBUF.invoke(null, stream, threadLimiter);
        } catch (Exception e){
            // ignore
        }
        return tag;
    }

    private static final MethodInvoker WRITE_NBT_COMPOUND_TO_BYTEBUF = Reflection.getMethod(NBT_TOOLS_CLAZZ, void.class,
            0, NBT_COMPOUND_CLAZZ, DataOutput.class);

    public static void writeCompoundTag(Object compoundTag, MutableByteBufOutputStream stream){
        WRITE_NBT_COMPOUND_TO_BYTEBUF.invoke(null, compoundTag, stream);
    }

    private static final Class<?> CRAFT_ITEM_CLAZZ = Reflection.getCraftBukkitClass("inventory.CraftItemStack");
    private static final Class<?> ITEM_NMS_CLAZZ = Reflection.getMinecraftClass("ItemStack");
    private static final Class<?> ITEM_TYPE_CLAZZ = Reflection.getMinecraftClass("Item");

    private static final MethodInvoker GET_NBT_TAG_FROM_ITEMSTACK_METHOD = Reflection.getMethod(ITEM_NMS_CLAZZ, "getTag");

    private static final FieldAccessor<?> GET_HANDLE_ITEM = Reflection.getField(CRAFT_ITEM_CLAZZ, "handle", ITEM_NMS_CLAZZ);

    public static Object getCompoundTagFromItem(ItemStack stack) {
        Object nms = GET_HANDLE_ITEM.get(stack);
        return GET_NBT_TAG_FROM_ITEMSTACK_METHOD.invoke(stack);
    }

    private static final MethodInvoker GET_ITEM_FROM_ID_METHOD = Reflection.getMethod(ITEM_TYPE_CLAZZ, "getById", int.class);
    private static final ConstructorInvoker ITEM_NMS_CONSTRUCTOR = Reflection.getConstructor(ITEM_NMS_CLAZZ, ITEM_TYPE_CLAZZ, int.class, int.class);
    private static final MethodInvoker SET_DATA_METHOD = Reflection.getMethod(ITEM_NMS_CLAZZ, void.class, 0, NBT_COMPOUND_CLAZZ);
    private static final MethodInvoker AS_BUKKIT_COPY_METHOD = Reflection.getMethod(CRAFT_ITEM_CLAZZ, "asBukkitCopy", ITEM_NMS_CLAZZ);

    public static ItemStack getItemFromWrapper(WrappedItem item){
        Object id = GET_ITEM_FROM_ID_METHOD.invoke(null, item.getId());
        Object nmsItem = ITEM_NMS_CONSTRUCTOR.invoke(id, item.getAmount(), item.getData());
        try {
            SET_DATA_METHOD.invoke(nmsItem, item.getTag());
        } catch (Exception e) {
            // ignored
        }
        return (ItemStack) AS_BUKKIT_COPY_METHOD.invoke(null, nmsItem);
    }

    /*
        Player
     */

    private static final Class<?> CRAFT_PLAYER_CLAZZ = Reflection.getCraftBukkitClass("entity.CraftPlayer");
    private static final Class<?> NMS_PLAYER_CLAZZ = Reflection.getMinecraftClass("EntityPlayer");
    private static final MethodInvoker GET_HANDLE_METHOD = Reflection.getMethod(CRAFT_PLAYER_CLAZZ, "getHandle");
    private static final FieldAccessor<Integer> PING_FIELD = Reflection.getField(NMS_PLAYER_CLAZZ, "ping", int.class);

    public static int getPing(Player player) {
        Object nmsPlayer = GET_HANDLE_METHOD.invoke(player);
        return PING_FIELD.get(nmsPlayer);
    }

}
