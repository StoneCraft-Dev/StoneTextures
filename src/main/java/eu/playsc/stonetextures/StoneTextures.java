package eu.playsc.stonetextures;

import com.mojang.logging.LogUtils;
import eu.playsc.stonetextures.network.PacketHandler;
import eu.playsc.stonetextures.network.packets.ServerboundResourcesInstalledPacket;
import eu.playsc.stonetextures.pack.PackHandler;
import eu.playsc.stonetextures.pack.StoneRepositorySource;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("stonetextures")
public class StoneTextures {
	public static final Logger LOGGER = LogUtils.getLogger();
	public static boolean resourcesInstalled = false;

	public StoneTextures() {
		MinecraftForge.EVENT_BUS.register(this);
		PackHandler.init();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerResourcePacks);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
	}

	public void registerResourcePacks(final AddPackFindersEvent e) {
		e.addRepositorySource(new StoneRepositorySource());
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		event.enqueueWork(PacketHandler::register);
	}

	@SubscribeEvent
	public void onPlayerJoinServer(final ClientPlayerNetworkEvent.LoggingIn event) {
		if (!event.getConnection().isConnected() || !resourcesInstalled) {
			return;
		}

		PacketHandler.sendToServer(new ServerboundResourcesInstalledPacket());
	}
}
