package cc.ghast.packet.wrapper.packet.play.server;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;

import java.util.UUID;

public class PacketPlayServerEntity extends Packet {
    public PacketPlayServerEntity(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    @Override
    public void handle(ProtocolByteBuf byteBuf) {

    }

    public static class PacketPlayServerRelEntityMove extends PacketPlayServerEntity {
        public PacketPlayServerRelEntityMove(UUID player, ProtocolVersion version) {
            super(player, version);
        }

        @Override
        public void handle(ProtocolByteBuf byteBuf) {

        }
    }

    public static class PacketPlayServerEntityLook extends PacketPlayServerEntity {
        public PacketPlayServerEntityLook(UUID player, ProtocolVersion version) {
            super(player, version);
        }

        @Override
        public void handle(ProtocolByteBuf byteBuf) {

        }
    }

    public static class PacketPlayServerRelEntityMoveLook extends PacketPlayServerEntity {
        public PacketPlayServerRelEntityMoveLook(UUID player, ProtocolVersion version) {
            super(player, version);
        }

        @Override
        public void handle(ProtocolByteBuf byteBuf) {

        }
    }
}
