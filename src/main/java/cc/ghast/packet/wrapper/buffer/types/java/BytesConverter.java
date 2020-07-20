package cc.ghast.packet.wrapper.buffer.types.java;

import cc.ghast.packet.wrapper.buffer.BufConverter;
import io.netty.buffer.ByteBuf;

/**
 * @author Ghast
 * @since 01-May-20
 */
public class BytesConverter extends BufConverter<ByteBuf> {

    public BytesConverter() {
        super("ByteArray", ByteBuf.class);
    }

    @Override
    public void write(ByteBuf buffer, ByteBuf value) {
        buffer.writeBytes(value);
    }

    @Override
    public ByteBuf read(ByteBuf buffer) {
        return buffer.readBytes(buffer.readableBytes());
    }
}
