package cc.ghast.packet.buffer.types.java;

import cc.ghast.packet.buffer.BufConverter;
import io.netty.buffer.ByteBuf;

/**
 * @author Ghast
 * @since 01-May-20
 */
public class DoubleConverter extends BufConverter<Double> {

    public DoubleConverter() {
        super("Double", Double.class);
    }

    @Override
    public void write(ByteBuf buffer, Double value) {
        buffer.writeDouble(value);
    }

    @Override
    public Double read(ByteBuf buffer, Object... args) {
        return buffer.readDouble();
    }
}
