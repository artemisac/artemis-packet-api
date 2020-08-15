package cc.ghast.packet.chain;

import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.wrapper.packet.Packet;

public interface PacketListener {

    boolean[] async = {false};

    default void setAsync(boolean var){
        this.async[0] = var;
    }

    default boolean isAsync(){
        return this.async[0];
    }

    void onPacket(Profile profile, Packet<?> packet);
}
