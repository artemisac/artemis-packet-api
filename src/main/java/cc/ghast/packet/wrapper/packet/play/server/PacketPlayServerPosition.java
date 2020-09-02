package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class PacketPlayServerPosition extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketPlayServerPosition(UUID player, ProtocolVersion version) {
        super("PacketPlayOutPosition", player, version);
    }

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private Set<PlayerTeleportFlags> flags;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.x = byteBuf.readDouble();
        this.y = byteBuf.readDouble();
        this.z = byteBuf.readDouble();
        this.yaw = byteBuf.readFloat();
        this.pitch = byteBuf.readFloat();
        this.flags = PlayerTeleportFlags.readFlags(byteBuf.readUnsignedByte());
    }



    @AllArgsConstructor
    @Getter
    public enum PlayerTeleportFlags {
        X(0),
        Y(1),
        Z(2),
        Y_ROT(3),
        X_ROT(4);

        private int id;

        private int suppose() {
            return 1 << this.id;
        }

        private boolean match(int var1) {
            return (var1 & this.suppose()) == this.suppose();
        }

        public static Set<PlayerTeleportFlags> readFlags(int var0) {
            Set<PlayerTeleportFlags> var1 = EnumSet.noneOf(PlayerTeleportFlags.class);

            for (PlayerTeleportFlags flag : values()) {
                if (flag.match(var0)) {
                    var1.add(flag);
                }
            }

            return var1;
        }
    }
}
