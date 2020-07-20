package cc.ghast.packet.wrapper.buffer.types.java;

import cc.ghast.packet.wrapper.buffer.BufConverter;
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
    public void write(ByteBuf buffer, Short value) {
        buffer.writeShort(value);
    }

    @Override
    public Short read(ByteBuf buffer) {
        return buffer.readShort();
    }
}
