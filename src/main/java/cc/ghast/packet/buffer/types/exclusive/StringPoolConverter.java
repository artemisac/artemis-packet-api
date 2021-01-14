package cc.ghast.packet.buffer.types.exclusive;

import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.buffer.types.Converters;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.codec.StringPool;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import com.google.common.base.Charsets;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;


public class StringPoolConverter extends BufConverter<StringPool> {
    public StringPoolConverter() {
        super("StringPool", StringPool.class);
    }

    @Override
    public void write(MutableByteBuf buffer, StringPool value) {
        byte[] abyte = value.getData().getBytes(Charsets.UTF_8);
        if (abyte.length > 32767) {
            throw new EncoderException("String too big (was " + value.getData().length() + " bytes encoded, max " + 32767 + ")");
        } else {
            Converters.VAR_INT.write(buffer, abyte.length);
            buffer.writeBytes(abyte);
        }
    }

    @Override
    public StringPool read(MutableByteBuf buffer, ProtocolVersion version, Object... args) {
        if (args.length < 1) throw new DecoderException("The received string is supposed to have a size");
        int i = (int) args[0];
        int j = Converters.VAR_INT.read(buffer, version);
        if (j > i * 4) {
            throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + j + " > " + i * 4 + ")");
        }

        if (j < 0) {
            throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
        }

        String s = new String(buffer.readBytes(j).array(), Charsets.UTF_8);
        if (s.length() > i) {
            throw new DecoderException("The received string length is longer than maximum allowed (" + j + " > " + i + ")");
        }

        return new StringPool(s, j);
    }
}
