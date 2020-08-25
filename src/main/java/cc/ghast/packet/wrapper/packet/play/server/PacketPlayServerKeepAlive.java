package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerKeepAlive extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerKeepAlive(UUID player, ProtocolVersion version) {
        super("PacketPlayOutKeepAlive", player, version);
    }

    private int id;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.id = byteBuf.readVarInt();
    }
}
