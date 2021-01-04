package cc.ghast.packet.buffer.types.minecraft;

import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import io.netty.buffer.ByteBuf;

public class LongLocationConverter extends BufConverter<BlockPosition> {


    public LongLocationConverter() {
        super("LongLocation", BlockPosition.class);
    }

    @Override
    public void write(MutableByteBuf buffer, BlockPosition value) {
        long var = ((long) value.getX() & BlockPosition.h) << BlockPosition.g
                | ((long) value.getY() & BlockPosition.i) << BlockPosition.f
                | ((long) value.getZ() & BlockPosition.j) << 0;

        buffer.writeLong(var);
    }

    @Override
    public BlockPosition read(MutableByteBuf buffer, ProtocolVersion version, Object... args) {
        long i = buffer.readLong();

        int x, y, z;

        if (version.isAbove(ProtocolVersion.V1_13_2)) {
            x = (int) (i >> 38);
            y = (int) (i & 0xFFF);
            z = (int) ((i << 38 >> 38) >> 12);
        } else {
            x = (int) (i << 64 - BlockPosition.g - BlockPosition.c >> 64 - BlockPosition.c);
            y = (int) (i << 64 - BlockPosition.f - BlockPosition.e >> 64 - BlockPosition.e);
            z = (int) (i << 64 - BlockPosition.d >> 64 - BlockPosition.d);
        }



        return new BlockPosition(x, y, z);
    }
}
