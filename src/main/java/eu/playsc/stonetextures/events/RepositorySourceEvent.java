package eu.playsc.stonetextures.events;

import net.minecraft.resources.IPackFinder;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.event.lifecycle.IModBusEvent;

import java.util.function.Consumer;

public class RepositorySourceEvent extends Event implements IModBusEvent {

	private final Consumer<IPackFinder> sources;

	public RepositorySourceEvent(final Consumer<IPackFinder> sources) {
		this.sources = sources;
	}

	public void addRepositorySource(final IPackFinder source) {
		this.sources.accept(source);
	}
}