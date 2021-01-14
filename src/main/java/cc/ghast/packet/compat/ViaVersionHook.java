package cc.ghast.packet.compat;

import cc.ghast.packet.PacketAPI;
import cc.ghast.packet.PacketManager;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.exceptions.CompatProfileNotFoundException;
import cc.ghast.packet.exceptions.CompatProfileNotValidException;
import cc.ghast.packet.nms.ItemNMS;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.EnumProtocolCurrent;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import cc.ghast.packet.wrapper.packet.handshake.PacketHandshakeClientSetProtocol;
import io.netty.buffer.ByteBuf;
import lombok.SneakyThrows;
import org.bukkit.inventory.ItemStack;
import us.myles.ViaVersion.ViaVersionPlugin;
import us.myles.ViaVersion.api.PacketWrapper;
import us.myles.ViaVersion.api.Via;
import us.myles.ViaVersion.api.ViaAPI;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.exception.CancelException;
import us.myles.ViaVersion.packets.Direction;
import us.myles.ViaVersion.packets.State;

import java.util.UUID;

/**
 * @author Ghast
 * @since 15/09/2020
 * ArtemisPacket © 2020
 */
public class ViaVersionHook {

    private static final ViaVersionPlugin api = ViaVersionPlugin.getInstance();

    public int getVersion(UUID uuid) {
        return api.getApi().isInjected(uuid) ? api.getApi().getPlayerVersion(uuid) : -1;
    }

    @SneakyThrows
    public ByteBuf transformPacket(UUID uuid, ByteBuf buf, int id) {
        if (uuid == null
                || api.getConnectionManager().getConnectedClients() == null
                || !api.getConnectionManager().getConnectedClients().containsKey(uuid)) {
            return buf;
        }

        final UserConnection connection = api.getConnectionManager().getConnectedClient(uuid);

        if (connection == null) {
            return buf;
        }

        buf.retain();
        buf.resetReaderIndex();

        try {
            connection.transformIncoming(buf, e -> new CancelException("ViaVersion failed to convert packet (type: " + id + ")", e.getCause()));
        } catch (CancelException e) {
            // do literally nothing
            buf = null;
        }
        return buf;
    }

    private State getState(EnumProtocolCurrent protocol) {
        switch (protocol) {
            case STATUS: return State.STATUS;
            case LOGIN: return State.LOGIN;
            case HANDSHAKE: return State.HANDSHAKE;
        }
        return State.PLAY;
    }
}
