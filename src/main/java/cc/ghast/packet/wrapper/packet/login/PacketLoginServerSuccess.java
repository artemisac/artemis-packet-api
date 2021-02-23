package cc.ghast.packet.wrapper.packet.login;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.mc.GameProfile;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketLoginServerSuccess extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketLoginServerSuccess(UUID player, ProtocolVersion version) {
        super("PacketLoginOutServerSuccess", player, version);
    }

    private GameProfile gameProfile;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        final String uid = byteBuf.readStringBuf(36);
        final UUID uuid = UUID.fromString(uid);
        final String username = byteBuf.readStringBuf(16);

        this.gameProfile = new GameProfile(uuid, username);
    }
}
