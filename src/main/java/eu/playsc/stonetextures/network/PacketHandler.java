package eu.playsc.stonetextures.network;

import eu.playsc.stonetextures.network.packets.ServerboundResourcesInstalledPacket;
import eu.playsc.stonetextures.network.packets.StonePacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;
import java.util.function.Function;

public class PacketHandler {
	private static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
			.named(new ResourceLocation("stonecraft:network"))
			.clientAcceptedVersions(s -> true)
			.serverAcceptedVersions(s -> true)
			.networkProtocolVersion(() -> "1.16")
			.simpleChannel();
	private static int index;

	public static void register() {
		registerClientToServer(ServerboundResourcesInstalledPacket.class, ServerboundResourcesInstalledPacket::decode);
	}

	private static <MSG extends StonePacket> void registerClientToServer(final Class<MSG> type, final Function<PacketBuffer, MSG> decoder) {
		HANDLER.registerMessage(index++, type, StonePacket::encode, decoder, StonePacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
	}

	public static <MSG extends StonePacket> void sendToServer(final MSG msg) {
		HANDLER.sendToServer(msg);
	}
}
