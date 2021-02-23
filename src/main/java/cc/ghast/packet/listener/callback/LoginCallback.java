package cc.ghast.packet.listener.callback;

import cc.ghast.packet.profile.Profile;

public interface LoginCallback {
    void onLogin(final Profile profile);
}
