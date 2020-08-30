package cc.ghast.packet.buffer.types.minecraft;

import cc.ghast.packet.buffer.BufConverter;
import cc.ghast.packet.buffer.types.Converters;
import cc.ghast.packet.reflections.ReflectUtil;
import cc.ghast.packet.wrapper.nbt.WrappedItem;
import cc.ghast.packet.wrapper.netty.MutableByteBuf;
import com.github.steveice10.opennbt.tag.builtin.CompoundTag;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

/**
 * @author Ghast
 * @since 30/08/2020
 * Artemis © 2020
 */
public class ItemStackConverter extends BufConverter<ItemStack> {
    public ItemStackConverter() {
        super("ItemStack", ItemStack.class);
    }

    @Override
    public void write(MutableByteBuf buffer, ItemStack value) {
        if (value == null || value.getType().getId()== -1) {
            buffer.writeShort(-1);
        } else {
            buffer.writeShort(value.getType().getId());
            buffer.writeByte(value.getAmount());
            // Todo Fix this
            Converters.NMS_NBT.write(buffer, ReflectUtil.getCompoundTagFromItem(value));
        }
    }

    @Override
    public ItemStack read(MutableByteBuf buffer, Object... args) throws IOException {
        short id = buffer.readShort();
        byte amount = buffer.readByte();
        Object tag = Converters.NMS_NBT.read(buffer);
        WrappedItem item = new WrappedItem(id, amount, tag);
        return ReflectUtil.getItemFromWrapper(item);
    }
}
