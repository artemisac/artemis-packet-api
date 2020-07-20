package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerKeepAlive extends Packet {
    public PacketPlayServerKeepAlive(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    private int id;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.id = byteBuf.readVarInt();
    }
}
