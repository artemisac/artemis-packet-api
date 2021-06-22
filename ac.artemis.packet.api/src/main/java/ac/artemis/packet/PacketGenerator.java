package ac.artemis.packet;

import ac.artemis.packet.protocol.ProtocolDirection;
import ac.artemis.packet.protocol.ProtocolState;
import ac.artemis.packet.wrapper.Packet;
import ac.artemis.packet.wrapper.PacketInfo;

public interface PacketGenerator {
    Packet getPacketFromId(final ProtocolDirection direction, final ProtocolState protocol, final int id);
}
