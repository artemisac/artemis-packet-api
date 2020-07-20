package cc.ghast.packet.buffer.types;

import cc.ghast.packet.buffer.types.exclusive.BytePoolConverter;
import cc.ghast.packet.buffer.types.exclusive.StringPoolConverter;
import cc.ghast.packet.buffer.types.exclusive.VarIntConverter;
import cc.ghast.packet.buffer.types.exclusive.VarLongConverter;
import cc.ghast.packet.buffer.types.java.*;
import cc.ghast.packet.buffer.types.minecraft.ItemConverter;
import cc.ghast.packet.buffer.types.minecraft.LongLocationConverter;
import cc.ghast.packet.buffer.types.minecraft.NBTCompoundConverter;
import cc.ghast.packet.protocol.EnumProtocol;
import io.netty.util.AttributeKey;

/**
 * @author Ghast
 * @since 09-May-20
 */
public class Converters {
    public static final AttributeKey<EnumProtocol> PROTOCOL_ATTRIBUTE = AttributeKey.valueOf("artemis_protocol");
    public static final ByteConverter BYTE = new ByteConverter();
    public static final BytesConverter BYTES = new BytesConverter();
    public static final DoubleConverter DOUBLE = new DoubleConverter();
    public static final IntegerConverter INTEGER = new IntegerConverter();
    public static final LongConverter LONG = new LongConverter();
    public static final StringConverter STRING = new StringConverter();

    public static final VarIntConverter VAR_INT = new VarIntConverter();
    public static final VarLongConverter VAR_LONG = new VarLongConverter();
    public static final BytePoolConverter BYTE_POOL = new BytePoolConverter();
    public static final StringPoolConverter STRING_POOL = new StringPoolConverter();

    public static final UUIDConverter UUID = new UUIDConverter();

    public static final NBTCompoundConverter NBT = new NBTCompoundConverter();
    public static final ItemConverter ITEM = new ItemConverter();

    public static final LongLocationConverter LOCATION_LONG = new LongLocationConverter();

}
