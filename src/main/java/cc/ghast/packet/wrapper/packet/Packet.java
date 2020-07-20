package cc.ghast.packet.wrapper.packet;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public abstract class Packet {
    protected final UUID uuid;
    protected final ProtocolVersion version;
    protected final long timestamp;

    public Packet(UUID player, ProtocolVersion version) {
        this.uuid = player;
        this.version = version;
        this.timestamp = System.currentTimeMillis();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public abstract void handle(ProtocolByteBuf byteBuf);
}
