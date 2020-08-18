package cc.ghast.packet.protocol;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.UUID;

/**
 * @author Ghast
 * @since 18/08/2020
 * Artemis © 2020
 */

public enum ArtemisEnumProtocol implements IEnumProtocol {
    HANDSHAKE(-1),
    PLAY(0),
    STATUS(1),
    LOGIN(2);

    private final int id;

    ArtemisEnumProtocol(int id) {
        this.id = id;
        init();
    }


    @Override
    public void init() {

    }

    @Override
    public Packet<?> getPacket(EnumProtocolDirection direction, int id, UUID playerId, ProtocolVersion version) {
        return null;
    }
}
