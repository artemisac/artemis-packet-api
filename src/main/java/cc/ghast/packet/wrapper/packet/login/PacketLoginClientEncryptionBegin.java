package cc.ghast.packet.wrapper.packet.login;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;

import java.util.UUID;

public class PacketLoginClientEncryptionBegin extends Packet<ClientPacket> {
    public PacketLoginClientEncryptionBegin(UUID player, ProtocolVersion version) {
        super("PacketLoginInEncryptionBegin", player, version);
    }

    @Override
    public void handle(ProtocolByteBuf byteBuf) {

    }
}
