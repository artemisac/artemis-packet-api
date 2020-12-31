package cc.ghast.packet.buffer.types.java;

import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
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
    public void write(MutableByteBuf buffer, Boolean value) {
        buffer.writeBoolean(value);
    }

    @Override
    public Boolean read(MutableByteBuf buffer, ProtocolVersion version, Object... args) {
        return buffer.readBoolean();
    }
}
