package cc.ghast.packet.buffer.types.java;

import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
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
    public void write(MutableByteBuf buffer, Float value) {
        buffer.writeFloat(value);
    }

    @Override
    public Float read(MutableByteBuf buffer, ProtocolVersion version, Object... args) {
        return buffer.readFloat();
    }
}
