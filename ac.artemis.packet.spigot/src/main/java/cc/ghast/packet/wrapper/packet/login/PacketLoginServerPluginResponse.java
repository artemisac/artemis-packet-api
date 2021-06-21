package cc.ghast.packet.wrapper.packet.login;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.ServerPacket;

import java.util.UUID;

public class PacketLoginServerPluginResponse extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketLoginServerPluginResponse(UUID player, ProtocolVersion version) {
        super("PacketLoginOutPluginResponse", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
