package cc.ghast.packet.buffer.types.java;

import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.nms.ProtocolVersion;
import io.netty.buffer.ByteBuf;

/**
 * @author Ghast
 * @since 01-May-20
 */
public class ByteConverter extends BufConverter<Byte> {

    public ByteConverter() {
        super("Byte", Byte.class);
    }

    @Override
    public void write(MutableByteBuf buffer, Byte value) {
        buffer.writeByte(value);
    }

    @Override
    public Byte read(MutableByteBuf buffer, ProtocolVersion version, Object... args) {
        return buffer.readByte();
    }
}
