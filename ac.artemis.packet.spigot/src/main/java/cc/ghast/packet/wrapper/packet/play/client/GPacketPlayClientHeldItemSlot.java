package cc.ghast.packet.wrapper.packet.play.client;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientItemHeldSlot;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayClientItemHeldSlot.class)
public class GPacketPlayClientHeldItemSlot extends GPacket implements PacketPlayClientItemHeldSlot, ReadableBuffer {

    private short slot;

    public GPacketPlayClientHeldItemSlot(UUID player, ProtocolVersion version) {
        super("PacketPlayInHeldItemSlot", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.slot = byteBuf.readShort();
    }
}
