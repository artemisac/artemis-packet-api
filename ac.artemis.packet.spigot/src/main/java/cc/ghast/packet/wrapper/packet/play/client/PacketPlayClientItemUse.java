package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
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
public class PacketPlayClientItemUse extends GPacket implements ReadableBuffer {
    public PacketPlayClientItemUse(UUID player, ProtocolVersion version) {
        super("PacketPlayInBlockPlace", player, version, e -> e.isOrAbove(ProtocolVersion.V1_9));
    }

    private PlayerEnums.Hand hand;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        // Lolz this is empty
        if (version.isBelow(ProtocolVersion.V1_9)) {
            this.hand = PlayerEnums.Hand.MAIN_HAND;
        } else {
            this.hand = PlayerEnums.Hand.values()[byteBuf.readVarInt()];
        }
    }

}

