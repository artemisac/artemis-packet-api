package cc.ghast.packet.listener.injector;

import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.listener.callback.LoginCallback;
import cc.ghast.packet.utils.PacketCallback;
import ac.artemis.packet.spigot.wrappers.GPacket;

import java.util.UUID;
import java.util.function.Consumer;

public interface Injector {

    String clientBound = "artemis_client";
    String serverBound = "artemis_server";
    String encoder = "artemis_encoder";

    void injectReader();
    void uninjectReader();
    void injectFuturePlayer(Profile profile);
    void uninjectFuturePlayer(Profile profile);
    void injectPlayer(Profile uuid);
    void uninjectPlayer(UUID uuid);
    Profile getProfile(UUID uuid);
    boolean contains(Profile profile);

    void addLoginCallback(LoginCallback loginCallback);
    void removeLoginCallback(LoginCallback loginCallback);
    void callLoginCallbacks(Profile profile);

    void writePacket(UUID target, GPacket packet, Consumer<PacketCallback> callback);
}
