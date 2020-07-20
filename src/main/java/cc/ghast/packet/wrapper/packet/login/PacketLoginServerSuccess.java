package cc.ghast.packet.wrapper.packet.login;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;

import java.util.UUID;

public class PacketLoginServerSuccess extends Packet {
    public PacketLoginServerSuccess(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    @Override
    public void handle(ProtocolByteBuf byteBuf) {

    }
}
