package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.bukkit.Vector3D;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.Optional;
import java.util.UUID;

@Getter
public class PacketPlayClientUseEntity extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientUseEntity(UUID player, ProtocolVersion version) {
        super("PacketPlayInUseEntity", player, version);
    }

    private int entityId;
    private PlayerEnums.UseType type;
    private Optional<Vector3D> body;
    private PlayerEnums.Hand hand;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.type = PlayerEnums.UseType.values()[byteBuf.readVarInt()];
        if (type.equals(PlayerEnums.UseType.INTERACT_AT)) {
            float targetX = byteBuf.readFloat();
            float targetY = byteBuf.readFloat();
            float targetZ = byteBuf.readFloat();
            this.body = Optional.of(new Vector3D(targetX, targetY, targetZ));
        } else {
            this.body = Optional.empty();
        }

        if (this.version.isOrBelow(ProtocolVersion.V1_9)) {
            this.hand = PlayerEnums.Hand.MAIN_HAND;
        } else {
            this.hand = PlayerEnums.Hand.values()[byteBuf.readVarInt()];
        }
    }

    public Entity getEntity() {
        return Bukkit.getPlayer(uuid).getWorld().getEntities().parallelStream().filter(e-> e.getEntityId() == entityId).findFirst().orElse(null);
    }


}
