package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;

import java.util.UUID;

public class PacketPlayServerEntityHeadRotation extends GPacket implements ReadableBuffer {
    public PacketPlayServerEntityHeadRotation(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntityHeadRotation", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
