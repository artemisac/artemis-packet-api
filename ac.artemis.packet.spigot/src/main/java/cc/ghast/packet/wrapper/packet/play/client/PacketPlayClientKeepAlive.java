package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientKeepAlive extends GPacket implements ReadableBuffer {
    public PacketPlayClientKeepAlive(UUID player, ProtocolVersion version) {
        super("PacketPlayInKeepAlive", player, version);
    }

    private long id;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        if (version.isBelow(ProtocolVersion.V1_12_2)) {
            this.id = byteBuf.readVarInt();
        } else {
            this.id = byteBuf.readLong();
        }
    }
}
