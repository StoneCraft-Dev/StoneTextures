package eu.playsc.stonetextures.network.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface StonePacket {
	static <PACKET extends StonePacket> void handle(final PACKET message, final Supplier<NetworkEvent.Context> ctx) {
		final NetworkEvent.Context context = ctx.get();
		context.enqueueWork(() -> message.handle(context));
		context.setPacketHandled(true);
	}

	void handle(NetworkEvent.Context context);

	void encode(PacketBuffer buffer);
}
