package eu.playsc.stonetextures.pack;

import net.minecraft.resources.FolderPack;

public class StoneResourcePack extends FolderPack {
	public StoneResourcePack() {
		super(PackHandler.resourcesDirectory);
	}

	@Override
	public String getName() {
		return PackHandler.PACK_NAME;
	}

	@Override
	public boolean isHidden() {
		return true;
	}
}
