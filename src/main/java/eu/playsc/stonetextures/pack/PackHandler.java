package eu.playsc.stonetextures.pack;

import eu.playsc.stonetextures.StoneTextures;
import net.lingala.zip4j.ZipFile;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.IResourcePack;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.data.PackMetadataSection;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.function.Supplier;

public class PackHandler {

	public static final String PACK_NAME = "StoneCraft Resources";
	public static final int PACK_FORMAT = 8;

	public static File resourcesDirectory = new File(Minecraft.getInstance().gameDirectory, "stonecraft_resources/");
	public static Path source = Minecraft.getInstance().gameDirectory.toPath().resolve("stonecraft_resources.zip");

	public static void init() {
		if (!resourcesDirectory.exists()) {
			resourcesDirectory.mkdirs();
		}

		try {
			final InputStream in = new URL("https://www.dropbox.com/sh/kuzodt5uru5eopz/AAAiV03vRhPv6RRC5-K09vIda?dl=1").openStream();
			Files.copy(in, source, StandardCopyOption.REPLACE_EXISTING);
		} catch (final Exception e) {
			StoneTextures.LOGGER.error("Failed to download resourcepack: " + e.getMessage());
		}

		try (final ZipFile zip = new ZipFile(source.toFile())) {
			zip.extractAll(resourcesDirectory.getAbsolutePath());
			Files.delete(source);
		} catch (final Exception e) {
			StoneTextures.LOGGER.error("Failed to extract resourcepack: " + e.getMessage());
		}
	}

	public static ResourcePackInfo createPack(final String name, final PackMetadataSection meta, final boolean forceEnablePack, final Supplier<IResourcePack> packSupplier, final ResourcePackInfo.IFactory constructor, final ResourcePackInfo.Priority position, final IPackNameDecorator source) {
		try {
			final IResourcePack res = packSupplier.get();
			final ResourcePackInfo pack;

			try {
				pack = constructor.create(name, forceEnablePack, packSupplier, res, meta, position, source);
			} catch (final Exception e) {
				res.close();
				throw e;
			}

			return pack;
		} catch (final Exception e) {
			StoneTextures.LOGGER.error("Failed to create pack: " + e.getMessage());
		}

		return null;
	}
}
