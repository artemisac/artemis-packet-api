package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class PacketPlayServerUpdateAttributes extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerUpdateAttributes(UUID player, ProtocolVersion version) {
        super("PacketPlayOutUpdateAttributes", player, version);
    }

    private int entityId;
    private List<Snapshot> attributes;

    @Override
    public void read(final ProtocolByteBuf byteBuf) {
        this.entityId = byteBuf.readVarInt();
        this.attributes = new ArrayList<>();

        final int max = byteBuf.readInt();

        for (int i = 0; i < max; ++i) {
            final String name;

            if (version.isAbove(ProtocolVersion.V1_14)) {
                name = byteBuf.readMinecraftKey().getKey();
            } else {
                name = byteBuf.readStringBuf(64);
            }

            final double base = byteBuf.readDouble();
            final List<Modifier> list = new ArrayList<>();
            final int maxModifiers = byteBuf.readVarInt();

            for (int j = 0; j < maxModifiers; ++j) {
                final UUID uuid = byteBuf.readUUID();
                list.add(new Modifier(uuid, "Unknown synced attribute modifier",
                        byteBuf.readDouble(), byteBuf.readByte()));
            }

            this.attributes.add(new Snapshot(name, base, list));
        }
    }

    @Data
    public static class Snapshot {
        private final String localName;
        private final double baseValue;
        private final List<Modifier> modifiers;
    }

    @Data
    public static class Modifier {
        private final UUID id;
        private final String name;
        private final double amount;
        private final int operation;
    }
}
