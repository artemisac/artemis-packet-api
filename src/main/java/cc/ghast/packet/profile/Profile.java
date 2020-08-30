package cc.ghast.packet.profile;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.EnumProtocol;
import lombok.Data;

import java.net.InetAddress;
import java.util.UUID;

/**
 * @author Ghast
 * @since 15/08/2020
 * Artemis © 2020
 */

@Data
public class Profile {
    private final UUID uuid;
    private final InetAddress address;
    private ProtocolVersion version;
    private Object channel;
    private EnumProtocol protocol;

    public Profile(UUID uuid, InetAddress address, ProtocolVersion version, Object channel) {
        this.uuid = uuid;
        this.address = address;
        this.version = version;
        this.channel = channel;
    }

    public EnumProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(EnumProtocol protocol) {
        this.protocol = protocol;
    }
}
