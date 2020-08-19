package cc.ghast.packet.wrapper.packet.login;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;

import java.util.UUID;

public class PacketLoginServerSuccess extends Packet<ServerPacket> {
    public PacketLoginServerSuccess(UUID player, ProtocolVersion version) {
        super("PacketLoginOutServerSuccess", player, version);
    }

    @Override
    public void handle(ProtocolByteBuf byteBuf) {

    }
}
