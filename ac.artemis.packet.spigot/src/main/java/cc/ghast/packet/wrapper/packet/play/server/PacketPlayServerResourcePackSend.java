package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerResourcePackSend extends Packet<ServerPacket> implements ReadableBuffer, WriteableBuffer {
    public PacketPlayServerResourcePackSend(UUID player, ProtocolVersion version) {
        super("PacketPlayOutResourcePackSend", player, version);
    }

    public PacketPlayServerResourcePackSend(String url, String hash) {
        super("PacketPlayOutResourcePackSend");
        this.url = url;
        this.hash = hash;
    }

    private String url;
    private String hash;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.url = byteBuf.readStringBuf(32767);
        this.hash = byteBuf.readStringBuf(40);
    }

    @Override
    public void write(ProtocolByteBuf byteBuf) {
        byteBuf.writeString(this.url);
        byteBuf.writeString(this.hash);
    }
}
