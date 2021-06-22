package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.mc.PlayerEnums.Hand;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientArmAnimation extends GPacket implements ReadableBuffer {
    public PacketPlayClientArmAnimation(UUID player, ProtocolVersion version) {
        super("PacketPlayInArmAnimation", player, version);
    }

    private Hand hand;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        // Lolz this is empty
        if (version.isBelow(ProtocolVersion.V1_9)) {
            this.hand = Hand.MAIN_HAND;
        } else {
            this.hand = Hand.values()[byteBuf.readVarInt()];
        }
    }


}
