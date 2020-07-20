package cc.ghast.packet.buffer.types.exclusive;

import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.buffer.types.Converters;
import cc.ghast.packet.wrapper.codec.BytePool;
import io.netty.buffer.ByteBuf;


public class BytePoolConverter extends BufConverter<BytePool> {
    public BytePoolConverter() {
        super("BytePool", BytePool.class);
    }

    @Override
    public void write(ByteBuf buffer, BytePool value) {
        Converters.VAR_INT.write(buffer, value.getVar());
        buffer.writeBytes(value.getData());
    }

    @Override
    public BytePool read(ByteBuf buffer, Object... args) {
        int varint = Converters.VAR_INT.read(buffer);
        byte[] abyte = new byte[varint];
        buffer.readBytes(abyte);
        return new BytePool(abyte, varint);
    }
}
