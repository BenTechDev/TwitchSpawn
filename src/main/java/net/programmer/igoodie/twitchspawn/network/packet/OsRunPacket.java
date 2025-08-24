package net.programmer.igoodie.twitchspawn.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.programmer.igoodie.twitchspawn.tslanguage.action.OsRunAction;

import java.util.function.Supplier;

public class OsRunPacket {

    public static void encode(OsRunPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.shell.ordinal());
        buffer.writeUtf(packet.script);
    }

    public static OsRunPacket decode(FriendlyByteBuf buffer) {
        OsRunAction.Shell shell = OsRunAction.Shell.values()[buffer.readInt()];
        String script = buffer.readUtf();

        return new OsRunPacket(shell, script);
    }

    public static void handle(final OsRunPacket packet, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> OsRunAction.handleLocalScript(packet.shell, packet.script));
        context.setPacketHandled(true);
    }

    /* ------------------------------------------------ */

    private OsRunAction.Shell shell;
    private String script;

    public OsRunPacket(OsRunAction.Shell shell, String script) {
        this.shell = shell;
        this.script = script;
    }

}
