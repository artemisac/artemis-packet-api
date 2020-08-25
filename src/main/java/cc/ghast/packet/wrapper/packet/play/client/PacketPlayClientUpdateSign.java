package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

@Getter
public class PacketPlayClientUpdateSign extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientUpdateSign(UUID player, ProtocolVersion version) {
        super("PacketPlayInUpdateSign", player, version);
    }

    private BlockPosition location;
    private String[] values;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        // 1.7.10
        if (version.isBelow(ProtocolVersion.V1_8)) {
            final int x = byteBuf.readInt();
            final int y = byteBuf.readByte();
            final int z = byteBuf.readInt();
            this.location = new BlockPosition(x, y, z);
        }

        // 1.8+
        else {
            this.location = byteBuf.readBlockPositionFromLong();
        }

        // Values
        for(int i = 0; i < 4; ++i) {
            this.values[i] = byteBuf.readStringBuf(384);
        }

    }
}
