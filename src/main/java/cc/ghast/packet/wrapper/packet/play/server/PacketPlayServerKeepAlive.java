package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import lombok.Getter;

import java.util.UUID;
import java.util.function.Predicate;

@Getter
public class PacketPlayServerKeepAlive extends Packet<ServerPacket> implements ReadableBuffer, WriteableBuffer {
    public PacketPlayServerKeepAlive(UUID player, ProtocolVersion version) {
        super("PacketPlayOutKeepAlive", player, version);
    }

    public PacketPlayServerKeepAlive(long id) {
        super("PacketPlayOutKeepAlive");
        this.id = id;
    }

    private long id;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        if (version.isOrAbove(ProtocolVersion.V1_12_2)) {
            this.id = byteBuf.readLong();
        } else {
            this.id = byteBuf.readVarInt();
        }
    }

    @Override
    public void write(ProtocolByteBuf byteBuf) {
        if (ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_12_2)) {
            byteBuf.writeLong(id);
        } else {
            byteBuf.writeVarInt((int) id);
        }

    }
}
