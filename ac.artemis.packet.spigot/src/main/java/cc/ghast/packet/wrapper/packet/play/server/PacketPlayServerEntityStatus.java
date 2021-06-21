package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerEntityStatus extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerEntityStatus(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntityStatus", player, version);
    }

    private int entityId;
    private byte logicOpcode;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readInt();
        this.logicOpcode = byteBuf.readByte();
    }
}
