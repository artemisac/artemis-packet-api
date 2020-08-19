package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientCloseWindow extends Packet<ClientPacket> {
    public PacketPlayClientCloseWindow(UUID player, ProtocolVersion version) {
        super("PacketPlayInCloseWindow", player, version);
    }

    private int windowId;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.windowId = byteBuf.readByte();
    }
}
