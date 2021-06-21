package cc.ghast.packet.wrapper.netty.input;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;

public interface NettyUtil {

    NettyUtil instance = ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_8) ? new ModernUtil() : new LegacyUtil();

    static NettyUtil getInstance() {
        return instance;
    }

    Object newByteBufStream(MutableByteBuf byteBuf);
}
