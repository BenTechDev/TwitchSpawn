package net.programmer.igoodie.twitchspawn.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.SimpleChannel;
import net.programmer.igoodie.twitchspawn.TwitchSpawn;
import net.programmer.igoodie.twitchspawn.network.packet.GlobalChatCooldownPacket;
import net.programmer.igoodie.twitchspawn.network.packet.OsRunPacket;
import net.programmer.igoodie.twitchspawn.network.packet.StatusChangedPacket;

public class NetworkManager {

    private static final int PROTOCOL_VERSION = 1;

    public static final SimpleChannel CHANNEL = ChannelBuilder.named(ResourceLocation.fromNamespaceAndPath(TwitchSpawn.MOD_ID, "network"))
            .clientAcceptedVersions(Channel.VersionTest.exact(PROTOCOL_VERSION))
            .serverAcceptedVersions(Channel.VersionTest.exact(PROTOCOL_VERSION))
            .networkProtocolVersion(PROTOCOL_VERSION)
            .simpleChannel();

    public static void initialize() {
        CHANNEL.messageBuilder(StatusChangedPacket.class, 0)
                .encoder(StatusChangedPacket::encode)
                .decoder(StatusChangedPacket::decode)
                .consumerMainThread(StatusChangedPacket::handle)
                .add();

        CHANNEL.messageBuilder(OsRunPacket.class, 1)
                .encoder(OsRunPacket::encode)
                .decoder(OsRunPacket::decode)
                .consumerMainThread(OsRunPacket::handle)
                .add();

        CHANNEL.messageBuilder(GlobalChatCooldownPacket.class, 2)
                .encoder(GlobalChatCooldownPacket::encode)
                .decoder(GlobalChatCooldownPacket::decode)
                .consumerMainThread(GlobalChatCooldownPacket::handle)
                .add();
    }

}
