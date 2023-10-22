package eu.playsc.stonetextures.network.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public final class ServerboundResourcesInstalledPacket implements StonePacket {
	public static ServerboundResourcesInstalledPacket decode(final PacketBuffer packetBuffer) {
		return new ServerboundResourcesInstalledPacket();
	}

	@Override
	public void handle(final NetworkEvent.Context context) {
		// To be implemented by StoneEssentials
	}

	@Override
	public void encode(final PacketBuffer buffer) {
	}
}
