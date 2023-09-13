package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.server.PacketPlayServerChunkLoadBulk;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.bukkit.Dimension;
import cc.ghast.packet.wrapper.mc.chunk.ChunkDataWrapper;
import cc.ghast.packet.wrapper.mc.chunk.ColumnDataWrapper;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Hydrogen, retrooper and PacketEvents contributors
 * <a href="https://github.com/retrooper/packetevents/tree/2.0">original source</a>
 */

@Getter
@PacketLink(PacketPlayServerChunkLoadBulk.class)
public class GPacketPlayServerChunkLoadBulk extends GPacket implements PacketPlayServerChunkLoadBulk, ReadableBuffer {
    public GPacketPlayServerChunkLoadBulk(UUID player, ProtocolVersion version) {
        super("PacketPlayOutMapChunkBulk", player, version);
    }

    private int columns;

    private int[] x;
    private int[] z;

    private int[] mask;

    private int[] chunks;

    private byte[][] data;

    private boolean skylight;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        // todo: 1.7 and post 1.8 implementation

        if (version.isOrBelow(ProtocolVersion.V1_7_10)) {
            System.out.println("GPacketPlayServerChunkLoadBulk: read on unsupported protocol version (legacy)");
        } else if (version.isOrBelow(ProtocolVersion.V1_8_9)) {
            read1_8(byteBuf);
        } else {
            System.out.println("GPacketPlayServerChunkLoadBulk: read on unsupported protocol version (modern)");
        }
    }

    private void read1_8(ProtocolByteBuf byteBuf) {
        boolean skylight = byteBuf.readBoolean();
        int columns = byteBuf.readVarInt();

        this.x = new int[columns];
        this.z = new int[columns];

        this.columns = columns;
        this.skylight = skylight;

        ChunkDataWrapper[] data = new ChunkDataWrapper[columns];

        this.chunks = new int[columns];
        this.mask = new int[columns];

        this.data = new byte[columns][];

        for (int column = 0; column < columns; column++) {
            this.x[column] = byteBuf.readInt();
            this.z[column] = byteBuf.readInt();

            int mask = byteBuf.readUnsignedShort();
            int chunks = Integer.bitCount(mask);
            int length = (chunks * ((4096 * 2) + 2048)) + (skylight ? chunks * 2048 : 0);

            byte[] dat = new byte[length];

            this.mask[column] = mask;
            this.chunks[column] = chunks;

            data[column] = new ChunkDataWrapper(mask, true, skylight, dat);
        }

        for (int column = 0; column < columns; column++) {
            data[column].setData(byteBuf.readBytes(data[column].getData().length).array());

            this.data[column] = data[column].getData();

//            Dimension dimension = Dimension.valueOf(getPlayer().getWorld().getEnvironment().toString());
//            ColumnDataWrapper wrapper = new ColumnDataWrapper(dimension, data[column].getData(), data[column].getMask());
        }
    }
}
