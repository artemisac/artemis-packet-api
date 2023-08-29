package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.server.PacketPlayServerEntityEffectRemove;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerEntityEffectRemove.class)
public class GPacketPlayServerEntityEffectRemove extends GPacket implements PacketPlayServerEntityEffectRemove, ReadableBuffer {

    private int entityId, effectId;

    public GPacketPlayServerEntityEffectRemove(UUID player, ProtocolVersion version) {
        super("PacketPlayOutRemoveEntityEffect", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.effectId = byteBuf.readUnsignedByte();
    }
}
