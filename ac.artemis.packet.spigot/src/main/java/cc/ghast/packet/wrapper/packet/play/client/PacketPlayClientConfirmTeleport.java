package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientConfirmTeleport extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientConfirmTeleport(UUID player, ProtocolVersion version) {
        super("PacketPlayInTeleportAccept", player, version, e -> e.isOrAbove(ProtocolVersion.V1_9));
    }

    private int teleportId;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.teleportId = byteBuf.readVarInt();
    }
}
