package eu.playsc.stonetextures.mixins;

import eu.playsc.stonetextures.events.RepositorySourceEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DownloadingPackFinder;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.ResourcePackList;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.client.ClientModLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientModLoader.class)
public abstract class MixinClientModLoader {
	@Inject(method = "begin", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/packs/ResourcePackLoader;loadResourcePacks(Lnet/minecraft/resources/ResourcePackList;Ljava/util/function/BiFunction;)V", shift = At.Shift.AFTER), remap = false)
	private static void onBegin(final Minecraft minecraft, final ResourcePackList defaultResourcePacks, final IReloadableResourceManager mcResourceManager, final DownloadingPackFinder metadataSerializer, final CallbackInfo ci) {
		ModLoader.get().postEvent(new RepositorySourceEvent(defaultResourcePacks::addPackFinder));
	}
}
