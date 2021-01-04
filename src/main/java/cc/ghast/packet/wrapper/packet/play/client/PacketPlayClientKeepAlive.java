package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientKeepAlive extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientKeepAlive(UUID player, ProtocolVersion version) {
        super("PacketPlayInKeepAlive", player, version);
    }

    private long id;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        if (version.isBelow(ProtocolVersion.V1_12)) {
            this.id = byteBuf.readVarInt();
        } else {
            this.id = byteBuf.readLong();
        }
    }
}
