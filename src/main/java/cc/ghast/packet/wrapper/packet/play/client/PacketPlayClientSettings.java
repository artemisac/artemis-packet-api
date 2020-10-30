package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.ReadableBuffer;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientSettings extends Packet<ClientPacket> implements ReadableBuffer {
    public PacketPlayClientSettings(UUID player, ProtocolVersion version) {
        super("PacketPlayInSettings", player, version);
    }

    private String locale;
    private int idk;
    private PlayerEnums.ChatVisibility visibility;
    private boolean bool;
    private int int16;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.locale = byteBuf.readStringBuf(7);
        this.idk = byteBuf.readByte();
        this.visibility = PlayerEnums.ChatVisibility.values()[byteBuf.readVarInt()];
        this.bool = byteBuf.readBoolean();
        this.int16 = byteBuf.readUnsignedByte();
    }


}
