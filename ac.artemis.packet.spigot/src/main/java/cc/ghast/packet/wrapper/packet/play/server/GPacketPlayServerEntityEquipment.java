package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.server.PacketPlayServerEntityEquipment;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerEntityEquipment.class)
public class GPacketPlayServerEntityEquipment extends GPacket implements PacketPlayServerEntityEquipment, ReadableBuffer {

    private int entityId;
    private short slot;
    private ItemStack item;

    public GPacketPlayServerEntityEquipment(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntityEquipment", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.slot = byteBuf.readShort();
        this.item = byteBuf.readItem();
    }
}
