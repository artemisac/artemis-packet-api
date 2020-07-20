package cc.ghast.packet.buffer.types.java;

import cc.ghast.packet.buffer.BufConverter;
import io.netty.buffer.ByteBuf;

/**
 * @author Ghast
 * @since 01-May-20
 */
public class BooleanConverter extends BufConverter<Boolean> {

    public BooleanConverter() {
        super("Boolean", Boolean.class);
    }

    @Override
    public void write(ByteBuf buffer, Boolean value) {
        buffer.writeBoolean(value);
    }

    @Override
    public Boolean read(ByteBuf buffer) {
        return buffer.readBoolean();
    }
}
