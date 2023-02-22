package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.spigot.utils.ServerUtil;
import ac.artemis.packet.wrapper.server.PacketPlayServerChunkLoad;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.mc.ExtendedBlockStorage;
import cc.ghast.packet.wrapper.mc.chunk.NetStreamInput;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;

import java.io.ByteArrayInputStream;
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
    private boolean overworld;

    private boolean ignoreOldData;

    // forwarding variables
    private int chunkX, chunkZ;

    private byte[] data;
    private long[] bitSet;

    private int[] biomeDataInts;
    private byte[] biomeDataBytes;

    private boolean fullChunk;

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

        long[] bitSet = readBitSetLongs(byteBuf);

        BitSet chunkMask = serverVersion.isOrAbove(ProtocolVersion.V_1_18) ? null : BitSet.valueOf(bitSet);

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

        boolean hasBlocklight = (serverVersion.isOrAbove(ProtocolVersion.V1_16) || serverVersion.isBelow(ProtocolVersion.V1_14))
                && !serverVersion.isOrAbove(ProtocolVersion.V1_8_9);

        boolean checkForSky = serverVersion.isOrAbove(ProtocolVersion.V1_16)
                || serverVersion.isOrAbove(ProtocolVersion.V1_8_9)
                || getPlayer().getWorld().getEnvironment().getId() == 0;

        NetStreamInput dataIn = serverVersion.isOrAbove(ProtocolVersion.V1_9)
                ? new NetStreamInput(new ByteArrayInputStream(data))
                : null;

        // BaseChunk[] chunks = getChunkReader().read(user.getDimension(), chunkMask, secondaryChunkMask,
        //         fullChunk, hasBlocklight, checkForSky, chunkSize, data, dataIn);

        // todo: tile entities 1.9+

        // todo: skylight shit (1.18+)

        // set forwarding variables (used in bear)
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;

        this.data = data;
        this.bitSet = bitSet;

        this.biomeDataInts = biomeDataInts;
        this.biomeDataBytes = biomeDataBytes;

        this.fullChunk = fullChunk;
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
    }
}
