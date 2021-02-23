package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import lombok.Getter;

import java.util.UUID;
import java.util.function.Predicate;

@Getter
public class PacketPlayServerWindowClose extends Packet<ServerPacket> implements ReadableBuffer, WriteableBuffer {
    public PacketPlayServerWindowClose(UUID player, ProtocolVersion version) {
        super("PacketPlayOutCloseWindow", player, version);
    }

    public PacketPlayServerWindowClose(int windowId) {
        super("PacketPlayOutCloseWindow");
        this.windowId = windowId;
    }

    private int windowId;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.windowId = byteBuf.readUnsignedByte();
    }

    @Override
    public void write(ProtocolByteBuf byteBuf) {
        byteBuf.writeByte(windowId);
    }
}
