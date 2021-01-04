package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

/**
 * @author Ghast
 * @since 31/10/2020
 * ArtemisPacket © 2020
 */
@Getter
public class PacketPlayClientEffectBeaconSet extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientEffectBeaconSet(UUID player, ProtocolVersion version) {
        super("PacketPlaySetBeaconEffect", player, version, e -> e.isOrAbove(ProtocolVersion.V1_13));
    }


    @Override
    public void read(ProtocolByteBuf byteBuf) {

    }

}

