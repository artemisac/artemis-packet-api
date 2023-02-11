package cc.ghast.packet.wrapper.mc.chunk;

import cc.ghast.packet.wrapper.bukkit.Dimension;
import lombok.Data;

@Data
public class ColumnDataWrapper {

    private final Dimension dimension;
    private final byte[] data;
    private final int mask;
}
