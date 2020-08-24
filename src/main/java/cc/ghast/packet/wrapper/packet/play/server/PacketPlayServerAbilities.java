package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerAbilities extends Packet<ServerPacket> {
    public PacketPlayServerAbilities(UUID player, ProtocolVersion version) {
        super("PacketPlayOutAbilities", player, version);
    }

    private boolean invulnerable;
    private boolean flying;
    private boolean allowedFlight;
    private boolean creativeMode;

    private float flySpeed;
    private float walkSpeed;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        // These are under a bit mask, see https://stackoverflow.com/questions/31575691/what-is-a-bitmask-and-a-mask
        byte flags = byteBuf.readByte();

        /*
            Creative Mode -> 0x01
            Flying -> 0x02
            Allowed Flight -> 0x04
            Invulnerable -> 0x08
         ------------------
            For those who don't understand why this works, basically we're reading binary: 0000 0001, 0000 0010
            The '&' symbol merges two bytes and UNIQUELY keeps the common bytes. If we take this into what's being done
            Here, basically we are placing a 1 on different scales. If the one is here, it's true. Simple yet complex.

        */
        this.creativeMode = (flags & 0x01) > 0;
        this.flying = (flags & 0x02) > 0;
        this.allowedFlight = (flags & 0x04) > 0;
        this.invulnerable = (flags & 0x08) > 0;

        this.flySpeed = byteBuf.readFloat();
        this.walkSpeed = byteBuf.readFloat();
    }
}
