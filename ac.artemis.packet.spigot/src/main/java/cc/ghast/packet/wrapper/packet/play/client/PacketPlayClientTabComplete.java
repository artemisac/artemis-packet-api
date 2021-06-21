package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.Optional;
import java.util.UUID;

@Getter
public class PacketPlayClientTabComplete extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientTabComplete(UUID player, ProtocolVersion version) {
        super("PacketPlayInTabComplete", player, version);
    }

    private String value;
    private Optional<Boolean> assumeCommand;
    private Optional<BlockPosition> blockPosition;
    private Optional<Integer> transactionId;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        if (version.isOrAbove(ProtocolVersion.V1_13)) {
            this.transactionId = Optional.of(byteBuf.readVarInt());
            this.blockPosition = Optional.empty();
            this.assumeCommand = Optional.empty();
            this.value = byteBuf.readStringBuf(32500);
        } else {
            this.value = byteBuf.readStringBuf(32767);

            if (version.isOrAbove(ProtocolVersion.V1_9)) {
                assumeCommand = Optional.of(byteBuf.readBoolean());
            } else {
                assumeCommand = Optional.empty();
            }

            final boolean flag = byteBuf.readBoolean();

            if (flag) {
                this.blockPosition = Optional.of(byteBuf.readBlockPositionFromLong());
            } else {
                this.blockPosition = Optional.empty();
            }

            this.transactionId = Optional.empty();
        }
    }
}
