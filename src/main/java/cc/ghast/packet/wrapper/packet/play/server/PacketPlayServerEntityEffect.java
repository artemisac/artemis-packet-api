package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayServerEntityEffect extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerEntityEffect(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntityEffect", player, version);
    }

    private int entityId;
    private byte effectId;
    private byte amplifier;
    private int duration;
    private boolean showParticles;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.effectId = byteBuf.readByte();
        this.amplifier = byteBuf.readByte();
        this.duration = byteBuf.readVarInt();
        this.showParticles = byteBuf.readByte() == (byte) 1;
    }
}
