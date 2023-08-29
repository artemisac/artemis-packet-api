package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.server.PacketPlayServerGameStateChange;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.WriteableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerGameStateChange.class)
public class GPacketPlayServerGameStateChange extends GPacket implements PacketPlayServerGameStateChange, ReadableBuffer, WriteableBuffer {

    private int reason;
    private float value;

    public GPacketPlayServerGameStateChange(UUID player, ProtocolVersion version) {
        super("PacketPlayOutGameStateChange", player, version);
    }

    public GPacketPlayServerGameStateChange(final int reason, final float value) {
        super("PacketPlayOutGameStateChange");

        this.reason = reason;
        this.value = value;
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.reason = byteBuf.readUnsignedByte();
        this.value = byteBuf.readFloat();
    }

    @Override
    public void write(ProtocolByteBuf byteBuf) {
        byteBuf.writeByte(this.reason);
        byteBuf.writeFloat(this.value);
    }
}
