package cc.ghast.packet.wrapper.packet;

import cc.ghast.packet.buffer.ProtocolByteBuf;

public interface Writeable {
    ProtocolByteBuf write();
}
