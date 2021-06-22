package cc.ghast.packet.wrapper.packet.play.client;

import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.client.PacketPlayClientWindowClose;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayClientWindowClose.class)
public class GPacketPlayClientWindowClose extends GPacket implements ReadableBuffer {
    public GPacketPlayClientWindowClose(UUID player, ProtocolVersion version) {
        super("PacketPlayInCloseWindow", player, version);
    }

    private int windowId;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.windowId = byteBuf.readByte();
    }
}
