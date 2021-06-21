package cc.ghast.packet.wrapper.packet.login;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketLoginServerEncryptionBegin extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketLoginServerEncryptionBegin(UUID player, ProtocolVersion version) {
        super("PacketLoginOutEncryptionBegin", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
