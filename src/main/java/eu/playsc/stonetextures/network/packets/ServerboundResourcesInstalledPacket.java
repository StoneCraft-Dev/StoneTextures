package eu.playsc.stonetextures.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ServerboundResourcesInstalledPacket implements StonePacket {

	public static ServerboundResourcesInstalledPacket decode(final FriendlyByteBuf buffer) {
		return new ServerboundResourcesInstalledPacket();
	}

	@Override
	public void handle(final NetworkEvent.Context context) {
		// To be implemented by StoneEssentials
	}

	@Override
	public void encode(final FriendlyByteBuf buffer) {
	}
}
