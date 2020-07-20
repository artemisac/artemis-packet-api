package cc.ghast.packet.wrapper.buffer.types.java;

import cc.ghast.packet.wrapper.buffer.BufConverter;
import io.netty.buffer.ByteBuf;

/**
 * @author Ghast
 * @since 01-May-20
 */
public class IntegerConverter extends BufConverter<Integer> {

    public IntegerConverter() {
        super("Integer", Integer.class);
    }

    @Override
    public void write(ByteBuf buffer, Integer value) {
        buffer.writeInt(value);
    }

    @Override
    public Integer read(ByteBuf buffer) {
        return buffer.readInt();
    }
}
