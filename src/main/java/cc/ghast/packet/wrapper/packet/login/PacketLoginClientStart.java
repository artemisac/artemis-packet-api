package cc.ghast.packet.wrapper.packet.login;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;

import java.util.UUID;

public class PacketLoginClientStart extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketLoginClientStart(UUID player, ProtocolVersion version) {
        super("PacketLoginInClientStart", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
