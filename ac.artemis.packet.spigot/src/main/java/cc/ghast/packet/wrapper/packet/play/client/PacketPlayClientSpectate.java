package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientSpectate extends GPacket implements ReadableBuffer {
    public PacketPlayClientSpectate(UUID player, ProtocolVersion version) {
        super("PacketPlayInSpectate", player, version);
    }

    private UUID entityId;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readUUID();
    }
}
