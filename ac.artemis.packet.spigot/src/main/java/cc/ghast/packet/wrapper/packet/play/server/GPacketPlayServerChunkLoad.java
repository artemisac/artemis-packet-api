package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.spigot.utils.ServerUtil;
import ac.artemis.packet.wrapper.server.PacketPlayServerChunkLoad;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.mc.ExtendedBlockStorage;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@PacketLink(PacketPlayServerChunkLoad.class)
public class GPacketPlayServerChunkLoad extends GPacket implements PacketPlayServerChunkLoad, ReadableBuffer, WriteableBuffer {
    public GPacketPlayServerChunkLoad(UUID player, ProtocolVersion version) {
        super("PacketPlayOutMapChunk", player, version);
    }

    private int x;
    private int z;
    private ChunkMap chunkMap;
    private boolean overworld;

    private boolean ignoreOldData;

    private long[] readBitSetLongs(ProtocolByteBuf byteBuf) {
        final ProtocolVersion serverVersion = ServerUtil.getGameVersion();

        if (serverVersion.isOrAbove(ProtocolVersion.V_1_17)) {
            //Read primary bit mask
//            return byteBuf.readLongArray();
            // TODO: long array
            return null;
        } else if (serverVersion.isOrAbove(ProtocolVersion.V1_9)) {
            //Read primary bit mask
            return new long[]{byteBuf.readVarInt()};
        } else {
            //Read primary bit mask
            return new long[]{byteBuf.readUnsignedShort()};
        }
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        ProtocolVersion serverVersion = ServerUtil.getGameVersion();

        int chunkX = byteBuf.readInt();
        int chunkZ = byteBuf.readInt();

        boolean checkFullChunk = serverVersion.isBelow(ProtocolVersion.V_1_17);

        boolean fullChunk = !checkFullChunk || byteBuf.readBoolean();

        if (serverVersion == ProtocolVersion.V1_16 || serverVersion == ProtocolVersion.V1_16_1) {
            ignoreOldData = byteBuf.readBoolean();
        }

        BitSet chunkMask = serverVersion.isOrAbove(ProtocolVersion.V_1_18) ? null :
                BitSet.valueOf(readBitSetLongs(byteBuf));

        boolean hasHeightMaps = serverVersion.isOrAbove(ProtocolVersion.V1_14);

        if (hasHeightMaps) {
            //...
        }

        // 1.7 sends a secondary bit mask for the block metadata
        BitSet secondaryChunkMask = null;
        if (serverVersion.isOrAbove(ProtocolVersion.V1_7_10)) {
            secondaryChunkMask = BitSet.valueOf(readBitSetLongs(byteBuf));
        }

        int chunkSize = 16;
        if (serverVersion.isOrAbove(ProtocolVersion.V_1_17)) {
//            chunkSize = user.getTotalWorldHeight() >> 4;
            // TODO: chunkSize on +1.17
        }

        boolean hasBiomeData = fullChunk && serverVersion.isAbove(ProtocolVersion.V_1_18);
        boolean bytesInsteadOfInts = serverVersion.isAbove(ProtocolVersion.V1_13);

        int[] biomeDataInts = null;
        byte[] biomeDataBytes = null;

        if (hasBiomeData && serverVersion.isOrAbove(ProtocolVersion.V1_16_2)) {
            biomeDataInts = readVarIntArray(byteBuf);
        } else if (hasBiomeData && serverVersion.isOrAbove(ProtocolVersion.V1_15)) {
            biomeDataInts = new int[1024];
            for (int i = 0; i < biomeDataInts.length; i++) {
                biomeDataInts[i] = byteBuf.readInt();
            }
        }

        byte[] data = byteBuf.readBytes(byteBuf.readableBytes()).array();
        // todo: deflate data array on 1.7

        //...
    }

    public int[] readVarIntArray(ProtocolByteBuf byteBuf) {
        int readableBytes = byteBuf.readableBytes();
        int size = byteBuf.readVarInt();
        if (size > readableBytes) {
            throw new IllegalStateException("VarIntArray with size " + size + " is bigger than allowed " + readableBytes);
        }

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = byteBuf.readVarInt();
        }
        return array;
    }

    @Override
    public void write(ProtocolByteBuf byteBuf) {
        byteBuf.writeInt(this.x);
        byteBuf.writeInt(this.z);
        byteBuf.writeBoolean(this.overworld);
        byteBuf.writeShort((short)(this.chunkMap.dataSize & 65535));
        byteBuf.writeBytes(this.chunkMap.data);
    }

    public static ChunkMap serialize(ExtendedBlockStorage[] aextendedblockstorage, byte[] biomeArray, boolean multiBiome, boolean skyLight, int size) {
        ChunkMap chunkMap = new ChunkMap();
        List<ExtendedBlockStorage> list = new ArrayList<>();

        for (int i = 0; i < aextendedblockstorage.length; ++i) {
            ExtendedBlockStorage extendedblockstorage = aextendedblockstorage[i];

            if (extendedblockstorage != null && (!multiBiome || !extendedblockstorage.isEmpty()) && (size & 1 << i) != 0) {
                chunkMap.dataSize |= 1 << i;
                list.add(extendedblockstorage);
            }
        }

        chunkMap.data = new byte[func_180737_a(Integer.bitCount(chunkMap.dataSize), skyLight, multiBiome)];
        int index = 0;

        for (ExtendedBlockStorage storage : list) {
            char[] achar = storage.getData();

            for (char c0 : achar) {
                chunkMap.data[index++] = (byte)(c0 & 255);
                chunkMap.data[index++] = (byte)(c0 >> 8 & 255);
            }
        }

        for (ExtendedBlockStorage storage : list) {
            index = copy(storage.getBlocklightArray().getData(), chunkMap.data, index);
        }

        if (skyLight) {
            for (ExtendedBlockStorage storage : list) {
                index = copy(storage.getSkylightArray().getData(), chunkMap.data, index);
            }
        }

        if (multiBiome) {
            copy(biomeArray, chunkMap.data, index);
        }

        return chunkMap;
    }

    private static int copy(byte[] from, byte[] to, int length) {
        System.arraycopy(from, 0, to, length, from.length);
        return length + from.length;
    }

    protected static int func_180737_a(int p_180737_0_, boolean p_180737_1_, boolean p_180737_2_)
    {
        int i = p_180737_0_ * 2 * 16 * 16 * 16;
        int j = p_180737_0_ * 16 * 16 * 16 / 2;
        int k = p_180737_1_ ? p_180737_0_ * 16 * 16 * 16 / 2 : 0;
        int l = p_180737_2_ ? 256 : 0;
        return i + j + k + l;
    }

    public static class ChunkMap {
        public byte[] data;
        public int dataSize;
    }
}
