package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientKeepAlive extends Packet {
    public PacketPlayClientKeepAlive(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    private int id;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.id = byteBuf.readVarInt();
    }
}
