package cc.ghast.packet.profile;

import cc.ghast.packet.PacketAPI;
import cc.ghast.packet.PacketManager;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.protocol.EnumProtocol;
import lombok.Data;

import java.net.InetAddress;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Ghast
 * @since 15/08/2020
 * Artemis © 2020
 */

@Data
public class Profile {
    private UUID uuid;
    private String address;
    private ProtocolVersion version;
    private Object channel;
    private EnumProtocol protocol;

    public Profile(UUID uuid, String address, Object channel) {
        this.uuid = uuid;
        this.address = address;
        this.channel = channel;
    }

    public EnumProtocol getProtocol() {
        return protocol;
    }

    public ProtocolVersion getVersion() {

        if (uuid == null) {
            return ProtocolVersion.getGameVersion();
        }
        /*
         * If the version is null, lets attempt to cache it. In this scenario, it is already cached, hence
         * we can return it without concern.
         */
        if (version != null) {
            return version;
        }

        final boolean viaVersion = PacketManager.INSTANCE.getHookManager().getViaVersionHook() != null;

        /*
         * The ViaVersion protocol is null, this means we can cache the game version
         * This will *not* work very well with ProtocolSupport. But f*ck protocolsupport for now.
         */
        if (!viaVersion) {
            return ProtocolVersion.getGameVersion();
        }
        /*
         * The ViaVersion protocol is not null but we don't know if the user is injected in or not.
         * Hence, the version will be -1 if he is not. So if the value retrieved is superior to 0
         * we can henceforth cache it, else we just return regular game version
         */
        final int versionFromVia = PacketManager.INSTANCE.getHookManager().getViaVersionHook().getVersion(uuid);

        /*
         * Version is not yet retrieved, do not cache
         */
        if (versionFromVia < 0) {
            return ProtocolVersion.getGameVersion();
        }

        /*
         * Version is valid, we cache it
         */
        return (version = ProtocolVersion.getVersion(versionFromVia));
    }

    public void setProtocol(EnumProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return Objects.equals(uuid, profile.uuid) &&
                Objects.equals(address, profile.address) &&
                version == profile.version &&
                Objects.equals(channel, profile.channel) &&
                Objects.equals(protocol, profile.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, address, version, channel, protocol);
    }
}
