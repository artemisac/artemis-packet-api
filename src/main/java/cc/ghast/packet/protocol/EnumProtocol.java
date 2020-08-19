package cc.ghast.packet.protocol;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.Packet;

import java.util.UUID;

public interface EnumProtocol {
    Packet<?> getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version);
}
