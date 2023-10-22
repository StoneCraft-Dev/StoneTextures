package eu.playsc.stonetextures;

import eu.playsc.stonetextures.events.RepositorySourceEvent;
import eu.playsc.stonetextures.network.PacketHandler;
import eu.playsc.stonetextures.network.packets.ServerboundResourcesInstalledPacket;
import eu.playsc.stonetextures.pack.PackHandler;
import eu.playsc.stonetextures.pack.StoneRepositorySource;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("stonetextures")
public class StoneTextures {
	public static final Logger LOGGER = LoggerFactory.getLogger("StoneTextures");
	public static boolean resourcesInstalled = false;

	public StoneTextures() {
		MinecraftForge.EVENT_BUS.register(this);
		PackHandler.init();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerResourcePacks);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
	}

	public void registerResourcePacks(final RepositorySourceEvent event) {
		event.addRepositorySource(new StoneRepositorySource());
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(PacketHandler::register);
	}

	@SubscribeEvent
	public void onPlayerJoinServer(final ClientPlayerNetworkEvent.LoggedInEvent event) {
		if (event.getNetworkManager() == null || event.getPlayer() == null || !resourcesInstalled) {
			return;
		}

		PacketHandler.sendToServer(new ServerboundResourcesInstalledPacket());
	}
}
