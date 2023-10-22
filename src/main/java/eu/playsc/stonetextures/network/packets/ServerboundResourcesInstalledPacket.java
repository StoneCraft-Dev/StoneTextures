package eu.playsc.stonetextures.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;

public record ServerboundResourcesInstalledPacket(UUID uuid) implements StonePacket {

	public static ServerboundResourcesInstalledPacket decode(final FriendlyByteBuf buffer) {
		return new ServerboundResourcesInstalledPacket(buffer.readUUID());
	}

	@Override
	public void handle(final NetworkEvent.Context context) {
		// To be implemented by StoneEssentials
	}

	@Override
	public void encode(final FriendlyByteBuf buffer) {
		buffer.writeUUID(this.uuid);
	}
}
