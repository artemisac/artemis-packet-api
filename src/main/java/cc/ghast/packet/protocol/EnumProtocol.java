package cc.ghast.packet.protocol;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.nms.*;
import cc.ghast.packet.wrapper.packet.Packet;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public interface EnumProtocol {
    Packet<?> getPacket(ProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) throws IllegalAccessException, InvocationTargetException, InstantiationException;
    int getPacketId(ProtocolDirection direction, Packet<?> packet);
    Class<? extends Packet<?>> getPacketClass(ProtocolDirection direction, String name);
    int getOrdinal();
    EnumProtocol[] getValues();

    static EnumProtocol[] getProtocolByVersion(ProtocolVersion version) {
        /*
         * These protocol versions are named by floored versions. By such I define the lowest version with a change
         * This means that for example, if 1.8 changes the packets and 1.8.8 has the same implementation, the version
         * name of the class will correspond to 1.8's, not 1.8.8's as 1.8.8 just uses the previous protocol.
         *
         * /!\ As of now, only *major* protocols are supported. If you wish for there to be experimental protocols
         * to be implemented, please do it yourself and open a Pull Request on github. It's really not hard.
         */
        switch (version) {
            /*
             * All protocol versions between 1.7.10 and itself have the same protocol
             */
            case V1_7_10: return EnumProtocol_5.values();
            /*
             * All protocol versions between 1.8 and 1.8.9 have the same protocol. Such protocol
             * is almost identical to 1.7.10 (5) however this one contains a couple new packets.
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
            case V1_14: return EnumProtocol_477.values();
            case V1_14_1:
            case V1_14_2:
            case V1_14_3:
            case V1_14_4:
            case V1_15:
            case V1_15_1:
            case V1_15_2:
            /*
             * Fallback protocol
             */
            default: return null;
        }
    }
}
