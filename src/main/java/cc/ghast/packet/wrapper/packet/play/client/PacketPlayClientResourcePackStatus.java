package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.Optional;
import java.util.UUID;

@Getter
public class PacketPlayClientResourcePackStatus extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientResourcePackStatus(UUID player, ProtocolVersion version) {
        super("PacketPlayInResourcePackStatus", player, version);
    }

    private Optional<String> url;
    private PlayerEnums.ResourcePackStatus status;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        if (version.isBelow(ProtocolVersion.V1_10)) {
            this.url = Optional.of(byteBuf.readStringBuf(40));
        }
        else {
            //Not sent on 1.10 and above
            this.url = Optional.empty();
        }
        this.status = PlayerEnums.ResourcePackStatus.values()[byteBuf.readVarInt()];
    }


}
