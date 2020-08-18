package cc.ghast.packet.buffer.types.java;

import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import io.netty.buffer.ByteBuf;

/**
 * @author Ghast
 * @since 01-May-20
 */
public class ShortConverter extends BufConverter<Short> {

    public ShortConverter() {
        super("Short", Short.class);
    }

    @Override
    public void write(MutableByteBuf buffer, Short value) {
        buffer.writeShort(value);
    }

    @Override
    public Short read(MutableByteBuf buffer, Object... args) {
        return buffer.readShort();
    }
}
