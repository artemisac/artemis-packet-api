package cc.ghast.packet.wrapper.packet.play.client;

import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.client.PacketPlayClientBlockMetadata;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayClientBlockMetadata.class)
public class GPacketPlayClientBlockMetadataQuery extends GPacket implements ReadableBuffer {
    public GPacketPlayClientBlockMetadataQuery(UUID player, ProtocolVersion version) {
        super("PacketPlayInBlockMetadataQuery", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
    }
}
