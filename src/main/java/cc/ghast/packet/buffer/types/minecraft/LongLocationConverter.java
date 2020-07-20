package cc.ghast.packet.buffer.types.minecraft;

import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import io.netty.buffer.ByteBuf;

public class LongLocationConverter extends BufConverter<BlockPosition> {


    public LongLocationConverter() {
        super("LongLocation", BlockPosition.class);
    }

    @Override
    public void write(ByteBuf buffer, BlockPosition value) {
        long var = ((long) value.getX() & BlockPosition.h) << BlockPosition.g
                | ((long) value.getY() & BlockPosition.i) << BlockPosition.f
                | ((long) value.getZ() & BlockPosition.j) << 0;

        buffer.writeLong(var);
    }

    @Override
    public BlockPosition read(ByteBuf buffer, Object... args) {
        long i = buffer.readLong();
        int j = (int) (i << 64 - BlockPosition.g - BlockPosition.c >> 64 - BlockPosition.c);
        int k = (int) (i << 64 - BlockPosition.f - BlockPosition.e >> 64 - BlockPosition.e);
        int l = (int) (i << 64 - BlockPosition.d >> 64 - BlockPosition.d);

        return new BlockPosition(j, k, l);
    }
}
