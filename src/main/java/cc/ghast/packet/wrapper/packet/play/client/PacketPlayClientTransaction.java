package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientTransaction extends Packet<ClientPacket> {
    public PacketPlayClientTransaction(UUID player, ProtocolVersion version) {
        super(player, version);
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
