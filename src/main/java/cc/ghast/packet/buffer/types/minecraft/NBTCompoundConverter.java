package cc.ghast.packet.buffer.types.minecraft;

import cc.ghast.packet.buffer.BufConverter;
import com.github.steveice10.opennbt.NBTIO;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author Myles
 * @since 01-May-20
 */
public class NBTCompoundConverter extends BufConverter<CompoundTag> {
    public NBTCompoundConverter() {
        super(CompoundTag.class);
    }

    @Override
    public void write(ByteBuf buffer, CompoundTag value) throws IOException {
        if (value == null) {
            buffer.writeByte(0);
        } else {
            ByteBufOutputStream bytebufStream = new ByteBufOutputStream(buffer);
            NBTIO.writeTag((DataOutput) bytebufStream, value);
        }
    }

    @Override
    public CompoundTag read(ByteBuf buffer, Object... args) throws IOException {
        Preconditions.checkArgument(buffer.readableBytes() <= 2097152,
                "Cannot read NBT (got %s bytes)", buffer.readableBytes());
        int readerIndex = buffer.readerIndex();
        byte b = buffer.readByte();
        if (b == 0) {
            return null;
        } else {
            buffer.readerIndex(readerIndex);
            return (CompoundTag) NBTIO.readTag((DataInput) new ByteBufInputStream(buffer));
        }
    }
}