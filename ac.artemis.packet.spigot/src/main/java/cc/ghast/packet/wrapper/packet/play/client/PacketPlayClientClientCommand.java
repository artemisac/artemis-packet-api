package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import ac.artemis.packet.spigot.wrappers.GPacket;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientClientCommand extends GPacket implements ReadableBuffer {
    public PacketPlayClientClientCommand(UUID player, ProtocolVersion version) {
        super("PacketPlayInClientCommand", player, version);
    }

    private PlayerEnums.ClientCommand command;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.command = PlayerEnums.ClientCommand.values()[byteBuf.readVarInt()];
    }


}
