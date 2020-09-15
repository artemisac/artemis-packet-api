package cc.ghast.packet.compat;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.ProtocolDirection;

public interface PacketModifier {
    ProtocolByteBuf modify(Profile profile, ProtocolDirection direction, ProtocolByteBuf byteBuf);
}
