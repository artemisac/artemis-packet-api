package cc.ghast.packet.wrapper.packet.status;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketStatusClientStart extends GPacket implements ReadableBuffer {
    public PacketStatusClientStart(UUID player, ProtocolVersion version) {
        super("PacketStatusInStart", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
