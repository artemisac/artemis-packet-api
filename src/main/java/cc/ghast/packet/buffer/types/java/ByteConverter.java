package cc.ghast.packet.buffer.types.java;

import cc.ghast.packet.buffer.BufConverter;
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
    public void write(ByteBuf buffer, Byte value) {
        buffer.writeByte(value);
    }

    @Override
    public Byte read(ByteBuf buffer, Object... args) {
        return buffer.readByte();
    }
}
