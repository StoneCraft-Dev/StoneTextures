package eu.playsc.stonetextures.pack;

import eu.playsc.stonetextures.StoneTextures;
import net.minecraft.resources.FolderPackFinder;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.IResourcePack;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.data.PackMetadataSection;
import net.minecraft.util.text.StringTextComponent;

import java.io.File;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StoneRepositorySource extends FolderPackFinder {
	public StoneRepositorySource() {
		super(new File("placeholder"), IPackNameDecorator.DEFAULT);
	}

	@Override
	public void loadPacks(final Consumer<ResourcePackInfo> consumer, final ResourcePackInfo.IFactory constructor) {
		final PackMetadataSection meta = new PackMetadataSection(new StringTextComponent(PackHandler.PACK_NAME), PackHandler.PACK_FORMAT);
		final ResourcePackInfo pack = PackHandler.createPack(PackHandler.PACK_NAME, meta, true, getSupplier(), constructor, ResourcePackInfo.Priority.TOP, IPackNameDecorator.DEFAULT);
		if (pack != null) {
			consumer.accept(pack);
			StoneTextures.resourcesInstalled = true;
		} else {
			StoneTextures.LOGGER.error("Failed to create pack: " + PackHandler.resourcesDirectory.getAbsolutePath());
		}
	}

	private Supplier<IResourcePack> getSupplier() {
		return StoneResourcePack::new;
	}
}
