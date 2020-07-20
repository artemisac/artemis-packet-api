package cc.ghast.packet.buffer.types.java;

import cc.ghast.packet.buffer.BufConverter;
import io.netty.buffer.ByteBuf;

/**
 * @author Ghast
 * @since 01-May-20
 */
public class FloatConverter extends BufConverter<Float> {

    public FloatConverter() {
        super("Float", Float.class);
    }

    @Override
    public void write(ByteBuf buffer, Float value) {
        buffer.writeFloat(value);
    }

    @Override
    public Float read(ByteBuf buffer, Object... args) {
        return buffer.readFloat();
    }
}
