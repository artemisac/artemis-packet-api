package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
public class PacketPlayServerEntityDestroy extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerEntityDestroy(UUID player, ProtocolVersion version) {
        super("PacketPlayOutEntityDestroy", player, version);
    }

    private int[] entities;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.entities = new int[byteBuf.readVarInt()];

        for (int i = 0; i < entities.length; i++) {
            this.entities[i] = byteBuf.readVarInt();
        }
    }
}
