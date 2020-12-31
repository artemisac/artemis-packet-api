package cc.ghast.packet.buffer.types.java;

import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
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
    public void write(MutableByteBuf buffer, Integer value) {
        buffer.writeInt(value);
    }

    @Override
    public Integer read(MutableByteBuf buffer, ProtocolVersion version, Object... args) {
        return buffer.readInt();
    }
}
