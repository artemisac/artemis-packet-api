package cc.ghast.packet.wrapper.packet.play.client;

import cc.ghast.packet.nms.ProtocolVersion;
import cc.ghast.packet.buffer.ProtocolByteBuf;
import cc.ghast.packet.wrapper.packet.ClientPacket;
import cc.ghast.packet.wrapper.packet.Packet;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PacketPlayClientWindowClick extends Packet<ClientPacket> {
    public PacketPlayClientWindowClick(UUID player, ProtocolVersion version) {
        super(player, version);
    }

    private byte windowId;
    private short slot;
    private byte button;
    private short actionNumber;
    private int shiftedMode;
    private SlotType mode;
    @Deprecated
    private Object clickedItem;

    @Override
    public void handle(ProtocolByteBuf byteBuf) {
        this.windowId = byteBuf.readByte();
        this.slot = byteBuf.readShort();
        this.button = byteBuf.readByte();
        this.actionNumber = byteBuf.readShort();
        this.shiftedMode = byteBuf.readByte();
        this.mode = types[shiftedMode][button];
    }

    private static final SlotType[][] types = {
            {SlotType.LEFT_MOUSE_CLICK, SlotType.RIGHT_MOUSE_CLICK},
            {SlotType.SHIFT_LEFT_MOUSE_CLICK, SlotType.SHIFT_RIGHT_MOUSE_CLICK},
            {SlotType.NUMBER_KEY_1, SlotType.NUMBER_KEY_2, SlotType.NUMBER_KEY_3, SlotType.NUMBER_KEY_4, SlotType.NUMBER_KEY_5, SlotType.NUMBER_KEY_6, SlotType.NUMBER_KEY_7, SlotType.NUMBER_KEY_8, SlotType.NUMBER_KEY_9},
            {SlotType.MIDDLE_CLICK},
            {SlotType.DROP_KEY, SlotType.DROP_STACK, SlotType.LEFT_CLICK_OUTSIDE_INV, SlotType.RIGHT_CLICK_OUTSIDE_INV},
            {SlotType.START_LEFT_MOUSE_DRAG, SlotType.START_RIGHT_MOUSE_DRAG, SlotType.ADD_SLOT_LEFT_MOUSE_DRAG, SlotType.ADD_SLOT_RIGHT_MOUSE_DRAG, SlotType.END_LEFT_MOUSE_DRAG, SlotType.END_RIGHT_MOUSE_DRAG},
            {SlotType.DOUBLE_CLICK}
    };

    enum SlotType {
        LEFT_MOUSE_CLICK,
        RIGHT_MOUSE_CLICK,
        SHIFT_LEFT_MOUSE_CLICK,
        SHIFT_RIGHT_MOUSE_CLICK,
        NUMBER_KEY_1,
        NUMBER_KEY_2,
        NUMBER_KEY_3,
        NUMBER_KEY_4,
        NUMBER_KEY_5,
        NUMBER_KEY_6,
        NUMBER_KEY_7,
        NUMBER_KEY_8,
        NUMBER_KEY_9,
        MIDDLE_CLICK,
        DROP_KEY,
        DROP_STACK,
        LEFT_CLICK_OUTSIDE_INV,
        RIGHT_CLICK_OUTSIDE_INV,
        START_LEFT_MOUSE_DRAG,
        START_RIGHT_MOUSE_DRAG,
        ADD_SLOT_LEFT_MOUSE_DRAG,
        ADD_SLOT_RIGHT_MOUSE_DRAG,
        END_LEFT_MOUSE_DRAG,
        END_RIGHT_MOUSE_DRAG,
        DOUBLE_CLICK
        ;
    }
}
