package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.server.PacketPlayServerBlockChangeMulti;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerBlockChangeMulti.class)
public class GPacketPlayServerBlockChangeMulti extends GPacket implements ReadableBuffer {
    public GPacketPlayServerBlockChangeMulti(UUID player, ProtocolVersion version) {
        super("PacketPlayOutMultiBlockChange", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }
}
