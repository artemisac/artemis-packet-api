package cc.ghast.packet.codec;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;

import java.util.List;
import java.util.zip.Inflater;

public class ArtemisDecompressor extends ByteToMessageDecoder {

    private final Inflater inflater;
    private final int compressionSize;

    public ArtemisDecompressor(int i) {
        this.compressionSize = i;
        this.inflater = new Inflater();
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() != 0) {
            ProtocolByteBuf packetdataserializer = new ProtocolByteBuf(byteBuf);
            int i = packetdataserializer.readVarInt();

            if (i == 0) {
                list.add(packetdataserializer.readBytes(packetdataserializer.readableBytes()));
            } else {
                if (i < this.compressionSize) {
                    throw new DecoderException("Badly compressed packet - size of " + i + " is below server threshold of " + this.compressionSize);
                }

                if (i > 2097152) {
                    throw new DecoderException("Badly compressed packet - size of " + i + " is larger than protocol maximum of " + 2097152);
                }

                byte[] abyte = new byte[packetdataserializer.readableBytes()];

                packetdataserializer.readBytes(abyte);
                this.inflater.setInput(abyte);
                byte[] abyte1 = new byte[i];

                this.inflater.inflate(abyte1);
                list.add(Unpooled.wrappedBuffer(abyte1));
                this.inflater.reset();
            }

        }
    }
}
