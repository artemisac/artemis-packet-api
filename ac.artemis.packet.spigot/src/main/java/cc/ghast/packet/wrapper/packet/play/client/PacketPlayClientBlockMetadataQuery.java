package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientBlockMetadataQuery extends GPacket implements ReadableBuffer {
    public PacketPlayClientBlockMetadataQuery(UUID player, ProtocolVersion version) {
        super("PacketPlayInBlockMetadataQuery", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
    }
}
