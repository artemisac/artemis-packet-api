package cc.ghast.packet.wrapper.packet.handshake;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.Packet;
import cc.ghast.packet.wrapper.packet.ServerPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketHandshakeClientSetProtocol extends Packet<ServerPacket> implements ReadableBuffer {
    public PacketHandshakeClientSetProtocol(UUID player, ProtocolVersion version) {
        super("PacketHandshakingInSetProtocol", player, version);
    }

    private int protocolVersion;
    private String serverAddress;
    private short serverPort;
    private State nextState;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.protocolVersion = byteBuf.readVarInt();
        this.serverAddress = byteBuf.readString();
        this.serverPort = (short) byteBuf.readUnsignedShort();
        this.nextState = byteBuf.readVarInt() == 1 ? State.STATUS : State.LOGIN;
    }

    @AllArgsConstructor
    @Getter
    public enum State {
        STATUS(1),
        LOGIN(2);
        private final int id;
    }
}
