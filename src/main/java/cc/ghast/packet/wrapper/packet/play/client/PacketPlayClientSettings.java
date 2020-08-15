package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientSettings extends Packet<ClientPacket> {
    public PacketPlayClientSettings(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    private String locale;
    private int idk;
    private ChatVisibility visibility;
    private boolean bool;
    private int int16;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.locale = byteBuf.readStringBuf(7);
        this.idk = byteBuf.readByte();
        this.visibility = ChatVisibility.values()[byteBuf.readVarInt()];
        this.bool = byteBuf.readBoolean();
        this.int16 = byteBuf.readUnsignedByte();
    }

    public enum ChatVisibility {

        FULL(0, "options.chat.visibility.full"),
        SYSTEM(1, "options.chat.visibility.system"),
        HIDDEN(2, "options.chat.visibility.hidden");

        private final int settingId;
        private final String settingPath;

        ChatVisibility(int i, String s) {
            this.settingId = i;
            this.settingPath = s;
        }
    }
}
