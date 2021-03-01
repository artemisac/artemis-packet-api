package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import lombok.Getter;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;


public class PacketPlayServerEntityMetadata extends Packet<ServerPacket> implements ReadableBuffer, WriteableBuffer {
    public PacketPlayServerEntityMetadata(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntityMetadata", player, version);
    }

    public PacketPlayServerEntityMetadata(int entityId, Consumer<ProtocolByteBuf> buffer) {
        super("PacketPlayOutEntityMetadata");
        this.entityId = entityId;
        this.modifier = buffer;
    }
    @Getter
    private int entityId;
    @Getter
    private ProtocolByteBuf buffer;

    private Consumer<ProtocolByteBuf> modifier;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.buffer = byteBuf.copy();
    }

    @Override
    public void write(ProtocolByteBuf byteBuf) {
        byteBuf.writeVarInt(entityId);
        modifier.accept(byteBuf);
    }
}
