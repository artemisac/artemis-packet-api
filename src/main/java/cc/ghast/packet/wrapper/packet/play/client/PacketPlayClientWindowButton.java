package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Getter
public class PacketPlayClientWindowButton extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientWindowButton(UUID player, ProtocolVersion version) {
        super("PacketPlayInWindowButton", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }


}
