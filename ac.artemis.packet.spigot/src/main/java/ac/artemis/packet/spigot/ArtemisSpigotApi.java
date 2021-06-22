package ac.artemis.packet.spigot;

import ac.artemis.packet.PacketAPI;
import ac.artemis.packet.PacketListener;
import ac.artemis.packet.callback.LoginCallback;
import ac.artemis.packet.callback.PacketCallback;
import ac.artemis.packet.profile.Profile;
import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.protocol.format.WrittenEnumProtocol;
import ac.artemis.packet.spigot.utils.Accessor;
import ac.artemis.packet.wrapper.Packet;

import java.util.UUID;
import java.util.function.Consumer;

public class ArtemisSpigotApi extends Accessor implements PacketAPI {
    public ArtemisSpigotApi(ArtemisSpigotPlugin plugin) {
        super(plugin);
    }

    @Override
    public void create() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void addListener(PacketListener listener) {

    }

    @Override
    public void addProtocol(ProtocolVersion version, WrittenEnumProtocol writtenEnumProtocol) {

    }

    @Override
    public Profile getProfile(UUID uuid) {
        return null;
    }

    @Override
    public boolean isInjected(UUID uuid) {
        return false;
    }

    @Override
    public void disinject(UUID player) {

    }

    @Override
    public ProtocolVersion getVersion(UUID uuid) {
        return null;
    }

    @Override
    public void sendPacket(UUID player, Packet packet) {

    }

    @Override
    public void sendPacket(UUID player, Packet packet, Consumer<PacketCallback> callback) {

    }

    @Override
    public void addLoginCallback(LoginCallback loginCallback) {

    }

    @Override
    public void removeLoginCallback(LoginCallback loginCallback) {

    }
}
