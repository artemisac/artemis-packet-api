package cc.ghast.packet.wrapper.bukkit;

import lombok.Getter;

@Getter
public enum Dimension {
    NETHER(-1),
    NORMAL(0),
    THE_END(1);

    private final int id;

    Dimension(int id) {
        this.id = id;
    }
}
