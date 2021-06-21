package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerEntityEffectRemove extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerEntityEffectRemove(UUID player, ProtocolVersion version) {
        super("PacketPlayOutRemoveEntityEffect", player, version);
    }

    private int entityId;
    private int effectId;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.effectId = byteBuf.readUnsignedByte();
    }
}
