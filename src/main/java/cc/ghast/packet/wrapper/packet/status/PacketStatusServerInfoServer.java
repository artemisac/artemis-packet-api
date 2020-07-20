package cc.ghast.packet.wrapper.packet.status;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;

import java.util.UUID;

public class PacketStatusServerInfoServer extends Packet {

    public PacketStatusServerInfoServer(UUID player, ProtocolVersion version) {
        super(player, version);
    }


    @Override
    public void handle(ProtocolByteBuf byteBuf) {

    }
}
