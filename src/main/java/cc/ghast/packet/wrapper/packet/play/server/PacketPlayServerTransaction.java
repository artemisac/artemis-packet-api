package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerTransaction extends Packet<ServerPacket> {
    public PacketPlayServerTransaction(UUID player, ProtocolVersion version) {
        super("PacketPlayOutTransaction", player, version);
    }

    private byte windowId;
    private short actionNumber;
    private boolean accepted;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.windowId = byteBuf.readByte();
        this.actionNumber = byteBuf.readShort();
        this.accepted = byteBuf.readBoolean();
    }
}
