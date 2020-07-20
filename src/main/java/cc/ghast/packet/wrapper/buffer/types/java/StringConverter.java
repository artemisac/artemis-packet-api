package cc.ghast.packet.wrapper.buffer.types.java;

import cc.ghast.packet.wrapper.buffer.BufConverter;
import cc.ghast.packet.wrapper.buffer.types.Converters;
import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

/**
 * @author Myles
 * @since 01-May-20
 */
public class StringConverter extends BufConverter<String> {

    public StringConverter() {
        super("String", String.class);
    }

    private static final int maxJavaCharUtf8Length = Character.toString(Character.MAX_VALUE)
            .getBytes(StandardCharsets.UTF_8).length;

    @Override
    public void write(ByteBuf buffer, String value) {
        Preconditions.checkArgument(value.length() <= Short.MAX_VALUE,
                "Cannot send string longer than Short.MAX_VALUE (got %s characters)", value.length());

        byte[] b = value.getBytes(StandardCharsets.UTF_8);
        Converters.VAR_INT.write(buffer, b.length);
        buffer.writeBytes(b);
    }

    @Override
    public String read(ByteBuf buffer) {
        int len = Converters.VAR_INT.read(buffer);

        Preconditions.checkArgument(len <= Short.MAX_VALUE * maxJavaCharUtf8Length,
                "Cannot receive string longer than Short.MAX_VALUE * " + maxJavaCharUtf8Length + " bytes (got %s bytes)", len);

        String string = buffer.toString(buffer.readerIndex(), len, StandardCharsets.UTF_8);
        buffer.skipBytes(len);

        Preconditions.checkArgument(string.length() <= Short.MAX_VALUE,
                "Cannot receive string longer than Short.MAX_VALUE characters (got %s bytes)", string.length());

        return string;
    }
}
