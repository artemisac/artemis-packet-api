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
public class PacketPlayClientEntityAction extends GPacket implements ReadableBuffer {
    public PacketPlayClientEntityAction(UUID player, ProtocolVersion version) {
        super("PacketPlayInEntityAction", player, version);
    }

    private int entityId;
    private PlayerEnums.PlayerAction action;
    private int parameter;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.action = PlayerEnums.PlayerAction.values()[byteBuf.readVarInt()];
        this.parameter = byteBuf.readVarInt();
    }


}
