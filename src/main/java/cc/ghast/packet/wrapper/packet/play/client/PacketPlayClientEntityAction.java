package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;
@Getter
public class PacketPlayClientEntityAction extends Packet<ClientPacket> {
    public PacketPlayClientEntityAction(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    private int entityId;
    private PlayerAction action;
    private int parameter;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.action = PlayerAction.values()[byteBuf.readVarInt()];
        this.parameter = byteBuf.readVarInt();
    }

    enum PlayerAction {
        START_SNEAKING,
        STOP_SNEAKING,
        LEAVE_BED,
        START_SPRINTING,
        STOP_SPRINTING,
        JUMP_HORSE,
        OPEN_RIDE_INVENTORY
    }
}
