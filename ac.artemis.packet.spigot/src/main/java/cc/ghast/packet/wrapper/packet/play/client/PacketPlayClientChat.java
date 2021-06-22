package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;

import java.util.UUID;


@Getter
public class PacketPlayClientChat extends GPacket implements ReadableBuffer {

    public PacketPlayClientChat(UUID player, ProtocolVersion version) {
        super("PacketPlayInChat", player, version);
    }

    private String message;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        if (version.isOrBelow(ProtocolVersion.V1_8_9)) {
            this.message = byteBuf.readString();
        } else {
            this.message = byteBuf.readStringBuf(100);
        }

    }
}
