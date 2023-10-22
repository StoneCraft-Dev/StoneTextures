package eu.playsc.stonetextures.network;

import eu.playsc.stonetextures.network.packets.ServerboundResourcesInstalledPacket;
import eu.playsc.stonetextures.network.packets.StonePacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.Optional;
import java.util.function.Function;

public class PacketHandler {
	private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation("stonecraft:network"))
			.clientAcceptedVersions(s -> true)
			.serverAcceptedVersions(s -> true)
			.networkProtocolVersion(() -> "1.18")
			.simpleChannel();
	private static int index;

	public static void register() {
		registerClientToServer(ServerboundResourcesInstalledPacket.class, ServerboundResourcesInstalledPacket::decode);
	}

	private static <MSG extends StonePacket> void registerClientToServer(final Class<MSG> type, final Function<FriendlyByteBuf, MSG> decoder) {
		HANDLER.registerMessage(index++, type, StonePacket::encode, decoder, StonePacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
	}

	public static <MSG extends StonePacket> void sendToServer(final MSG msg) {
		HANDLER.sendToServer(msg);
	}
}
