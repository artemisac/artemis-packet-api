package cc.ghast.packet.wrapper.packet.handshake;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GPacketHandshakeLegacyServerListPing extends GPacket implements ReadableBuffer {
    public GPacketHandshakeLegacyServerListPing(UUID player, ProtocolVersion version) {
        super("PacketHandshakingLegacyServerListPing", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }

}
