package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.nms.ProtocolVersion;
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

    //Client's language
    private String locale;
    //Client's view distance
    private int viewDistance;
    private PlayerEnums.ChatVisibility visibility;
    //Chat colors setting on the client
    private boolean chatColors;
    private int displayedSkinPartsMask;
    private PlayerEnums.Hand hand;

    @Override
    public void read(ProtocolByteBuf byteBuf) {
        this.locale = byteBuf.readStringBuf(7);
        this.viewDistance = byteBuf.readByte();
        this.visibility = PlayerEnums.ChatVisibility.values()[byteBuf.readVarInt()];
        this.chatColors = byteBuf.readBoolean();
        this.displayedSkinPartsMask = byteBuf.readUnsignedByte();
        if (version.isBelow(ProtocolVersion.V1_9)) {
            this.hand = PlayerEnums.Hand.MAIN_HAND;
        }
        else {
            this.hand = PlayerEnums.Hand.values()[byteBuf.readVarInt()];
        }
    }


}
