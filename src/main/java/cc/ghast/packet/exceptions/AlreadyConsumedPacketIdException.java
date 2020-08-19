package cc.ghast.packet.exceptions;

import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.packet.Packet;

public class AlreadyConsumedPacketIdException extends RuntimeException {

    public AlreadyConsumedPacketIdException(ProtocolDirection dir, Class<? extends Packet> packet, int exist) {
        super(dir + " packet " + packet + " is already known to ID " + exist);
    }
}
