package cc.ghast.packet.wrapper.packet.play.client;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.client.PacketPlayClientSetCreativeSlot;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.UUID;

@Getter
@PacketLink(PacketPlayClientSetCreativeSlot.class)
public class GPacketPlayClientSetCreativeSlot extends GPacket implements PacketPlayClientSetCreativeSlot, ReadableBuffer {
    public GPacketPlayClientSetCreativeSlot(UUID player, ProtocolVersion version) {
        super("PacketPlayInSetCreativeSlot", player, version);
    }

    private int slot;
    private Optional<ItemStack> item;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.slot = byteBuf.readShort();
        // TODO: This has a type of slot data of newer versions
        try {
            this.item = Optional.of(byteBuf.readItem());
        } catch (Exception ignore) {
            this.item = Optional.empty();
        }
    }
}
