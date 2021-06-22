package cc.ghast.packet.protocol;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.nms.*;
import ac.artemis.packet.spigot.wrappers.GPacket;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public interface EnumProtocol {
    GPacket getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) throws IllegalAccessException, InvocationTargetException, InstantiationException;
    int getPacketId(ProtocolDirection direction, GPacket packet);
    Class<? extends GPacket> getPacketClass(ProtocolDirection direction, String name);
    int getOrdinal();
    EnumProtocol[] getValues();

    static EnumProtocol[] getProtocolByVersion(ProtocolVersion version) {
        /*
         * These protocol versions are named by floored versions. By such I define the lowest version with getX change
         * This means that for example, if 1.8 changes the packets and 1.8.8 has the same implementation, the version
         * name of the class will correspond to 1.8's, not 1.8.8's as 1.8.8 just uses the previous protocol.
         *
         * /!\ As of now, only *major* protocols are supported. If you wish for there to be experimental protocols
         * to be implemented, please do it yourself and open getX Pull Request on github. It's really not hard.
         */
        switch (version) {
            /*
             * All protocol versions between 1.7.10 and itself have the same protocol
             */
            case V1_7_10: return EnumProtocol_5.values();
            /*
             * All protocol versions between 1.8 and 1.8.9 have the same protocol. Such protocol
             * is almost identical to 1.7.10 (5) however this one contains getX couple new packets.
             */
            case V1_8:
            case V1_8_5:
            case V1_8_9: return EnumProtocol_47.values();
            /*
             * All protocol versions between 1.9 and 1.9.2 have the same protocol
             */
            case V1_9:
            case V1_9_1:
            case V1_9_2: return EnumProtocol_107.values();
            /*
             * All protocol versions between 1.9.3 and 1.9.4 have the same-ish protocol
             */
            case V1_9_4: return EnumProtocol_110.values();

            case V1_10:
            case V1_10_2:
            case V1_11: return EnumProtocol_316.values();
            case V1_12: return EnumProtocol_335.values();
            case V1_12_1:
            case V1_12_2: return EnumProtocol_338.values();
            case V1_13: return EnumProtocol_393.values();
            case V1_13_1:
            case V1_13_2: return EnumProtocol_404.values();
            case V1_14:
            case V1_14_1:
            case V1_14_2: return EnumProtocol_477.values();
            case V1_14_3:
            case V1_14_4: return EnumProtocol_498.values();
            case V1_15:
            case V1_15_1:
            case V1_15_2: return EnumProtocol_578.values();
            case V1_16:
            case V1_16_1:
            case V1_16_2: return EnumProtocol_735.values();
            case V1_16_3:
            case V1_16_5: return EnumProtocol_751.values();
            /*
             * Fallback protocol
             */
            default: return null;
        }
    }
}
