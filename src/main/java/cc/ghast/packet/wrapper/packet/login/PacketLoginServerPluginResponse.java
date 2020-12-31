package cc.ghast.packet.wrapper.packet.login;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketLoginServerSuccess extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketLoginServerSuccess(UUID player, ProtocolVersion version) {
        super("PacketLoginOutServerSuccess", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
