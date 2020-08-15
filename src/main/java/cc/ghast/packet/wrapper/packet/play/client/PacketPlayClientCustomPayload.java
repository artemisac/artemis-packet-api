package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.payload.MinecraftKey;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;
import lombok.SneakyThrows;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;

import java.io.IOException;
import java.util.UUID;

@Getter
public class PacketPlayClientCustomPayload extends Packet<ClientPacket> {
    public PacketPlayClientCustomPayload(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    private String header;
    private ProtocolByteBuf message;

    @Override
    @SneakyThrows
    public void handle(ProtocolByteBuf byteBuf) {
        // Header
        if (version.isBelow(ProtocolVersion.V1_13)) {
            this.header = byteBuf.readStringBuf(20);
        } else {
            this.header = new MinecraftKey(byteBuf.readStringBuf(32767)).getKey();
        }

        // Payload
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes >= 0 && readableBytes <= 32767) {
            this.message = new ProtocolByteBuf(byteBuf.readBytes(readableBytes));
        } else {
            throw new IOException("Payload may not be larger than 32767 bytes");
        }
    }
}
