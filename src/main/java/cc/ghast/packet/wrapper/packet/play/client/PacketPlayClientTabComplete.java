package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientTabComplete extends Packet<ClientPacket> {
    public PacketPlayClientTabComplete(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    private String value;
    private BlockPosition blockPosition;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.value = byteBuf.readStringBuf(32767);

        boolean flag = byteBuf.readBoolean();

        if (flag) {
            this.blockPosition = byteBuf.readBlockPositionFromLong();
        }
    }
}
