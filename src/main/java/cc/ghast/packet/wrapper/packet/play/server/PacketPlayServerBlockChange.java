package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketPlayServerBlockChange extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerBlockChange(UUID player, ProtocolVersion version) {
        super("PacketPlayOutBlockChange", player, version);
    }

    private BlockPosition position;
    private int blockId;
    @Override
    public void read(ProtocolByteBuf byteBuf) {
        if (version.isBelow(ProtocolVersion.V1_8)) {
            int x = byteBuf.readInt();
            int y = byteBuf.readUnsignedByte();
            int z = byteBuf.readInt();
            this.position = new BlockPosition(x, y, z);
        }
        else {
            this.position = byteBuf.readBlockPositionFromLong();
        }

        this.blockId =   byteBuf.readVarInt();
    }
}
