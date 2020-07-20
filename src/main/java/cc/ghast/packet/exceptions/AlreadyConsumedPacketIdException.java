package cc.ghast.packet.exceptions;

import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.protocol.EnumProtocolDirection;

public class AlreadyConsumedPacketIdException extends RuntimeException {

    public AlreadyConsumedPacketIdException(EnumProtocolDirection dir, Class<? extends Packet> packet, int exist) {
        super(dir + " packet " + packet + " is already known to ID " + exist);
    }
}
