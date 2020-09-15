package cc.ghast.packet.utils;

import cc.ghast.packet.nms.ProtocolVersion;

/**
 * @author Ghast
 * @since 15/09/2020
 * ArtemisPacket © 2020
 */
public class PacketUtil {
    public static String BLOCK_PLACE = ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_11) ? "PacketPlayInBlockPlace" : "PacketPlayInUseItem";
}
