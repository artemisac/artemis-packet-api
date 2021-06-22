package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientTransaction extends GPacket implements ReadableBuffer {
    public PacketPlayClientTransaction(UUID player, ProtocolVersion version) {
        super("PacketPlayInTransaction", player, version);
    }

    private byte windowId;
    private short actionNumber;
    private boolean accepted;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.windowId = byteBuf.readByte();
        this.actionNumber = byteBuf.readShort();
        this.accepted = byteBuf.readByte() != 0;
    }
}
