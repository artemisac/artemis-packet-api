package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerBlockChangeMulti extends GPacket implements ReadableBuffer {
    public PacketPlayServerBlockChangeMulti(UUID player, ProtocolVersion version) {
        super("PacketPlayOutMultiBlockChange", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
