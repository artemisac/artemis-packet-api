package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.buffer.types.Converters;
import cc.ghast.packet.nms.EnumDirection;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.bukkit.Vector3D;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Getter
public class PacketPlayClientBlockMetadataQuery extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientBlockMetadataQuery(UUID player, ProtocolVersion version) {
        super("PacketPlayInBlockMetadataQuery", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
    }
}
