package net.programmer.igoodie.twitchspawn.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.programmer.igoodie.twitchspawn.client.gui.GlobalChatCooldownOverlay;

import java.util.function.Supplier;

public class GlobalChatCooldownPacket {

    public static void encode(GlobalChatCooldownPacket packet, FriendlyByteBuf buffer) {
        buffer.writeLong(packet.timestamp);
    }

    public static GlobalChatCooldownPacket decode(FriendlyByteBuf buffer) {
        return new GlobalChatCooldownPacket(buffer.readLong());
    }

    public static void handle(final GlobalChatCooldownPacket packet,
                              CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            GlobalChatCooldownOverlay.setCooldownTimestamp(packet.timestamp);
        });
        context.setPacketHandled(true);
    }

    /* -------------------------------- */

    private long timestamp;

    public GlobalChatCooldownPacket(long timestamp) {
        this.timestamp = timestamp;
    }

}
