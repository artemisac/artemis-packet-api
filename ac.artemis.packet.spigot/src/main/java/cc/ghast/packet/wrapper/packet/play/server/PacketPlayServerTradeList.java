package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Ghast
 * @since 31/10/2020
 * ArtemisPacket © 2020
 */
@Getter
public class PacketPlayServerTradeList extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerTradeList(UUID player, ProtocolVersion version) {
        super("PacketPlayOutTradeList", player, version, e -> e.isOrAbove(ProtocolVersion.V1_13));
    }


    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }

}

