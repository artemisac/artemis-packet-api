package cc.ghast.packet.wrapper.buffer.types.java;

import cc.ghast.packet.wrapper.buffer.BufConverter;
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
    public Double read(ByteBuf buffer) {
        return buffer.readDouble();
    }
}
