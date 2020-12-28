package cc.ghast.packet.compat;

import cc.ghast.packet.PacketAPI;
import cc.ghast.packet.PacketManager;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.exceptions.CompatProfileNotFoundException;
import cc.ghast.packet.exceptions.CompatProfileNotValidException;
import cc.ghast.packet.profile.Profile;
import cc.ghast.packet.protocol.EnumProtocol;
import cc.ghast.packet.protocol.EnumProtocolCurrent;
import cc.ghast.packet.protocol.ProtocolDirection;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import io.netty.buffer.ByteBuf;
import us.myles.ViaVersion.ViaVersionPlugin;
import us.myles.ViaVersion.api.PacketWrapper;
import us.myles.ViaVersion.api.ViaAPI;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.packets.Direction;
import us.myles.ViaVersion.packets.State;

/**
 * @author Ghast
 * @since 15/09/2020
 * ArtemisPacket © 2020
 */
public class ViaVersionHook implements PacketModifier {

    private static final ViaVersionPlugin api = ViaVersionPlugin.getInstance();

    @Override
    public ProtocolByteBuf modify(Profile profile, ProtocolDirection direction, ProtocolByteBuf byteBuf) {
        int id = byteBuf.readVarInt();
        if (id == PacketWrapper.PASSTHROUGH_ID) return byteBuf;
        UserConnection connection = api.getConnectionManager().getConnectedClient(profile.getUuid());

        if (connection == null) {
            return byteBuf;
            //throw new CompatProfileNotFoundException(profile.getUuid());
        }

        if (connection.getProtocolInfo() == null) {
            throw new CompatProfileNotValidException(profile.getUuid());
        }

        PacketWrapper wrapper = new PacketWrapper(id, (ByteBuf) byteBuf.getByteBuf().getParent(), connection);
        try {
            connection.getProtocolInfo()
                    .getPipeline()
                    .transform(direction.equals(ProtocolDirection.IN) ? Direction.INCOMING : Direction.OUTGOING,
                            getState((EnumProtocolCurrent) profile.getProtocol()),
                            wrapper
                    );
        } catch (Exception e){
            e.printStackTrace();
        }

        ByteBuf modified = ((ByteBuf)byteBuf.getByteBuf().getParent()).alloc().buffer();

        try {
            wrapper.writeToBuffer(modified);
            byteBuf.clear().writeBytes(MutableByteBuf.translate(modified));
        } catch (Exception e) {
            e.printStackTrace();
        }

        byteBuf.resetReaderIndex();

        return byteBuf;
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
