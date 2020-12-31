package cc.ghast.packet.compat;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ghast
 * @since 28/12/2020
 * ArtemisPacket © 2020
 */

@Getter
public class HookManager {
    private final List<PacketModifier> modifiers = new ArrayList<>();
    private ViaVersionHook viaVersionHook;

    public HookManager() {
        this.init();
    }

    public void init() {
        final boolean viaversion = Bukkit.getPluginManager().isPluginEnabled("ViaVersion");

        if (viaversion) viaVersionHook = new ViaVersionHook();
    }

    public void modifyAll(Profile profile, ProtocolDirection direction, ProtocolByteBuf byteBuf, int packetId) {
        modifiers.forEach(e -> e.modify(profile, direction, byteBuf, packetId));
    }
}
