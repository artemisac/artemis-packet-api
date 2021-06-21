package cc.ghast.packet.wrapper.packet.handshake;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketHandshakeLegacyServerListPing extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketHandshakeLegacyServerListPing(UUID player, ProtocolVersion version) {
        super("PacketHandshakingLegacyServerListPing", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }

}
