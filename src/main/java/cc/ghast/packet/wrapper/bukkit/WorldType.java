package cc.ghast.packet.wrapper.bukkit;

public enum WorldType {
    NORMAL("DEFAULT"),
    FLAT("FLAT"),
    VERSION_1_1("DEFAULT_1_1"),
    LARGE_BIOMES("LARGEBIOMES"),
    AMPLIFIED("AMPLIFIED"),
    CUSTOMIZED("CUSTOMIZED");

    private final String name;

    WorldType(String name) {
        this.name = name;
    }

    public static WorldType getByName(String s) {
        return WorldType.valueOf(s.toUpperCase());
    }
}
