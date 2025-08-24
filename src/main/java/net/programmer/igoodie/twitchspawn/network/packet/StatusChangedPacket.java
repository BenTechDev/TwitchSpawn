package net.programmer.igoodie.twitchspawn.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.programmer.igoodie.twitchspawn.client.gui.StatusIndicatorOverlay;

import java.util.function.Supplier;

public class StatusChangedPacket {

    public static void encode(StatusChangedPacket packet, FriendlyByteBuf buffer) {
        buffer.writeBoolean(packet.status);
    }

    public static StatusChangedPacket decode(FriendlyByteBuf buffer) {
        return new StatusChangedPacket(buffer.readBoolean());
    }

    public static void handle(final StatusChangedPacket packet,
                              CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            StatusIndicatorOverlay.setRunning(packet.status);
        });
        context.setPacketHandled(true);
    }

    /* ---------------------------- */

    private boolean status;

    public StatusChangedPacket(boolean status) {
        this.status = status;
    }

}
