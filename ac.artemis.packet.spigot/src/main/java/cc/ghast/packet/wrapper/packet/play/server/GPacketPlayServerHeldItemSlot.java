package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.server.PacketPlayServerItemHeldSlot;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerItemHeldSlot.class)
public class GPacketPlayServerHeldItemSlot extends GPacket implements PacketPlayServerItemHeldSlot, ReadableBuffer, WriteableBuffer {

    private byte slot;

    public GPacketPlayServerHeldItemSlot(UUID player, ProtocolVersion version) {
        super("PacketPlayOutHeldItemSlot", player, version);
    }

    public GPacketPlayServerHeldItemSlot(byte slot) {
        super("PacketPlayOutHeldItemSlot");

        this.slot = slot;
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.slot = byteBuf.readByte();
    }

    @Override
    public void write(ProtocolByteBuf byteBuf) {
        byteBuf.writeByte(this.slot);
    }
}
