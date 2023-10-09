package eu.playsc.stonetextures.pack;

import net.minecraft.server.packs.FolderPackResources;
import org.jetbrains.annotations.NotNull;

public class StoneResourcePack extends FolderPackResources {
	public StoneResourcePack() {
		super(PackHandler.resourcesDirectory);
	}

	@Override
	public @NotNull String getName() {
		return PackHandler.PACK_NAME;
	}

	@Override
	public boolean isHidden() {
		return true;
	}
}
