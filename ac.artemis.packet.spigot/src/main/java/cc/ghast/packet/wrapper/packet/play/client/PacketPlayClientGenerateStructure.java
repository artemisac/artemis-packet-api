package cc.ghast.packet.wrapper.packet.play.client;

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
public class PacketPlayClientGenerateStructure extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayClientGenerateStructure(UUID player, ProtocolVersion version) {
        super("PacketPlayOutTradeList", player, version, e -> e.isOrAbove(ProtocolVersion.V1_16));
    }


    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }

}

