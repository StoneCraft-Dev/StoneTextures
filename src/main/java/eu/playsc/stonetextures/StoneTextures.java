package eu.playsc.stonetextures;

import com.mojang.logging.LogUtils;
import eu.playsc.stonetextures.pack.PackHandler;
import eu.playsc.stonetextures.pack.StoneRepositorySource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("stonetextures")
public class StoneTextures {
	public static final Logger LOGGER = LogUtils.getLogger();

	public StoneTextures() {
		MinecraftForge.EVENT_BUS.register(this);
		PackHandler.init();
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerResourcePacks);
	}

	public void registerResourcePacks(final AddPackFindersEvent e) {
		e.addRepositorySource(new StoneRepositorySource());
	}
}
