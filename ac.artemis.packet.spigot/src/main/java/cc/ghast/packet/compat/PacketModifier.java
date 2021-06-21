package cc.ghast.packet.compat;

import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.buffer.ProtocolByteBuf;

public interface PacketModifier {
    ProtocolByteBuf modify(Profile profile, ProtocolDirection direction, ProtocolByteBuf byteBuf, int packetId);
}
