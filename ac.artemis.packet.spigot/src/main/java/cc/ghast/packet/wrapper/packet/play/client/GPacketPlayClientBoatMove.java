package cc.ghast.packet.wrapper.packet.play.client;

import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.client.PacketPlayClientBoatMove;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Ghast
 * @since 31/10/2020
 * ArtemisPacket © 2020
 */

@Getter
@PacketLink(PacketPlayClientBoatMove.class)
public class GPacketPlayClientBoatMove extends GPacket implements ReadableBuffer {
    public GPacketPlayClientBoatMove(UUID player, ProtocolVersion version) {
        super("PacketPlayInBoatMove", player, version, e -> e.isOrAbove(ProtocolVersion.V1_9));
    }

    private boolean left;
    private boolean right;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.left = byteBuf.readBoolean();
        this.right = byteBuf.readBoolean();
    }
}