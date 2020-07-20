package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;

import java.util.UUID;

public class PacketPlayServerEntityMetadata extends Packet {
    public PacketPlayServerEntityMetadata(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    @Override
    public void handle(ProtocolByteBuf byteBuf) {

    }

}
