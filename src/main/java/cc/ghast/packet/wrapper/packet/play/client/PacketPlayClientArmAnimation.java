package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;

import java.util.UUID;

public class PacketPlayClientArmAnimation extends Packet {
    public PacketPlayClientArmAnimation(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        // Lolz this is empty
    }
}
