package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerTransaction extends Packet<ServerPacket> implements ReadableBuffer, WriteableBuffer {
    public PacketPlayServerTransaction(UUID player, ProtocolVersion version) {
        super("PacketPlayOutTransaction", player, version);
    }

    public PacketPlayServerTransaction(UUID player, ProtocolVersion version, byte windowId, short actionNumber, boolean accepted) {
        super("PacketPlayOutTransaction", player, version);
        this.windowId = windowId;
        this.actionNumber = actionNumber;
        this.accepted = accepted;
    }

    public PacketPlayServerTransaction(byte windowId, short actionNumber, boolean accepted) {
        super("PacketPlayOutTransaction");
        this.windowId = windowId;
        this.actionNumber = actionNumber;
        this.accepted = accepted;
    }

    private byte windowId;
    private short actionNumber;
    private boolean accepted;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.windowId = byteBuf.readByte();
        this.actionNumber = byteBuf.readShort();
        this.accepted = byteBuf.readBoolean();
    }

    @Override
    public void write(ProtocolByteBuf byteBuf) {
        byteBuf.writeByte(windowId);
        byteBuf.writeShort(actionNumber);
        byteBuf.writeBoolean(accepted);
    }
}
