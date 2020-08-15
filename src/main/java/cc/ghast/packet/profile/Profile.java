package cc.ghast.packet.profile;

import cc.ghast.packet.nms.ProtocolVersion;
import io.netty.channel.Channel;
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
    private Channel channel;

    public Profile(UUID uuid, InetAddress address, ProtocolVersion version, Channel channel) {
        this.uuid = uuid;
        this.address = address;
        this.version = version;
        this.channel = channel;
    }
}
