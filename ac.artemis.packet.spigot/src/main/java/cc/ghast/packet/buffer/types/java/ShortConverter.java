package cc.ghast.packet.buffer.types.java;

import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.nms.ProtocolVersion;
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
    public Short read(MutableByteBuf buffer, ProtocolVersion version, Object... args) {
        return buffer.readShort();
    }
}
