package cc.ghast.packet.exceptions;

import cc.ghast.packet.wrapper.packet.Packet;

/**
 * @author Ghast
 * @since 30/08/2020
 * Artemis © 2020
 */
public class InvalidPacketException extends RuntimeException {
    public InvalidPacketException(Class<? extends Packet> clazz){
        super("Packet of type " + clazz + " is not getX valid packet!");
    }
}
