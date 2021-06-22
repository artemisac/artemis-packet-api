package cc.ghast.packet.wrapper.packet.play.client;

import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.client.PacketPlayClientSpectate;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayClientSpectate.class)
public class GPacketPlayClientSpectate extends GPacket implements ReadableBuffer {
    public GPacketPlayClientSpectate(UUID player, ProtocolVersion version) {
        super("PacketPlayInSpectate", player, version);
    }

    private UUID entityId;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readUUID();
    }
}
