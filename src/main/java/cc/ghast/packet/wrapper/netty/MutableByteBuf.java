package cc.ghast.packet.wrapper.netty;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.netty.bytebuf.CurrentByteBuf;
import cc.ghast.packet.wrapper.netty.bytebuf.LegacyByteBuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

public interface MutableByteBuf {
    
    Object getParent();
    
    static MutableByteBuf translate(Object byteBuf) {
        return ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_8) ? new CurrentByteBuf(byteBuf) : new LegacyByteBuf(byteBuf);
    }
    
    int capacity() ;

    MutableByteBuf capacity(final int i) ;

    int maxCapacity() ;

    MutableByteBufAllocator alloc() ;

    ByteOrder order() ;

    MutableByteBuf order(final ByteOrder byteOrder) ;

    MutableByteBuf unwrap() ;

    boolean isDirect() ;

    boolean isReadOnly() ;

    MutableByteBuf asReadOnly() ;

    int readerIndex() ;

    MutableByteBuf readerIndex(final int i) ;

    int writerIndex() ;

    MutableByteBuf writerIndex(final int i) ;

    MutableByteBuf setIndex(final int i, final int i1) ;

    int readableBytes() ;

    int writableBytes() ;

    int maxWritableBytes() ;

    boolean isReadable() ;

    boolean isReadable(final int i) ;

    boolean isWritable() ;

    boolean isWritable(final int i) ;

    MutableByteBuf clear() ;

    MutableByteBuf markReaderIndex() ;

    MutableByteBuf resetReaderIndex() ;

    MutableByteBuf markWriterIndex() ;

    MutableByteBuf resetWriterIndex() ;

    MutableByteBuf discardReadBytes() ;

    MutableByteBuf discardSomeReadBytes() ;

    MutableByteBuf ensureWritable(final int i) ;

    int ensureWritable(final int i, final boolean b) ;

    boolean getBoolean(final int i) ;

    byte getByte(final int i) ;

    short getUnsignedByte(final int i) ;

    short getShort(final int i) ;

    short getShortLE(final int i) ;

    int getUnsignedShort(final int i) ;

    int getUnsignedShortLE(final int i) ;

    int getMedium(final int i) ;

    int getMediumLE(final int i) ;

    int getUnsignedMedium(final int i) ;

    int getUnsignedMediumLE(final int i) ;

    int getInt(final int i) ;

    int getIntLE(final int i) ;

    long getUnsignedInt(final int i) ;

    long getUnsignedIntLE(final int i) ;

    long getLong(final int i) ;

    long getLongLE(final int i) ;

    char getChar(final int i) ;

    float getFloat(final int i) ;

    double getDouble(final int i) ;

    MutableByteBuf getBytes(final int i, final MutableByteBuf byteBuf) ;

    MutableByteBuf getBytes(final int i, final MutableByteBuf byteBuf, final int i1) ;

    MutableByteBuf getBytes(final int i, final MutableByteBuf byteBuf, final int i1, final int i2) ;

    MutableByteBuf getBytes(final int i, final byte[] bytes) ;

    MutableByteBuf getBytes(final int i, final byte[] bytes, final int i1, final int i2) ;

    MutableByteBuf getBytes(final int i, final ByteBuffer byteBuffer) ;

    MutableByteBuf getBytes(final int i, final OutputStream outputStream, final int i1) throws IOException ;

    int getBytes(final int i, final GatheringByteChannel gatheringByteChannel, final int i1) throws IOException ;

    int getBytes(final int i, final FileChannel fileChannel, final long l, final int i1) throws IOException ;

    CharSequence getCharSequence(final int i, final int i1, final Charset charset) ;

    MutableByteBuf setBoolean(final int i, final boolean b) ;

    MutableByteBuf setByte(final int i, final int i1) ;

    MutableByteBuf setShort(final int i, final int i1) ;

    MutableByteBuf setShortLE(final int i, final int i1) ;

    MutableByteBuf setMedium(final int i, final int i1) ;

    MutableByteBuf setMediumLE(final int i, final int i1) ;

    MutableByteBuf setInt(final int i, final int i1) ;

    MutableByteBuf setIntLE(final int i, final int i1) ;

    MutableByteBuf setLong(final int i, final long l) ;

    MutableByteBuf setLongLE(final int i, final long l) ;

    MutableByteBuf setChar(final int i, final int i1) ;

    MutableByteBuf setFloat(final int i, final float v) ;

    MutableByteBuf setDouble(final int i, final double v) ;

    MutableByteBuf setBytes(final int i, final MutableByteBuf byteBuf) ;

    MutableByteBuf setBytes(final int i, final MutableByteBuf byteBuf, final int i1) ;

    MutableByteBuf setBytes(final int i, final MutableByteBuf byteBuf, final int i1, final int i2) ;

    MutableByteBuf setBytes(final int i, final byte[] bytes) ;

    MutableByteBuf setBytes(final int i, final byte[] bytes, final int i1, final int i2) ;

    MutableByteBuf setBytes(final int i, final ByteBuffer byteBuffer) ;

    int setBytes(final int i, final InputStream inputStream, final int i1) throws IOException ;

    int setBytes(final int i, final ScatteringByteChannel scatteringByteChannel, final int i1) throws IOException ;

    int setBytes(final int i, final FileChannel fileChannel, final long l, final int i1) throws IOException ;

    MutableByteBuf setZero(final int i, final int i1) ;

    int setCharSequence(final int i, final CharSequence charSequence, final Charset charset) ;

    boolean readBoolean() ;

    byte readByte() ;

    short readUnsignedByte() ;

    short readShort() ;

    short readShortLE() ;

    int readUnsignedShort() ;

    int readUnsignedShortLE() ;

    int readMedium() ;

    int readMediumLE() ;

    int readUnsignedMedium() ;

    int readUnsignedMediumLE() ;

    int readInt() ;

    int readIntLE() ;

    long readUnsignedInt() ;

    long readUnsignedIntLE() ;

    long readLong() ;

    long readLongLE() ;

    char readChar() ;

    float readFloat() ;

    double readDouble() ;

    MutableByteBuf readBytes(final int i) ;

    MutableByteBuf readSlice(final int i) ;

    MutableByteBuf readRetainedSlice(final int i) ;

    MutableByteBuf readBytes(final MutableByteBuf byteBuf) ;

    MutableByteBuf readBytes(final MutableByteBuf byteBuf, final int i) ;

    MutableByteBuf readBytes(final MutableByteBuf byteBuf, final int i, final int i1) ;

    MutableByteBuf readBytes(final byte[] bytes) ;

    MutableByteBuf readBytes(final byte[] bytes, final int i, final int i1) ;

    MutableByteBuf readBytes(final ByteBuffer byteBuffer) ;

    MutableByteBuf readBytes(final OutputStream outputStream, final int i) throws IOException ;

    int readBytes(final GatheringByteChannel gatheringByteChannel, final int i) throws IOException ;

    CharSequence readCharSequence(final int i, final Charset charset) ;

    int readBytes(final FileChannel fileChannel, final long l, final int i) throws IOException ;

    MutableByteBuf skipBytes(final int i) ;

    MutableByteBuf writeBoolean(final boolean b) ;

    MutableByteBuf writeByte(final int i) ;

    MutableByteBuf writeShort(final int i) ;

    MutableByteBuf writeShortLE(final int i) ;

    MutableByteBuf writeMedium(final int i) ;

    MutableByteBuf writeMediumLE(final int i) ;

    MutableByteBuf writeInt(final int i) ;

    MutableByteBuf writeIntLE(final int i) ;

    MutableByteBuf writeLong(final long l) ;

    MutableByteBuf writeLongLE(final long l) ;

    MutableByteBuf writeChar(final int i) ;

    MutableByteBuf writeFloat(final float v) ;

    MutableByteBuf writeDouble(final double v) ;

    MutableByteBuf writeBytes(final MutableByteBuf byteBuf) ;

    MutableByteBuf writeBytes(final MutableByteBuf byteBuf, final int i) ;

    MutableByteBuf writeBytes(final MutableByteBuf byteBuf, final int i, final int i1) ;

    MutableByteBuf writeBytes(final byte[] bytes) ;

    MutableByteBuf writeBytes(final byte[] bytes, final int i, final int i1) ;

    MutableByteBuf writeBytes(final ByteBuffer byteBuffer) ;

    int writeBytes(final InputStream inputStream, final int i) throws IOException ;

    int writeBytes(final ScatteringByteChannel scatteringByteChannel, final int i) throws IOException ;

    int writeBytes(final FileChannel fileChannel, final long l, final int i) throws IOException ;

    MutableByteBuf writeZero(final int i) ;

    int writeCharSequence(final CharSequence charSequence, final Charset charset) ;

    int indexOf(final int i, final int i1, final byte b) ;

    int bytesBefore(final byte b) ;

    int bytesBefore(final int i, final byte b) ;

    int bytesBefore(final int i, final int i1, final byte b) ;

    int forEachByte(final MutableByteProcessor byteProcessor) ;

    int forEachByte(final int i, final int i1, final MutableByteProcessor byteProcessor) ;

    int forEachByteDesc(final MutableByteProcessor byteProcessor) ;

    int forEachByteDesc(final int i, final int i1, final MutableByteProcessor byteProcessor) ;

    MutableByteBuf copy() ;

    MutableByteBuf copy(final int i, final int i1) ;

    MutableByteBuf slice() ;

    MutableByteBuf retainedSlice() ;

    MutableByteBuf slice(final int i, final int i1) ;

    MutableByteBuf retainedSlice(final int i, final int i1) ;

    MutableByteBuf duplicate() ;

    MutableByteBuf retainedDuplicate() ;

    int nioBufferCount() ;

    ByteBuffer nioBuffer() ;

    ByteBuffer nioBuffer(final int i, final int i1) ;

    ByteBuffer internalNioBuffer(final int i, final int i1) ;

    ByteBuffer[] nioBuffers() ;

    ByteBuffer[] nioBuffers(final int i, final int i1) ;

    boolean hasArray() ;

    byte[] array() ;

    int arrayOffset() ;

    boolean hasMemoryAddress() ;

    long memoryAddress() ;

    String toString(final Charset charset) ;

    String toString(final int i, final int i1, final Charset charset) ;

    int hashCode() ;

    boolean equals(final Object o) ;

    int compareTo(final MutableByteBuf byteBuf) ;

    String toString() ;

    MutableByteBuf retain(final int i) ;

    MutableByteBuf retain() ;

    MutableByteBuf touch() ;

    MutableByteBuf touch(final Object o) ;

    int refCnt() ;

    boolean release() ;

    boolean release(final int i) ;

    MutableByteBuf getByteBuf() ;
}
