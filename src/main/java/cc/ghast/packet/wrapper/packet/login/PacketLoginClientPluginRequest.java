package cc.ghast.packet.wrapper.packet.login;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketLoginClientPluginRequest extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketLoginClientPluginRequest(UUID player, ProtocolVersion version) {
        super("PacketLoginInPluginRequest", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
