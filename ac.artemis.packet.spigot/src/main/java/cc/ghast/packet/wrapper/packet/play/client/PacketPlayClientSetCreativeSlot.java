package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Getter
public class PacketPlayClientSetCreativeSlot extends GPacket implements ReadableBuffer {
    public PacketPlayClientSetCreativeSlot(UUID player, ProtocolVersion version) {
        super("PacketPlayInSetCreativeSlot", player, version);
    }

    private int slot;
    private ItemStack item;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.slot = byteBuf.readShort();
        this.item = byteBuf.readItem();
    }
}
