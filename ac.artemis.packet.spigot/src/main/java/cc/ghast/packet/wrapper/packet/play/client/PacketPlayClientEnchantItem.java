package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientEnchantItem extends GPacket implements ReadableBuffer {
    public PacketPlayClientEnchantItem(UUID player, ProtocolVersion version) {
        super("PacketPlayInEnchantItem", player, version, e -> e.isOrAbove(ProtocolVersion.V1_9));
    }

    private int windowId;
    private int button;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.windowId = byteBuf.readByte();
        this.button = byteBuf.readByte();
    }
}
