package cc.ghast.packet.wrapper.packet.play.server;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.spigot.protocol.PacketLink;
import ac.artemis.packet.wrapper.server.PacketPlayServerWindowOpen;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import lombok.Getter;

import java.util.UUID;

@Getter
@PacketLink(PacketPlayServerWindowOpen.class)
public class GPacketPlayServerWindowOpen extends GPacket implements PacketPlayServerWindowOpen, ReadableBuffer {

    private int windowId;
    private String windowType, windowTitle;
    private int slots, entityId;

    public GPacketPlayServerWindowOpen(UUID player, ProtocolVersion version) {
        super("PacketPlayOutOpenWindow", player, version);
    }

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        final int stringBufArg = version.isBelow(ProtocolVersion.V1_13) ? 32767 : 262144;

        this.windowId = byteBuf.readUnsignedByte();
        this.windowType = byteBuf.readString();
        this.windowTitle = byteBuf.readStringBuf(stringBufArg);

        if (this.windowType.equals("EntityHorse")) {
            this.entityId = byteBuf.readInt();
        }
    }
}
