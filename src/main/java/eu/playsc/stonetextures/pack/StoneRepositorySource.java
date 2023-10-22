package eu.playsc.stonetextures.pack;

import eu.playsc.stonetextures.StoneTextures;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class StoneRepositorySource implements RepositorySource {
	@Override
	public void loadPacks(@NotNull final Consumer<Pack> consumer, final Pack.@NotNull PackConstructor constructor) {
		final PackMetadataSection meta = new PackMetadataSection(new TextComponent(PackHandler.PACK_NAME), PackHandler.PACK_FORMAT);
		final Pack pack = PackHandler.createPack(PackHandler.PACK_NAME, meta, true, StoneResourcePack::new, constructor, Pack.Position.TOP, PackSource.DEFAULT);
		if (pack != null) {
			consumer.accept(pack);
			StoneTextures.resourcesInstalled = true;
		} else {
			StoneTextures.LOGGER.error("Failed to create pack: " + PackHandler.resourcesDirectory.getAbsolutePath());
		}
	}
}
