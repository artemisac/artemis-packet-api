package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.bukkit.Vector3D;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.Optional;
import java.util.UUID;

@Getter
public class PacketPlayClientUseEntity extends Packet<ClientPacket> {
    public PacketPlayClientUseEntity(UUID player, ProtocolVersion version) {
        super("PacketPlayInUseEntity", player, version);
    }

    private int entityId;
    private UseType type;
    private Optional<Vector3D> body;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.type = UseType.values()[byteBuf.readVarInt()];
        if (type.equals(UseType.INTERACT_AT)) {
            float targetX = byteBuf.readFloat();
            float targetY = byteBuf.readFloat();
            float targetZ = byteBuf.readFloat();
            this.body = Optional.of(new Vector3D(targetX, targetY, targetZ));
        } else {
            this.body = Optional.empty();
        }
    }

    public enum UseType {
        INTERACT,
        ATTACK,
        INTERACT_AT;
    }
}
